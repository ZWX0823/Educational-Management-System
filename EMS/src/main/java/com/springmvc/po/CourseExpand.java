package com.springmvc.po;

import java.util.Date;

public class CourseExpand extends Course {

    //所属院系名
    private String deptName;

    //SpecialtyName
    private String specialtyName;

    public Boolean getStudiedByOneSpecialty() {
        return isStudiedByOneSpecialty;
    }

    public void setStudiedByOneSpecialty(Boolean studiedByOneSpecialty) {
        isStudiedByOneSpecialty = studiedByOneSpecialty;
    }

    private Boolean isStudiedByOneSpecialty;

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
}
