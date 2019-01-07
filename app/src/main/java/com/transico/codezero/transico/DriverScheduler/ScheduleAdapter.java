package com.transico.codezero.transico.DriverSchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.transico.codezero.transico.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends FirestoreRecyclerAdapter<DriverSchedule, ScheduleAdapter.ScheduleHolder> {

     private Context mContext;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options accept a list of DriverSchedule
     *
     */
     ScheduleAdapter(@NonNull FirestoreRecyclerOptions<DriverSchedule> options, Context mContext) {
        super(options);
        this.mContext = mContext;


    }

    @Override
    protected void onBindViewHolder(@NonNull ScheduleHolder scheduleHolder, int i, @NonNull DriverSchedule driverSchedule) {
        scheduleHolder.routeText.setText(driverSchedule.getRoute());
        scheduleHolder.busNumberText.setText(driverSchedule.getBusNumber());
        scheduleHolder.busModelText.setText(driverSchedule.getBusModel());

        scheduleHolder.inspectorText.setText(driverSchedule.getInspector());
        scheduleHolder.timeText.setText(driverSchedule.getTime());

        RecyclerView recyclerView1 = scheduleHolder.mView.findViewById(R.id.rv_via_route);

        ViaRouteAdapter viaRouteAdapter  = new ViaRouteAdapter(driverSchedule.getVia(), mContext);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        recyclerView1.setHasFixedSize(false);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(viaRouteAdapter);






//        scheduleHolder.startTimeText.setText(Helper.DateFormatter("h:mm", driverSchedule.getStartTime()));
//        scheduleHolder.startTimeMariddean.setText(Helper.DateFormatter("a",driverSchedule.getStartTime()));
//
//        Log.v("Array Count", String.valueOf(i));
//
//            scheduleHolder.endTimeMariddean.setText("");
//            scheduleHolder.endTimeText.setText("");
//
//        if(i!=0){
//            scheduleHolder.endTimeText.setText(Helper.DateFormatter("h:mm",driverSchedule.getEndTime()));
//            scheduleHolder.endTimeMariddean.setText(Helper.DateFormatter("a",driverSchedule.getEndTime()));
//        }


    }

    @NonNull 
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule1, parent, false);
       return new ScheduleHolder(v);
    }





    class ScheduleHolder extends RecyclerView.ViewHolder{

        private View mView;

        TextView routeText;
        TextView timeText;
        TextView busModelText;
        TextView inspectorText;
        TextView busNumberText;
        TextView startTimeText;
        TextView startTimeMariddean;
        TextView endTimeText;
        TextView endTimeMariddean;





         ScheduleHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            routeText = mView.findViewById(R.id.tv_route);
            timeText = mView.findViewById(R.id.tv_time);
            busModelText = mView.findViewById(R.id.tv_busModel);
            busNumberText = mView.findViewById(R.id.tv_busNumber);
            inspectorText = mView.findViewById(R.id.tv_inspector);

//            startTimeText = mView.findViewById(R.id.tv_start_time);
//            startTimeMariddean = mView.findViewById(R.id.tv_start_time_indicator);
//            endTimeText = mView.findViewById(R.id.tv_end_time);
//            endTimeMariddean = mView.findViewById(R.id.tv_end_time_indicator);

        }
    }


}
