package com.springmvc.service;

import com.springmvc.po.*;

import java.util.List;

public interface CourseService {


    Boolean addCourse(CourseExpand courseExpand) throws Exception;

    //Delete course
    void deleteCourse(String courseID)throws Exception;

    //根据课程名关键字 院系ID模糊查询课程相关信息
    List<Schedule> findCourseByName_Dept(String courseName,String deptID) throws Exception;

    //根据课程名关键字模糊查询课程相关信息
    List<Schedule> findCourseByName(String courseName) throws Exception;

    //search course
    List<Schedule> findCourse(String courseName,String deptID,String specialty,String courseType)throws Exception;

    //根据教师_课程编号查询选课学生
    List<StudentCourseExpand> findByTeacher_CourseID(String teacher_CourseID) throws Exception;

    //查询课程数量
    int getCountOfCourse() throws Exception;

    //Get count of course of one dept
    int getCountOfCourseOfDept(String deptID)throws Exception;

    //Get count of course of one specialty
    int getCountOfCourseOfSpecialty(String specialty)throws Exception;

    //搜索课程结果数量
    int getCountOfSearchCourse(String courseName,String deptID)throws Exception;

    //课程分页显示（仅仅课程信息）
    List<CourseExpand> findSimpleCourseByPaging(Integer toPageNo)throws Exception;

    //课程分页显示（已安排课程）
    List<Schedule> findDetailCourseByPaging(Integer toPageNo) throws Exception;

    //分页显示搜索课程结果
    List<Schedule> findSearchCourseByPaging(String courseName,String deptID,Integer toPageNo)throws Exception;

    //得到学生成绩
    StudentCourseExpand getGradeOfOne (StudentCourseExpand studentCourseExpand) throws Exception;

    //打分
    void updateGradeOfOne (StudentCourse studentCourse) throws Exception;

    //根据课程号查询课程
    CourseExpand findCourseByID(String courseID)throws Exception;

    //更新课程
    void updateCourse(Course course)throws Exception;

    //Get courseName by courseID
    String getCourseNameById(String courseID)throws Exception;

    //Admin of dept search course by courseName and specialty
    List<Schedule> getCourseOfSpecialty_Year(String courseName,String deptID,String specialtyID,String year,String courseType)throws Exception;

    //Get all course of one specialty
    List<CourseExpand> getCourseOfSpecialty(String specialty,String specialty_Year,Integer toPageNo)throws Exception;

//    //Judge specialty_Year whether study one course
//    List<CourseExpand> isStudyOneCourse(String specialty_Year)throws Exception;
}
