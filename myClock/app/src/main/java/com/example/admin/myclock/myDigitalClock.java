package com.example.admin.myclock;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class myDigitalClock extends AppCompatActivity {

    // private TextView CurrentTime;
     private   TimeChangeController t = new TimeChangeController(new ClockModel(0,0,0), this);
    private int nHour = 0;
    private int nMinute = 0;
    private int nSecond = 0;
    private boolean ClockisSet = false;
    private DateModel myDate = new DateModel(0, 0, 0);
    private ClockModel myClock = new ClockModel(0, 0, 0);
    private DateChangeController dateController = new DateChangeController(new DateModel(0, 0, 0), this);
    private TextView[] newTextView;
    private LinearLayout myClockView;
    private int nCount = 0;

    private String sFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digitalclock);
        t = new TimeChangeController(myClock,this);
        dateController = new DateChangeController(myDate,this);

        final EditText txnMinute_Day = findViewById(R.id.nMinute_Day);
        final EditText txnHour_Month = findViewById(R.id.nHour_Month);
        final EditText txnSecond_Year = findViewById(R.id.nSecond_Year);
        TextView CurrentDate = findViewById(R.id.CurrentDate);
       CurrentDate.setText(dateController.CurrentDate());
        newTextView = new TextView[10];
        for (int i  = 0; i < 10; i++){
            newTextView[i] = new TextView(this);

        }


        Button changeDate = findViewById(R.id.Change_Date);
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView CurrentDate = findViewById(R.id.CurrentDate);
                myDate.setnDay(Integer.parseInt(txnMinute_Day.getText().toString()));
                myDate.setnMonth(Integer.parseInt(txnHour_Month.getText().toString()));
                myDate.setnYear(Integer.parseInt(txnSecond_Year.getText().toString()));
                CurrentDate.setText(dateController.UpdateDate());
            }
        });

        LocalTime();

        Button changeTime = findViewById(R.id.change_Time);
        changeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //need to add error checking here.
                //if the user enters a empty time then display current time.
               myClockView = findViewById(R.id.multi);
                myClockView.addView(newTextView[nCount]);
                myClock.setnHour(Integer.parseInt(txnHour_Month.getText().toString()));
                myClock.setnMinute(Integer.parseInt(txnMinute_Day.getText().toString()));
                myClock.setnSecond(Integer.parseInt(txnSecond_Year.getText().toString()));
               // t.UpdateCurrentLocalTime();
                ClockisSet = true;
                newTextView[nCount].setText(t.UpdateCurrentLocalTime());
                UsersSetTime(newTextView[nCount]);
                nCount++;
            }
        });


    }

    public synchronized void LocalTime() {
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    while(!ClockisSet) {
                        sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tx = findViewById(R.id.CurrentTime);
                                tx.setText(t.obtainCurrentTime());
                            }
                        });
                    }
                }catch(Exception e){}
            }
        };
        th.start();
    }

    public synchronized void UsersSetTime(final TextView tx2){
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    while(ClockisSet) {
                        sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               TextView tx = findViewById(R.id.CurrentTime);
                                //Maybe this will now start incrementing the clock.
                                t.Tick();
                                tx.setText(myClock.getnHour() + ":" + myClock.getnMinute() + ":" + myClock.getnSecond());


                            }
                        });
                    }
                }catch(Exception e){}
            }
        };
        th.start();
    }

    }
