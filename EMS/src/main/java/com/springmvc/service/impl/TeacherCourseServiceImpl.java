package com.springmvc.service.impl;

import com.springmvc.exception.CustomException;
import com.springmvc.mapper.*;
import com.springmvc.po.*;
import com.springmvc.service.TeacherCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Resource(name = "studentCourseMapper")
    private StudentCourseMapper studentCourseMapper;

    @Resource(name = "teacherCourseMapper")
    private TeacherCourseMapper teacherCourseMapper;

    @Resource(name = "timetableMapper")
    private TimetableMapper timetableMapper;

    @Resource(name = "courseMapper")
    private CourseMapper courseMapper;

    @Resource(name = "teacherMapper")
    private TeacherMapper teacherMapper;

    @Resource(name = "timeMapper")
    private TimeMapper timeMapper;

    @Resource(name = "classroomMapper")
    private ClassroomMapper classroomMapper;

    @Resource(name = "deptMapper")
    private DeptMapper deptMapper;

    @Resource(name = "courseMapperExpand")
    private CourseMapperExpand courseMapperExpand;

    @Override
    public void addTeacherCourse(String courseID, List<String> teacherIDList) throws Exception {
        for (String teacherID:teacherIDList){
            TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
            TeacherCourseExample.Criteria criteria = teacherCourseExample.createCriteria();
            criteria.andTeacherIDEqualTo(teacherID);

            List<String> teacher_CourseIDList = teacherCourseMapper.getTeacherCourseIDListByExample(teacherCourseExample);

            criteria.andCourseIDEqualTo(courseID);
            List<TeacherCourse> teacherCourseList = teacherCourseMapper.selectByExample(teacherCourseExample);
            if (teacherCourseList.size() == 0){     //The database doesn't have this teacherCourse info
                int max = 1;
                if (teacher_CourseIDList.size() > 0){       //There are already some teacherCourse info(s) of this teacher in database
                    for (String tcID:teacher_CourseIDList){
                        int i = Integer.valueOf(tcID);
                        if (i > max){
                            max = i;
                        }
                    }
                    TeacherCourse teacherCourse = new TeacherCourse();
                    teacherCourse.setTeacher_CourseID(String.valueOf(max+1));
                    teacherCourse.setTeacherID(teacherID);
                    teacherCourse.setCourseID(courseID);
                    teacherCourse.setNumber(0);
                    try{
                        teacherCourseMapper.insert(teacherCourse);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else {      //There is no teacherCourse info of this teacher in database
                    TeacherCourse teacherCourse = new TeacherCourse();
                    teacherCourse.setTeacher_CourseID(teacherID + "01");
                    teacherCourse.setTeacherID(teacherID);
                    teacherCourse.setCourseID(courseID);
                    teacherCourse.setNumber(0);
                    try{
                        teacherCourseMapper.insert(teacherCourse);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public Boolean deleteTeacherCourse(String teacher_CourseID) throws Exception {
        TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(teacher_CourseID);
        if (teacherCourse == null){
            return false;
        }
        //删除学生选课表里信息（若有）
        StudentCourseExample studentCourseExample = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();
        criteria.andTeacher_CourseIDEqualTo(teacher_CourseID);
        List<StudentCourse> studentCourseList = studentCourseMapper.selectByExample(studentCourseExample);
        if (studentCourseList.size() > 0){
            try {
                studentCourseMapper.deleteByExample(studentCourseExample);
            }catch (Exception e ){
                e.printStackTrace();
            }
        }
        List<StudentCourse> studentCourseList1 = studentCourseMapper.selectByExample(studentCourseExample);
        if (studentCourseList1.size() != 0){
            throw new CustomException("删除失败");
        }
        //删除排课表里信息(若有)
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
        List<Timetable> timetableList1 = timetableMapper.selectByExample(timetableExample);
        if (timetableList1.size() != 0){
            throw new CustomException("删除失败");
        }
        //一定要最后删除教师课程表里信息(teacher_CourseID是其他一些表的外键)
        try {
            teacherCourseMapper.deleteByPrimaryKey(teacher_CourseID);
        }catch (Exception e){
            e.printStackTrace();
        }
        teacherCourse = teacherCourseMapper.selectByPrimaryKey(teacher_CourseID);
        if (teacherCourse != null){
            throw new CustomException("删除失败");
        }
        return true;
    }

    @Override
    public List<TeacherExpand> isTeachOneCourse(String courseID, List<TeacherExpand> teacherExpandList) throws Exception {
        for (TeacherExpand teacherExpand:teacherExpandList){

            TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
            TeacherCourseExample.Criteria criteria = teacherCourseExample.createCriteria();
            criteria.andTeacherIDEqualTo(teacherExpand.getTeacherID());
            criteria.andCourseIDEqualTo(courseID);

            List<TeacherCourse> teacherCourseList = teacherCourseMapper.selectByExample(teacherCourseExample);
            if (teacherCourseList.size() == 1){
                teacherExpand.setTeachOneCourse(true);
            }
            else {
                teacherExpand.setTeachOneCourse(false);
            }
        }
        return teacherExpandList;
    }

    @Override
    public List<Schedule> findCourseOfTeacher(String courseName, String teacherID) throws Exception {

        CourseExample courseExample = new CourseExample();
        //自定义查询条件
        CourseExample.Criteria criteria = courseExample.createCriteria();

        criteria.andCourseNameLike("%"+courseName+"%");

        List<Course> list = courseMapper.selectByExample(courseExample);

        List<Schedule> scheduleList = null;

        if (list != null && list.size()>0){

            scheduleList = new ArrayList<Schedule>();

            for (Course course:list){

                //获取教师编号，教师名
                TeacherCourseExample teacherCourseExample = new TeacherCourseExample();

                TeacherCourseExample.Criteria criteria1 = teacherCourseExample.createCriteria();
                criteria1.andCourseIDEqualTo(course.getCourseID());
                criteria1.andTeacherIDEqualTo(teacherID);

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
                            BeanUtils.copyProperties(course,schedule); //拷贝了课程号 课程名 学分 课程类型 周数
                            //获取院系名
                            Dept dept = deptMapper.selectByPrimaryKey(course.getDeptID());
                            schedule.setDeptName(dept.getDeptName());
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
                        //拷贝
                        BeanUtils.copyProperties(teacher,schedule);//拷贝了教师编号，名字
                        //获取教师_课程编号,选课人数
                        BeanUtils.copyProperties(teacherCourse,schedule);

                        scheduleList.add(schedule);
                    }
                }
            }
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> findCourseOfTeacherByPaging(Teacher teacher, Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        //Get all courseID of one teacher
        List<String> courseIDList = new ArrayList<>();
        try{
            courseIDList = teacherCourseMapper.getCourseIDListByExample(pagingVO,teacher.getTeacherID());
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Schedule> list = new ArrayList<>();
        if (courseIDList.size() > 0){
            for (String courseID:courseIDList){
                //Get courseExpand info by courseID
                CourseExpand courseExpand = courseMapperExpand.getCourseExpandByPrimaryKey(courseID);

                //Get teacher_CourseID
                TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
                TeacherCourseExample.Criteria criteria = teacherCourseExample.createCriteria();
                criteria.andTeacherIDEqualTo(teacher.getTeacherID());
                criteria.andCourseIDEqualTo(courseID);
                List<TeacherCourse> teacherCourse = teacherCourseMapper.selectByExample(teacherCourseExample);

                //Whether it has been scheduled
                TimetableExample timetableExample = new TimetableExample();
                TimetableExample.Criteria criteria1 = timetableExample.createCriteria();
                criteria1.andTeacher_CourseIDEqualTo(teacherCourse.get(0).getTeacher_CourseID());

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
                        BeanUtils.copyProperties(teacherCourse.get(0),schedule);

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
                    BeanUtils.copyProperties(teacherCourse.get(0),schedule);

                    list.add(schedule);
                }
            }
        }
        return list;
    }

    @Override
    public int getCountOfCoursesOfTeacher(String teacherID) throws Exception {
        TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
        TeacherCourseExample.Criteria criteria = teacherCourseExample.createCriteria();
        criteria.andTeacherIDEqualTo(teacherID);

        return teacherCourseMapper.countByExample(teacherCourseExample);
    }

    @Override
    public int getCountOfTeacherCourse(String teacher_CourseID) throws Exception {
        TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
        TeacherCourseExample.Criteria criteria = teacherCourseExample.createCriteria();
        criteria.andTeacher_CourseIDEqualTo(teacher_CourseID);

        return teacherCourseMapper.countByExample(teacherCourseExample);
    }
}
