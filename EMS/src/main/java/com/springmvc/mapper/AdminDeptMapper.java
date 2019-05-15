package com.springmvc.mapper;

import com.springmvc.po.AdminDept;
import com.springmvc.po.AdminDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminDeptMapper {
    int countByExample(AdminDeptExample example);

    int deleteByExample(AdminDeptExample example);

    int deleteByPrimaryKey(String adminID);

    int insert(AdminDept record);

    int insertSelective(AdminDept record);

    List<AdminDept> selectByExample(AdminDeptExample example);

    AdminDept selectByPrimaryKey(String adminID);

    int updateByExampleSelective(@Param("record") AdminDept record, @Param("example") AdminDeptExample example);

    int updateByExample(@Param("record") AdminDept record, @Param("example") AdminDeptExample example);

    int updateByPrimaryKeySelective(AdminDept record);

    int updateByPrimaryKey(AdminDept record);

    AdminDept selectByAccountNumber(String accountNumber);
}