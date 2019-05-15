package com.springmvc.service;

import com.springmvc.po.Schedule;
import com.springmvc.po.Teacher;
import com.springmvc.po.TeacherExpand;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import java.util.List;

public interface TeacherCourseService {

    //Add new teacherCourse info(s)
    void addTeacherCourse(String courseID, List<String> teacherIDList)throws Exception;

    //Delete one teacherCourse
    Boolean deleteTeacherCourse(String teacher_CourseID)throws Exception;

    //Judge whether the teacher has taught this course
    List<TeacherExpand> isTeachOneCourse(String courseID, List<TeacherExpand> teacherExpandList)throws Exception;

    //Search course of mine
    List<Schedule> findCourseOfTeacher(String courseName, String teacherID) throws Exception;

    //Show course of mine by paging
    List<Schedule> findCourseOfTeacherByPaging(Teacher teacher, Integer toPageNo) throws Exception;

    //Get count of courses that been taught by one teacher
    int getCountOfCoursesOfTeacher(String teacherID)throws Exception;

    //Get count of teacher_course
    int getCountOfTeacherCourse(String teacher_CourseID)throws Exception;
}
