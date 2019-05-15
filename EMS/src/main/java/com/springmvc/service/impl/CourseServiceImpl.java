package com.springmvc.service.impl;

import com.springmvc.exception.CustomException;
import com.springmvc.mapper.*;
import com.springmvc.po.*;
import com.springmvc.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseMapperExpand courseMapperExpand;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Autowired
    private TimeMapper timeMapper;

    @Autowired
    private TimetableMapper timetableMapper;

    @Resource(name = "specialtyMapper")
    private SpecialtyMapper specialtyMapper;

    @Autowired
    private SpecialtyYearCourseMapper specialtyYearCourseMapper;


    @Override
    public Boolean addCourse(CourseExpand courseExpand) throws Exception {

        Course course = courseMapper.selectByPrimaryKey(courseExpand.getCourseID());
        if (course == null){
            courseMapper.insert(courseExpand);
            return true;
        }
        return false;
    }

    @Override
    public void deleteCourse(String courseID) throws Exception {
        if (courseID.equals("")){
            throw new CustomException("传入空课程号");
        }

        TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
        TeacherCourseExample.Criteria criteria = teacherCourseExample.createCriteria();
        criteria.andCourseIDEqualTo(courseID);
        List<String> tcList = teacherCourseMapper.getTeacherCourseIDListByExample(teacherCourseExample);
        for (String teacher_CourseID :tcList){
            //First delete teacherCourse info in timetable
            TimetableExample timetableExample = new TimetableExample();
            TimetableExample.Criteria criteria1 = timetableExample.createCriteria();
            criteria1.andTeacher_CourseIDEqualTo(teacher_CourseID);

            List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);
            if (timetableList.size() > 0){
                try {
                    timetableMapper.deleteByExample(timetableExample);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //Second delete teacherCourse info in student_course
            StudentCourseExample studentCourseExample = new StudentCourseExample();
            StudentCourseExample.Criteria criteria2 = studentCourseExample.createCriteria();
            criteria2.andTeacher_CourseIDEqualTo(teacher_CourseID);

            List<StudentCourse> studentCourseList = studentCourseMapper.selectByExample(studentCourseExample);
            if (studentCourseList.size() > 0){
                try{
                    studentCourseMapper.deleteByExample(studentCourseExample);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //Then delete teacherCourse info in teacher_course
            TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(teacher_CourseID);
            if (teacherCourse != null){
                try{
                    teacherCourseMapper.deleteByPrimaryKey(teacher_CourseID);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        //In the end delete course info in course
        try{
            courseMapper.deleteByPrimaryKey(courseID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Schedule> findCourseByName_Dept(String courseName, String deptID) throws Exception {
        CourseExample courseExample = new CourseExample();
        //自定义查询条件
        CourseExample.Criteria criteria = courseExample.createCriteria();

        criteria.andCourseNameLike("%"+courseName+"%");
        criteria.andDeptIDEqualTo(deptID);

        List<Course> list = null;

        list = courseMapper.selectByExample(courseExample);

        List<Schedule> scheduleList = null;

        if (list != null && list.size()>0){

            scheduleList = new ArrayList<Schedule>();

            for (Course course:list){

                //获取教师编号，教师名
                TeacherCourseExample teacherCourseExample = new TeacherCourseExample();

                TeacherCourseExample.Criteria criteria1 = teacherCourseExample.createCriteria();
                criteria1.andCourseIDEqualTo(course.getCourseID());

                List<TeacherCourse> teacherCourseList = teacherCourseMapper.selectByExample(teacherCourseExample);

                if (teacherCourseList != null && teacherCourseList.size()>0){

                    for (TeacherCourse teacherCourse:teacherCourseList){

                        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());

                        //获取课程表信息
                        TimetableExample timetableExample = new TimetableExample();

                        TimetableExample.Criteria criteria2 = timetableExample.createCriteria();
                        criteria2.andTeacher_CourseIDEqualTo(teacherCourse.getTeacher_CourseID());

                        List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);

                        if (timetableList != null && timetableList.size()>0){
                            for (Timetable timetable:timetableList){
                                Schedule schedule = new Schedule();
                                //拷贝
                                BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                                //获取院系名
                                Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                                schedule.setDeptName(dept.getDeptName());
                                //获取专业名
                                String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                                schedule.setSpecialtyName(specialtyName);
                                //拷贝
                                BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                                //获取上课时间，上课地点
                                //上课时间，上课地点 需要对对应代码转换为明文 如0101101 --> 明德N101
                                Time time = timeMapper.selectByPrimaryKey(timetable.getTime());
                                Classroom classroom = classroomMapper.selectByPrimaryKey(timetable.getClassroom());
                                //获取上课时间 上课地点
                                schedule.setCourseTime(time.getTimeName());
                                schedule.setClassroom(classroom.getClassroomName());
                                //获取教师_课程编号
                                schedule.setTeacher_CourseID(teacherCourse.getTeacher_CourseID());

                                scheduleList.add(schedule);
                            }
                        }
                        else {
                            Schedule schedule = new Schedule();
                            //拷贝
                            BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                            //获取院系名
                            Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                            schedule.setDeptName(dept.getDeptName());
                            //获取专业名
                            String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                            schedule.setSpecialtyName(specialtyName);
                            //拷贝
                            BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                            //获取教师_课程编号
                            schedule.setTeacher_CourseID(teacherCourse.getTeacher_CourseID());

                            scheduleList.add(schedule);
                        }
                    }
                }
                else {
                    Schedule schedule = new Schedule();
                    //拷贝
                    BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                    //获取院系名
                    Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                    schedule.setDeptName(dept.getDeptName());
                    //获取专业名
                    String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                    schedule.setSpecialtyName(specialtyName);
                    scheduleList.add(schedule);
                }
            }
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> findCourseByName(String courseName) throws Exception {

        CourseExample courseExample = new CourseExample();
        //自定义查询条件
        CourseExample.Criteria criteria = courseExample.createCriteria();

        criteria.andCourseNameLike("%"+courseName+"%");

        List<Course> list = null;

        list = courseMapper.selectByExample(courseExample);

        List<Schedule> scheduleList = null;

        if (list != null && list.size()>0){

            scheduleList = new ArrayList<Schedule>();

            for (Course course:list){

                //获取教师编号，教师名
                TeacherCourseExample teacherCourseExample = new TeacherCourseExample();

                TeacherCourseExample.Criteria criteria1 = teacherCourseExample.createCriteria();
                criteria1.andCourseIDEqualTo(course.getCourseID());

                List<TeacherCourse> teacherCourseList = teacherCourseMapper.selectByExample(teacherCourseExample);

                if (teacherCourseList != null && teacherCourseList.size()>0){

                    for (TeacherCourse teacherCourse:teacherCourseList){

                        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());

                        //获取课程表信息
                        TimetableExample timetableExample = new TimetableExample();

                        TimetableExample.Criteria criteria2 = timetableExample.createCriteria();
                        criteria2.andTeacher_CourseIDEqualTo(teacherCourse.getTeacher_CourseID());

                        List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);

                        if (timetableList != null && timetableList.size()>0){
                            for (Timetable timetable:timetableList){
                                Schedule schedule = new Schedule();
                                //拷贝
                                BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                                //获取院系名
                                Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                                schedule.setDeptName(dept.getDeptName());
                                //获取专业名
                                String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                                schedule.setSpecialtyName(specialtyName);
                                //拷贝
                                BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                                //获取上课时间，上课地点
                                //上课时间，上课地点 需要对对应代码转换为明文 如0101101 --> 明德N101
                                Time time = timeMapper.selectByPrimaryKey(timetable.getTime());
                                Classroom classroom = classroomMapper.selectByPrimaryKey(timetable.getClassroom());
                                //获取上课时间 上课地点
                                schedule.setCourseTime(time.getTimeName());
                                schedule.setClassroom(classroom.getClassroomName());
                                //获取教师_课程编号
                                schedule.setTeacher_CourseID(teacherCourse.getTeacher_CourseID());

                                scheduleList.add(schedule);
                            }
                        }
                        else {
                            Schedule schedule = new Schedule();
                            //拷贝
                            BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                            //获取院系名
                            Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                            schedule.setDeptName(dept.getDeptName());
                            //获取专业名
                            String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                            schedule.setSpecialtyName(specialtyName);
                            //拷贝
                            BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                            //获取教师_课程编号
                            schedule.setTeacher_CourseID(teacherCourse.getTeacher_CourseID());

                            scheduleList.add(schedule);
                        }
                    }
                }
                else {
                    Schedule schedule = new Schedule();
                    //拷贝
                    BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                    //获取院系名
                    Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                    schedule.setDeptName(dept.getDeptName());
                    //获取专业名
                    String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                    schedule.setSpecialtyName(specialtyName);

                    scheduleList.add(schedule);
                }
            }
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> findCourse(String courseName,String deptID,String specialty,String courseType) throws Exception {

        CourseExample courseExample = new CourseExample();
        //自定义查询条件
        CourseExample.Criteria criteria = courseExample.createCriteria();

        criteria.andCourseNameLike("%"+courseName+"%");
        if (!deptID.equals("")){
            criteria.andDeptIDEqualTo(deptID);
        }
        if (!specialty.equals("")){
            criteria.andSpecialtyEqualTo(specialty);
        }
        if (!courseType.equals("")){
            criteria.andCourseTypeEqualTo(courseType);
        }

        List<Course> list = null;

        list = courseMapper.selectByExample(courseExample);

        List<Schedule> scheduleList = null;

        if (list != null && list.size()>0){

            scheduleList = new ArrayList<Schedule>();

            for (Course course:list){

                //获取教师编号，教师名
                TeacherCourseExample teacherCourseExample = new TeacherCourseExample();

                TeacherCourseExample.Criteria criteria1 = teacherCourseExample.createCriteria();
                criteria1.andCourseIDEqualTo(course.getCourseID());

                List<TeacherCourse> teacherCourseList = teacherCourseMapper.selectByExample(teacherCourseExample);

                if (teacherCourseList != null && teacherCourseList.size()>0){

                    for (TeacherCourse teacherCourse:teacherCourseList){

                        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());

                        //获取课程表信息
                        TimetableExample timetableExample = new TimetableExample();

                        TimetableExample.Criteria criteria2 = timetableExample.createCriteria();
                        criteria2.andTeacher_CourseIDEqualTo(teacherCourse.getTeacher_CourseID());

                        List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);

                        if (timetableList != null && timetableList.size()>0){
                            for (Timetable timetable:timetableList){
                                Schedule schedule = new Schedule();
                                //拷贝
                                BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                                //获取院系名
                                Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                                schedule.setDeptName(dept.getDeptName());
                                //获取专业名
                                String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                                schedule.setSpecialtyName(specialtyName);
                                //拷贝
                                BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                                //获取上课时间，上课地点
                                //上课时间，上课地点 需要对对应代码转换为明文 如0101101 --> 明德N101
                                Time time = timeMapper.selectByPrimaryKey(timetable.getTime());
                                Classroom classroom = classroomMapper.selectByPrimaryKey(timetable.getClassroom());
                                //获取上课时间 上课地点
                                schedule.setCourseTime(time.getTimeName());
                                schedule.setClassroom(classroom.getClassroomName());
                                //获取教师_课程编号
                                schedule.setTeacher_CourseID(teacherCourse.getTeacher_CourseID());

                                scheduleList.add(schedule);
                            }
                        }
                        else {
                            Schedule schedule = new Schedule();
                            //拷贝
                            BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                            //获取院系名
                            Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                            schedule.setDeptName(dept.getDeptName());
                            //获取专业名
                            String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                            schedule.setSpecialtyName(specialtyName);
                            //拷贝
                            BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                            //获取教师_课程编号
                            schedule.setTeacher_CourseID(teacherCourse.getTeacher_CourseID());

                            scheduleList.add(schedule);
                        }
                    }
                }
                else {
                    Schedule schedule = new Schedule();
                    //拷贝
                    BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                    //获取院系名
                    Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                    schedule.setDeptName(dept.getDeptName());
                    //获取专业名
                    String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                    schedule.setSpecialtyName(specialtyName);
                    scheduleList.add(schedule);
                }
            }
        }
        return scheduleList;
    }

    @Override
    public List<StudentCourseExpand> findByTeacher_CourseID(String teacher_CourseID) throws Exception {

        StudentCourseExample studentCourseExample = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();
        criteria.andTeacher_CourseIDEqualTo(teacher_CourseID);

        List<StudentCourse> studentCourseList = studentCourseMapper.selectByExample(studentCourseExample);
        List<StudentCourseExpand> studentCourseExpandList = new ArrayList<StudentCourseExpand>();
        for (StudentCourse studentCourse:studentCourseList){
            StudentCourseExpand studentCourseExpand = new StudentCourseExpand();
            BeanUtils.copyProperties(studentCourse,studentCourseExpand);
            //判断是否完成该类课程
            if (studentCourseExpand.getGrade()!=null){
                studentCourseExpand.setOver(true);
            }
            Student student = studentMapper.selectByPrimaryKey(studentCourseExpand.getStudentID());
            StudentExpand studentExpand = new StudentExpand();
            BeanUtils.copyProperties(student,studentExpand);

            //获取院系名
            TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(teacher_CourseID);
            Course course = courseMapper.selectByPrimaryKey(teacherCourse.getCourseID());
            Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
            studentExpand.setDeptName(dept.getDeptName());

            studentCourseExpand.setStudentExpand(studentExpand);

            studentCourseExpandList.add(studentCourseExpand);
        }
        return studentCourseExpandList;
    }

    @Override
    public int getCountOfCourse() throws Exception {
        //自定义查询条件
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andCourseIDIsNotNull();
        return courseMapper.countByExample(courseExample);
    }

    @Override
    public int getCountOfCourseOfDept(String deptID) throws Exception {
        //自定义查询条件
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andCourseIDIsNotNull();
        criteria.andDeptIDEqualTo(deptID);
        return courseMapper.countByExample(courseExample);
    }

    @Override
    public int getCountOfCourseOfSpecialty(String specialty) throws Exception {
        //自定义查询条件
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andCourseIDIsNotNull();
        criteria.andSpecialtyEqualTo(specialty);
        return courseMapper.countByExample(courseExample);
    }

    @Override
    public int getCountOfSearchCourse(String courseName, String deptID) throws Exception {

        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        if (!courseName.equals("")){
            criteria.andCourseNameLike("%"+courseName+"%");
        }
        if (!deptID.equals("")){
            criteria.andDeptIDEqualTo(deptID);
        }
        return courseMapper.countByExample(courseExample);
    }

    @Override
    public List<CourseExpand> findSimpleCourseByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return courseMapperExpand.findCourseByPaging(pagingVO);
    }

    @Override
    public List<Schedule> findDetailCourseByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<CourseExpand> courseExpandList = courseMapperExpand.findDetailCourseByPaging(pagingVO);
        List<Schedule> list = new ArrayList<Schedule>();
        if (courseExpandList!=null && courseExpandList.size() > 0){

            for (CourseExpand courseExpand:courseExpandList){
                //获取教师编号，教师名
                TeacherCourseExample teacherCourseExample = new TeacherCourseExample();

                TeacherCourseExample.Criteria criteria1 = teacherCourseExample.createCriteria();
                criteria1.andCourseIDEqualTo(courseExpand.getCourseID());

                List<TeacherCourse> teacherCourseList;
                teacherCourseList = teacherCourseMapper.selectByExample(teacherCourseExample);

                for (TeacherCourse teacherCourse:teacherCourseList){

                    Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());

                    //获取课程表信息
                    TimetableExample timetableExample = new TimetableExample();

                    TimetableExample.Criteria criteria2 = timetableExample.createCriteria();
                    criteria2.andTeacher_CourseIDEqualTo(teacherCourse.getTeacher_CourseID());

                    List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);

                    if (timetableList.size() > 0){
                        for (Timetable timetable:timetableList){
                            Schedule schedule = new Schedule();
                            //拷贝
                            BeanUtils.copyProperties(courseExpand,schedule); //拷贝了课程号 课程名 学分 课程类型 周数

                            //拷贝
                            BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                            //获取上课时间，上课地点
                            //上课时间，上课地点 需要对对应代码转换为明文 如0101101 --> 明德N101
                            Time time = timeMapper.selectByPrimaryKey(timetable.getTime());
                            Classroom classroom = classroomMapper.selectByPrimaryKey(timetable.getClassroom());
                            //获取上课时间 上课地点
                            schedule.setCourseTime(time.getTimeName());
                            schedule.setClassroom(classroom.getClassroomName());
                            //获取教师_课程编号,选课人数
                            BeanUtils.copyProperties(teacherCourse,schedule);

                            list.add(schedule);
                        }
                    }
                    else {
                        Schedule schedule = new Schedule();
                        //拷贝
                        BeanUtils.copyProperties(courseExpand,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                        //拷贝
                        BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                        //获取教师_课程编号,选课人数
                        BeanUtils.copyProperties(teacherCourse,schedule);

                        list.add(schedule);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<Schedule> findSearchCourseByPaging(String courseName, String deptID, Integer toPageNo) throws Exception {

        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        if (!courseName.equals("")){
            criteria.andCourseNameLike("%"+courseName+"%");
        }
        if (!deptID.equals("")){
            criteria.andDeptIDEqualTo(deptID);
        }
        List<CourseExpand> courseExpandList = courseMapperExpand.findSearchCourseByPaging_Example(pagingVO,courseExample);

        List<Schedule> list = new ArrayList<Schedule>();
        if (courseExpandList!=null){

            for (CourseExpand courseExpand:courseExpandList){
                //获取教师编号，教师名
                TeacherCourseExample teacherCourseExample = new TeacherCourseExample();

                TeacherCourseExample.Criteria criteria1 = teacherCourseExample.createCriteria();
                criteria1.andCourseIDEqualTo(courseExpand.getCourseID());

                List<TeacherCourse> teacherCourseList;
                teacherCourseList = teacherCourseMapper.selectByExample(teacherCourseExample);

                for (TeacherCourse teacherCourse:teacherCourseList){

                    Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());

                    //获取课程表信息
                    TimetableExample timetableExample = new TimetableExample();

                    TimetableExample.Criteria criteria2 = timetableExample.createCriteria();
                    criteria2.andTeacher_CourseIDEqualTo(teacherCourse.getTeacher_CourseID());

                    List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);

                    for (Timetable timetable:timetableList){
                        Schedule schedule = new Schedule();
                        //拷贝
                        BeanUtils.copyProperties(courseExpand,schedule); //拷贝了课程号 课程名 学分 课程类型 周数

                        //拷贝
                        BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                        //获取上课时间，上课地点
                        //上课时间，上课地点 需要对对应代码转换为明文 如0101101 --> 明德N101
                        Time time = timeMapper.selectByPrimaryKey(timetable.getTime());
                        Classroom classroom = classroomMapper.selectByPrimaryKey(timetable.getClassroom());
                        //获取上课时间 上课地点
                        schedule.setCourseTime(time.getTimeName());
                        schedule.setClassroom(classroom.getClassroomName());
                        //获取教师_课程编号,选课人数
                        BeanUtils.copyProperties(teacherCourse,schedule);

                        list.add(schedule);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public StudentCourseExpand getGradeOfOne(StudentCourseExpand studentCourseExpand) throws Exception {

        StudentCourseExample studentCourseExample = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();

        criteria.andTeacher_CourseIDEqualTo(studentCourseExpand.getTeacher_CourseID());
        criteria.andStudentIDEqualTo(studentCourseExpand.getStudentID());

        List<StudentCourse> list = studentCourseMapper.selectByExample(studentCourseExample);
        if (list.size()>0){
            StudentCourseExpand sce = new StudentCourseExpand();

            BeanUtils.copyProperties(list.get(0),sce);
            //获取扩展学生信息
            Student student = studentMapper.selectByPrimaryKey(studentCourseExpand.getStudentID());
            StudentExpand studentExpand = new StudentExpand();
            BeanUtils.copyProperties(student,studentExpand);

            //获取教师课程扩展信息
            TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(studentCourseExpand.getTeacher_CourseID());

            //获取扩展教师课程中的扩展课程信息
            Course course = courseMapper.selectByPrimaryKey(teacherCourse.getCourseID());
            CourseExpand courseExpand = new CourseExpand();
            BeanUtils.copyProperties(course,courseExpand);
            //获取院系信息
            Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
            courseExpand.setDeptName(dept.getDeptName());

            //获取扩展教师课程中的扩展教师信息
            Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());
            TeacherExpand teacherExpand = new TeacherExpand();
            BeanUtils.copyProperties(teacher,teacherExpand);

            TeacherCourseExpand teacherCourseExpand = new TeacherCourseExpand();

            BeanUtils.copyProperties(teacherCourse,teacherCourseExpand);
            teacherCourseExpand.setTeacherExpand(teacherExpand);
            teacherCourseExpand.setCourseExpand(courseExpand);
//            BeanUtils.copyProperties(teacherExpand,teacherCourseExpand); //此方法无法传值
//            BeanUtils.copyProperties(courseExpand,teacherCourseExpand);  //此方法无法传值

            sce.setStudentExpand(studentExpand);
            sce.setTeacherCourseExpand(teacherCourseExpand);
            return sce;
        }
        return null;
    }

    @Override
    public void updateGradeOfOne(StudentCourse studentCourse) throws Exception {
        StudentCourseExample studentCourseExample = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();

        criteria.andStudentIDEqualTo(studentCourse.getStudentID());
        criteria.andTeacher_CourseIDEqualTo(studentCourse.getTeacher_CourseID());

        studentCourseMapper.updateByExample(studentCourse,studentCourseExample);
    }

    @Override
    public CourseExpand findCourseByID(String courseID) throws Exception {

        CourseExpand courseExpand = new CourseExpand();
        Course course = courseMapper.selectByPrimaryKey(courseID);
        String deptName = deptMapper.getDeptName(course.getDeptID());
        BeanUtils.copyProperties(course,courseExpand);
        courseExpand.setDeptName(deptName);

        return courseExpand;
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        try{
            courseMapper.updateByPrimaryKeySelective(course);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getCourseNameById(String courseID) throws Exception {
        return courseMapper.getCourseNameByPrimaryKey(courseID);
    }

    @Override
    public List<Schedule> getCourseOfSpecialty_Year(String courseName, String deptID, String specialtyID,String year,String courseType) throws Exception {
        SpecialtyYearCourseExample specialtyYearCourseExample = new SpecialtyYearCourseExample();
        //自定义查询条件
        SpecialtyYearCourseExample.Criteria criteria = specialtyYearCourseExample.createCriteria();

        if (!specialtyID.equals("") && !year.equals("")){
            String specialty_YearID = year + specialtyID;
            criteria.andSpecialty_YearIDEqualTo(specialty_YearID);
        }
        else if (!specialtyID.equals("")){
            criteria.andSpecialty_YearIDLike("%" + specialtyID);
        }
        else if (!year.equals("")){
            criteria.andSpecialty_YearIDLike(year + "%");
        }
        List<String> courseIdList = specialtyYearCourseMapper.getCourseIDListByExample(specialtyYearCourseExample);

        //根据课程名、课程类型获取课程Id集合
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria courseCriteria = courseExample.createCriteria();
        courseCriteria.andDeptIDEqualTo(deptID);
        if (!courseName.equals("")){
            courseCriteria.andCourseNameLike("%"+courseName+"%");
        }
        if (!courseType.equals("")){
            courseCriteria.andCourseTypeEqualTo(courseType);
        }
        if (courseIdList.size() > 0){
            courseCriteria.andCourseIDIn(courseIdList);
        }

        List<Course> list = null;

        list = courseMapper.selectByExample(courseExample);

        List<Schedule> scheduleList = null;

        if (list != null && list.size()>0){

            scheduleList = new ArrayList<Schedule>();

            for (Course course:list){

                //获取教师编号，教师名
                TeacherCourseExample teacherCourseExample = new TeacherCourseExample();

                TeacherCourseExample.Criteria criteria1 = teacherCourseExample.createCriteria();
                criteria1.andCourseIDEqualTo(course.getCourseID());

                List<TeacherCourse> teacherCourseList = teacherCourseMapper.selectByExample(teacherCourseExample);

                if (teacherCourseList != null && teacherCourseList.size()>0){

                    for (TeacherCourse teacherCourse:teacherCourseList){

                        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherID());

                        //获取课程表信息
                        TimetableExample timetableExample = new TimetableExample();

                        TimetableExample.Criteria criteria2 = timetableExample.createCriteria();
                        criteria2.andTeacher_CourseIDEqualTo(teacherCourse.getTeacher_CourseID());

                        List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);

                        if (timetableList != null && timetableList.size()>0){
                            for (Timetable timetable:timetableList){
                                Schedule schedule = new Schedule();
                                //拷贝
                                BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                                //获取院系名
                                Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                                schedule.setDeptName(dept.getDeptName());
                                //获取专业名
                                String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                                schedule.setSpecialtyName(specialtyName);
                                //拷贝
                                BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                                //获取上课时间，上课地点
                                //上课时间，上课地点 需要对对应代码转换为明文 如0101101 --> 明德N101
                                Time time = timeMapper.selectByPrimaryKey(timetable.getTime());
                                Classroom classroom = classroomMapper.selectByPrimaryKey(timetable.getClassroom());
                                //获取上课时间 上课地点
                                schedule.setCourseTime(time.getTimeName());
                                schedule.setClassroom(classroom.getClassroomName());
                                //获取教师_课程编号
                                schedule.setTeacher_CourseID(teacherCourse.getTeacher_CourseID());

                                scheduleList.add(schedule);
                            }
                        }
                        else {
                            Schedule schedule = new Schedule();
                            //拷贝
                            BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                            //获取院系名
                            Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                            schedule.setDeptName(dept.getDeptName());
                            //获取专业名
                            String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                            schedule.setSpecialtyName(specialtyName);
                            //拷贝
                            BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                            //获取教师_课程编号
                            schedule.setTeacher_CourseID(teacherCourse.getTeacher_CourseID());

                            scheduleList.add(schedule);
                        }
                    }
                }
                else {
                    Schedule schedule = new Schedule();
                    //拷贝
                    BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                    //获取院系名
                    Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                    schedule.setDeptName(dept.getDeptName());
                    //获取专业名
                    String specialtyName = specialtyMapper.getSpecialtyName(course.getSpecialty());
                    schedule.setSpecialtyName(specialtyName);
                    scheduleList.add(schedule);
                }
            }
        }
        return scheduleList;
    }

    @Override
    public List<CourseExpand> getCourseOfSpecialty(String specialty,String specialty_Year,Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<CourseExpand> courseExpandList = courseMapperExpand.getCourseOfSpecialtyByPaging(pagingVO,specialty);
        if (courseExpandList.size() > 0){
            for (CourseExpand courseExpand:courseExpandList){
                String courseId = courseExpand.getCourseID();
                SpecialtyYearCourseExample specialtyYearCourseExample = new SpecialtyYearCourseExample();
                SpecialtyYearCourseExample.Criteria criteria = specialtyYearCourseExample.createCriteria();
                criteria.andSpecialty_YearIDEqualTo(specialty_Year);
                criteria.andCourseIDEqualTo(courseId);
                List<SpecialtyYearCourse> specialtyYearCourseList = specialtyYearCourseMapper.selectByExample(specialtyYearCourseExample);
                if (specialtyYearCourseList.size() == 1){
                    courseExpand.setStudiedByOneSpecialty(true);
                }
                else {
                    courseExpand.setStudiedByOneSpecialty(false);
                }
            }
        }

        return courseExpandList;
    }
}
