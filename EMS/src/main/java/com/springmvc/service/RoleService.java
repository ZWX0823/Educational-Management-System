package com.springmvc.service;

import com.springmvc.po.Role;

public interface RoleService {

    //查找用户角色
    Role findByRoleId(String id) throws Exception;
}
