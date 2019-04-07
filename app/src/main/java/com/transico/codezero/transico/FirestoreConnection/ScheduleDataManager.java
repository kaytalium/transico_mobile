package com.transico.codezero.transico.FirestoreConnection;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.Date;


public class ScheduleDataManager extends FirebaseManager{

    ScheduleDataManager() {
        super(databaseCommand.DriverSchedule.parentNode);
    }

    public ScheduleDataManager(String collectionRefName) {
        super(collectionRefName);
    }

    public FirestoreRecyclerOptions<DriverSchedule> ScheduleQuery(String staffID, Date selectedDate){
        Query query = this.getCollectionRef().whereEqualTo(databaseCommand.DriverSchedule.staffID,staffID)
                .whereEqualTo(databaseCommand.DriverSchedule.scheduleDate, Helper.DateFormatter(databaseCommand.DateTimeFormat.monthDateYear,selectedDate).trim())
                .orderBy("startTime", Query.Direction.ASCENDING);


        /* Get Query result from Firebase database */
        return new FirestoreRecyclerOptions.Builder<DriverSchedule>()
                .setQuery(query, DriverSchedule.class)
                .build();
    }
}
