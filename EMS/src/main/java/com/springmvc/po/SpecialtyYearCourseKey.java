package com.springmvc.po;

public class SpecialtyYearCourseKey {
    private String specialty_YearID;

    private String courseID;

    public String getSpecialty_YearID() {
        return specialty_YearID;
    }

    public void setSpecialty_YearID(String specialty_YearID) {
        this.specialty_YearID = specialty_YearID == null ? null : specialty_YearID.trim();
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID == null ? null : courseID.trim();
    }
}