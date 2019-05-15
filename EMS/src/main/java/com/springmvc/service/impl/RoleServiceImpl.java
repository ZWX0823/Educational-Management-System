package com.springmvc.service.impl;

import com.springmvc.mapper.RoleMapper;
import com.springmvc.po.Role;
import com.springmvc.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource(name = "roleMapper")
    private RoleMapper roleMapper;

    @Override
    public Role findByRoleId(String id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }
}
