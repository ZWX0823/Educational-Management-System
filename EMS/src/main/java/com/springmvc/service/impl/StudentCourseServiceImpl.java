package com.springmvc.service.impl;

import com.springmvc.exception.CustomException;
import com.springmvc.mapper.*;
import com.springmvc.po.*;
import com.springmvc.service.StudentCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Resource(name = "studentCourseMapper")
    private StudentCourseMapper studentCourseMapper;

    @Resource(name = "teacherMapper")
    private TeacherMapper teacherMapper;

    @Resource(name = "courseMapper")
    private CourseMapper courseMapper;

    @Resource(name = "deptMapper")
    private DeptMapper deptMapper;

    @Resource(name = "teacherCourseMapper")
    private TeacherCourseMapper teacherCourseMapper;

    @Resource(name = "timetableMapper")
    private TimetableMapper timetableMapper;

    @Resource(name = "timeMapper")
    private TimeMapper timeMapper;

    @Resource(name = "classroomMapper")
    private ClassroomMapper classroomMapper;

    @Resource(name = "studentCourseMapperExpand")
    private StudentCourseMapperExpand studentCourseMapperExpand;

    @Resource(name = "studentMapper")
    private StudentMapper studentMapper;

    @Resource(name = "studentMapperExpand")
    private StudentMapperExpand studentMapperExpand;

    @Override
    public Boolean IsSelectCourse(String studentID, String teacher_CourseID) throws Exception {

        if (studentID.equals("") || teacher_CourseID.equals("")){
            return false;
        }
        StudentCourseKey studentCourseKey = new StudentCourseKey();
        studentCourseKey.setStudentID(studentID);
        studentCourseKey.setTeacher_CourseID(teacher_CourseID);
        StudentCourse studentCourse = studentCourseMapper.selectByPrimaryKey(studentCourseKey);
        if (studentCourse != null){
            return true;
        }
        return false;
    }

    @Override
    public void selectCourse(String studentID, String teacher_CourseID) throws Exception {

        if (studentID.equals("") || teacher_CourseID.equals("")){
            throw new CustomException("学生不存在或该课程未安排教师");
        }
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentID(studentID);
        studentCourse.setTeacher_CourseID(teacher_CourseID);
        //学生选课表新增学生选课信息
        studentCourseMapper.insert(studentCourse);
        //教师课程表人数增一
        TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(teacher_CourseID);
        if (teacherCourse.getNumber() == null){
            teacherCourse.setNumber(0);
        }
        teacherCourse.setNumber(teacherCourse.getNumber()+1);
        teacherCourseMapper.updateByPrimaryKey(teacherCourse);
    }

    @Override
    public List<StudentCourseExpand> getSelectedCourse(Student student) throws Exception {

        StudentCourseExample studentCourseExample = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();
        criteria.andStudentIDEqualTo(student.getStudentID());
        //条件查询得到选课集合
        List<StudentCourse> studentCourseList = studentCourseMapper.selectByExample(studentCourseExample);
        if (studentCourseList!=null && studentCourseList.size()>0){

            List<StudentCourseExpand> list = new ArrayList<StudentCourseExpand>();
            StudentCourseExpand studentCourseExpand = new StudentCourseExpand();
            for (StudentCourse studentCourse:studentCourseList){

                /**
                 * 获取教师课程扩展信息
                 */
                TeacherCourseExpand teacherCourseExpand = new TeacherCourseExpand();

                TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(studentCourse.getTeacher_CourseID());
                //获取教师扩展信息
                Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());
                TeacherExpand teacherExpand = new TeacherExpand();
                BeanUtils.copyProperties(teacher,teacherExpand);
                //获取课程扩展信息
                Course course = courseMapper.selectByPrimaryKey(teacherCourse.getCourseID());
                CourseExpand courseExpand = new CourseExpand();
                BeanUtils.copyProperties(course,courseExpand);
                courseExpand.setDeptName(deptMapper.getDeptName(course.getDeptID()));   //获取院系名
                //TeacherCourseExpand对象
                BeanUtils.copyProperties(teacherCourse,teacherCourseExpand);
                teacherCourseExpand.setTeacherExpand(teacherExpand);
                teacherCourseExpand.setCourseExpand(courseExpand);
                /**
                 * 获取上课时间 上课地点
                 */
                //获取课程表信息
                TimetableExample timetableExample = new TimetableExample();
                TimetableExample.Criteria criteria1 = timetableExample.createCriteria();
                criteria1.andTeacher_CourseIDEqualTo(studentCourse.getTeacher_CourseID());
                List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);

                //若该课已安排教室
                if (timetableList != null && timetableList.size()>0){
                    for (Timetable timetable:timetableList){

                        //获取上课时间 上课地点
                        //上课时间，上课地点 需要对对应代码转换为明文 如0101101 --> 明德N101
                        Time time = timeMapper.selectByPrimaryKey(timetable.getTime());
                        Classroom classroom = classroomMapper.selectByPrimaryKey(timetable.getClassroom());
                        /**
                         * StudentCourseExpand对象
                         */
                        studentCourseExpand.setCourseTime(time.getTimeName());
                        studentCourseExpand.setClassroom(classroom.getClassroomName());
                        studentCourseExpand.setCourseName(course.getCourseName());
                        studentCourseExpand.setTeacherCourseExpand(teacherCourseExpand);
                        BeanUtils.copyProperties(studentCourse,studentCourseExpand);
                        //该课程是否已修
                        if (studentCourseExpand.getGrade() == null){
                            studentCourseExpand.setOver(false);
                            list.add(studentCourseExpand);
                        }
                    }
                }
                else {
                    /**
                     * StudentCourseExpand对象
                     */
                    studentCourseExpand.setCourseName(course.getCourseName());
                    studentCourseExpand.setTeacherCourseExpand(teacherCourseExpand);
                    BeanUtils.copyProperties(studentCourse,studentCourseExpand);
                    //该课程是否已修
                    if (studentCourseExpand.getGrade() == null){
                        studentCourseExpand.setOver(false);
                        list.add(studentCourseExpand);
                    }
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public void revokeCourse(String studentID, String teacher_CourseID) throws Exception {

        if (studentID.equals("") || teacher_CourseID.equals("")){
            throw new CustomException("学生不存在或未选该课程");
        }
        StudentCourseExample studentCourseExample = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();
        criteria.andStudentIDEqualTo(studentID);
        criteria.andTeacher_CourseIDEqualTo(teacher_CourseID);
        int i = studentCourseMapper.deleteByExample(studentCourseExample);
        if (i>0){
            TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(teacher_CourseID);
            int n = teacherCourse.getNumber()-1;
            teacherCourse.setNumber(n);
            teacherCourseMapper.updateByPrimaryKey(teacherCourse);
        }
    }

    @Override
    public List<StudentCourseExpand> findSelectedCourseByPaging(Integer toPageNo,String studentID) throws Exception {

        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<StudentCourse> studentCourseList = studentCourseMapperExpand.findByPaging(pagingVO,studentID);
        if (studentCourseList!=null && studentCourseList.size()>0){

            List<StudentCourseExpand> list = new ArrayList<StudentCourseExpand>();
            for (StudentCourse studentCourse:studentCourseList){

                /**
                 * 获取教师课程扩展信息
                 */
                TeacherCourseExpand teacherCourseExpand = new TeacherCourseExpand();

                TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(studentCourse.getTeacher_CourseID());
                //获取教师扩展信息
                Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());
                TeacherExpand teacherExpand = new TeacherExpand();
                BeanUtils.copyProperties(teacher,teacherExpand);
                //获取课程扩展信息
                Course course = courseMapper.selectByPrimaryKey(teacherCourse.getCourseID());
                CourseExpand courseExpand = new CourseExpand();
                BeanUtils.copyProperties(course,courseExpand);
                courseExpand.setDeptName(deptMapper.getDeptName(course.getDeptID()));   //获取院系名
                //TeacherCourseExpand对象
                BeanUtils.copyProperties(teacherCourse,teacherCourseExpand);
                teacherCourseExpand.setTeacherExpand(teacherExpand);
                teacherCourseExpand.setCourseExpand(courseExpand);
                /**
                 * 获取上课时间 上课地点
                 */
                //获取课程表信息
                TimetableExample timetableExample = new TimetableExample();
                TimetableExample.Criteria criteria1 = timetableExample.createCriteria();
                criteria1.andTeacher_CourseIDEqualTo(studentCourse.getTeacher_CourseID());
                List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);

                //若该课已安排教室
                if (timetableList != null && timetableList.size()>0){
                    for (Timetable timetable:timetableList){

                        //获取上课时间 上课地点
                        //上课时间，上课地点 需要对对应代码转换为明文 如0101101 --> 明德N101
                        Time time = timeMapper.selectByPrimaryKey(timetable.getTime());
                        Classroom classroom = classroomMapper.selectByPrimaryKey(timetable.getClassroom());
                        /**
                         * StudentCourseExpand对象
                         */
                        StudentCourseExpand studentCourseExpand = new StudentCourseExpand();

                        studentCourseExpand.setCourseTime(time.getTimeName());
                        studentCourseExpand.setClassroom(classroom.getClassroomName());
                        studentCourseExpand.setCourseName(course.getCourseName());
                        studentCourseExpand.setTeacherCourseExpand(teacherCourseExpand);
                        BeanUtils.copyProperties(studentCourse,studentCourseExpand);
                        //该课程是否已修
                        if (studentCourseExpand.getGrade() == null){
                            studentCourseExpand.setOver(false);
                            list.add(studentCourseExpand);
                        }
                    }
                }
                else {
                    /**
                     * StudentCourseExpand对象
                     */
                    StudentCourseExpand studentCourseExpand = new StudentCourseExpand();

                    studentCourseExpand.setCourseName(course.getCourseName());
                    studentCourseExpand.setTeacherCourseExpand(teacherCourseExpand);
                    BeanUtils.copyProperties(studentCourse,studentCourseExpand);
                    //该课程是否已修
                    if (studentCourseExpand.getGrade() == null){
                        studentCourseExpand.setOver(false);
                        list.add(studentCourseExpand);
                    }
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public List<StudentCourseExpand> findFinishedCourseByPaging(Integer toPageNo, String studentID) throws Exception {

        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<StudentCourse> studentCourseList = studentCourseMapperExpand.findByPaging(pagingVO,studentID);
        if (studentCourseList!=null && studentCourseList.size()>0){

            List<StudentCourseExpand> list = new ArrayList<StudentCourseExpand>();
            for (StudentCourse studentCourse:studentCourseList){

                /**
                 * 获取教师课程扩展信息
                 */
                TeacherCourseExpand teacherCourseExpand = new TeacherCourseExpand();

                TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(studentCourse.getTeacher_CourseID());
                //获取教师扩展信息
                Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());
                TeacherExpand teacherExpand = new TeacherExpand();
                BeanUtils.copyProperties(teacher,teacherExpand);
                //获取课程扩展信息
                Course course = courseMapper.selectByPrimaryKey(teacherCourse.getCourseID());
                CourseExpand courseExpand = new CourseExpand();
                BeanUtils.copyProperties(course,courseExpand);
                courseExpand.setDeptName(deptMapper.getDeptName(course.getDeptID()));   //获取院系名
                //TeacherCourseExpand对象
                BeanUtils.copyProperties(teacherCourse,teacherCourseExpand);
                teacherCourseExpand.setTeacherExpand(teacherExpand);
                teacherCourseExpand.setCourseExpand(courseExpand);
                /**
                 * 获取上课时间 上课地点
                 */
                //获取课程表信息
                TimetableExample timetableExample = new TimetableExample();
                TimetableExample.Criteria criteria1 = timetableExample.createCriteria();
                criteria1.andTeacher_CourseIDEqualTo(studentCourse.getTeacher_CourseID());
                List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);

                //若该课已安排教室
                if (timetableList != null && timetableList.size()>0){
                    for (Timetable timetable:timetableList){

                        //获取上课时间 上课地点
                        //上课时间，上课地点 需要对对应代码转换为明文 如0101101 --> 明德N101
                        Time time = timeMapper.selectByPrimaryKey(timetable.getTime());
                        Classroom classroom = classroomMapper.selectByPrimaryKey(timetable.getClassroom());
                        /**
                         * StudentCourseExpand对象
                         */
                        StudentCourseExpand studentCourseExpand = new StudentCourseExpand();

                        studentCourseExpand.setCourseTime(time.getTimeName());
                        studentCourseExpand.setClassroom(classroom.getClassroomName());
                        studentCourseExpand.setCourseName(course.getCourseName());
                        studentCourseExpand.setTeacherCourseExpand(teacherCourseExpand);
                        BeanUtils.copyProperties(studentCourse,studentCourseExpand);
                        //该课程是否已修
                        if (studentCourse.getGrade() != null && studentCourseExpand.getGrade() >= 60){
                            studentCourseExpand.setOver(true);
                            list.add(studentCourseExpand);
                        }
                    }
                }
                else {
                    /**
                     * StudentCourseExpand对象
                     */
                    StudentCourseExpand studentCourseExpand = new StudentCourseExpand();

                    studentCourseExpand.setCourseName(course.getCourseName());
                    studentCourseExpand.setTeacherCourseExpand(teacherCourseExpand);
                    BeanUtils.copyProperties(studentCourse,studentCourseExpand);
                    //该课程是否已修
                    if (studentCourse.getGrade() != null && studentCourseExpand.getGrade() >= 60){
                        studentCourseExpand.setOver(true);
                        list.add(studentCourseExpand);
                    }
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public int getCountOfSelectedCourse(String studentID) throws Exception {

        StudentCourseExample studentCourseExample = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();
        criteria.andStudentIDEqualTo(studentID);
        criteria.andGradeIsNull();

        return studentCourseMapper.countByExample(studentCourseExample);

    }

    @Override
    public int getCountOfFinishedCourse(String studentID) throws Exception {

        StudentCourseExample studentCourseExample = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();
        criteria.andStudentIDEqualTo(studentID);
        criteria.andGradeIsNotNull();

        return studentCourseMapper.countByExample(studentCourseExample);
    }

    @Override
    public void commentTeacherCourse(String studentID, String teacher_CourseID,String comment) throws Exception {
        if (studentID.equals("") || teacher_CourseID.equals("")){
            throw new CustomException("未知学生或教师课程");
        }
        StudentCourseExample studentCourseExample = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();
        criteria.andStudentIDEqualTo(studentID);
        criteria.andTeacher_CourseIDEqualTo(teacher_CourseID);

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentID(studentID);
        studentCourse.setTeacher_CourseID(teacher_CourseID);
        studentCourse.setComment(comment);

        try{
            studentCourseMapper.updateByExampleSelective(studentCourse,studentCourseExample);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<StudentCourseExpand> getStudentCourse(String teacher_CourseID, Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<StudentCourse> studentCourseList = studentCourseMapperExpand.getStudentCourse(pagingVO,teacher_CourseID);
        if (studentCourseList != null && studentCourseList.size() > 0){
            List<StudentCourseExpand> list = new ArrayList<>();
            for (StudentCourse studentCourse:studentCourseList){

                StudentCourseExpand studentCourseExpand = new StudentCourseExpand();
                //Get studentExpand
                StudentExpand studentExpand = studentMapperExpand.getStudentExpandById(studentCourse.getStudentID());
                //Copy
                BeanUtils.copyProperties(studentCourse,studentCourseExpand);
                studentCourseExpand.setStudentExpand(studentExpand);

                list.add(studentCourseExpand);
            }
            return list;
        }
        return null;
    }

}
