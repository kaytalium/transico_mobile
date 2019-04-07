package com.transico.codezero.transico.CustomComponents.CustomDatePicker;

import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.Calendar;
import java.util.Date;

 class MyDates {

    private String weekDay;
    private String weekDate;
    private String month; //Month in number
    private Calendar calendar;
    private  Date date;

    MyDates(Calendar calendar) {

        weekDay = Helper.DateFormatter(databaseCommand.DateTimeFormat.dayOfTheWeek, calendar.getTime());
        weekDate = Helper.DateFormatter(databaseCommand.DateTimeFormat.date, calendar.getTime());
        month =  Helper.DateFormatter(databaseCommand.DateTimeFormat.month, calendar.getTime());
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(calendar.getTime());
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
