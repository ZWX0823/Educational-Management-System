package com.springmvc.po;

import java.util.Date;

public class Class {
    private String classID;

    private String className;

    private String specialty;

    private Integer number;

    private Date admissionDate;

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID == null ? null : classID.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }
}