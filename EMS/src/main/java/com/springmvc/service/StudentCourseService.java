package com.springmvc.service;

import com.springmvc.po.Student;
import com.springmvc.po.StudentCourseExpand;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface StudentCourseService {

    //查询某学生是否选了某门课
    Boolean IsSelectCourse(String studentID,String teacher_CourseID)throws Exception;

    //学生选课
    void selectCourse(String studentID,String teacher_CourseID)throws Exception;

    //查询已选课程
    List<StudentCourseExpand> getSelectedCourse(Student student)throws Exception;

    //退课
    void revokeCourse(String studentID,String teacher_CourseID)throws Exception;

    //分页查询已选课程
    List<StudentCourseExpand> findSelectedCourseByPaging(Integer toPageNo,String studentID)throws Exception;

    //分页查询已修课程
    List<StudentCourseExpand> findFinishedCourseByPaging(Integer toPageNo,String studentID)throws Exception;

    //已选课程数量
    int getCountOfSelectedCourse(String studentID)throws Exception;

    //已修课程数量
    int getCountOfFinishedCourse(String studentID)throws Exception;

    //Comment teacher_Course
    void commentTeacherCourse(String studentID,String teacher_CourseID,String comment)throws Exception;

    //Get the list of student_course by one teacher_courseID
    List<StudentCourseExpand> getStudentCourse(String teacher_CourseID, Integer toPageNo)throws Exception;
}
