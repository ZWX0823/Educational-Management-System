package com.springmvc.po;

public class SpecialtyYearCourse extends SpecialtyYearCourseKey {
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}