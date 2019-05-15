package com.springmvc.service;

import com.springmvc.po.*;
import com.springmvc.po.Class;


import java.util.List;

public interface DeptService {

    //查询所有的院系
    List<Dept> findAllDept() throws Exception;

    //查询所有的专业
    List<Specialty> findAllSpecialty() throws Exception;

    //查询所有的班级
    List<com.springmvc.po.Class> findAllClass() throws Exception;

    //根据院系ID查询所有的专业
    List<Specialty> findAllSpecialtyOfDept(String deptID) throws Exception;

    //根据院系ID 专业ID查询所有的班级
    List<com.springmvc.po.Class> findAllClassOfSpecialty(String specialtyID) throws Exception;

    //Get all simple course of dept
    List<CourseExpand> getCourseOfDeptByPaging(String deptID,Integer toPageNo)throws Exception;

    //Get all course of dept
    List<Schedule> findDetailCourseByPaging(String deptID,Integer toPageNo) throws Exception;

    //Get dept by id
    Dept getDeptById(String deptID)throws Exception;

    //Get the count of specialty_year of one dept
    int getCountOfSpecialtyYear(String deptID)throws Exception;

    //Get count of classes of one dept
    int getCountOfClassesOfDeot(String deptId)throws Exception;

    //Get all specialty_year of one dept
    List<SpecialtyYear> getAllSpecialtyYear(String deptID,Integer toPageNo)throws Exception;

    //Get the specialty_year by id
    SpecialtyYear getSpecialtyYearById(String specialty_YearID)throws Exception;

    //Get specialty_year by condition
    List<SpecialtyYear> getSpecialtyYear(String entranceYear,String specialty)throws Exception;

    //Add specialty_year
    void addSpecialty_Year(SpecialtyYear specialtyYear)throws Exception;

    //Update specialty_year
    void updateSpecialty_Year(SpecialtyYear specialtyYear)throws Exception;

    //Add class
    void addClass(String specialty_YearID,String classNumber)throws Exception;

    //Add specialty_year_course
    void addSpecialty_Year_Course(String specialty_Year,List<String> courseIDList,String time)throws Exception;

    //Get all classes of one dept
    List<ClassExpand> getClassesOfDept(String deptID,Integer toPageNo)throws Exception;

    //Get all students of one class
    List<StudentExpand> getStudentsOfClass(String classID,Integer toPageNo)throws Exception;

    //Get class by id
    Class getClassById(String classID)throws Exception;

    //Get class by condition
    List<ClassExpand> getClassOfSpecialty(String specialty_Year,Integer toPageNo)throws Exception;
}
