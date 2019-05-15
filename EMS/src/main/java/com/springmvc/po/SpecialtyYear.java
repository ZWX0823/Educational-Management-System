package com.springmvc.po;

import java.util.Date;

public class SpecialtyYear {
    private String specialty_YearID;

    private String specialty_YearName;

    private String specialty;

    private Integer number;

    private Date admissionDate;

    private String finish;

    private String dept;

    private Short classNumber;

    public String getSpecialty_YearID() {
        return specialty_YearID;
    }

    public void setSpecialty_YearID(String specialty_YearID) {
        this.specialty_YearID = specialty_YearID == null ? null : specialty_YearID.trim();
    }

    public String getSpecialty_YearName() {
        return specialty_YearName;
    }

    public void setSpecialty_YearName(String specialty_YearName) {
        this.specialty_YearName = specialty_YearName == null ? null : specialty_YearName.trim();
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

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish == null ? null : finish.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public Short getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Short classNumber) {
        this.classNumber = classNumber;
    }
}