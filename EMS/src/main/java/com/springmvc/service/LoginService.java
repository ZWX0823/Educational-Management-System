package com.springmvc.service;

import com.springmvc.po.Login;

/**
 * 用户登录的业务逻辑层
 * @author ZWX
 */
public interface LoginService {

    //根据用户名(账号)查找用户
    Login findByAccount(String accountNumber) throws Exception;

    /**
     * 更新用户密码
     * @param accountNumber
     * @param login
     * @throws Exception
     */
    void updateByAccount(String accountNumber,Login login) throws Exception;

    //添加用户
    Boolean addUser(Login login)throws Exception;

    //删除用户
    Boolean deleteUser(Login login)throws Exception;
}
