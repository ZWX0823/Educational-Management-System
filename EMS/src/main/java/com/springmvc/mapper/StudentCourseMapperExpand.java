package com.springmvc.mapper;

import com.springmvc.po.PagingVO;
import com.springmvc.po.StudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCourseMapperExpand {

    //分页查询
    List<StudentCourse> findByPaging(@Param("pagingVO") PagingVO pagingVO,@Param("studentID")String studentID);

    //Get student_course by teacher_CourseID and paging
    List<StudentCourse> getStudentCourse(@Param("pagingVO") PagingVO pagingVO,@Param("teacher_CourseID")String teacher_CourseID);
}
