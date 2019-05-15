package com.springmvc.mapper;

import com.springmvc.po.Teacher;
import com.springmvc.po.TeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    int countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(String teacherID);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByExample(TeacherExample example);

    Teacher selectByPrimaryKey(String teacherID);

    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
	
	Teacher selectByAccountNumber(String accountNumber);
	
	String getTeacherNameByPrimaryKey(String teacherID);

    List<String> getAllTeacherIDByDept(TeacherExample example);

    List<Teacher> getAllTeacherByDept(TeacherExample example);
}