package com.springmvc.mapper;

import com.springmvc.po.PagingVO;
import com.springmvc.po.StudentExpand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapperExpand {

    //分页查询学生信息
    List<StudentExpand> findByPaging(PagingVO pagingVO)throws Exception;

    //Get students'info of one dept by paging
    List<StudentExpand> getStudentOfDeptByPaging(@Param("deptID") String deptID,@Param("pagingVO") PagingVO pagingVO);

    //Get students'info of one class by paging
    List<StudentExpand> getStudentOfClassByPaging(@Param("classID") String classID,@Param("pagingVO") PagingVO pagingVO);

    //Get studentExpand by id
    StudentExpand getStudentExpandById(String studentID);
}
