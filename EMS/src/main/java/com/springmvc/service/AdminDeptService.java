package com.springmvc.service;

import com.springmvc.po.AdminDept;

public interface AdminDeptService {

    //Get admin of dept by accountNumber
    AdminDept findByAccountNumber(String accountNumber)throws Exception;
}
