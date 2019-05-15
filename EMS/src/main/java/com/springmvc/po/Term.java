package com.springmvc.po;

public class Term {
    private String termID;

    private String termName;

    public String getTermID() {
        return termID;
    }

    public void setTermID(String termID) {
        this.termID = termID == null ? null : termID.trim();
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName == null ? null : termName.trim();
    }
}