package com.transico.codezero.transico.CustomComponents.CustomDatePicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.transico.codezero.transico.R;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class HorizantalDatePicker extends LinearLayout {


    private RecyclerView mRecyclerView;
    Kcalendar kc;


    public HorizantalDatePicker(Context context) {
        super(context);
        init();
    }

    public HorizantalDatePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
        init();
    }

    public HorizantalDatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
        init();
    }

    public HorizantalDatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
        init();
    }

    private void parseAttributes(AttributeSet attrs){}

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
     * @description add
     * @param l OnClickDatePickerListener class 
     */
    public void setOnClickDatePickerListener(@Nullable OnClickDatePickListener l){
         kc.setOnClickDatePickListener(l);
    }


}
