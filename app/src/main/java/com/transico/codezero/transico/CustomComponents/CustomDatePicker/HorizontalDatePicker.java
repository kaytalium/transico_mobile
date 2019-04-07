package com.transico.codezero.transico.CustomComponents.CustomDatePicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.transico.codezero.transico.R;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/**
 * The horizontal Date Picker class extends the LinearLayout to hold a horizontal calendar that allow
 * users to view the calendar for two week from the current date and two weeks from the current date.
 * the class have have a setOnClickDatePickerListener method that allow users to access the date that was clicked.
 * additional values like shortMonth = "Jan", longMonth = "January", dayDate = "09", and selectedDate = new Date()
 */
public class HorizontalDatePicker extends LinearLayout {


    private RecyclerView mRecyclerView;
    Kcalendar kc;


    public HorizontalDatePicker(Context context) {
        super(context);
        init();
    }

    public HorizontalDatePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
        init();
    }

    public HorizontalDatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
        init();
    }

    public HorizontalDatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
        init();
    }

    /**
     *
     * @param attrs user defined attributes from XML settings
     */
    private void parseAttributes(AttributeSet attrs){}

    /**
     * The init function is used to initialize the calendar widget that will and set all supporting
     * markers
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.custom_calendar, this, true);

        //container to store the calendar UI and control scrolling
        mRecyclerView = findViewById(R.id.calendar_container);

        //control the scrolling of each week in the adapter from continuous scrolling to a snap scroll
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);


        //create calendar class. This class have all the control and logic of the calendar
        kc = new Kcalendar();
        kc.generateCalendar(mRecyclerView, getContext());

    }

    /**
     * Set the onclick date picker listener for the calendar for when the user click on a date
     * @param l OnClickDatePickerListener class
     */
    public void setOnClickDatePickerListener(@Nullable OnClickDatePickListener l){
         kc.setOnClickDatePickListener(l);
    }

    public void setOnHorizontalScrollListener(@Nullable OnHorizontalScrollListener l){
        kc.setOnHorizontalScrollListener(l);
    }


}
