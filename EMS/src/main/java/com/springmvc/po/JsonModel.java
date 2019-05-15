package com.springmvc.po;

import java.util.List;

public class JsonModel {
    private String courseID;
    private List<String> teacherIDList;
    private String deptID;

    private String specialty_Year;
    private List<String> courseIDList;
    private String time;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public List<String> getTeacherIDList() {
        return teacherIDList;
    }

    public void setTeacherIDList(List<String> teacherIDList) {
        this.teacherIDList = teacherIDList;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getSpecialty_Year() {
        return specialty_Year;
    }

    public void setSpecialty_Year(String specialty_Year) {
        this.specialty_Year = specialty_Year;
    }

    public List<String> getCourseIDList() {
        return courseIDList;
    }

    public void setCourseIDList(List<String> courseIDList) {
        this.courseIDList = courseIDList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
