package com.transico.codezero.transico.GeneralUI.CustomDatePicker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transico.codezero.transico.DriverScheduler.CalendarViewOnClickListner;
import com.transico.codezero.transico.DriverScheduler.DriverScheduleFragment;
import com.transico.codezero.transico.R;
import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class Kcalendar {

    private OnClickDatePickListener listner;

    void generateCalendar(RecyclerView recyclerView, Context $this){
        //Var to store the list of dates
        ArrayList<MyDates> list = new ArrayList<>();
        ArrayList<ArrayList<MyDates>> mainList = new ArrayList<ArrayList<MyDates>>();


        /*
         * We need to do a lazy load of dates
         * seeing that we will be viewing the dates in one week intervals
         * we need to create dates from this day to two(2) weeks in the future and two(2)
         * weeks in the past
         */

        //setup current time and date
        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        //calendar.set(Calendar.MONTH, 10);
        //calendar.set(Calendar.DAY_OF_MONTH,19);
        calendar.set(Calendar.HOUR_OF_DAY, 0);// for midnight
        calendar.set(Calendar.MINUTE, 0);// for 0 min
        calendar.set(Calendar.SECOND, 0);// for 0 sec

        /*
         *@description: When the application is loaded it will display 4 week
         * ie. -14 days and +14 days from current date
         * With the start date always a Sunday
         * therefore we must ensure that the whatever date we calculate to be the start date we must push or pull the day to the closest sunday before it.
         */
        Integer timelaspe = -14;//initial 14 days
        Integer timeStopper = -14;

        //set the starting date to a sunday 2 weeks from current date
        calendar.add(Calendar.DAY_OF_MONTH, setStartDate());

       Log.d("Knymbus-DayCount:", Helper.DateFormatter(databaseCommand.DateTimeFormat.dayMonthDate, calendar.getTime()));


        /*
         * We then loop from -14 to +14 days
         * on each loop we resent the current date in the calendar
         *
         */
        for(int i = 0; i<4;i++) {

            timeStopper +=  7;


            while (timelaspe < timeStopper) { //While timelapse is less than +14 from the current date we will loop


                //Once we get our date we can now set the MyDate object to capture the information the way we want it.
                MyDates myDates = new MyDates(Helper.DateFormatter(databaseCommand.DateTimeFormat.dayOfTheWeek, calendar.getTime()),
                        Helper.DateFormatter(databaseCommand.DateTimeFormat.date, calendar.getTime()),
                        Helper.DateFormatter(databaseCommand.DateTimeFormat.month, calendar.getTime()));

                //We then this object to a list of myDates object for later use
                list.add(myDates);


                calendar.add(Calendar.DAY_OF_MONTH, 1);// add 1 one day on each iteration

                //increment the count on the timelapse var to reach its terminating value of 28
                timelaspe++;
            }

            mainList.add(list);
//            list.clear();
        }
        //Now that we have looped the current date and created a list of dates we can now add this list to our recyclerview for display
        DateAdapter dateAdapter = new DateAdapter(mainList, $this, listner);


        LinearLayoutManager layoutManager = new LinearLayoutManager($this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(dateAdapter);

        Objects.requireNonNull(recyclerView.getLayoutManager()).scrollToPosition(2);

    }






    private int setStartDate(){
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        int numberOfDaysToStart = -14;
        String[] DaysOfTheWeek = databaseCommand.DateTimeFormat.daysOfWeek;
        calendar.setTime(currentDate);

        //Testing dates
        //calendar.set(Calendar.DAY_OF_MONTH,19);

        //check what day of the week is today
        String dayOfTheWeek = Helper.DateFormatter(databaseCommand.DateTimeFormat.dayOfTheWeek,calendar.getTime()); // Sun, Mon, Tue, Wed, Thu, Fri, Sat

        Log.d("DayInString", dayOfTheWeek);

        if(!dayOfTheWeek.equals(databaseCommand.DateTimeFormat.sunday)){
            int index = Arrays.asList(DaysOfTheWeek).indexOf(dayOfTheWeek);
            numberOfDaysToStart -= index;
        }

        Log.d("DayCount", Integer.valueOf(numberOfDaysToStart).toString());

        return numberOfDaysToStart;
    }

    class DateAdapter extends RecyclerView.Adapter<DateHolder>  {

        private ArrayList<ArrayList<MyDates>> mDate;
        private Context mContext;
        boolean isDateChange  = false;
        String titleChanged;

        private OnClickDatePickListener mListner;




        //Constructor
        DateAdapter(ArrayList<ArrayList<MyDates>> dateList, Context context, OnClickDatePickListener listner) {
            mDate = dateList;
            mContext = context;
            mListner = listner;

        }


        @NonNull
        @Override
        public DateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_calendar, parent,false);
            return new DateHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DateHolder holder, final int position) {

            final ArrayList<MyDates> weeklyList = mDate.get(position);
            int stop, start = 0;
            if(position == 0){
                stop = 7;
            }else{
                start = position * 7;
                stop = start + 7;

            }
            int todaysDate  = Integer.valueOf(Helper.DateFormatter(databaseCommand.DateTimeFormat.date, new Date()));
            String today = Helper.DateFormatter(databaseCommand.DateTimeFormat.dayOfTheWeek, new Date());

            Log.d("Today is: ", today);

            for(int i = start; i<stop;i++){


                //Sunday
                if(weeklyList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.sunday)){
                   activateDaylist(holder, holder.calendarDateSun,holder.calendarDaySun,weeklyList.get(i).getWeekDate(),weeklyList.get(i).getCalendar().getTime());
                    holder.calendarDateSun.setBackgroundResource(R.drawable.select_date_indicator);
                    holder.calendarDateSun.setTextColor(ContextCompat.getColor(mContext, R.color.calendar_active_color));

                }

                //Monday
                if(weeklyList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.monday)){
                    activateDaylist(holder, holder.calendarDateMon,holder.calendarDayMon,weeklyList.get(i).getWeekDate(),weeklyList.get(i).getCalendar().getTime());
                }

                //Tuesday
                if(weeklyList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.tuesday)) {
                   activateDaylist(holder, holder.calendarDateTue, holder.calendarDayTue,weeklyList.get(i).getWeekDate(),weeklyList.get(i).getCalendar().getTime());
                }

                //Wednesday
                if(weeklyList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.wednesday)){
                    activateDaylist(holder, holder.calendarDateWed,holder.calendarDayWed,weeklyList.get(i).getWeekDate(),weeklyList.get(i).getCalendar().getTime());
                }

                //Thursday
                if(weeklyList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.thursday)) {
                    activateDaylist(holder, holder.calendarDateThu,holder.calendarDayThu,weeklyList.get(i).getWeekDate(),weeklyList.get(i).getCalendar().getTime());
                }

                //Friday
                if(weeklyList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.friday)){
                    activateDaylist(holder, holder.calendarDateFri,holder.calendarDayFri,weeklyList.get(i).getWeekDate(),weeklyList.get(i).getCalendar().getTime());
                }

                //Saturday
                if(weeklyList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.saturday)){
                   activateDaylist(holder, holder.calendarDateSat,holder.calendarDaySat,weeklyList.get(i).getWeekDate(), weeklyList.get(i).getCalendar().getTime());
                }
            }
        }


        void activateDaylist(final DateHolder holder, final TextView textDate, final TextView textDay, final String listDate, Date longDate){
            final  String userData = listDate;
            final int todaysDate  = Integer.valueOf(Helper.DateFormatter(databaseCommand.DateTimeFormat.date, new Date()));
            final Date listLongDate = longDate;


            textDate.setText(listDate);
            if(todaysDate == Integer.valueOf(listDate)) {
                textDate.setBackgroundResource(R.drawable.current_date_indicator);
                textDay.setTextColor(ContextCompat.getColor(mContext, R.color.calendar_active_color));

            }




            textDate.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
//                    Toast.makeText(mContext, "You have selected "+ userData , Toast.LENGTH_SHORT).show();

                    mListner.datePickListener(Helper.DateFormatter(databaseCommand.DateTimeFormat.shortMonth,listLongDate), Helper.DateFormatter(databaseCommand.DateTimeFormat.date,listLongDate), listLongDate);


                    /* Sunday */
                    applyHighlight(holder.calendarDateSun, textDate, todaysDate);

                    /* Monday */
                    applyHighlight(holder.calendarDateMon, textDate, todaysDate);

                    /* Tuesday */
                    applyHighlight(holder.calendarDateTue, textDate, todaysDate);

                    /* Wednesday */
                    applyHighlight(holder.calendarDateWed, textDate, todaysDate);

                    /* Thursday */
                    applyHighlight(holder.calendarDateThu, textDate, todaysDate);

                    /* Friday */
                    applyHighlight(holder.calendarDateFri, textDate, todaysDate);

                    /* Saturday */
                    applyHighlight(holder.calendarDateSat, textDate, todaysDate);


                }

            });
        }

        private void applyHighlight(TextView tv_date, TextView currentEl, int current_date){
            if(!tv_date.getText().toString().equals(String.valueOf(current_date)) ){
                tv_date.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                tv_date.setBackgroundResource(0);

                currentEl.setBackgroundResource(R.drawable.select_date_indicator);
                currentEl.setTextColor(ContextCompat.getColor(mContext, R.color.calendar_active_color));

            }

            if(currentEl.getText().toString().equals(String.valueOf(current_date))){
                currentEl.setBackgroundResource(R.drawable.current_date_indicator);
                currentEl.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            }
        }

        @Override
        public int getItemCount() {
            return mDate.size();
        }


    }



    class DateHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private View mView;
            private Calendar c = Calendar.getInstance();




            /* Sun */
            TextView calendarDaySun;
            TextView calendarDateSun;

            /* Mon */
            TextView calendarDayMon;
            TextView calendarDateMon;

            /* Tue */
            TextView calendarDayTue;
            TextView calendarDateTue;

            /* Wed */
            TextView calendarDayWed;
            TextView calendarDateWed;

            /* Thu */
            TextView calendarDayThu;
            TextView calendarDateThu;

            /* Fri */
            TextView calendarDayFri;
            TextView calendarDateFri;

            /* Sat */
            TextView calendarDaySat;
            TextView calendarDateSat;

            DateHolder(@NonNull View itemView) {
                super(itemView);
                this.mView = itemView;



                itemView.setOnClickListener(this);

                //Setup the current date to get current time and date
                Date d = new Date();
                c.setTime(d);

                //Sunday
                calendarDaySun = mView.findViewById(R.id.txtCalendarDaySun);
                calendarDateSun = mView.findViewById(R.id.txtCalendarDateSun);
                calendarDateSun.setText("");

                //Monday
                calendarDayMon = mView.findViewById(R.id.txtCalendarDayMon);
                calendarDateMon = mView.findViewById(R.id.txtCalendarDateMon);
                calendarDateMon.setText("");

                //Tuesday
                calendarDayTue = mView.findViewById(R.id.txtCalendarDayTue);
                calendarDateTue = mView.findViewById(R.id.txtCalendarDateTue);
                calendarDateTue.setText("");

                //Wednesday
                calendarDayWed = mView.findViewById(R.id.txtCalendarDayWed);
                calendarDateWed = mView.findViewById(R.id.txtCalendarDateWed);
                calendarDateWed.setText("");

                //Thursday
                calendarDayThu = mView.findViewById(R.id.txtCalendarDayThu);
                calendarDateThu = mView.findViewById(R.id.txtCalendarDateThu);
                calendarDateThu.setText("");

                //Friday
                calendarDayFri = mView.findViewById(R.id.txtCalendarDayFri);
                calendarDateFri = mView.findViewById(R.id.txtCalendarDateFri);
                calendarDateFri.setText("");

                //Saturday
                calendarDaySat = mView.findViewById(R.id.txtCalendarDaySat);
                calendarDateSat = mView.findViewById(R.id.txtCalendarDateSat);
                calendarDateSat.setText("");

            }



        @Override
        public void onClick(View v) {

        }
    }

}
