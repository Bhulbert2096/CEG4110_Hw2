package com.example.admin.myclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class myDigitalClock extends AppCompatActivity {

   // private TextView CurrentTime;
    private   TimeChangeController t = new TimeChangeController(new ClockModel(), this);

    private String sFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp);


        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    for(;;) {
                        sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView CurrentTime = findViewById(R.id.CurrentTime);
                                long lTime = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy  hh mm ss a");
                                String dateToString = sdf.format(lTime);
                                CurrentTime.setText(dateToString);
                                //setMyFormat(dateToString);
                            }
                        });
//                        clock.setnHour(currentTime.getHour());
//                        clock.setnMinute(currentTime.getMinute());
//                        clock.setnSecond(currentTime.getSecond());



                    }
                }catch(Exception e){}

            }
        };
        th.start();


        // t.UpdateCurrentLocalTime();

        //CurrentTime.setText(getTimeAndDate());
        Button bt = findViewById(R.id.myButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView CurrentTime = findViewById(R.id.CurrentTime);
                t.UpdateCurrentLocalTime();
                CurrentTime.setText(getTimeAndDate());
            }
        });
    }

    public String getTimeAndDate(){
        return sFormat;
    }

    public void setMyFormat(String sString){
        sFormat = sString;
    }

    public void setCurrentTime(TextView currentTime) {
       // CurrentTime = currentTime;
    }
}
