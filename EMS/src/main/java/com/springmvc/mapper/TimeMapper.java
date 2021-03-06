package com.springmvc.mapper;

import com.springmvc.po.Time;
import com.springmvc.po.TimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TimeMapper {
    int countByExample(TimeExample example);

    int deleteByExample(TimeExample example);

    int deleteByPrimaryKey(String timeID);

    int insert(Time record);

    int insertSelective(Time record);

    List<Time> selectByExample(TimeExample example);

    Time selectByPrimaryKey(String timeID);

    int updateByExampleSelective(@Param("record") Time record, @Param("example") TimeExample example);

    int updateByExample(@Param("record") Time record, @Param("example") TimeExample example);

    int updateByPrimaryKeySelective(Time record);

    int updateByPrimaryKey(Time record);
}