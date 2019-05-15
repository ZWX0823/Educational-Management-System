package com.springmvc.service.impl;

import com.springmvc.mapper.AdminDeptMapper;
import com.springmvc.po.AdminDept;
import com.springmvc.service.AdminDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminDeptServiceImpl implements AdminDeptService {

    @Resource(name = "adminDeptMapper")
    private AdminDeptMapper adminDeptMapper;

    @Override
    public AdminDept findByAccountNumber(String accountNumber) throws Exception {
        return adminDeptMapper.selectByAccountNumber(accountNumber);
    }
}
