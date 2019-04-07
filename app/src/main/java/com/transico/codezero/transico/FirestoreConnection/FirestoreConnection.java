package com.transico.codezero.transico.FirestoreConnection;

public interface FirestoreConnection {
    ScheduleDataManager ScheduleManager = new ScheduleDataManager();
    TimeLogDataManager TimeManager = new TimeLogDataManager();
    ReportDataManager ReportManager = new ReportDataManager();
    ProfileDataManager ProfileManager = new ProfileDataManager();
}
