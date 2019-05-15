package com.springmvc.exception;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * springmvc提供一个HandlerExceptionResolver接口
 * 只要实现该接口，并配置到spring 容器里，该类就能成为默认全局异常处理类
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView modelAndView = new ModelAndView();

        CustomException customException;
        if (e instanceof CustomException){
            customException = (CustomException)e;
        }else if (e instanceof UnknownAccountException){
            //用户名错误异常
            modelAndView.addObject("message","没有该用户");
            modelAndView.setViewName("error");
            return modelAndView;
        }else if (e instanceof IncorrectCredentialsException){
            //密码错误异常
            modelAndView.addObject("message","密码错误");
            modelAndView.setViewName("error");
            return modelAndView;
        }else if (e instanceof ExcessiveAttemptsException){
            //登录失败次数过多
            modelAndView.addObject("message","登录失败次数过多");
            modelAndView.setViewName("error");
            return modelAndView;
        }else {
            customException = new CustomException("未知错误");
        }

        //错误信息
        String message = customException.getMessage();



        //错误信息传递和错误页面跳转
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
