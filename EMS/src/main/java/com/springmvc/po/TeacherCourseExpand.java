package com.springmvc.po;

public class TeacherCourseExpand extends TeacherCourse {

    //扩展教师信息
    private TeacherExpand teacherExpand;
    //扩展课程信息
    private CourseExpand courseExpand;

    public TeacherExpand getTeacherExpand() {
        return teacherExpand;
    }

    public void setTeacherExpand(TeacherExpand teacherExpand) {
        this.teacherExpand = teacherExpand;
    }

    public CourseExpand getCourseExpand() {
        return courseExpand;
    }

    public void setCourseExpand(CourseExpand courseExpand) {
        this.courseExpand = courseExpand;
    }
}
