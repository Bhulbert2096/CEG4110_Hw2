package com.example.admin.myclock;

import android.app.ExpandableListActivity;
import android.widget.DigitalClock;
import android.widget.TextView;

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

    private ClockModel clockModel;
    private Calendar calendar;
    private Time currentTime;
    private myDigitalClock digitalClockView;
    private MainPage userChangesTimeView;

    public TimeChangeController(ClockModel myClock, myDigitalClock dClock){
        this.digitalClockView = dClock;
        this.clockModel = myClock;
       // this.time = view;
    }

    public TimeChangeController(ClockModel myClock,MainPage view){
        this.clockModel = myClock;
        this.userChangesTimeView = view;
    }

    @Override
    public void execute() {

    }

    public String UpdateCurrentLocalTime(){
        //this will set the hour, min and second.
        clockModel.setnSecond(clockModel.getnSecond());
        clockModel.setnMinute(clockModel.getnMinute());
        clockModel.setnHour(clockModel.getnHour());
        currentTime = new Time(clockModel.getnHour(),clockModel.getnMinute(),clockModel.getnSecond());
        return currentTime.toString();

    }

    public String obtainCurrentTime(){
        long lTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("hh mm ss a");
        return sdf.format(lTime);
    }

    public String Tick(){

    clockModel.setnSecond(clockModel.getnSecond()+1);
    if(clockModel.getnSecond() > 59){
        int nTmp = clockModel.getnMinute()+clockModel.getnSecond() / 60;
        clockModel.setnMinute(nTmp);
        clockModel.setnSecond(clockModel.getnSecond() % 60);
    }
    if(clockModel.getnMinute() > 59) {
        int nTmp = clockModel.getnHour()+clockModel.getnMinute() / 60;
        clockModel.setnMinute(nTmp);
        clockModel.setnMinute(clockModel.getnMinute() % 60);
    }
    if(clockModel.getnHour() > 24){
        clockModel.setnHour(0);
    }



     currentTime = new Time(clockModel.getnHour(),clockModel.getnMinute(),clockModel.getnSecond());
    return currentTime.toString();
    }

}
