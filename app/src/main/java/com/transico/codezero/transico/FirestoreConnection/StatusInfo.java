package com.transico.codezero.transico.FirestoreConnection;

import com.google.firebase.Timestamp;

public class StatusInfo {

    private String status;
    private int progressBarMeterValue = 0;
    private Boolean isCheckedOut;
    private boolean isCheckedIn;
    private Timestamp checkedOutTime;
    private Timestamp checkedInTime;

    public StatusInfo() {
    }

    public StatusInfo(String status, int progressBarMeterValue, Boolean isCheckedOut, boolean isCheckedIn, Timestamp checkedOutTime, Timestamp checkedInTime) {
        this.status = status;
        this.progressBarMeterValue = progressBarMeterValue;
        this.isCheckedOut = isCheckedOut;
        this.isCheckedIn = isCheckedIn;
        this.checkedOutTime = checkedOutTime;
        this.checkedInTime = checkedInTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProgressBarMeterValue() {
        return progressBarMeterValue;
    }

    public void setProgressBarMeterValue(int progressBarMeterValue) {
        this.progressBarMeterValue = progressBarMeterValue;
    }

    public Boolean getCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(Boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        isCheckedIn = checkedIn;
    }

    public Timestamp getCheckedOutTime() {
        return checkedOutTime;
    }

    public void setCheckedOutTime(Timestamp checkedOutTime) {
        this.checkedOutTime = checkedOutTime;
    }

    public Timestamp getCheckedInTime() {
        return checkedInTime;
    }

    public void setCheckedInTime(Timestamp checkedInTime) {
        this.checkedInTime = checkedInTime;
    }
}
