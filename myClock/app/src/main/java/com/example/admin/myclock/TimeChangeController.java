package com.example.admin.myclock;

import android.app.ExpandableListActivity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.DigitalClock;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executor;

public class TimeChangeController implements Command{
    /*
    * This will extend the MainPage cause this is the user input page(main page)
    * What if we had two methods the first will get the current time using the built in clock method
    * The second will get in user input for changing the clock and keeps that one running
    *
    * */

    private ClockModel myClock;
    private Calendar calendar;
    private Time currentTime;
    private myDigitalClock digitalClockView;
    private MainPage userChangesTimeView;
    private AnalogView analogClock;

    public TimeChangeController(ClockModel myClock, myDigitalClock dClock){
        this.digitalClockView = dClock;
        this.myClock = myClock;
       // this.time = view;
    }

    public TimeChangeController(ClockModel myClock,MainPage view){
        this.myClock = myClock;
        this.userChangesTimeView = view;
    }
    public TimeChangeController(ClockModel myClock,AnalogView view){
        this.myClock = myClock;
        this.analogClock = view;
    }

    @Override
    public void execute() {

    }

    public synchronized String UpdateCurrentLocalTime(){
        //this will set the hour, min and second.
        myClock.setnSecond(myClock.getnSecond());
        myClock.setnMinute(myClock.getnMinute());
        myClock.setnHour(myClock.getnHour());
        currentTime = new Time(myClock.getnHour(),myClock.getnMinute(),myClock.getnSecond());
        return currentTime.toString();

    }

    public synchronized String obtainCurrentTime(){
        long lTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("hh mm ss a");
        return sdf.format(lTime);
    }

    public synchronized void Tick(){

        myClock.setnSecond(myClock.getnSecond()+1);
        if(myClock.getnSecond() > 59){
            int nTmp = myClock.getnMinute()+myClock.getnSecond() / 60;
            myClock.setnMinute(nTmp);
            myClock.setnSecond(myClock.getnSecond() % 60);
        }
        if(myClock.getnMinute() > 59) {
            int nTmp = myClock.getnHour()+myClock.getnMinute() / 60;
            myClock.setnMinute(nTmp);
            myClock.setnMinute(myClock.getnMinute() % 60);
        }
        if(myClock.getnHour() > 24){
            myClock.setnHour(0);
        }

    }


}
