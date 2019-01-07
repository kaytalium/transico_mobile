package com.transico.codezero.transico.DriverSchedule;

import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.List;


public class DriverSchedule {

    private String route, time, inspector, driverID, busModel, busNumber, scheduleDate;



    private List<String> via;

    @ServerTimestamp
    private Date startTime, endTime;

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public DriverSchedule() {}

    public DriverSchedule(String route, String time, String inspector, String driverID, String busModel, String busNumber, Date startTime, Date endTime, String scheduleDate) {
        this.route = route;
        this.time = time;
        this.inspector = inspector;
        this.driverID = driverID;
        this.busModel = busModel;
        this.busNumber = busNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduleDate = scheduleDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setVia(List<String> via) {
        this.via = via;
    }

    @PropertyName("via")
    public List<String> getVia() {
        return via;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getBusModel() {
        return busModel;
    }

    public void setBusModel(String busModel) {
        this.busModel = busModel;
    }
}
