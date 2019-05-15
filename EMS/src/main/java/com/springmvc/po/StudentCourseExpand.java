package com.springmvc.po;

public class StudentCourseExpand extends StudentCourse {

    //新增Student对象字段
    private StudentExpand studentExpand;
    //扩展教师课程信息对象
    private TeacherCourseExpand teacherCourseExpand;
    //课程名称
    private String courseName;
    //判断该学生是否已经完成该课程
    private Boolean over = false;
    //上课时间
    private String courseTime;
    //上课地点
    private String classroom;

    public StudentExpand getStudentExpand() {
        return studentExpand;
    }

    public void setStudentExpand(StudentExpand studentExpand) {
        this.studentExpand = studentExpand;
    }

    public TeacherCourseExpand getTeacherCourseExpand() {
        return teacherCourseExpand;
    }

    public void setTeacherCourseExpand(TeacherCourseExpand teacherCourseExpand) {
        this.teacherCourseExpand = teacherCourseExpand;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
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
}
