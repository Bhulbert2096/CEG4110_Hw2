package com.example.admin.myclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class myDigitalClock extends AppCompatActivity {

    private TimeChangeController timeChange = new TimeChangeController(new Model(0, 0, 0), this);
    private boolean ClockisSet = false;
    private Model myClock = new Model(0, 0, 0);
    private TextView[] newTextView;
    private LinearLayout myClockView;
    private int nCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digitalclock);
         timeChange = new TimeChangeController(myClock, this);

        final EditText txnMinute_Day = findViewById(R.id.nMinute_Day);
        final EditText txnHour_Month = findViewById(R.id.nHour_Month);
        final EditText txnSecond_Year = findViewById(R.id.nSecond_Year);
        TextView CurrentDate = findViewById(R.id.CurrentDate);
        CurrentDate.setText(timeChange.CurrentDate());
        newTextView = new TextView[10];
        for (int i = 0; i < 10; i++) {
            newTextView[i] = new TextView(this);

        }

        Button changeDate = findViewById(R.id.Change_Date);
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txnHour_Month.getText().equals("") && txnMinute_Day.getText().equals("") && txnSecond_Year.getText().equals("")) {
                    TextView CurrentDate = findViewById(R.id.CurrentDate);
                    myClock.setnDay(Integer.parseInt(txnMinute_Day.getText().toString()));
                    myClock.setnMonth(Integer.parseInt(txnHour_Month.getText().toString()));
                    myClock.setnYear(Integer.parseInt(txnSecond_Year.getText().toString()));
                    CurrentDate.setText(timeChange.UpdateDate());
                }
                else{
                    txnHour_Month.setText("Please");
                    txnMinute_Day.setText("Enter");
                    txnSecond_Year.setText("Numbers");
                }
            }
        });

        LocalTime();

        final Button changeTime = findViewById(R.id.change_Time);
        changeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txnHour_Month.getText().equals("") && txnMinute_Day.getText().equals("") && txnSecond_Year.getText().equals("")) {
                    myClockView = findViewById(R.id.multi);
                    myClockView.addView(newTextView[nCount]);
                    myClock.setnHour(Integer.parseInt(txnHour_Month.getText().toString()));
                    myClock.setnMinute(Integer.parseInt(txnMinute_Day.getText().toString()));
                    myClock.setnSecond(Integer.parseInt(txnSecond_Year.getText().toString()));
                    Model tmp;
                    timeChange.setqUndo(tmp = new Model(myClock.getnHour(), myClock.getnMinute(), myClock.getnSecond()));
                    ClockisSet = true;
                    newTextView[nCount].setText(timeChange.UpdateCurrentLocalTime());
                    UsersSetTime(newTextView[nCount]);

                    nCount++;
                }else{
                    txnHour_Month.setText("Please");
                    txnMinute_Day.setText("Enter");
                    txnSecond_Year.setText("Numbers");
                }
            }
        });

        Button bUndo = findViewById(R.id.bUndo);
        bUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timeChange.getqUndo() != null) {
                    timeChange.setqRedo(timeChange.getqUndo());
                    timeChange.undo();
                    myClock.setnHour(timeChange.getqRedo().getnHour());
                    myClock.setnMinute(timeChange.getqRedo().getnMinute());
                    myClock.setnSecond(timeChange.getqRedo().getnSecond());
                    newTextView[nCount--].setText(timeChange.UpdateCurrentLocalTime());
                }
            }
        });

        Button bRedo = findViewById(R.id.bRedo);
        bRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timeChange.getqRedo() != null) {
                    timeChange.setqUndo(timeChange.getqRedo());
                    timeChange.redo();
                    myClock.setnSecond(timeChange.getqUndo().getnSecond());
                    myClock.setnMinute(timeChange.getqUndo().getnMinute());
                    myClock.setnHour(timeChange.getqUndo().getnHour());
                    newTextView[nCount--].setText(timeChange.UpdateCurrentLocalTime());
                }
            }
        });

    }

    /*
     *This method will obtain the current time from the timechangecontroller class and display the
     * current time to the console.
     */
    public synchronized void LocalTime() {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    while (!ClockisSet) {
                        sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            TextView tx = findViewById(R.id.CurrentTime);
                                tx.setText(timeChange.obtainCurrentTime());
                            }
                        });
                    }
                } catch (Exception e) {
                }
            }
        };
        th.start();
    }

    /*
    *  This will take in the textview that corresponds to the view that needs to be
    *  added to linear layout. This method will call the tick method and will then get the
    *  corresponding time and display it to the console.
    */
    public synchronized void UsersSetTime(final TextView tx) {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    while (ClockisSet) {
                        sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timeChange.Tick();
                                tx.setText(myClock.getnHour() + ":" + myClock.getnMinute() + ":" + myClock.getnSecond());


                            }
                        });
                    }
                } catch (Exception e) {
                }
            }
        };
        th.start();
    }

}

