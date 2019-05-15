package com.springmvc.mapper;

import com.springmvc.po.SpecialtyYearCourse;
import com.springmvc.po.SpecialtyYearCourseExample;
import com.springmvc.po.SpecialtyYearCourseKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecialtyYearCourseMapper {
    int countByExample(SpecialtyYearCourseExample example);

    int deleteByExample(SpecialtyYearCourseExample example);

    int deleteByPrimaryKey(SpecialtyYearCourseKey key);

    int insert(SpecialtyYearCourse record);

    int insertSelective(SpecialtyYearCourse record);

    List<SpecialtyYearCourse> selectByExample(SpecialtyYearCourseExample example);

    SpecialtyYearCourse selectByPrimaryKey(SpecialtyYearCourseKey key);

    int updateByExampleSelective(@Param("record") SpecialtyYearCourse record, @Param("example") SpecialtyYearCourseExample example);

    int updateByExample(@Param("record") SpecialtyYearCourse record, @Param("example") SpecialtyYearCourseExample example);

    int updateByPrimaryKeySelective(SpecialtyYearCourse record);

    int updateByPrimaryKey(SpecialtyYearCourse record);

    List<String> getCourseIDListByExample(SpecialtyYearCourseExample example);
}