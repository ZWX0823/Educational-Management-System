package com.springmvc.service.impl;

import com.springmvc.mapper.AdminMapper;
import com.springmvc.po.Admin;
import com.springmvc.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource(name = "adminMapper")
    private AdminMapper adminMapper;

    @Override
    public Admin findByAccountNumber(String accountNumber) throws Exception {
        return adminMapper.selectByAccountNumber(accountNumber);
    }
}
