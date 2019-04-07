package com.transico.codezero.transico.FirestoreConnection;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

public class ReportDataManager extends FirebaseManager {


    public ReportDataManager() {
        super(databaseCommand.DriverReportLog.parentNode);

    }

    public ReportDataManager(String collectionRefName) {
        super(collectionRefName);
    }

    public FirestoreRecyclerOptions<ReportLogger> mainQuery(String driverID) {

        /* Setup Query for Firebase database */
        Query query = this.getCollectionRef().whereEqualTo(databaseCommand.DriverReportLog.driverID,driverID)
                .orderBy(databaseCommand.DriverReportLog.postTime, Query.Direction.DESCENDING);


        /* Get Query result from Firebase database */
        return new FirestoreRecyclerOptions.Builder<ReportLogger>()
                .setQuery(query, ReportLogger.class)
                .build();
    }


}
