package com.example.admin.myclock;

import android.app.ExpandableListActivity;

import java.time.Clock;
import java.time.LocalTime;

public class TimeChangeController implements Command{
    /*
    * This will extend the MainPage cause this is the user input page(main page)
    * What if we had two methods the first will get the current time using the built in clock method
    * The second will get in user input for changing the clock and keeps that one running
    *
    * */

    private ClockModel clock;
    private TimeView time;
    private LocalTime currentTime;

    public TimeChangeController(ClockModel myClock, TimeView view){
        this.clock = myClock;
        this.time = view;
    }

    @Override
    public void execute() {

    }

    public void getCurrentLocalTime(){
        //this will set the hour, min and second.
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    for(;;) {
                        clock.setnHour(currentTime.getHour());
                        clock.setnMinute(currentTime.getMinute());
                        clock.setnSecond(currentTime.getSecond());
                        sleep(1000);

                    }
                }catch(Exception e){}

            }
        };

    }
}
