package com.transico.codezero.transico.CustomComponents.CustomDatePicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.transico.codezero.transico.R;
import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.DoubleAdder;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class Kcalendar {

    private OnClickDatePickListener onClickDatePickListener;
    private OnHorizontalScrollListener onHorizontalScrollListener;
    private DateAdapter dateAdapter;
    DateGenerator dateGenerator;
    boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItem;
    private ArrayList<MyDates> calendarDates;
    int currentPosition;

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.M)
    void generateCalendar(RecyclerView recyclerView, Context $this){
        //Date Generator is a class that handle the creation of sequential dates on the calendar
        //The Generate method returns an ArrayList<ArrayList<myDates>
        dateGenerator = new DateGenerator();

        //List of dates formatted for the calendar view
        calendarDates = dateGenerator.generate();

        //Set the adapter view with the list of dates generated from the DateGenerator class
        dateAdapter = new DateAdapter(dateGenerator.generate(), $this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager($this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(dateAdapter);

        Objects.requireNonNull(recyclerView.getLayoutManager()).scrollToPosition(1);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){

                    /**
                     * At this point we now have the accurate position of the adapter therefore we can use the
                     * Formula to get the first day of each week i.e. sunday
                     * However, we need to highlight and select the current date for the current week view
                     */

                    currentPosition =  layoutManager.findFirstVisibleItemPosition();
                    final int firstDayOfTheWeek = currentPosition * 7;


                    if(currentPosition == 1){
                        onHorizontalScrollListener.horizontalScrollListener(new Date());
                    }else{
                        onHorizontalScrollListener.horizontalScrollListener(calendarDates.get(firstDayOfTheWeek).getCalendar().getTime());
                    }

                    //this crap is not working fix it!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    dateAdapter.setOnScrollInnerListener(new OnScrollInnerListener() {
                        @Override
                        public void innerScrollListener(TextView textView, MyDates singleDates) {
                                Log.d("Ovel","we have been scrolled: "+Helper.DateFormatter(databaseCommand.DateTimeFormat.dateMonthYear,singleDates.getCalendar().getTime()));

                                if(Helper.DateFormatter(databaseCommand.DateTimeFormat.dateMonthYear,singleDates.getCalendar().getTime())
                                        .equals(Helper.DateFormatter(databaseCommand.DateTimeFormat.dateMonthYear,calendarDates.get(firstDayOfTheWeek).getCalendar().getTime()))){
                                    textView.setBackgroundResource(R.drawable.select_date_indicator);
                                    textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.calendar_active_color));
                                }
                        }
                    });



                    if(((currentPosition * 7)+7) == calendarDates.size()){
                        isScrolling = false;

                         //The code below will fetch new data and create continuous scrolling on the view
//                        fetchNewDates(calendarDates.get((currentPosition * 7)+6).getCalendar().getTime());

                    }

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItem = layoutManager.findFirstVisibleItemPosition();

                //Log.d("Ovel","You are viewing adapter position: "+currentItems);

                if(isScrolling && (currentItems + scrollOutItem == totalItems)){

                    //Get add another 3 weeks to view


                }
            }
        });


    }

    private void fetchNewDates(final Date date){

       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c = dateGenerator.getDate(c);
                for(int i=0; i<14; i++){
                    MyDates md = new MyDates(c);
                    calendarDates.add(md);


                    c = dateGenerator.getDate(c);
                    String data = Helper.DateFormatter(databaseCommand.DateTimeFormat.dateMonthYear,calendarDates.get(calendarDates.size() - 1).getCalendar().getTime());
                    Log.d("Ovel","Calendar Date size: "+calendarDates.size()+" and date is: "+data);


                }
                dateAdapter.setList(calendarDates);
                dateAdapter.notifyDataSetChanged();
            }
        },500);


    }


    /**
     * SetOnClickDatePicker fn will accept a listen that will allow a user defined function to perform some action
     * on the date that was clicked.
     * @param l OnClickDatePickerListener
     */
    void setOnClickDatePickListener(OnClickDatePickListener l){
        onClickDatePickListener = l;
        dateAdapter.setOnClickDateListener(l);
    }

    void setOnHorizontalScrollListener(OnHorizontalScrollListener l){
        onHorizontalScrollListener = l;
    }

    /**
     * Calendar Date Value adapter to bind and handle the UI and data of the calendar component
     */
    class DateAdapter extends RecyclerView.Adapter<DateHolder>  implements OnScrollInnerListener{

        private ArrayList<MyDates> mDateList;
        private Context mContext;
        boolean isDateChange  = false;
        String titleChanged;

        private OnClickDatePickListener mListener;
        OnScrollInnerListener onScrollInnerListener;


        public void setList(ArrayList<MyDates> am ){
            mDateList = am;
        }


        //Constructor
        DateAdapter(ArrayList<MyDates> dateList, Context context) {
            mDateList = dateList;
            mContext = context;
        }


        @NonNull
        @Override
        public DateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_calendar, parent,false);
            return new DateHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DateHolder holder, final int position) {


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

                MyDates singleDates = mDateList.get(i);


                //Sunday
                if(mDateList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.sunday)){
                   activateDaylist(holder, holder.calendarDateSun,holder.calendarDaySun,singleDates);


//

//                    holder.calendarDateSun.setBackgroundResource(R.drawable.select_date_indicator);
//                    holder.calendarDateSun.setTextColor(ContextCompat.getColor(mContext, R.color.calendar_active_color));


                }

                //Monday
                if(mDateList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.monday)){
                    activateDaylist(holder, holder.calendarDateMon,holder.calendarDayMon,singleDates);
                }

                //Tuesday
                if(mDateList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.tuesday)) {
                   activateDaylist(holder, holder.calendarDateTue, holder.calendarDayTue,singleDates);
                }

                //Wednesday
                if(mDateList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.wednesday)){
                    activateDaylist(holder, holder.calendarDateWed,holder.calendarDayWed,singleDates);
                }

                //Thursday
                if(mDateList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.thursday)) {
                    activateDaylist(holder, holder.calendarDateThu,holder.calendarDayThu,singleDates);
                }

                //Friday
                if(mDateList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.friday)){
                    activateDaylist(holder, holder.calendarDateFri,holder.calendarDayFri,singleDates);
                }

                //Saturday
                if(mDateList.get(i).getWeekDay().equals(databaseCommand.DateTimeFormat.saturday)){
                   activateDaylist(holder, holder.calendarDateSat,holder.calendarDaySat,singleDates);
                }
            }
        }


        void activateDaylist(final DateHolder holder, final TextView textDate, final TextView textDay, final MyDates myDates){
            final  String userData = myDates.getWeekDate();
            final int todaysDate  = Integer.valueOf(Helper.DateFormatter(databaseCommand.DateTimeFormat.date, new Date()));
            final Date listLongDate = myDates.getCalendar().getTime();


            textDate.setText(myDates.getWeekDate());
            if(todaysDate == Integer.valueOf(myDates.getWeekDate())) {
                textDate.setBackgroundResource(R.drawable.current_date_indicator);
                textDay.setTextColor(ContextCompat.getColor(mContext, R.color.calendar_active_color));

            }


            if(onScrollInnerListener !=null){
                onScrollInnerListener.innerScrollListener(textDate,myDates);
            }


            textDate.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
//                    Toast.makeText(mContext, "You have selected "+ userData , Toast.LENGTH_SHORT).show();

                    if(mListener != null){
                        mListener.datePickListener(Helper.DateFormatter(databaseCommand.DateTimeFormat.shortMonth,listLongDate),
                                Helper.DateFormatter(databaseCommand.DateTimeFormat.longMonth,listLongDate),
                                Helper.DateFormatter(databaseCommand.DateTimeFormat.date,listLongDate),
                                listLongDate);
                    }else{
                        Log.d("Ovel","No instruction was found");
                    }



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
            return mDateList.size()/7;
        }


        void setOnClickDateListener(OnClickDatePickListener l) {
            mListener = l;
        }

        private void setOnScrollInnerListener(OnScrollInnerListener l) { onScrollInnerListener = l;}

        @Override
        public void innerScrollListener(TextView calendarDateSun, MyDates singleDates) {
            setOnScrollInnerListener(onScrollInnerListener);
            Log.d("Ovel","listener triggered"+singleDates.getMonth());
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
