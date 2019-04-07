package com.transico.codezero.transico.FirestoreConnection;
import com.google.firebase.Timestamp;


public class ReportLogger {
// variables
    private String workGroup;
    private String detail;
    private String driverID;
    private Timestamp postTime;
    private String title;
    private UserResponds latestResponds;
    private String busModel;
    private String reportStatus;

    public ReportLogger() {
    }

    public ReportLogger(String workGroup, String detail, String driverID, Timestamp postTime, String title,
                        UserResponds latestResponds, String busModel, String reportStatus) {
        this.workGroup = workGroup;
        this.detail = detail;
        this.driverID = driverID;
        this.postTime = postTime;
        this.title = title;
        this.latestResponds = latestResponds;
        this.busModel = busModel;
        this.reportStatus = reportStatus;
    }

    public String getWorkGroup() {
        return workGroup;
    }

    public String getDetail() {
        return detail;
    }

    public String getDriverID() {
        return driverID;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public String getTitle() {
        return title;
    }

    public UserResponds getLatestResponds() {
        return latestResponds;
    }

    public String getBusModel() {
        return busModel;
    }

    public String getReportStatus() {
        return reportStatus;
    }
}
