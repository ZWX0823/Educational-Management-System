package com.springmvc.mapper;

import com.springmvc.po.PagingVO;
import com.springmvc.po.TeacherCourse;
import com.springmvc.po.TeacherCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherCourseMapper {
    int countByExample(TeacherCourseExample example);

    int deleteByExample(TeacherCourseExample example);

    int deleteByPrimaryKey(String teacher_CourseID);

    int insert(TeacherCourse record);

    int insertSelective(TeacherCourse record);

    List<TeacherCourse> selectByExample(TeacherCourseExample example);

    TeacherCourse selectByPrimaryKey(String teacher_CourseID);

    int updateByExampleSelective(@Param("record") TeacherCourse record, @Param("example") TeacherCourseExample example);

    int updateByExample(@Param("record") TeacherCourse record, @Param("example") TeacherCourseExample example);

    int updateByPrimaryKeySelective(TeacherCourse record);

    int updateByPrimaryKey(TeacherCourse record);

    //Ger all teacherID
    List<String> getTeacherOfCourse(TeacherCourseExample example);

    //Get a teacher_CourseID
    String getTeacherCourseIDByExample(TeacherCourseExample example);

    //Get all courseID
    List<String> getCourseIDListByExample(@Param("pagingVO")PagingVO pagingVO,@Param("teacherID")String teacherID);

    //Get all teacher_CourseID
    List<String> getTeacherCourseIDListByExample(TeacherCourseExample example);
}