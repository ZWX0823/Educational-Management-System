package com.springmvc.mapper;

import com.springmvc.po.PagingVO;
import com.springmvc.po.TeacherExpand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapperExpand {

    //分页查询教师信息
    List<TeacherExpand> findByPaging(PagingVO pagingVO)throws Exception;

    //Select teacher by condition and paging
    List<TeacherExpand> getTeacherOfDeptByPaging(@Param("pagingVO")PagingVO pagingVO,@Param("deptID") String deptID);
}
