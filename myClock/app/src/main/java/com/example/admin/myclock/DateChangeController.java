package com.example.admin.myclock;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateChangeController {
    private DateModel myDate;
    private myDigitalClock myClock;
    private Calendar myCalendar;

    public DateChangeController(DateModel myDate, myDigitalClock dClock){
        this.myClock = dClock;
        this.myDate = myDate;
        // this.time = view;
    }

    public String CurrentDate(){
        long lTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd YYYY");
        return sdf.format(lTime);
    }

    public String UpdateDate(){
        myCalendar.set(myDate.getnYear(),myDate.getnMonth(),myDate.getnDay());
        return myCalendar.toString();
    }
}
