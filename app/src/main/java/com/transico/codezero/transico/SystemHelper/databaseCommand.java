package com.transico.codezero.transico;

public interface databaseCommand {

    /**
     *
     * @return String
     */
    public String driverID = "driverID";

    String startTime = "startTime";

    /**
     *
     * @return String
     */
    public String driverSchedule = "driver_schedule";

    /**
     * @description Time and date format
     * @return
     */
    public String dayMonthDate = "E, MMM dd";

    /**
     * @description Time and date format
     * @return e.g. Dec 04
     */
    public String monthDate = "MMM dd";

    /**
     * @description Time and date format
     * @return e.g. December 04
     */
    String longMonthDate = "MMMM dd";

    /**
     * @return e.g. Dec 03, 2018
     */
    String dateMonthYear = "MMM dd, Y";

    /**
     * @return e.g. 10:02 PM
     */
    String timeFormat = "H:mm a";

    /**
     * @Description return the day in string 3 character long
     * @return e.g Tue
     */
    String dayOfTheWeek = "E";

    /**
     *
     * @Description
     * @return e.g. Tuesday
     */
    String dayOfTheWeekLong = "EEEE";

    String scheduleDate = "scheduleDate";

    String date = "dd";

    String month = "M";

    String shortMonth = "MMM";

    String LongMonth = "MMMM";
}
