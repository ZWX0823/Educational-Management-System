package com.springmvc.po;

public class Specialty {
    private String specialtyID;

    private String dept;

    private String specialtyName;

    public String getSpecialtyID() {
        return specialtyID;
    }

    public void setSpecialtyID(String specialtyID) {
        this.specialtyID = specialtyID == null ? null : specialtyID.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName == null ? null : specialtyName.trim();
    }
}