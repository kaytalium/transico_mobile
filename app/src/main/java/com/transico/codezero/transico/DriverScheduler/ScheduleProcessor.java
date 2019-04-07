package com.transico.codezero.transico.DriverScheduler;

import android.content.Context;
import android.util.Log;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.transico.codezero.transico.FirestoreConnection.FirestoreConnection;
import com.transico.codezero.transico.FirestoreConnection.ScheduleDataManager;
import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.Date;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class ScheduleProcessor {
//    Private vars
    private String viewDay  = Helper.DateFormatter(databaseCommand.DateTimeFormat.dayOfTheWeek,new Date());
    private String viewDate = Helper.DateFormatter(databaseCommand.DateTimeFormat.date,new Date());
    private Date filterDate;
    private RecyclerView recyclerView;
    private Context mContext;

    //Public vars
    ScheduleAdapter scheduleAdapter;

    ScheduleProcessor(RecyclerView recyclerView, Context context) {
        filterDate = new Date();
        this.recyclerView = recyclerView;
        this.mContext = context;

    }


    void loadSchedule(){



        /* Load Result in Adapter*/
        scheduleAdapter = new ScheduleAdapter(FirestoreConnection.ScheduleManager.ScheduleQuery("35264",filterDate), mContext);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(scheduleAdapter);
    }

    void updateFilterDate(Date userRequest){
        filterDate = userRequest;

        loadSchedule();
        scheduleAdapter.notifyDataSetChanged();
        scheduleAdapter.startListening();

    }

}
