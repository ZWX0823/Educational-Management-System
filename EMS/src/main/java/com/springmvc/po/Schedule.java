package com.springmvc.po;

public class Schedule {

    //课程号
    private String courseID;
    //课程名
    private String courseName;
    //授课教师编号
    private String teacherID;
    //授课教师名
    private String teacherName;
    //所属院系名
    private String deptName;
    //专业名
    private String specialtyName;
    //上课时间
    private String courseTime;
    //上课地点
    private String classroom;
    //周数
    private Integer weeks;
    //课程类型
    private String courseType;
    //学分
    private Integer credit;
    //教师_课程编号
    private String teacher_CourseID;
    //选课人数
    private Integer number;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getTeacher_CourseID() {
        return teacher_CourseID;
    }

    public void setTeacher_CourseID(String teacher_CourseID) {
        this.teacher_CourseID = teacher_CourseID;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
