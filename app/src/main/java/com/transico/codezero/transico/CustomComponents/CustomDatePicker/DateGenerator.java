package com.transico.codezero.transico.CustomComponents.CustomDatePicker;

import android.util.Log;

import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class DateGenerator {


    /**
     * The generate function will return 21 days
     * 1 week to the sunday from current date and 2 weeks to the Saturday.
     * @return
     */
    public ArrayList<MyDates> generate(){

        //setup current time and date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return generateList(calendar);

    }

    public ArrayList<MyDates> generate(Date date){
        //setup current time and date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return generateList(calendar);
    }

    private int setStartDate(Calendar calendar){
        int numberOfDaysToStart = -7;
        String[] DaysOfTheWeek = databaseCommand.DateTimeFormat.daysOfWeek; // Sun, Mon, Tue, Wed, Thu, Fri, Sat


        //check what day of the week is today
        String dayOfTheWeek = Helper.DateFormatter(databaseCommand.DateTimeFormat.dayOfTheWeek,calendar.getTime()); // Sun, Mon, Tue, Wed, Thu, Fri, Sat


        if(!dayOfTheWeek.equals(databaseCommand.DateTimeFormat.sunday)){
            int index = Arrays.asList(DaysOfTheWeek).indexOf(dayOfTheWeek);
            numberOfDaysToStart -= index;
        }

        return numberOfDaysToStart;
    }

    private ArrayList<MyDates> generateList(Calendar calendar){
        ArrayList<MyDates> list = new ArrayList<MyDates>();

        /*
         * When the application is loaded it will display 4 week
         * ie. -14 days and +14 days from current date
         * With the start date always a Sunday in the series of days
         * therefore we must ensure that whatever date is calculated is the start date.
         * We must push or pull the day to the closest sunday before.
         */


        //set the starting date to a sunday 1 week from current date
        calendar.add(Calendar.DATE, setStartDate(calendar));


        //now that we have a start date from a sunday we can now add 21 days to our list
        int numberOfDaysToLoop = 21;
        for(int i = 0; i<numberOfDaysToLoop;i++) {

            //Once we get our date we can now set the MyDate object to capture the information the way we want it.
            MyDates myDates = new MyDates(calendar);

            //We then this object to a list of myDates object for later use
            list.add(myDates);

           calendar = getDate(calendar);// add 1 one day on each iteration
        }

        return list;
    }

    public Calendar getDate(Calendar c){
        c.add(Calendar.DAY_OF_MONTH,1);
        return c;
    }


}
