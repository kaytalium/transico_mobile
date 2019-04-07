package com.transico.codezero.transico.TimeLoger;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transico.codezero.transico.FirestoreConnection.ScheduledItem;
import com.transico.codezero.transico.R;
import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.TimeKeeper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoricalScheduledAdapter extends RecyclerView.Adapter<HistoricalScheduledAdapter.ScheduledItemHolder> {

    private List<ScheduledItem> itemList;
    public HistoricalScheduledAdapter(List<ScheduledItem> options) {
        this.itemList = options;

        Log.d("Historical", "Trigger");
    }

    @NonNull
    @Override
    public ScheduledItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.historical_scheduled, parent, false);
        return new ScheduledItemHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ScheduledItemHolder holder, int position) {
        Log.d("Historical", "onBindView triggered" + itemList.size());
        holder.busModel.setText(itemList.get(position).getBusNumber());
        holder.routeID.setText(String.valueOf(itemList.get(position).getRouteID()));

        String checkIn = Helper.DateFormatter(databaseCommand.DateTimeFormat.timeFormat,itemList.get(position).getCheckIn().toDate());
        String checkOut = Helper.DateFormatter(databaseCommand.DateTimeFormat.timeFormat,itemList.get(position).getCheckOut().toDate());

        if(itemList.get(position).getScheduleIn() != null || itemList.get(position).getScheduleOut() !=null) {
            String scheduleIn = Helper.DateFormatter(databaseCommand.DateTimeFormat.timeFormat, itemList.get(position).getScheduleIn().toDate());
            String scheduleOut = Helper.DateFormatter(databaseCommand.DateTimeFormat.timeFormat, itemList.get(position).getScheduleOut().toDate());


//        schedule hours for work
            holder.scheduledHoursToWork.setText(Helper.stringBuilder("%s to %s",scheduleIn,scheduleOut));
            holder.totalScheduledHoursToWork.setText(TimeKeeper.timeDiff(itemList.get(position).getScheduleIn(),itemList.get(position).getScheduleOut()));

        }




//        actual work done
        holder.actualHoursWorked.setText(Helper.stringBuilder("%s to %s",checkIn,checkOut));
        holder.totalActualHoursWorked.setText(TimeKeeper.timeDiff(itemList.get(position).getCheckIn(),itemList.get(position).getCheckOut()));

//      setup percentage
        double totalSchedule = TimeKeeper.timeDiffToDouble(itemList.get(position).getScheduleIn(),itemList.get(position).getScheduleOut());
        double totalActual = TimeKeeper.timeDiffToDouble(itemList.get(position).getCheckIn(),itemList.get(position).getCheckOut());

        Log.d("Timmer","Actual Time: "+totalActual);

        holder.percentageAchieved.setText(Helper.stringBuilder("%d%% ",(int)TimeKeeper.percentage(totalActual,totalSchedule,1)));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ScheduledItemHolder extends RecyclerView.ViewHolder{

        public TextView routeID;
        public TextView busModel;

//      actual hours worked
        public TextView actualHoursWorked;
        public TextView totalActualHoursWorked;

//        Scheduled to work
        public TextView scheduledHoursToWork;
        public TextView totalScheduledHoursToWork;

//        percentage achieved
        public TextView percentageAchieved;



        public ScheduledItemHolder(@NonNull View itemView) {
            super(itemView);

            routeID = itemView.findViewById(R.id.txtRouteID);
            busModel = itemView.findViewById(R.id.txtBusNumber_value);
            actualHoursWorked = itemView.findViewById(R.id.txtworktime_value);
            totalActualHoursWorked = itemView.findViewById(R.id.txtTotal_worked);
            scheduledHoursToWork        = itemView.findViewById(R.id.txtschedule_to_work);
            totalScheduledHoursToWork   = itemView.findViewById(R.id.txttotal_scheduled_to_work);
            percentageAchieved   = itemView.findViewById(R.id.percentage_achieve_value);
        }
    }
}
