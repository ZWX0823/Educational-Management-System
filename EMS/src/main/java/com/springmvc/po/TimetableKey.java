package com.springmvc.po;

public class TimetableKey {
    private String teacher_CourseID;

    private String specialty_Year;

    private String classroom;

    private String time;

    public String getTeacher_CourseID() {
        return teacher_CourseID;
    }

    public void setTeacher_CourseID(String teacher_CourseID) {
        this.teacher_CourseID = teacher_CourseID == null ? null : teacher_CourseID.trim();
    }

    public String getSpecialty_Year() {
        return specialty_Year;
    }

    public void setSpecialty_Year(String specialty_Year) {
        this.specialty_Year = specialty_Year == null ? null : specialty_Year.trim();
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}