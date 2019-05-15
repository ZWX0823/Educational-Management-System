package com.springmvc.po;

public class TeacherExpand extends Teacher {

    //院系名
    private String deptName;

    //Is or no teacher one course
    private Boolean isTeachOneCourse;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Boolean getTeachOneCourse() {
        return isTeachOneCourse;
    }

    public void setTeachOneCourse(Boolean teachOneCourse) {
        isTeachOneCourse = teachOneCourse;
    }
}
