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
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

public class TimeChangeController implements Command{

    /*
     *This class will contain the main functionality between the view class and the model class.
     */

    private Model myClock;
    private Time currentTime;
    private myDigitalClock digitalClockView;
    private MainPage userChangesTimeView;
    private AnalogView analogView;
    private Queue<Model> qUndo = new LinkedList<Model>();
    private Queue<Model> qRedo = new LinkedList<Model>();


    public TimeChangeController(Model myClock, myDigitalClock dClock){
        this.digitalClockView = dClock;
        this.myClock = myClock;
       // this.time = view;
    }

    public TimeChangeController(Model myClock,MainPage view){
        this.myClock = myClock;
        this.userChangesTimeView = view;
    }
    public TimeChangeController(Model myClock,DrawAnalog view){
        this.myClock = myClock;
    }
    public TimeChangeController(Model myClock,AnalogView view){
        this.myClock = myClock;
        this.analogView = view;
    }

    @Override
    public void execute() {

    }
    @Override
    public void undo() {
        if(qUndo.peek() != null){
            qUndo.poll();
        }
    }
    @Override
    public void redo() {
        if(qRedo.peek() != null) {
            qRedo.poll();
        }

    }

    /*
     *This method will obtain the time that the user had set and will return a string containing the
     * specific time that the user had set.
     */
    public String UpdateCurrentLocalTime(){
        myClock.setnSecond(myClock.getnSecond());
        myClock.setnMinute(myClock.getnMinute());
        myClock.setnHour(myClock.getnHour());
        currentTime = new Time(myClock.getnHour(),myClock.getnMinute(),myClock.getnSecond());
        return currentTime.toString();

    }

    /*
     *This will obtain the current local time and return a date formate as a string.
     */
    public String obtainCurrentTime(){
        long lTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        return sdf.format(lTime);
    }

    /*
     *This method will increment the seconds by one and will determine if it has reached a minute.
     * It will also check if the minute has reached an hour yet or not.
     */
    public  void Tick(){

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

    /*
     *This method will return the current date and return a string in a specific format
     */
    public String CurrentDate(){
        long lTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        return sdf.format(lTime);
    }


    /*
     *This will get the updated date and return it as a string to the user.
     */
    public String UpdateDate(){
        return (Integer.toString(myClock.getnMonth()) + "/" + Integer.toString(myClock.getnDay()) + "/" +Integer.toString(myClock.getnYear()));
    }

    /*
     *These will look at the first item in the undo and redo queues and will set the values in them
     * as well.
     */
    public Model getqUndo() {
        return qUndo.peek();
    }
    public Model getqRedo(){
        return qRedo.peek();
    }
    public void setqRedo(Model clockModel){
        qRedo.offer(clockModel);
    }
    public void setqUndo(Model clockModel){
        qUndo.offer(clockModel);
    }


}
