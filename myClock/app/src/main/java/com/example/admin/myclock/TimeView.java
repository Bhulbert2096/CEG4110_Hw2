package com.example.admin.myclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class TimeView extends AppCompatActivity {

    //take everything from the xml and put it here that corresponds to the time.
    private int nHour = 0;
    private int nMinute = 0;
    private int nSecond = 0;
   // private   TimeChangeController timeChange = new TimeChangeController(new ClockModel(), this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Button bAnalogClock = findViewById(R.id.Analog_Clock);
        Button DigitalClock = findViewById(R.id.Digital_Clock);
        //use set on action for these and set them equal to nHour,nMinute,and nSecond
        //Button ChangeTime = findViewById(R.id.Change_Time);
        final EditText txnMinute = findViewById(R.id.nMinute_Day);
        final EditText txnHour = findViewById(R.id.nHour_Month);
        final EditText txnSecond = findViewById(R.id.nSecond_Year);
//        ChangeTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setnHour(Integer.parseInt(txnHour.getText().toString()));
//                setnMinute(Integer.parseInt(txnMinute.getText().toString()));
//                setnSecond(Integer.parseInt(txnSecond.getText().toString()));
//            }
//        });

        Intent startIntent = new Intent(getApplicationContext(),myDigitalClock.class);
        startActivity(startIntent);


    }



    public int getnHour() {
        return nHour;
    }

    public void setnHour(int nHour) {
        this.nHour = nHour;
    }

    public int getnMinute() {
        return nMinute;
    }

    public void setnMinute(int nMinute) {
        this.nMinute = nMinute;
    }

    public int getnSecond() {
        return nSecond;
    }

    public void setnSecond(int nSecond) {
        this.nSecond = nSecond;
    }
}
