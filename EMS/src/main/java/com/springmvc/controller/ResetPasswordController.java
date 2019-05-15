package com.springmvc.controller;

import com.springmvc.exception.CustomException;
import com.springmvc.po.Login;
import com.springmvc.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/password")
public class ResetPasswordController {

    @Resource(name = "loginServiceImpl")
    private LoginService loginService;

    //账户密码重置
    @RequestMapping(value = "/passwordReset",method = {RequestMethod.POST})
    public ModelAndView passwordReset(String oldPassword, String newPassword) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String accountNumber = (String) subject.getPrincipal();

        Login login = loginService.findByAccount(accountNumber);
        ModelAndView modelAndView = new ModelAndView();
        int flag;
        if (login.getRole().equals("0")){
            if (!oldPassword.equals(login.getPassword())){
                modelAndView.setViewName("admin/passwordReset");
                modelAndView.addObject("msg","旧密码不正确");
                flag = 1;
                modelAndView.addObject("flag",flag);
            }
            else {
                login.setPassword(newPassword);
                loginService.updateByAccount(accountNumber,login);
                flag = 2;
                modelAndView.setViewName("admin/passwordReset");
                modelAndView.addObject("msg","密码修改成功,请重新登录");
                modelAndView.addObject("flag",flag);

            }
        }
        else if (login.getRole().equals("1")){
            if (!oldPassword.equals(login.getPassword())){
                modelAndView.setViewName("teacher/passwordReset");
                modelAndView.addObject("msg","旧密码不正确");
                flag = 1;
                modelAndView.addObject("flag",flag);
            }
            else {
                login.setPassword(newPassword);
                loginService.updateByAccount(accountNumber,login);
                flag = 2;
                modelAndView.setViewName("teacher/passwordReset");
                modelAndView.addObject("msg","密码修改成功,请重新登录");
                modelAndView.addObject("flag",flag);

            }
        }
        else if (login.getRole().equals("2")){
            if (!oldPassword.equals(login.getPassword())){
                modelAndView.setViewName("student/passwordReset");
                modelAndView.addObject("msg","旧密码不正确");
                flag = 1;
                modelAndView.addObject("flag",flag);
            }
            else {
                login.setPassword(newPassword);
                loginService.updateByAccount(accountNumber,login);
                flag = 2;
                modelAndView.setViewName("student/passwordReset");
                modelAndView.addObject("msg","密码修改成功,请重新登录");
                modelAndView.addObject("flag",flag);

            }
        }
        else if (login.getRole().equals("3")){
            if (!oldPassword.equals(login.getPassword())){
                modelAndView.setViewName("dept/passwordReset");
                modelAndView.addObject("msg","旧密码不正确");
                flag = 1;
                modelAndView.addObject("flag",flag);
            }
            else {
                login.setPassword(newPassword);
                loginService.updateByAccount(accountNumber,login);
                flag = 2;
                modelAndView.setViewName("dept/passwordReset");
                modelAndView.addObject("msg","密码修改成功,请重新登录");
                modelAndView.addObject("flag",flag);

            }
        }
        return modelAndView;
    }
}
