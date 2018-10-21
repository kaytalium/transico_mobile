package com.transico.codezero.transico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends FirestoreRecyclerAdapter<DriverSchedule, ScheduleAdapter.ScheduleHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ScheduleAdapter(@NonNull FirestoreRecyclerOptions<DriverSchedule> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ScheduleHolder scheduleHolder, int i, @NonNull DriverSchedule driverSchedule) {
        scheduleHolder.routeText.setText(driverSchedule.getRoute());
        scheduleHolder.busNumberText.setText(driverSchedule.getBusNumber());
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_schedule, parent, false);
        return new ScheduleHolder(v);
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder{

        private View mView;

        public TextView routeText;
        public TextView timeText;
        public TextView busModelText;
        public TextView inspectorText;
        public TextView busNumberText;
        public TextView startTimeText;
        public TextView endTimeText;




        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            routeText = mView.findViewById(R.id.tv_route);
//            timeText = mView.findViewById(R.id.tv_time);
//            busModelText = mView.findViewById(R.id.tv_busModel);
            busNumberText = mView.findViewById(R.id.tv_busNumber);
//            inspectorText = mView.findViewById(R.id.tv_inspector);
            startTimeText = mView.findViewById(R.id.tv_start_time);
            endTimeText = mView.findViewById(R.id.tv_end_time);

        }
    }


}
