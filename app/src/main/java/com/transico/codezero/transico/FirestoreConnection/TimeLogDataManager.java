package com.transico.codezero.transico.FirestoreConnection;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

public class TimeLogDataManager  extends FirebaseManager{



    TimeLogDataManager() {
        super(databaseCommand.DriverTimeLog.parentNode);
    }

    public TimeLogDataManager(String collectionRefName) {
        super(collectionRefName);
    }

    public FirestoreRecyclerOptions<TimeLog> dailyLogQuery(String driverID){

        Query timeLogQuery = this.getCollectionRef().whereEqualTo(databaseCommand.DriverTimeLog.driverID,driverID)
                .orderBy("dayDate", Query.Direction.DESCENDING);


        /* Get Query result from Firebase database */
        return new FirestoreRecyclerOptions.Builder<TimeLog>()
                .setQuery(timeLogQuery, TimeLog.class)
                .build();

    }
}
