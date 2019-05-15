package com.springmvc.util;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeCompare {

    private int year;
    private int month;
    private int day;

    /**
     * 计算2个日期之间相差的  相差多少年
     * 比如：2011-02-02 到  2017-03-02 相差 6.1年
     * @param fromDate
     * @param toDate
     * @return
     */
    public static Double timeCompare(Calendar fromDate,Calendar toDate){

        int fromYear = fromDate.get(Calendar.YEAR);
        int fromMonth = fromDate.get(Calendar.MONTH);
        int fromDay = fromDate.get(Calendar.DAY_OF_MONTH);

        int toYear = toDate.get(Calendar.YEAR);
        int toMonth = toDate.get(Calendar.MONTH);
        int toDay = toDate.get(Calendar.DAY_OF_MONTH);
        int year = toYear  -  fromYear;
        int month = toMonth  - fromMonth;
        int day = toDay  - fromDay;
        //Return two decimal places and rounding
//        DecimalFormat df = new DecimalFormat();

        return (year + (double)month/12 + (double)day/365);
    }

}
