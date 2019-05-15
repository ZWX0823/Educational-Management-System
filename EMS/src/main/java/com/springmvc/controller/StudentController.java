package com.springmvc.controller;

import com.springmvc.exception.CustomException;
import com.springmvc.po.*;
import com.springmvc.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "studentCourseServiceImpl")
    private StudentCourseService studentCourseService;

    @Resource(name = "curriculumServiceImpl")
    private CurriculumService curriculumService;

    @Resource(name = "deptServiceImpl")
    private DeptService deptService;

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

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
        if (model.containsAttribute("selectCourseMessage")){
            model.addAttribute("message","1");
        }
        List<Specialty> specialtyList = deptService.findAllSpecialty();
        model.addAttribute("courseList",list);
        model.addAttribute("pagingVO",pagingVO);
        model.addAttribute("specialtyList",specialtyList);

        return "student/index";
    }

    //选课操作
    @RequestMapping(value = "/selectCourse")
    public String selectCourse(String teacher_CourseID, RedirectAttributes attr)throws Exception{

        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();

        Student student = studentService.findByAccountNumber(accountNumber);
        if (student != null && !teacher_CourseID.equals("")){
            //判断是否选了某教师教授的某门课
            Boolean result = studentCourseService.IsSelectCourse(student.getStudentID(),teacher_CourseID);
            if (result){
                attr.addFlashAttribute("selectCourseMessage","该门课程你已经选了");
                return "redirect:/student/index";
            }
            else {
                studentCourseService.selectCourse(student.getStudentID(),teacher_CourseID);
            }
        }
        else if (teacher_CourseID.equals("")){
            attr.addAttribute("selectCourseMessage","该课程还未安排教师");
            return "redirect:/student/index";
        }
        return "redirect:/student/selectedCourse";
    }

    //搜索课程
    @RequestMapping(value = "/searchCourse",method = {RequestMethod.POST})
    public String searchCourse(String courseName, String deptID, String specialty, String courseType, Model model)throws Exception{

        List<Schedule> list;
        list = courseService.findCourse(courseName,deptID,specialty,courseType);
        model.addAttribute("courseList",list);

        return "student/index";
    }
/**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<已选课程>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //已选课程
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model,Integer page)throws Exception{

        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();

        Student student = studentService.findByAccountNumber(accountNumber);

        List<StudentCourseExpand> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(studentCourseService.getCountOfSelectedCourse(student.getStudentID()));
        if (page == null||page == 0){
            pagingVO.setToPageNo(1);
            list = studentCourseService.findSelectedCourseByPaging(1,student.getStudentID());
        }
        else {
            pagingVO.setToPageNo(page);
            list = studentCourseService.findSelectedCourseByPaging(page,student.getStudentID());
        }
        model.addAttribute("selectedCourseList",list);
        model.addAttribute("pagingVO",pagingVO);
        if(model.containsAttribute("revokeMessage")){
            model.addAttribute("message","你已成功退选该课");
        }
        return "student/selectedCourse";
    }

    //退课操作
    @RequestMapping(value = "/revokeCourse")
    public String revokeCourse(String teacher_CourseID,RedirectAttributes attr)throws Exception{

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String)subject.getPrincipal();

        Student student = studentService.findByAccountNumber(accountNumber);
        if (student != null && !teacher_CourseID.equals("")){
            //判断是否选了这门课
            Boolean result = studentCourseService.IsSelectCourse(student.getStudentID(),teacher_CourseID);
            if (result){
                studentCourseService.revokeCourse(student.getStudentID(),teacher_CourseID);
                attr.addFlashAttribute("revokeMessage","你已成功退选该课");
            }
            else {
                throw new CustomException("没有你选该课的信息");
            }
        }
        return "redirect:/student/selectedCourse";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<已修课程>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //已修课程
    @RequestMapping(value = "/finishedCourse")
    public String finishedCourse(Model model,Integer page)throws Exception{

        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();

        Student student = studentService.findByAccountNumber(accountNumber);

        List<StudentCourseExpand> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(studentCourseService.getCountOfFinishedCourse(student.getStudentID()));
        if (page == null||page == 0){
            pagingVO.setToPageNo(1);
            list = studentCourseService.findFinishedCourseByPaging(1,student.getStudentID());
        }
        else {
            pagingVO.setToPageNo(page);
            list = studentCourseService.findFinishedCourseByPaging(page,student.getStudentID());
        }
        model.addAttribute("finishedCourseList",list);
        model.addAttribute("pagingVO",pagingVO);

        return "student/finishedCourse";
    }

    //Comment teacher_course
    @RequestMapping(value = "/comment")
    public String comment( String teacher_CourseID, String comment)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();

        Student student = studentService.findByAccountNumber(accountNumber);
        String studentID = student.getStudentID();
        studentCourseService.commentTeacherCourse(studentID,teacher_CourseID,comment);

        return "redirect:/student/finishedCourse";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<我的课程表>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //Show curriculum
    @RequestMapping(value = "/curriculum")
    public String schedule(Model model)throws Exception{
        return "student/curriculum";
    }

    //Search curriculum
    @RequestMapping(value = "/searchCurriculum")
    public String searchCurriculum(String specialty,String grade,String year,String half,Model model)throws Exception{

        if (specialty.equals("") || grade.equals("") || year.equals("") || half.equals("")){
            return "student/curriculum";
        }
        String term = year+half;
        String specialty_YearID = grade+specialty;
        List<Timetable> timetableList = curriculumService.getCurriculum(specialty_YearID,term);
        List<CurriculumInfo> list = new ArrayList<>();
        if (timetableList.size() > 0){
            list = curriculumService.getDetailCurriculum(timetableList);
        }
        model.addAttribute("curriculumList",list);
        return "student/curriculum";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<其他>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //密码重置
    @RequestMapping("/passwordReset")
    public String passwordReset() throws Exception{
        return "student/passwordReset";
    }
}
