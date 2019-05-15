package com.springmvc.mapper;

import com.springmvc.po.Term;
import com.springmvc.po.TermExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TermMapper {
    int countByExample(TermExample example);

    int deleteByExample(TermExample example);

    int deleteByPrimaryKey(String termID);

    int insert(Term record);

    int insertSelective(Term record);

    List<Term> selectByExample(TermExample example);

    Term selectByPrimaryKey(String termID);

    int updateByExampleSelective(@Param("record") Term record, @Param("example") TermExample example);

    int updateByExample(@Param("record") Term record, @Param("example") TermExample example);

    int updateByPrimaryKeySelective(Term record);

    int updateByPrimaryKey(Term record);
}