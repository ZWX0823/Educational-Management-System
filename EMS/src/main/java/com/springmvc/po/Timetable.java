package com.springmvc.po;

public class Timetable extends TimetableKey {
    private String term;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }
}