package com.springmvc.service.impl;

import com.springmvc.mapper.*;
import com.springmvc.po.*;
import com.springmvc.po.Class;
import com.springmvc.service.DeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource(name = "deptMapper")
    private DeptMapper deptMapper;

    @Resource(name = "specialtyMapper")
    private SpecialtyMapper specialtyMapper;

    @Resource(name = "classMapper")
    private ClassMapper classMapper;

    @Resource(name = "teacherCourseMapper")
    private TeacherCourseMapper teacherCourseMapper;

    @Resource(name = "teacherMapper")
    private TeacherMapper teacherMapper;

    @Resource(name = "timetableMapper")
    private TimetableMapper timetableMapper;

    @Resource(name = "timeMapper")
    private TimeMapper timeMapper;

    @Resource(name = "classroomMapper")
    private ClassroomMapper classroomMapper;

    @Resource(name = "courseMapperExpand")
    private CourseMapperExpand courseMapperExpand;

    @Resource(name = "specialtyYearMapper")
    private SpecialtyYearMapper specialtyYearMapper;

    @Resource(name = "specialtyYearCourseMapper")
    private SpecialtyYearCourseMapper specialtyYearCourseMapper;

    @Resource(name = "classMapperExpand")
    private ClassMapperExpand classMapperExpand;

    @Resource(name = "studentMapperExpand")
    private StudentMapperExpand studentMapperExpand;

    @Override
    public List<Dept> findAllDept() throws Exception {

        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        criteria.andDeptIDIsNotNull();

        return deptMapper.selectByExample(deptExample);
    }

    @Override
    public List<Specialty> findAllSpecialty() throws Exception {

        SpecialtyExample specialtyExample = new SpecialtyExample();
        SpecialtyExample.Criteria criteria = specialtyExample.createCriteria();
        criteria.andSpecialtyIDIsNotNull();

        return specialtyMapper.selectByExample(specialtyExample);
    }

    @Override
    public List<com.springmvc.po.Class> findAllClass() throws Exception {

        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        criteria.andClassIDIsNotNull();

        return classMapper.selectByExample(classExample);
    }

    @Override
    public List<Specialty> findAllSpecialtyOfDept(String deptID) throws Exception {

        SpecialtyExample specialtyExample = new SpecialtyExample();
        SpecialtyExample.Criteria criteria = specialtyExample.createCriteria();
        criteria.andDeptEqualTo(deptID);

        return specialtyMapper.selectByExample(specialtyExample);
    }

    @Override
    public List<com.springmvc.po.Class> findAllClassOfSpecialty(String specialtyID) throws Exception {

        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        criteria.andSpecialtyEqualTo(specialtyID);

        return classMapper.selectByExample(classExample);
    }

    @Override
    public List<CourseExpand> getCourseOfDeptByPaging(String deptID, Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return courseMapperExpand.getCourseOfDeptByPaging(pagingVO,deptID);
    }

    @Override
    public List<Schedule> findDetailCourseByPaging(String deptID,Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<CourseExpand> courseExpandList = courseMapperExpand.getCourseOfDeptByPaging(pagingVO,deptID);
        List<Schedule> list = new ArrayList<>();
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
    public Dept getDeptById(String deptID) throws Exception {
        return deptMapper.selectByPrimaryKey(deptID);
    }

    @Override
    public int getCountOfSpecialtyYear(String deptID) throws Exception {
        SpecialtyYearExample specialtyYearExample = new SpecialtyYearExample();
        SpecialtyYearExample.Criteria criteria = specialtyYearExample.createCriteria();
        criteria.andDeptEqualTo(deptID);
        return specialtyYearMapper.countByExample(specialtyYearExample);
    }

    @Override
    public int getCountOfClassesOfDeot(String deptId) throws Exception {
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        criteria.andSpecialtyLike("____"+ deptId +"__");

        return classMapper.countByExample(classExample);
    }

    @Override
    public List<SpecialtyYear> getAllSpecialtyYear(String deptID, Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        return specialtyYearMapper.selectByDept_Paging(deptID,pagingVO);
    }

    @Override
    public SpecialtyYear getSpecialtyYearById(String specialty_YearID) throws Exception {
        return specialtyYearMapper.selectByPrimaryKey(specialty_YearID);
    }

    @Override
    public List<SpecialtyYear> getSpecialtyYear(String entranceYear, String specialty) throws Exception {
        SpecialtyYearExample specialtyYearExample = new SpecialtyYearExample();
        SpecialtyYearExample.Criteria criteria = specialtyYearExample.createCriteria();
        if (!entranceYear.equals("")){
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Integer.valueOf(entranceYear),7,30);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Integer.valueOf(entranceYear),10,1);
            criteria.andAdmissionDateBetween(calendar1.getTime(),calendar2.getTime());
        }
        if (!specialty.equals("")){
            criteria.andSpecialtyEqualTo(specialty);
        }
        criteria.andSpecialty_YearIDIsNotNull();

        return specialtyYearMapper.selectByExample(specialtyYearExample);
    }

    @Override
    public void addSpecialty_Year(SpecialtyYear specialtyYear) throws Exception {

        String specialtyName = specialtyMapper.getSpecialtyName(specialtyYear.getSpecialty());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(specialtyYear.getAdmissionDate());
        String specialty_YearName = calendar.get(Calendar.YEAR) +"级" + specialtyName ;
        String specialty_YearID = calendar.get(Calendar.YEAR) + specialtyYear.getSpecialty();
        specialtyYear.setSpecialty_YearName(specialty_YearName);
        specialtyYear.setSpecialty_YearID(specialty_YearID);
        specialtyYear.setNumber(0);
        specialtyYear.setFinish("0");
        specialtyYear.setClassNumber(Short.valueOf("0"));

        try{
            specialtyYearMapper.insert(specialtyYear);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSpecialty_Year(SpecialtyYear specialtyYear) throws Exception {
        try{
            specialtyYearMapper.updateByPrimaryKeySelective(specialtyYear);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addClass(String specialty_YearID,String classNumber) throws Exception {
        SpecialtyYear specialtyYear = specialtyYearMapper.selectByPrimaryKey(specialty_YearID);

        if (specialtyYear.getClassNumber() < Short.valueOf(classNumber)){
            Class c = new Class();
            c.setClassID(specialty_YearID + "0" + (specialtyYear.getClassNumber() + 1));
            String className = specialtyMapper.getSpecialtyName(specialtyYear.getSpecialty()) + (specialtyYear.getClassNumber() + 1) + "班";
            c.setClassName(className);
            c.setSpecialty(specialtyYear.getSpecialty_YearID());
            c.setNumber(0);
            c.setAdmissionDate(specialtyYear.getAdmissionDate());
            try{
                classMapper.insert(c);
            }catch (Exception e){
                e.printStackTrace();
            }
            //The classNumber of specialty_year add 1
            specialtyYear.setClassNumber(Short.valueOf(classNumber));
            try{
                specialtyYearMapper.updateByPrimaryKeySelective(specialtyYear);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (specialtyYear.getClassNumber() > Short.valueOf(classNumber)){
            throw new Exception("不能删除班级");
        }
    }

    @Override
    public void addSpecialty_Year_Course(String specialty_Year, List<String> courseIDList,String time) throws Exception {
        for (String courseId:courseIDList){
            SpecialtyYearCourse specialtyYearCourse = new SpecialtyYearCourse();
            specialtyYearCourse.setSpecialty_YearID(specialty_Year);
            specialtyYearCourse.setCourseID(courseId);

            //There is not the specialty_year_course in database?
            SpecialtyYearCourseKey specialtyYearCourseKey = new SpecialtyYearCourseKey();
            specialtyYearCourseKey.setSpecialty_YearID(specialty_Year);
            specialtyYearCourseKey.setCourseID(courseId);
            SpecialtyYearCourse specialtyYearCourse1 = specialtyYearCourseMapper.selectByPrimaryKey(specialtyYearCourseKey);
            if (specialtyYearCourse1 == null){
                specialtyYearCourse.setTime(time);
                try{
                    specialtyYearCourseMapper.insert(specialtyYearCourse);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<ClassExpand> getClassesOfDept(String deptID,Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        return classMapperExpand.getClassesOfDeptByPaging(pagingVO,deptID);
    }

    @Override
    public List<StudentExpand> getStudentsOfClass(String classID, Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        return studentMapperExpand.getStudentOfClassByPaging(classID,pagingVO);
    }

    @Override
    public Class getClassById(String classID) throws Exception {
        return classMapper.selectByPrimaryKey(classID);
    }

    @Override
    public List<ClassExpand> getClassOfSpecialty(String specialty_Year, Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        return classMapperExpand.getClassesOfSpecialtyByPaging(pagingVO,specialty_Year);
    }

}
