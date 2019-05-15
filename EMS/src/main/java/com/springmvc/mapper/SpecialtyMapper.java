package com.springmvc.mapper;

import com.springmvc.po.Specialty;
import com.springmvc.po.SpecialtyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecialtyMapper {
    int countByExample(SpecialtyExample example);

    int deleteByExample(SpecialtyExample example);

    int deleteByPrimaryKey(String specialtyID);

    int insert(Specialty record);

    int insertSelective(Specialty record);

    List<Specialty> selectByExample(SpecialtyExample example);

    Specialty selectByPrimaryKey(String specialtyID);

    int updateByExampleSelective(@Param("record") Specialty record, @Param("example") SpecialtyExample example);

    int updateByExample(@Param("record") Specialty record, @Param("example") SpecialtyExample example);

    int updateByPrimaryKeySelective(Specialty record);

    int updateByPrimaryKey(Specialty record);

    String getSpecialtyName(String specialtyID);
}