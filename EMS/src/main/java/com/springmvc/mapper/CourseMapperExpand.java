package com.springmvc.mapper;

import com.springmvc.po.Course;
import com.springmvc.po.CourseExample;
import com.springmvc.po.CourseExpand;
import com.springmvc.po.PagingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapperExpand {

    //分页查询课程信息
    List<CourseExpand> findCourseByPaging(PagingVO pagingVO);

    //分页查询课程信息
    List<CourseExpand> findDetailCourseByPaging(PagingVO pagingVO);

    //Get all course of one dept by paging
    List<CourseExpand> getCourseOfDeptByPaging(@Param("pagingVO")PagingVO pagingVO,@Param("deptID")String deptID);

    //分页查询搜索课程结果
    List<CourseExpand> findSearchCourseByPaging_Example(@Param("pagingVO") PagingVO pagingVO,@Param("example") CourseExample example);

    //Get detail course info by PrimaryKey
    CourseExpand getCourseExpandByPrimaryKey(String courseID);

    //Get all course of one dept by paging
    List<CourseExpand> getCourseOfSpecialtyByPaging(@Param("pagingVO")PagingVO pagingVO,@Param("specialty")String specialty);

}
