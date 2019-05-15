package com.springmvc.po;

public class TeacherCourse {
    private String teacher_CourseID;

    private String teacherID;

    private String courseID;

    private Integer number;

    public String getTeacher_CourseID() {
        return teacher_CourseID;
    }

    public void setTeacher_CourseID(String teacher_CourseID) {
        this.teacher_CourseID = teacher_CourseID == null ? null : teacher_CourseID.trim();
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID == null ? null : teacherID.trim();
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID == null ? null : courseID.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}