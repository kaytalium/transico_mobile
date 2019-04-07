package com.transico.codezero.transico.TimeLoger;

import com.google.firebase.Timestamp;

public class ScheduledItem {

    String busNumber;
    Timestamp checkIn;
    Timestamp checkOut;

    /* schedule */
    Timestamp scheduleIn;
    Timestamp scheduleOut;

    Integer routeID;

    public ScheduledItem() {
    }

    public ScheduledItem(String busNumber, Timestamp checkIn, Timestamp checkOut, Integer routeID, Timestamp scheduleIn, Timestamp scheduleOut) {
        this.busNumber = busNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.routeID = routeID;
        this.scheduleIn = scheduleIn;
        this.scheduleOut = scheduleOut;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public Timestamp getCheckIn() {
        return checkIn;
    }

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public Integer getRouteID() {
        return routeID;
    }

    public Timestamp getScheduleIn() {
        return scheduleIn;
    }

    public Timestamp getScheduleOut() {
        return scheduleOut;
    }
}
