package com.springmvc.service;

import com.springmvc.po.Admin;

public interface AdminService {

    //根据管理员账号查询管理员信息
    Admin findByAccountNumber(String accountNumber) throws Exception;
}
