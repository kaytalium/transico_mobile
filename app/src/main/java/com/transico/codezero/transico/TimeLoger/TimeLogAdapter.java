package com.transico.codezero.transico.TimeLoger;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.transico.codezero.transico.FirestoreConnection.TimeLog;
import com.transico.codezero.transico.R;
import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.TimeKeeper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class TimeLogAdapter extends FirestoreRecyclerAdapter<TimeLog, TimeLogAdapter.TimeLogHolder> {


    private Context mContext;
    private final String TAG = "TimeLogAdapter";


    public TimeLogAdapter(FirestoreRecyclerOptions<TimeLog> firestoreList, Context mContext) {
        super(firestoreList);
        this.mContext = mContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull TimeLogHolder timeLogHolder, int i, @NonNull TimeLog timeLog) {

        Log.d(TAG, "Total Hours worked: "+timeLog.getActualWorked().toString());


        String id = getSnapshots().getSnapshot(i).getId();

        /* Setting the Date values */

        timeLogHolder.dayDay.setText(Helper.DateFormatter(databaseCommand.DateTimeFormat.dayOfTheWeek, timeLog.getDayDate().toDate()));
        timeLogHolder.dayDate.setText(Helper.DateFormatter(databaseCommand.DateTimeFormat.date,timeLog.getDayDate().toDate()));

        /* Setting the progressbar and values */

        double actualWorked = (timeLog.getActualWorked() > 0.0 ? timeLog.getActualWorked(): 0.0);
        double scheduledHours = (timeLog.getScheduledHours() > 0 ? timeLog.getScheduledHours(): 0);
        int total = (int)TimeKeeper.percentage(actualWorked,scheduledHours, 2);

        timeLogHolder.progressMeter.setProgress(total);

        /* Display the values in the view */

        timeLogHolder.hoursWorked.setText(TimeKeeper.DurationToTime(actualWorked));
        timeLogHolder.scheduledHours.setText(TimeKeeper.DurationToTime(scheduledHours));
        timeLogHolder.totalAchieved.setText(Helper.stringBuilder("%s%%", String.valueOf(total)));
//        timeLogHolder.totalAchieved.setText(id);

        /* get the list of actual scheduled and display the result in a new recycler view */


        if(timeLog.getActivities() != null) {
            HistoricalScheduledAdapter historicalScheduledAdapter = new HistoricalScheduledAdapter(timeLog.getActivities());

            timeLogHolder.mRecyclerView.setHasFixedSize(true);
            timeLogHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            timeLogHolder.mRecyclerView.setAdapter(historicalScheduledAdapter);

        }







    }

    @NonNull
    @Override
    public TimeLogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d(TAG,"OncreateViewHolder");

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_log_detail, parent, false);
        return new TimeLogAdapter.TimeLogHolder(v);
    }



    /*Holder class for said adapter*/
    public class TimeLogHolder extends RecyclerView.ViewHolder{

        private View mView;
        public TextView dayDate;
        public TextView dayDay;
        public TextView scheduledHours;
        public TextView hoursWorked;
        public ProgressBar progressMeter;
        public TextView totalAchieved;

        //inner recycler
        public RecyclerView mRecyclerView;


        public TimeLogHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            dayDate         = itemView.findViewById(R.id.tv_dayDate);
            scheduledHours  = itemView.findViewById(R.id.tv_total_time_value);
            hoursWorked     = itemView.findViewById(R.id.tv_actual_time_value);
            dayDay          = itemView.findViewById(R.id.tv_dayDay);
            progressMeter   = itemView.findViewById(R.id.tl_progressBar);
            totalAchieved   = itemView.findViewById(R.id.tv_actual_time_total);

            mRecyclerView   = itemView.findViewById(R.id.rv_time_log_items);


        }
    }
}
