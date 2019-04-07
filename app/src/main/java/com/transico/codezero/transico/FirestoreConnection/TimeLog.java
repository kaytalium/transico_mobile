package com.transico.codezero.transico.TimeLoger;

import com.google.firebase.Timestamp;

import java.util.List;

public class TimeLog {

    private Timestamp dayDate;
    private String driverID;
    private Double scheduledHours = 0.0;
    private Double actualWorked = 0.0;

    private List<ScheduledItem> activities;



    public TimeLog(){}

    public TimeLog(Timestamp dayDate, String driverID, Double scheduledHours, Double actualWorked, List<ScheduledItem> activities) {
        this.dayDate = dayDate;
        this.driverID = driverID;
        this.scheduledHours = scheduledHours;
        this.actualWorked = actualWorked;
        this.activities = activities;
    }

    public Timestamp getDayDate() {
        return dayDate;
    }

    public String getDriverID() {
        return driverID;
    }

    public Double getScheduledHours() { return scheduledHours; }

    public Double getActualWorked() { return actualWorked; }

    public List<ScheduledItem> getActivities() {
        return activities;
    }
}
