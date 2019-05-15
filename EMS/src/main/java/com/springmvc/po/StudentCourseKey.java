package com.springmvc.po;

public class StudentCourseKey {
    private String studentID;

    private String teacher_CourseID;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID == null ? null : studentID.trim();
    }

    public String getTeacher_CourseID() {
        return teacher_CourseID;
    }

    public void setTeacher_CourseID(String teacher_CourseID) {
        this.teacher_CourseID = teacher_CourseID == null ? null : teacher_CourseID.trim();
    }
}