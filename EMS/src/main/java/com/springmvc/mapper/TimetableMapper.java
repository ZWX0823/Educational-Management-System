package com.springmvc.mapper;

import com.springmvc.po.Timetable;
import com.springmvc.po.TimetableExample;
import com.springmvc.po.TimetableKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TimetableMapper {
    int countByExample(TimetableExample example);

    int deleteByExample(TimetableExample example);

    int deleteByPrimaryKey(TimetableKey key);

    int insert(Timetable record);

    int insertSelective(Timetable record);

    List<Timetable> selectByExample(TimetableExample example);

    Timetable selectByPrimaryKey(TimetableKey key);

    int updateByExampleSelective(@Param("record") Timetable record, @Param("example") TimetableExample example);

    int updateByExample(@Param("record") Timetable record, @Param("example") TimetableExample example);

    int updateByPrimaryKeySelective(Timetable record);

    int updateByPrimaryKey(Timetable record);
}