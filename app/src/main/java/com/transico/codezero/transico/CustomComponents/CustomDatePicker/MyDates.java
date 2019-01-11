package com.transico.codezero.transico.GeneralUI.CustomDatePicker;

import java.util.Calendar;
import java.util.Date;

 class MyDates {

    private String weekDay;
    private String weekDate;
    private String month; //Month in number
    private Calendar calendar;
    private  Date date;

    MyDates(String weekDay, String weekDate, String month) {
        this.weekDay = weekDay;
        this.weekDate = weekDate;
        this.month = month;

        //setup the calendar to reflect date given to object
        calendar = Calendar.getInstance();
        date = new Date();
//        /**/calendar.setTime(date);
        calendar.set(Calendar.MONTH, (Integer.valueOf(month)-1));
        calendar.set(Calendar.DAY_OF_MONTH,Integer.valueOf(weekDate));
    }

    MyDates() {
    }

    String getMonth() {
        return month;
    }

    void setMonth(String month) {
        this.month = month;
    }

    String getWeekDay() {
        return weekDay;
    }

    void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    String getWeekDate() {
        return weekDate;
    }

    void setWeekDate(String weekDate) {
        this.weekDate = weekDate;
    }

    Calendar getCalendar() {
        return calendar;
    }

    void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }


}
