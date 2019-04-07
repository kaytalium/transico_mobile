package com.transico.codezero.transico.SystemHelper;

public interface databaseCommand {

    /**
     * General Time and date formatting
     */
    public interface DateTimeFormat{

        /**
         * description Time and date format
         * return
         */
        String dayMonthDate = "E, MMM dd";

        /**
         * description Time and date format
         * return e.g. Dec 04
         */
        String monthDate = "MMM dd";

        /**
         * description Time and date format
         * return e.g. Dec 04
         */
        String monthDateYear = "MMMddy";

        /**
         *
         */
        String shortMonthDate = "MMM d";

        /**
         * description Time and date format
         * return e.g. December 04
         */
        String longMonthDate = "MMMM dd";

        /**
         * return e.g. Dec 03, 2018
         */
        String dateMonthYear = "MMM dd, y";

        /**
         * return e.g. 10:02 PM
         */
        String timeFormat = "h:mm a";

        /**
         * Description return the day in string 3 character long
         * return e.g Tue
         */
        String dayOfTheWeek = "E";

        /**
         *
         */
        String DateWithAtTime = Helper.stringBuilder("%s at %s",monthDate,timeFormat);

        /**
         *
         * Description
         * return e.g. Tuesday
         */
        String dayOfTheWeekLong = "EEEE";

        /**
         * Description:
         * return
         */
        String date = "dd";

        /**
         * Description:
         * return
         */
        String month = "M";

        /**
         * Description:
         * return
         */
        String shortMonth = "MMM";

        /**
         *
         * January
         */
        String longMonth = "MMMM";

        /**
         * January 2018
         */
        String longMonthWithYear = "MMMM y";

        /**
         * Description:
         * return
         */
        String[] daysOfWeek = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};

        /**
         * Description:
         * return
         */
        String sunday = "Sun";

        /**
         * Description:
         * return
         */
        String monday = "Mon";

        /**
         * Description:
         * return
         */
        String tuesday = "Tue";

        /**
         * Description:
         * return
         */
        String wednesday = "Wed";

        /**
         * Description:
         * return
         */
        String thursday = "Thu";

        /**
         * Description:
         * return
         */
        String friday = "Fri";

        /**
         * Description:
         * return
         */
        String saturday = "Sat";


        String year = "y";


    }

    /**
     *
     */
    public interface DriverSchedule{

        /**
         *return String
         */
        public String parentNode = "driver_schedule";

        /**
         *
         * return String
         */
        public String driverID = "driverID";


        /**
         *
         * return String
         */
        public String staffID = "staffID";

        /**
         *
         */
        String startTime = "startTime";

        /**
         *
         */
        String scheduleDate = "scheduleDate";


    }


    /**
     * Time log Collection
     */
    public interface DriverTimeLog{

        /**
         *
         */
        String parentNode = "driver_time_log";

        /**
         *
         */
        String driverID = "driverID";
    }


    /**
     * Driver Report Log
     */
    interface DriverReportLog{
        /**
         * main firebase node name
         */
        String parentNode = "driver_report_log";

        /**
         * The time that the active post was made
         */
        String postTime = "postTime";

        /**
         * Driver ID
         */
        String driverID = "driverID";

        /**
         * String for view all responses
         */
        String viewAll = "%d Responses";

        /**
         * If no responds is recorded
         */
        String noResponds = "0 Responses";

        /**
         * When only one responds is found
         */
        String onlyOneResponds = "%d Response";

        /**
         * prepend @busModel
         */
        String busModel = "#%s";
    }
}
