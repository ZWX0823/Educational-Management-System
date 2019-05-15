package com.springmvc.controller;

import com.springmvc.exception.CustomException;
import com.springmvc.po.*;
import com.springmvc.po.Class;
import com.springmvc.service.*;

import com.springmvc.service.GA.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "deptServiceImpl")
    private DeptService deptService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "loginServiceImpl")
    private LoginService loginService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "teacherCourseServiceImpl")
    private TeacherCourseService teacherCourseService;

    @Resource(name = "scheduleServiceImpl")
    private ScheduleService scheduleService;

    @Resource(name = "curriculumServiceImpl")
    private CurriculumService curriculumService;
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //课程信息显示
    @RequestMapping("/index")
    public String showCourse(Model model,Integer page)throws Exception{
        List<CourseExpand> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(courseService.getCountOfCourse());
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = courseService.findSimpleCourseByPaging(1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = courseService.findSimpleCourseByPaging(page);
        }
        if (model.containsAttribute("deleteMessage1")){
            model.addAttribute("deleteMessage","删除成功");
        }
        if (model.containsAttribute("deleteMessage2")){
            model.addAttribute("deleteMessage","删除失败");
        }
        model.addAttribute("courseList",list);
        model.addAttribute("pagingVO",pagingVO);

        return "admin/index";
    }

    //添加课程
    @RequestMapping(value = "/addCourse",method = {RequestMethod.GET})
    public String addCourseUI(Model model) throws Exception{
        List<Dept> deptList = deptService.findAllDept();

        model.addAttribute("deptList",deptList);
        return "admin/addCourse";
    }

    //添加课程信息处理
    @RequestMapping(value = "/addCourse",method = {RequestMethod.POST})
    public String addCourse(CourseExpand courseExpand,Model model) throws Exception{

        Boolean result = courseService.addCourse(courseExpand);
        if (!result){
            model.addAttribute("message","课程号重复");
            return "error";
        }
        return "redirect:/admin/index";
    }

    //Delete course
    @RequestMapping(value = "/deleteCourse")
    public String deleteCourse(String courseID)throws Exception{
        if (courseID.equals("")){
            throw new Exception("错误:未知课程");
        }
        courseService.deleteCourse(courseID);

        return "redirect:/admin/index";
    }

    //修改课程
    @RequestMapping(value = "/updateCourse",method = {RequestMethod.GET})
    public String updateCourseUI(String courseID,Model model)throws Exception{
        if (courseID.equals("")){
            return "redirect:/admin/index";
        }
        CourseExpand courseExpand = courseService.findCourseByID(courseID);
        if (courseExpand == null){
            throw new CustomException("未找到该课程！");
        }

        List<Dept> list = deptService.findAllDept();

        model.addAttribute("course",courseExpand);
        model.addAttribute("deptList",list);
        if (model.containsAttribute("msg")){
            model.addAttribute("updateMessage","更新成功");
        }
        return "admin/updateCourse";
    }

    //修改课程
    @RequestMapping(value = "/updateCourse",method = {RequestMethod.POST})
    public String updateCourse(Course course,RedirectAttributes attr)throws Exception{

        courseService.updateCourse(course);

        attr.addFlashAttribute("msg","更新成功");

        return "redirect:/admin/updateCourse?courseID="+course.getCourseID();
    }

    //搜索课程
    @RequestMapping(value = "/searchCourse")
    public String searchCourse(@ModelAttribute(value = "courseName") String courseName,String deptID,String specialty,String courseType,Model model) throws Exception{

        List<Schedule> list = courseService.findCourse(courseName,deptID,specialty,courseType);

        if (model.containsAttribute("addTCMsg")) {
            model.addAttribute("addTCMsg","添加成功");
        }
        model.addAttribute("courseList",list);

        return "admin/searchedCourse";
    }

    //添加教师课程UI
    @RequestMapping(value = "/addTeacherCourse",method = {RequestMethod.GET})
    public String addTeacherCourseUI(String courseID, String deptID,Integer page,Model model)throws Exception{
        if (courseID.equals("") || deptID.equals("")){
            return "admin/index";
        }
        List<TeacherExpand> list;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(courseService.getCountOfCourse());
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = teacherService.getAllTeacherOfDept(deptID,1);
            list = teacherCourseService.isTeachOneCourse(courseID,list);
        }
        else {
            pagingVO.setToPageNo(page);
            list = teacherService.getAllTeacherOfDept(deptID,page);
            list = teacherCourseService.isTeachOneCourse(courseID,list);
        }
        String courseName = courseService.getCourseNameById(courseID);

        model.addAttribute("pagingVO",pagingVO);
        model.addAttribute("teacherList",list);
        model.addAttribute("courseID",courseID);
        model.addAttribute("courseName",courseName);

        return "admin/addTeacherCourse";
    }
    //添加教师课程
    @RequestMapping(value = "/addTeacherCourse",method = {RequestMethod.POST})
    public String addTeacherCourse(@RequestBody JsonModel jsonModel, RedirectAttributes attr)throws Exception{
        if (jsonModel.getCourseID().equals("") || jsonModel.getTeacherIDList().size() == 0 || jsonModel.getDeptID().equals("")){
            return "admin/index";
        }
        teacherCourseService.addTeacherCourse(jsonModel.getCourseID(),jsonModel.getTeacherIDList());
        String courseName = courseService.getCourseNameById(jsonModel.getCourseID());
        attr.addFlashAttribute("courseName",courseName);
        attr.addFlashAttribute("addTCMsg","添加成功");
        return "redirect:/admin/searchCourse?deptID=" +jsonModel.getDeptID();
    }

    //删除教师课程
    @RequestMapping(value = "/deleteTeacherCourse")
    public String deleteTeacherCourse(String teacher_CourseID,RedirectAttributes attr)throws Exception{

        if (teacher_CourseID.equals("")){
            throw new CustomException("错误:未知教师课程信息");
        }
        Boolean result = teacherCourseService.deleteTeacherCourse(teacher_CourseID);
        if (!result){
            attr.addFlashAttribute("deleteMessage1","删除失败");
            return "redirect:/admin/index";
        }
        else {
            attr.addFlashAttribute("deleteMessage2","删除成功");
            return "redirect:/admin/index";
        }
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<院系操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>**/

    //查询所有院系
    @RequestMapping(value = "/searchAllDept",method = {RequestMethod.GET})
    public String searchAllDept(Model model)throws Exception{

        List<Dept> list = deptService.findAllDept();

        model.addAttribute("deptList",list);

        return "admin/index";
    }

    //查询所有院系、专业、班级信息
    @RequestMapping(value = "/getAllDSC",method = {RequestMethod.GET})
    public String getAllDSC(Model model)throws Exception{
        List<Dept> deptList = deptService.findAllDept();
        List<Specialty> specialtyList = deptService.findAllSpecialty();
        List<com.springmvc.po.Class> classList = deptService.findAllClass();

        int flag = 1;
        model.addAttribute("deptList",deptList);
        model.addAttribute("specialtyList",specialtyList);
        model.addAttribute("classList",classList);
        flag = 2;
        model.addAttribute("flag",flag);

        return "admin/addStudent";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<学生操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //学生信息显示
    @RequestMapping("/showStudent")
    public String showStudent(Model model,Integer page) throws Exception{
        List<StudentExpand> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(studentService.getCountOfStudent());
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = studentService.findByPaging(1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = studentService.findByPaging(page);
        }
        if(model.containsAttribute("deleteMessage")){
            model.addAttribute("deleteMessage","删除失败");
        }
        model.addAttribute("studentList",list);
        model.addAttribute("pagingVO",pagingVO);

        return "admin/showStudent";
    }

    //添加学生页面显示
    @RequestMapping(value = "/addStudent",method = {RequestMethod.GET})
    public String addStudentUI(Model model) throws Exception{

        List<Dept> deptList = deptService.findAllDept();
        List<Class> classList = deptService.findAllClass();
        List<Specialty> specialtyList = deptService.findAllSpecialty();

        model.addAttribute("deptList",deptList);
        model.addAttribute("classList",classList);
        model.addAttribute("specialtyList",specialtyList);
        return "admin/addStudent";
    }

    //添加学生
    @RequestMapping(value = "/addStudent",method = {RequestMethod.POST})
    public String addStudent(Student student,Login login,Model model,RedirectAttributes attr) throws Exception{
        //student中的accountNumber是外键 所以先增加login信息，再增加student信息
        if (student == null || login == null || student.getStudentID().equals("") || login.getAccountNumber().equals("") || login.getPassword().equals("")){
            throw new CustomException("添加失败");
        }
        Boolean result1 = loginService.addUser(login);
        Boolean result2 = studentService.addStudent(student);
        if (result1){
            if (!result2){
                model.addAttribute("message","学号重复！");
                loginService.deleteUser(login);   //删除已存入的账号信息
                return "error";
            }
            else {
                attr.addFlashAttribute("addMessage","添加成功");
            }
        }
        else{
            model.addAttribute("message","账号已存在！");
            return "error";
        }
        return "redirect:/admin/updateStudent?studentID="+student.getStudentID();
    }

    //删除学生
    @RequestMapping("/deleteStudent")
    public String deleteStudent(String studentID,Model model,RedirectAttributes attr) throws Exception{
        if (studentID.equals("")){
            return "admin/showStudent";
        }
        Boolean result = studentService.deleteStudent(studentID);
        if (!result){
            model.addAttribute("message","没有该学生！");
            return "error";
        }
        else {
            attr.addFlashAttribute("deleteMessage","删除成功");
        }
        return "redirect:/admin/showStudent";
    }

    //修改学生页面显示
    @RequestMapping(value = "/updateStudent",method = {RequestMethod.GET})
    public String updateStudentUI(String studentID, Model model) throws Exception{

        if (studentID.equals("")){
            return "redirect:/admin/showStudent";
        }
        Student student = studentService.findStudentByID(studentID);
        Login login = null;
        if (student == null){
            model.addAttribute("message","未找到该学生！");
            return "error";
        }
        else {
            login = loginService.findByAccount(student.getAccountNumber());
        }
        List<Dept> deptList = deptService.findAllDept();
        List<Class> classList = deptService.findAllClass();
        List<Specialty> specialtyList = deptService.findAllSpecialty();
        model.addAttribute("deptList",deptList);
        model.addAttribute("classList",classList);
        model.addAttribute("specialtyList",specialtyList);
        model.addAttribute("student",student);
        model.addAttribute("login",login);
        if (model.containsAttribute("msg")){
            model.addAttribute("updateMessage","更新成功");
        }
        if (model.containsAttribute("addMessage")){
            model.addAttribute("addMessage","添加成功");
        }
        return "admin/updateStudent";
    }

    //修改学生
    @RequestMapping(value = "/updateStudent",method = {RequestMethod.POST})
    public String updateStudent(Student student,Login login,RedirectAttributes attr) throws Exception{
        if (student.getStudentID().equals("")){
            return "admin/showStudent";
        }
        studentService.updateStudent(student);
        loginService.updateByAccount(student.getAccountNumber(),login);
        attr.addFlashAttribute("msg","更新成功");

        return "redirect:/admin/updateStudent?studentID="+student.getStudentID();
    }

    //搜索学生
    @RequestMapping(value = "/searchStudent",method = {RequestMethod.POST})
    public String searchStudent(String studentName,String deptID,String entranceYear,String specialtyID,String classID,Model model)throws Exception{

        List<StudentExpand> list;

        list = studentService.findStudentByName(studentName,deptID,entranceYear,specialtyID,classID);

        model.addAttribute("studentList",list);

        return "admin/showStudent";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<教师操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //教师信息显示
    @RequestMapping(value = "/showTeacher")
    public String showTeacher(Model model,Integer page)throws Exception{

        List<TeacherExpand> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(teacherService.getCountOfTeacher());
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = teacherService.findByPaging(1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = teacherService.findByPaging(page);
        }
        if (model.containsAttribute("deleteMessage")){
            model.addAttribute("deleteMessage","删除成功");
        }
        model.addAttribute("teacherList",list);
        model.addAttribute("pagingVO",pagingVO);

        return "admin/showTeacher";
    }

    //添加教师页面显示
    @RequestMapping(value = "/addTeacher",method = {RequestMethod.GET})
    public String addTeacherUI(Model model)throws Exception{

        List<Dept> deptList = deptService.findAllDept();
        model.addAttribute("deptList",deptList);

        return "admin/addTeacher";
    }

    //添加教师
    @RequestMapping(value = "/addTeacher",method = {RequestMethod.POST})
    public String addTeacher(Teacher teacher,Login login,Model model,RedirectAttributes attr)throws Exception{

        //teacher中的accountNumber是外键 所以先增加login信息，再增加teacher信息
        if (teacher == null || login == null || teacher.getTeacherID().equals("") || login.getAccountNumber().equals("") || login.getPassword().equals("")){
            throw new CustomException("添加失败");
        }
        Boolean result1 = loginService.addUser(login);
        Boolean result2 = teacherService.addTeacher(teacher);
        if (result1){
            if (!result2){
                model.addAttribute("message","教师编号重复！");
                loginService.deleteUser(login);   //删除已存入的账号信息
                return "error";
            }
            else {
                attr.addFlashAttribute("addMessage","添加成功");
            }
        }
        else{
            model.addAttribute("message","账号已存在！");
            return "error";
        }
        return "redirect:/admin/updateTeacher?teacherID="+teacher.getTeacherID();
    }

    //删除教师
    @RequestMapping(value = "/deleteTeacher")
    public String deleteTeacher(String teacherID,Model model,RedirectAttributes attr)throws Exception{

        if (teacherID.equals("")){
            return "admin/showTeacher";
        }
        Boolean result = teacherService.deleteTeacher(teacherID);
        if (!result){
            model.addAttribute("message","没有该教师！");
            return "error";
        }
        else {
            attr.addFlashAttribute("deleteMessage","删除成功");
        }
        return "redirect:/admin/showTeacher";
    }

    //修改教师信息页面显示
    @RequestMapping(value = "/updateTeacher",method = {RequestMethod.GET})
    public String updateTeacherUI(String teacherID,Model model)throws Exception{

        if (teacherID.equals("")){
            return "redirect:admin/showTeacher";
        }
        TeacherExpand teacherExpand = teacherService.findTeacherByID(teacherID);
        if (teacherExpand == null){
            throw new CustomException("未找到该教师！");
        }

        List<Dept> list = deptService.findAllDept();


        Login login = loginService.findByAccount(teacherExpand.getAccountNumber());
        model.addAttribute("deptList",list);
        model.addAttribute("teacher",teacherExpand);
        model.addAttribute("login",login);
        if (model.containsAttribute("updateMessage")){
            model.addAttribute("updateMessage","更新成功");
        }
        if (model.containsAttribute("addMessage")){
            model.addAttribute("addMessage","添加成功");
        }
        return "admin/updateTeacher";
    }

    //修改教师
    @RequestMapping(value = "/updateTeacher",method = {RequestMethod.POST})
    public String updateTeacher(Teacher teacher, Login login, RedirectAttributes attr)throws Exception{

        if (teacher.getTeacherID().equals("") || login.getAccountNumber().equals("") || login.getPassword().equals("")){
            return "admin/updateTeacher";
        }
        //First update login info(because there is a foreign key)
        loginService.updateByAccount(teacher.getAccountNumber(),login);
        //Then update teacher info
        teacherService.updateTeacher(teacher);

        attr.addFlashAttribute("updateMessage","更新成功");

        return "redirect:/admin/updateTeacher?teacherID="+teacher.getTeacherID();
    }

    //搜索教师
    @RequestMapping(value = "searchTeacher",method = {RequestMethod.POST})
    public String searchTeacher(String teacherName,String deptID,Model model)throws Exception{

        List<TeacherExpand> list;

        list = teacherService.findTeacherByName(teacherName,deptID);

        model.addAttribute("teacherList",list);

        return "admin/showTeacher";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<个人信息管理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>**/
    //修改密码
    @RequestMapping("/passwordReset")
    public String passwordReset() throws Exception{
        return "admin/passwordReset";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<其他系统设置>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //普通用户账号密码重置
    @RequestMapping("/userPasswordReset")
    public String userPasswordResetUI()throws Exception{

        return "admin/userPasswordReset";
    }

    //普通用户账号密码重置处理
    @RequestMapping(value = "/userPasswordReset",method = {RequestMethod.POST})
    public String userPasswordReset(String accountNumber,String newPassword,Model model)throws Exception{

        Login login = loginService.findByAccount(accountNumber);

        if (login != null){
            if (login.getRole().equals("0")){
                throw new CustomException("该账户为管理员账户，无法修改");
            }
            login.setPassword(newPassword);
            loginService.updateByAccount(login.getAccountNumber(),login);
            model.addAttribute("message","用户密码修改成功");
        }
        else {
            throw new CustomException("没有找到该用户");
        }

        return "admin/userPasswordReset";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<排课>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //Show curriculum
    @RequestMapping(value = "/curriculum")
    public String schedule(Model model)throws Exception{
        return "admin/curriculum";
    }

    //Search curriculum
    @RequestMapping(value = "/searchCurriculum",method = {RequestMethod.POST})
    public String searchSchedule(String specialty,String grade,String year,String half,Model model)throws Exception{
        if (specialty.equals("") || grade.equals("") || year.equals("") || half.equals("")){
            return "admin/curriculum";
        }
        String term = year+half;
        String specialty_YearID = grade+specialty;
        List<Timetable> timetableList = curriculumService.getCurriculum(specialty_YearID,term);
        List<CurriculumInfo> list = new ArrayList<>();
        if (timetableList.size() > 0){
            list = curriculumService.getDetailCurriculum(timetableList);
        }
        model.addAttribute("curriculumList",list);
        return "admin/curriculum";
    }

    //排课
    @RequestMapping(value = "/scheduleCourse")
    public String scheduleCourse()throws Exception{

        scheduleService.scheduleCourse();

        return "admin/curriculum";
    }
}
