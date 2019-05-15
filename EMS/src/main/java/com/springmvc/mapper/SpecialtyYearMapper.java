package com.springmvc.mapper;

import com.springmvc.po.PagingVO;
import com.springmvc.po.SpecialtyYear;
import com.springmvc.po.SpecialtyYearExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecialtyYearMapper {
    int countByExample(SpecialtyYearExample example);

    int deleteByExample(SpecialtyYearExample example);

    int deleteByPrimaryKey(String specialty_YearID);

    int insert(SpecialtyYear record);

    int insertSelective(SpecialtyYear record);

    List<SpecialtyYear> selectByExample(SpecialtyYearExample example);

    SpecialtyYear selectByPrimaryKey(String specialty_YearID);

    int updateByExampleSelective(@Param("record") SpecialtyYear record, @Param("example") SpecialtyYearExample example);

    int updateByExample(@Param("record") SpecialtyYear record, @Param("example") SpecialtyYearExample example);

    int updateByPrimaryKeySelective(SpecialtyYear record);

    int updateByPrimaryKey(SpecialtyYear record);

    List<SpecialtyYear> selectByDept_Paging(@Param("deptID")String deptID, @Param("pagingVO") PagingVO pagingVO);
}