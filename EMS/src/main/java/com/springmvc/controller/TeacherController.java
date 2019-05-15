package com.springmvc.controller;

import com.springmvc.po.*;
import com.springmvc.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "curriculumServiceImpl")
    private CurriculumService curriculumService;

    @Resource(name = "teacherCourseServiceImpl")
    private TeacherCourseService teacherCourseService;

    @Resource(name = "deptServiceImpl")
    private DeptService deptService;
    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程管理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>**/

    //课程信息显示
    @RequestMapping(value = "/index")
    public String showCourse(Model model,Integer page)throws Exception{

        List<Schedule> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(courseService.getCountOfCourse());
        if (page == null||page == 0){
            pagingVO.setToPageNo(1);
            list = courseService.findDetailCourseByPaging(1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = courseService.findDetailCourseByPaging(page);
        }
        List<Specialty> specialtyList = deptService.findAllSpecialty();
        model.addAttribute("courseList",list);
        model.addAttribute("pagingVO",pagingVO);
        model.addAttribute("specialtyList",specialtyList);

        return "teacher/index";
    }

    //显示我的课程
    @RequestMapping("/myCourse")
    public String courseShow(Model model,Integer page) throws Exception{

        List<Schedule> list;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(courseService.getCountOfCourse());
        //Get user --teacher
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();
        Teacher teacher = teacherService.findByAccountNumber(accountNumber);
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = teacherCourseService.findCourseOfTeacherByPaging(teacher,1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = teacherCourseService.findCourseOfTeacherByPaging(teacher,page);
        }
        model.addAttribute("schedule",list);
        model.addAttribute("pagingVO",pagingVO);

        return "teacher/myCourse";
    }

    //搜索课程
    @RequestMapping(value = "/searchCourse",method = {RequestMethod.POST})
    public String searchCourse(String courseName,String deptID,String specialty,String courseType,Model model)throws Exception{

        List<Schedule> list;
        list = courseService.findCourse(courseName,deptID,specialty,courseType);
        model.addAttribute("courseList",list);

        return "teacher/index";
    }

    //搜索我的课程
    @RequestMapping(value = "/searchMyCourse",method = {RequestMethod.POST})
    public String selectCourse(String courseName,Model model) throws Exception{

        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();

        Teacher teacher = teacherService.findByAccountNumber(accountNumber);
        if (teacher != null){
            String teacherID = teacher.getTeacherID();
            List<Schedule> list = teacherCourseService.findCourseOfTeacher(courseName,teacherID);

            model.addAttribute("schedule",list);
        }
        else {
            model.addAttribute("message","未知教师用户");
            return "error";
        }
        return "teacher/myCourse";
    }

    //课程成绩
    @RequestMapping("/courseGrade")
    public String courseGrade(String teacher_CourseID,Model model) throws Exception{
        if (teacher_CourseID==null){
            model.addAttribute("message","异常错误:可能与教师-课程有关！");
            return "error";
        }
        List<StudentCourseExpand> studentCourseExpandList = courseService.findByTeacher_CourseID(teacher_CourseID);
        model.addAttribute("studentCourseList",studentCourseExpandList);
        return "teacher/showGrade";
    }

    //打分页面
    @RequestMapping(value = "/mark",method = {RequestMethod.GET})
    public String markUI(StudentCourseExpand sec,Model model) throws Exception{
        StudentCourseExpand studentCourseExpand = courseService.getGradeOfOne(sec);

        model.addAttribute("studentCourse",studentCourseExpand);
        return "teacher/mark";
    }
    //打分
    @RequestMapping(value = "/mark",method = {RequestMethod.POST})
    public String mark(StudentCourse studentCourse,Integer grade,String teacher_CourseID) throws Exception{


        studentCourse.setGrade(grade);
        studentCourse.setTeacher_CourseID(teacher_CourseID);

        courseService.updateGradeOfOne(studentCourse);

        return "redirect:/teacher/courseGrade?teacher_CourseID="+studentCourse.getTeacher_CourseID();
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程表>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/
    //Show curriculum
    @RequestMapping(value = "/curriculum")
    public String schedule(Model model)throws Exception{
        return "teacher/curriculum";
    }

    //Search curriculum
    @RequestMapping(value = "/searchCurriculum")
    public String searchCurriculum(String specialty,String grade,String year,String half,Model model)throws Exception{

        if (specialty.equals("") || grade.equals("") || year.equals("") || half.equals("")){
            return "teacher/curriculum";
        }
        String term = year+half;
        String specialty_YearID = grade+specialty;
        List<Timetable> timetableList = curriculumService.getCurriculum(specialty_YearID,term);
        List<CurriculumInfo> list = new ArrayList<>();
        if (timetableList.size() > 0){
            list = curriculumService.getDetailCurriculum(timetableList);
        }
        model.addAttribute("curriculumList",list);
        return "teacher/curriculum";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<个人信息管理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>**/
    //修改密码
    @RequestMapping("/passwordReset")
    public String passwordReset() throws Exception{
        return "teacher/passwordReset";
    }
}
