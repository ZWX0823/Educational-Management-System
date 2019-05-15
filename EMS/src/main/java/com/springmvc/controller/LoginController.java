package com.springmvc.controller;


import com.springmvc.po.*;
import com.springmvc.service.AdminDeptService;
import com.springmvc.service.AdminService;
import com.springmvc.service.StudentService;
import com.springmvc.service.TeacherService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * 用户登陆
 * @author ZWX
 *
 */
@Controller
public class LoginController {

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;
    @Resource(name = "studentServiceImpl")
    private StudentService studentService;
    @Resource(name = "adminDeptServiceImpl")
    private AdminDeptService adminDeptService;

    //登录
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(HttpSession session,Login userLogin) throws Exception{


        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()){

            //shiro实现登录
            UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getAccountNumber(),userLogin.getPassword());

            try{
                subject.login(token);
            }catch (IncorrectCredentialsException ice){
                //捕获密码错误异常
                ice.printStackTrace();
            }catch (UnknownAccountException uae) {
                // 捕获未知用户名异常
                uae.printStackTrace();
            } catch (ExcessiveAttemptsException eae) {
                // 捕获错误登录过多的异常
                eae.printStackTrace();
            }
        }
        if (subject.hasRole("admin")){
            Admin admin = adminService.findByAccountNumber(userLogin.getAccountNumber());
            //在session中保存管理员身份信息
            session.setAttribute("username",admin.getAdminName());
            return "redirect:/admin/index";
        }else if (subject.hasRole("teacher")){
            Teacher teacher = teacherService.findByAccountNumber(userLogin.getAccountNumber());
            //在session中保存教师信息
            session.setAttribute("username",teacher.getTeacherName());
            return "redirect:/teacher/index";
        }else if (subject.hasRole("student")){
            Student student = studentService.findByAccountNumber(userLogin.getAccountNumber());
            //在session中保存学生信息
            session.setAttribute("username",student.getStudentName());
            return "redirect:/student/index";
        }else if (subject.hasRole("dept")){
            AdminDept adminDept = adminDeptService.findByAccountNumber(userLogin.getAccountNumber());
            //在session中保存院办管理员信息
            session.setAttribute("username",adminDept.getAdminName());
            return "redirect:/dept/index";
        }
        return "/login";
    }

}
