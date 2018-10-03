package com.example.admin.myclock;

import android.app.ExpandableListActivity;
import android.widget.DigitalClock;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalTime;
import java.util.Calendar;

public class TimeChangeController implements Command{
    /*
    * This will extend the MainPage cause this is the user input page(main page)
    * What if we had two methods the first will get the current time using the built in clock method
    * The second will get in user input for changing the clock and keeps that one running
    *
    * */

    private ClockModel clock;
    private Calendar calendar;
    private TimeView time;
    private LocalTime currentTime;
    private myDigitalClock digitalClockView;

    public TimeChangeController(ClockModel myClock, myDigitalClock dClock){
        this.digitalClockView = dClock;
        this.clock = myClock;
       // this.time = view;
    }

    @Override
    public void execute() {

    }

    public void UpdateCurrentLocalTime(){
        //this will set the hour, min and second.
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    for(;;) {
//                        clock.setnHour(currentTime.getHour());
//                        clock.setnMinute(currentTime.getMinute());
//                        clock.setnSecond(currentTime.getSecond());
                        long lTime = System.currentTimeMillis();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy  hh mm ss a");
                        String dateToString = sdf.format(lTime);
                        digitalClockView.setMyFormat(dateToString);
                        sleep(1000);

                    }
                }catch(Exception e){}

            }
        };
        th.start();

    }
}
