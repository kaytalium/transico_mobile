package com.transico.codezero.transico.DriverSchedule;

import android.content.Context;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.Date;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class ScheduleProcessor {
//    Private vars
    private String viewDay  = Helper.DateFormatter(databaseCommand.dayOfTheWeek,new Date());
    private String viewDate = Helper.DateFormatter(databaseCommand.date,new Date());
    private Date filterDate;
    private CollectionReference firebaseCollectionReference;
    private RecyclerView recyclerView;
    private Context mContext;

    //Public vars
    ScheduleAdapter scheduleAdapter;

    ScheduleProcessor( CollectionReference cr, RecyclerView recyclerView, Context context) {
        filterDate = new Date();
        firebaseCollectionReference = cr;
        this.recyclerView = recyclerView;
        this.mContext = context;

    }

    //Getters and Setters
    String getViewDay() {
        return viewDay;
    }

    void setViewDay(String viewDay) {
        this.viewDay = viewDay;
    }

    String getViewDate() {
        return viewDate;
    }

    void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }


    void loadSchedule(){

        /* Setup Query for Firebase database */
        Query query = firebaseCollectionReference.whereEqualTo(databaseCommand.driverID,"35264")
                .whereEqualTo(databaseCommand.scheduleDate, Helper.DateFormatter(databaseCommand.monthDate,filterDate).trim());
//                .orderBy("startTime", Query.Direction.ASCENDING);


        /* Get Query result from Firebase database */
        FirestoreRecyclerOptions<DriverSchedule> options = new FirestoreRecyclerOptions.Builder<DriverSchedule>()
                .setQuery(query, DriverSchedule.class)
                .build();

        /* Load Result in Adapter*/
        scheduleAdapter = new ScheduleAdapter(options, mContext);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(scheduleAdapter);

//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);

    }

    void updateFilterDate(Date userRequest){
        filterDate = userRequest;

        loadSchedule();
        scheduleAdapter.notifyDataSetChanged();
        scheduleAdapter.startListening();

    }

}
