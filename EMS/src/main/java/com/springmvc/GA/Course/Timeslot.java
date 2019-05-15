package com.springmvc.GA.Course;

/**
 * 时段
 */
public class Timeslot {

    private final String timeslotId;
    private final String timeslot;

    public Timeslot(String timeslotId,String timeslot){
        this.timeslotId = timeslotId;
        this.timeslot = timeslot;
    }

    public String getTimeslotId() {
        return timeslotId;
    }

    public String getTimeslot() {
        return timeslot;
    }
}
