package com.springmvc.service.GA;

import com.springmvc.GA.Course.Class;
import com.springmvc.GA.Course.Timetable;

public interface ScheduleService {

    //Initialize timetable
    Timetable initializeTimetable()throws Exception;

    //Course scheduling information is stored in database
    void storeScheduleInfo(Timetable timetable)throws Exception;

    //Schedule Course
    void scheduleCourse()throws Exception;
}
