package com.springmvc.po;

public class Time {
    private String timeID;

    private String timeName;

    public String getTimeID() {
        return timeID;
    }

    public void setTimeID(String timeID) {
        this.timeID = timeID == null ? null : timeID.trim();
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName == null ? null : timeName.trim();
    }
}