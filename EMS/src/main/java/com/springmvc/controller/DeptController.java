package com.springmvc.controller;

import com.springmvc.exception.CustomException;
import com.springmvc.po.*;
import com.springmvc.po.Class;
import com.springmvc.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "adminDeptServiceImpl")
    private AdminDeptService adminDeptService;

    @Resource(name = "deptServiceImpl")
    private DeptService deptService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "teacherCourseServiceImpl")
    private TeacherCourseService teacherCourseService;

    @Resource(name = "curriculumServiceImpl")
    private CurriculumService curriculumService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "loginServiceImpl")
    private LoginService loginService;

    @Resource(name = "studentCourseServiceImpl")
    private StudentCourseService studentCourseService;

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Course Manager>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //Show course
    @RequestMapping(value = "/index")
    public String showCourse(Model model, Integer page)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);

        List<CourseExpand> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(courseService.getCountOfCourseOfDept(adminDept.getDept()));
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = deptService.getCourseOfDeptByPaging(adminDept.getDept(),1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = deptService.getCourseOfDeptByPaging(adminDept.getDept(),page);
        }
        //List of specialty
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(adminDept.getDept());
        model.addAttribute("specialtyList",specialtyList);

        Dept dept = deptService.getDeptById(adminDept.getDept());
        model.addAttribute("dept",dept);

        if(model.containsAttribute("deleteCourse")){
            model.addAttribute("deleteMessage","删除成功");
        }
        if (model.containsAttribute("deleteMessage1")){
            model.addAttribute("deleteMessage","删除成功");
        }
        if (model.containsAttribute("deleteMessage2")){
            model.addAttribute("deleteMessage","删除失败");
        }
        //List of course
        model.addAttribute("courseList",list);
        model.addAttribute("pagingVO",pagingVO);

        return "dept/index";
    }

    //Add course UI
    @RequestMapping(value = "/addCourse",method = {RequestMethod.GET})
    public String addCourseUI(String deptID,String deptName,Model model) throws Exception{

        //Get all specialty of this dept
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(deptID);

        model.addAttribute("specialtyList",specialtyList);
        model.addAttribute("deptID",deptID);
        model.addAttribute("deptName",deptName);
        return "dept/addCourse";
    }

    //Add course
    @RequestMapping(value = "/addCourse",method = {RequestMethod.POST})
    public String addCourse(CourseExpand courseExpand,Model model) throws Exception{

        Boolean result = courseService.addCourse(courseExpand);
        if (!result){
            model.addAttribute("message","课程号重复");
            return "error";
        }
        return "redirect:/dept/index";
    }

    //Delete course
    @RequestMapping(value = "/deleteCourse")
    public String deleteCourse(String courseID,RedirectAttributes attr)throws Exception{
        if (courseID.equals("")){
            throw new Exception("错误:未知课程");
        }
        courseService.deleteCourse(courseID);
        attr.addFlashAttribute("deleteCourse","删除成功");
        return "redirect:/dept/index";
    }

    //Update course
    @RequestMapping(value = "/updateCourse",method = {RequestMethod.GET})
    public String updateCourseUI(String courseID,String deptID,Model model)throws Exception{
        if (courseID.equals("")){
            return "redirect:/dept/index";
        }
        CourseExpand courseExpand = courseService.findCourseByID(courseID);
        if (courseExpand == null){
            throw new CustomException("未找到该课程！");
        }
        //Get all specialty of this dept
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(deptID);

        model.addAttribute("specialtyList",specialtyList);
        model.addAttribute("course",courseExpand);
        model.addAttribute("deptID",deptID);
        if (model.containsAttribute("msg")){
            model.addAttribute("updateMessage","更新成功");
        }
        return "dept/updateCourse";
    }

    //Update course
    @RequestMapping(value = "/updateCourse",method = {RequestMethod.POST})
    public String updateCourse(Course course, RedirectAttributes attr)throws Exception{

        courseService.updateCourse(course);

        attr.addFlashAttribute("msg","更新成功");

        return "redirect:/dept/updateCourse?courseID="+course.getCourseID() + "&deptID=" +course.getDeptID();
    }

    //Add teacher_Course of this dept UI
    @RequestMapping(value = "/addTeacherCourse",method = {RequestMethod.GET})
    public String addTeacherCourseUI(String courseID, String deptID,Integer page,Model model)throws Exception{
        if (courseID.equals("") || deptID.equals("")){
            return "dept/index";
        }
        List<TeacherExpand> list;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(courseService.getCountOfCourseOfDept(deptID));
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

        return "dept/addTeacherCourse";
    }
    //Add teacher_Course
    @RequestMapping(value = "/addTeacherCourse",method = {RequestMethod.POST})
    public String addTeacherCourse(@RequestBody JsonModel jsonModel, RedirectAttributes attr)throws Exception{
        if (jsonModel.getCourseID().equals("") || jsonModel.getTeacherIDList().size() == 0 || jsonModel.getDeptID().equals("")){
            return "dept/index";
        }
        teacherCourseService.addTeacherCourse(jsonModel.getCourseID(),jsonModel.getTeacherIDList());
        String courseName = courseService.getCourseNameById(jsonModel.getCourseID());
        attr.addFlashAttribute("courseName",courseName);
        attr.addFlashAttribute("addTCMsg","添加成功");
        return "redirect:/dept/searchCourse?deptID=" +jsonModel.getDeptID();
    }

    //Delete teacher_Course
    @RequestMapping(value = "/deleteTeacherCourse")
    public String deleteTeacherCourse(String teacher_CourseID,RedirectAttributes attr)throws Exception{

        if (teacher_CourseID.equals("")){
            throw new CustomException("错误:未知教师课程信息");
        }
        Boolean result = teacherCourseService.deleteTeacherCourse(teacher_CourseID);
        if (!result){
            attr.addFlashAttribute("deleteMessage1","删除失败");
            return "redirect:/dept/index";
        }
        else {
            attr.addFlashAttribute("deleteMessage2","删除成功");
            return "redirect:/dept/index";
        }
    }

    //Search course
    @RequestMapping(value = "/searchCourse")
    public String searchCourse(@ModelAttribute(value = "courseName") String courseName, String deptID,String specialty,String year,String courseType,Model model) throws Exception{

        List<Schedule> list;

        if (deptID.equals("")){
            model.addAttribute("message","院系为空");
            return "error";
        }

        list = courseService.getCourseOfSpecialty_Year(courseName,deptID,specialty,year,courseType);

        if (model.containsAttribute("addTCMsg")) {
            model.addAttribute("addTCMsg","添加成功");
        }
        Dept dept = deptService.getDeptById(deptID);
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(deptID);
        model.addAttribute("courseList",list);
        model.addAttribute("dept",dept);
        model.addAttribute("specialtyList",specialtyList);

        return "dept/searchedCourse";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Teacher Management>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //Show teacher
    @RequestMapping(value = "/showTeacher")
    public String showTeacher(Model model,Integer page)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String)subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);

        List<TeacherExpand> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(teacherService.getCountOfTeacherOfDept(adminDept.getDept()));
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = teacherService.getAllTeacherOfDept(adminDept.getDept(),1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = teacherService.getAllTeacherOfDept(adminDept.getDept(),page);
        }
        model.addAttribute("teacherList",list);
        model.addAttribute("pagingVO",pagingVO);

        return "dept/showTeacher";
    }

    //Search teacher
    @RequestMapping(value = "/searchTeacher",method = {RequestMethod.POST})
    public String searchTeacher(String teacherName,Model model)throws Exception{

        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String)subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);
        List<TeacherExpand> list = teacherService.findTeacherByName(teacherName,adminDept.getDept());

        model.addAttribute("teacherList",list);

        return "dept/showTeacher";
    }

    //Search courses that taught by teacher
    @RequestMapping(value = "/searchTeacherCourse",method = {RequestMethod.GET})
    public String searchTeacherCourseUI(String teacherID,Model model,Integer page)throws Exception{

        Teacher teacher = teacherService.getTeacherById(teacherID);
        Dept dept = deptService.getDeptById(teacher.getDeptID());
        List<Schedule> teacherCourseList = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(teacherCourseService.getCountOfCoursesOfTeacher(teacherID));
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            teacherCourseList = teacherCourseService.findCourseOfTeacherByPaging(teacher,1);
        }
        else {
            pagingVO.setToPageNo(page);
            teacherCourseList = teacherCourseService.findCourseOfTeacherByPaging(teacher,page);
        }
        model.addAttribute("teacherCourseList",teacherCourseList);
        model.addAttribute("dept",dept);
        model.addAttribute("pagingVO",pagingVO);
        return "dept/teacherCourse";
    }

    //See student's comments for one teacher_course
    @RequestMapping(value = "/seeComments",method = {RequestMethod.GET})
    public String seeCommentsUI(String teacher_CourseID,Model model,Integer page)throws Exception{

        List<StudentCourseExpand> list;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(teacherCourseService.getCountOfTeacherCourse(teacher_CourseID));
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = studentCourseService.getStudentCourse(teacher_CourseID,1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = studentCourseService.getStudentCourse(teacher_CourseID,page);
        }
        model.addAttribute("studentCourseList",list);
        model.addAttribute("pagingVO",pagingVO);

        return "dept/comments";
    }

    //Comment teacher
    @RequestMapping(value = "/comment")
    public String comment( String teacherID, String comment,Model model)throws Exception{
        if (teacherID.equals("")){
            model.addAttribute("message","教师编号传入空值");
            return "error";
        }
        teacherService.commentTeacher(teacherID,comment);

        return "redirect:/dept/showTeacher";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Student Management>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/

    //Show students of this dept
    @RequestMapping(value = "/showStudent")
    public String showStudent(Model model,Integer page)throws Exception{
        //Get adminDept object
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);

        List<StudentExpand> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(studentService.getCountOfStudentOfDept(adminDept.getDept()));
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = studentService.getStudentOfDeptByPaging(adminDept.getDept(),1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = studentService.getStudentOfDeptByPaging(adminDept.getDept(),page);
        }
        if(model.containsAttribute("deleteMessage")){
            model.addAttribute("deleteMessage","删除失败");
        }
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(adminDept.getDept());
        model.addAttribute("specialtyList",specialtyList);
        model.addAttribute("studentList",list);
        model.addAttribute("pagingVO",pagingVO);

        return "dept/showStudent";
    }

    //Add student UI
    @RequestMapping(value = "/addStudent",method = {RequestMethod.GET})
    public String addStudentUI(Model model) throws Exception{

        Subject subject = SecurityUtils.getSubject();
        String accountNumber =(String) subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);
        Dept dept = deptService.getDeptById(adminDept.getDept());
        List<Class> classList = deptService.findAllClass();
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(adminDept.getDept());

        model.addAttribute("dept",dept);
        model.addAttribute("classList",classList);
        model.addAttribute("specialtyList",specialtyList);
        return "dept/addStudent";
    }

    //Add student
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
        return "redirect:/dept/updateStudent?studentID="+student.getStudentID();
    }

    //Delete student
    @RequestMapping("/deleteStudent")
    public String deleteStudent(String studentID,Model model,RedirectAttributes attr) throws Exception{
        if (studentID.equals("")){
            return "dept/showStudent";
        }
        Boolean result = studentService.deleteStudent(studentID);
        if (!result){
            model.addAttribute("message","没有该学生！");
            return "error";
        }
        else {
            attr.addFlashAttribute("deleteMessage","删除成功");
        }
        return "redirect:/dept/showStudent";
    }

    //Update student UI
    @RequestMapping(value = "/updateStudent",method = {RequestMethod.GET})
    public String updateStudentUI(String studentID,String deptID, Model model) throws Exception{

        if (studentID.equals("")){
            return "redirect:/dept/showStudent";
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
        Dept dept = deptService.getDeptById(deptID);
        List<Class> classList = deptService.findAllClass();
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(deptID);
        model.addAttribute("dept",dept);
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
        return "dept/updateStudent";
    }

    //Update student
    @RequestMapping(value = "/updateStudent",method = {RequestMethod.POST})
    public String updateStudent(Student student,Login login,RedirectAttributes attr) throws Exception{
        if (student.getStudentID().equals("")){
            return "dept/showStudent";
        }
        studentService.updateStudent(student);
        loginService.updateByAccount(student.getAccountNumber(),login);
        attr.addFlashAttribute("msg","更新成功");

        return "redirect:/dept/updateStudent?studentID="+student.getStudentID();
    }

    //Search student
    @RequestMapping(value = "/searchStudent",method = {RequestMethod.POST})
    public String searchStudent(String studentName,String entranceYear,String specialty,String classID,Model model)throws Exception{

        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String)subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);
        List<StudentExpand> list;

        list = studentService.findStudentByName(studentName,adminDept.getDept(),entranceYear,specialty,classID);

        model.addAttribute("studentList",list);

        return "dept/showStudent";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Specialty>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/
    //Show specialty
    @RequestMapping(value = "/specialty")
    public String showSpecialty(Model model,Integer page)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);

        List<SpecialtyYear> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(deptService.getCountOfSpecialtyYear(adminDept.getDept()));
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = deptService.getAllSpecialtyYear(adminDept.getDept(),1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = deptService.getAllSpecialtyYear(adminDept.getDept(),page);
        }
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(adminDept.getDept());
        if (model.containsAttribute("addSpecialtyYearMessage")){
            model.addAttribute("addSpecialtyYearMessage","添加成功");
        }
        if (model.containsAttribute("updateSpecialtyYearMessage")){
            model.addAttribute("updateSpecialtyYearMessage","修改成功");
        }
        model.addAttribute("specialtyYearList",list);
        model.addAttribute("specialtyList",specialtyList);
        model.addAttribute("pagingVO",pagingVO);
        return "dept/specialty";
    }

    //Add specialty_yearUI
    @RequestMapping(value = "/addSpecialty_Year",method = {RequestMethod.GET})
    public String addSpecialty_YearUI(Model model) throws Exception{

        Subject subject = SecurityUtils.getSubject();
        String accountNumber =(String) subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);

        Dept dept = deptService.getDeptById(adminDept.getDept());
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(adminDept.getDept());

        model.addAttribute("dept",dept);
        model.addAttribute("specialtyList",specialtyList);
        return "dept/addSpecialty_Year";
    }

    //Add addSpecialty_Year
    @RequestMapping(value = "/addSpecialty_Year",method = {RequestMethod.POST})
    public String addSpecialty_Year(SpecialtyYear specialtyYear,RedirectAttributes attr) throws Exception{

        deptService.addSpecialty_Year(specialtyYear);
        attr.addFlashAttribute("addSpecialtyYearMessage","添加成功");

        return "redirect:/dept/specialty";
    }

    //Update Specialty_YearUI
    @RequestMapping(value = "/updateSpecialty_Year",method = {RequestMethod.GET})
    public String updateSpecialty_YearUI(String specialty_YearID,Model model) throws Exception{

        Subject subject = SecurityUtils.getSubject();
        String accountNumber =(String) subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);

        SpecialtyYear specialtyYear = deptService.getSpecialtyYearById(specialty_YearID);
        Dept dept = deptService.getDeptById(adminDept.getDept());
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(adminDept.getDept());
        model.addAttribute("specialtyYear",specialtyYear);
        model.addAttribute("dept",dept);
        model.addAttribute("specialtyList",specialtyList);

        return "dept/updateSpecialty_Year";
    }

    //Update Specialty_Year
    @RequestMapping(value = "/updateSpecialty_Year",method = {RequestMethod.POST})
    public String updateSpecialty_Year(SpecialtyYear specialtyYear,RedirectAttributes attr) throws Exception{

        deptService.updateSpecialty_Year(specialtyYear);
        attr.addFlashAttribute("updateSpecialtyYearMessage","修改成功");

        return "redirect:/dept/specialty";
    }

    //Search specialty_year
    @RequestMapping(value = "/searchSpecialtyYear",method = {RequestMethod.POST})
    public String searchSpecialtyYear(@ModelAttribute(value = "year") String entranceYear,@ModelAttribute(value = "specialty") String specialty,Model model)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);

        List<SpecialtyYear> list = new ArrayList<>();
        if (!entranceYear.equals("") && !specialty.equals("")){
            String specialty_YearID = entranceYear + specialty;
            SpecialtyYear specialtyYear = deptService.getSpecialtyYearById(specialty_YearID);
            list.add(specialtyYear);
        }
        else {
            list = deptService.getSpecialtyYear(entranceYear,specialty);
        }
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(adminDept.getDept());
        model.addAttribute("specialtyYearList",list);
        model.addAttribute("specialtyList",specialtyList);
        return "dept/specialty";
    }

    //Add class number
    @RequestMapping(value = "/addClass")
    public String addClass(String specialty_YearID,String classNumber,Model model)throws Exception{

        if (!specialty_YearID.equals("") && !classNumber.equals("")){
            deptService.addClass(specialty_YearID,classNumber);
        }
        else {
            model.addAttribute("message","传入空值参数");
            return "error";
        }
        return "redirect:/dept/specialty";
    }

    //Set specialty_Year finished
    @RequestMapping(value = "/setFinished")
    public String setFinished(String specialty_YearID,String finish)throws Exception{
        SpecialtyYear specialtyYear = deptService.getSpecialtyYearById(specialty_YearID);
        if (finish.equals("1")){
            specialtyYear.setFinish("1");
        }
        else if (finish.equals("0")){
            specialtyYear.setFinish("0");
        }
        deptService.updateSpecialty_Year(specialtyYear);
        return "redirect:/dept/specialty";
    }

    //Arranging courses UI
    @RequestMapping(value = "/arrangeCourses",method = {RequestMethod.GET})
    public String arrangeCoursesUI(String specialty_YearID,String specialty,Integer page,Model model)throws Exception{

        if (specialty_YearID.equals("") || specialty.equals("")){
            model.addAttribute("message","传入年级专业值或专业值为空");
            return "error";
        }
        List<CourseExpand> list;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(courseService.getCountOfCourseOfSpecialty(specialty));
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = courseService.getCourseOfSpecialty(specialty,specialty_YearID,1);
        }
        else {
            pagingVO.setToPageNo(page);
            list = courseService.getCourseOfSpecialty(specialty,specialty_YearID,page);
        }

        SpecialtyYear specialtyYear = deptService.getSpecialtyYearById(specialty_YearID);
        String specialty_YearName = specialtyYear.getSpecialty_YearName();

        model.addAttribute("specialty_YearName",specialty_YearName);
        model.addAttribute("specialty_Year",specialty_YearID);
        model.addAttribute("pagingVO",pagingVO);
        model.addAttribute("courseList",list);

        return "dept/arrangeCourses";
    }

    //Arrange courses
    @RequestMapping(value = "/arrangeCourses",method = {RequestMethod.POST})
    public  String arrangeCourses(@RequestBody JsonModel jsonModel,RedirectAttributes attr)throws Exception{
        if (jsonModel.getSpecialty_Year().equals("") || jsonModel.getCourseIDList().size() == 0 || jsonModel.getTime().equals("")){
            return "dept/specialty";
        }
        deptService.addSpecialty_Year_Course(jsonModel.getSpecialty_Year(),jsonModel.getCourseIDList(),jsonModel.getTime());

        String year = jsonModel.getSpecialty_Year().substring(0,4);
        String specialty = jsonModel.getSpecialty_Year().substring(4,8);
        attr.addFlashAttribute("year",year);
        attr.addFlashAttribute("specialty",specialty);
        return "redirect:/dept/searchSpecialtyYear";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Classes>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/
    //Show classes
    @RequestMapping(value = "/classes")
    public String showClasses(Integer page,Model model)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String)subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);

        List<ClassExpand> classes;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(deptService.getCountOfClassesOfDeot(adminDept.getDept()));
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            classes = deptService.getClassesOfDept(adminDept.getDept(),1);
        }
        else {
            pagingVO.setToPageNo(page);
            classes = deptService.getClassesOfDept(adminDept.getDept(),page);
        }
        List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(adminDept.getDept());
        model.addAttribute("specialtyList",specialtyList);
        model.addAttribute("classes",classes);
        model.addAttribute("pagingVO",pagingVO);

        return "dept/classes";
    }

    //Get detail info of class
    @RequestMapping("/getDetailInfoOfClass")
    public String getDetailInfoOfClass(String classId,Integer page, Model model)throws Exception{

        List<StudentExpand> studentList;
        Class c = deptService.getClassById(classId);
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalPageCount(c.getNumber());
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            studentList = deptService.getStudentsOfClass(classId,1);
        }
        else {
            pagingVO.setToPageNo(page);
            studentList = deptService.getStudentsOfClass(classId,page);
        }

        model.addAttribute("studentList",studentList);
        model.addAttribute("pagingVO",pagingVO);

        return "dept/students";
    }

    //Search class
    @RequestMapping(value = "searchClasses",method = {RequestMethod.POST})
    public String searchClasses(String entranceYear,String specialty,Integer page,Model model)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(accountNumber);

        List<ClassExpand> list = new ArrayList<>();
        if (!entranceYear.equals("") && !specialty.equals("")){
            String specialty_YearID = entranceYear + specialty;

            SpecialtyYear specialtyYear = deptService.getSpecialtyYearById(specialty_YearID);
            //页码对象
            PagingVO pagingVO = new PagingVO();
            //设置总页数
            pagingVO.setTotalPageCount(specialtyYear.getClassNumber());
            if (page == null || page == 0){
                pagingVO.setToPageNo(1);
                list = deptService.getClassOfSpecialty(specialty_YearID,1);
            }
            else {
                pagingVO.setToPageNo(page);
                list = deptService.getClassOfSpecialty(specialty_YearID,page);
            }
            List<Specialty> specialtyList = deptService.findAllSpecialtyOfDept(adminDept.getDept());
            model.addAttribute("classes",list);
            return "dept/classes";
        }
        model.addAttribute("message","年级或专业不能为空");
        return "error";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Curriculum>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/
    //Show curriculum
    @RequestMapping(value = "/curriculum")
    public String showCurriculum(Model model)throws Exception{
        return "dept/curriculum";
    }

    //Search curriculum
    @RequestMapping(value = "/searchCurriculum",method = {RequestMethod.POST})
    public String searchSchedule(String specialty,String grade,String year,String half,Model model)throws Exception{
        if (specialty.equals("") || grade.equals("") || year.equals("") || half.equals("")){
            return "dept/curriculum";
        }
        String term = year+half;
        String specialty_YearID = grade+specialty;
        List<Timetable> timetableList = curriculumService.getCurriculum(specialty_YearID,term);
        List<CurriculumInfo> list = new ArrayList<>();
        if (timetableList.size() > 0){
            list = curriculumService.getDetailCurriculum(timetableList);
        }
        model.addAttribute("curriculumList",list);
        return "dept/curriculum";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Reset user password>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/
    //Reset student's password of this dept UI
    @RequestMapping("/userPasswordReset")
    public String userPasswordResetUI()throws Exception{
        return "dept/userPasswordReset";
    }

    //Reset student's password of this dept
    @RequestMapping(value = "/userPasswordReset",method = {RequestMethod.POST})
    public String userPasswordReset(String accountNumber,String newPassword,Model model)throws Exception{

        Subject subject = SecurityUtils.getSubject();
        String a = (String)subject.getPrincipal();
        AdminDept adminDept = adminDeptService.findByAccountNumber(a);

        Login login = loginService.findByAccount(accountNumber);
        Student student = studentService.findByAccountNumber(accountNumber);
        Teacher teacher = teacherService.findByAccountNumber(accountNumber);

        if (login.getRole().equals("0") || login.getRole().equals("3")){
            throw new CustomException("该账户为管理员账户，无法修改");
        }
        if ( student !=null && teacher == null){
            if ( !student.getDept().equals(adminDept.getDept())){
                throw new CustomException("您无权修改该用户密码");
            }
            login.setPassword(newPassword);
            loginService.updateByAccount(login.getAccountNumber(),login);
            model.addAttribute("message","用户密码修改成功");
        }
        else if (student == null && teacher != null){
            if ( !teacher.getDeptID().equals(adminDept.getDept())){
                throw new CustomException("您无权修改该用户密码");
            }
            login.setPassword(newPassword);
            loginService.updateByAccount(login.getAccountNumber(),login);
            model.addAttribute("message","用户密码修改成功");
        }
        else {
            throw new CustomException("没有找到该用户");
        }

        return "dept/userPasswordReset";
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Reset personal password>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>></>*/
    //Reset personal password
    @RequestMapping("/passwordReset")
    public String passwordReset() throws Exception{
        return "dept/passwordReset";
    }

}
