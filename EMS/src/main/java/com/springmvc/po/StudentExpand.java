package com.springmvc.po;

import java.util.List;

public class StudentExpand extends Student {

    //班级名
    private String className;
    //专业名
    private String specialtyName;
    //所属院系名
    private String deptName;
    //选课列表
//    private List<StudentCourse> studentCourseList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

//    public List<StudentCourse> getStudentCourseList() {
////        return studentCourseList;
////    }
////
////    public void setStudentCourseList(List<StudentCourse> studentCourseList) {
////        this.studentCourseList = studentCourseList;
////    }
}
