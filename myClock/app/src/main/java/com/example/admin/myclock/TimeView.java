package com.example.admin.myclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class TimeView extends AppCompatActivity {

    //take everything from the xml and put it here that corresponds to the time.
    private int nHour = 0;
    private int nMinute = 0;
    private int nSecond = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Button bAnalogClock = findViewById(R.id.Analog_Clock);
        Button DigitalClock = findViewById(R.id.Digital_Clock);
        //use set on action for these and set them equal to nHour,nMinute,and nSecond
        Button ChangeTime = findViewById(R.id.Change_Time);
        TextView txnMinute = findViewById(R.id.nMinute);
        TextView txnHour = findViewById(R.id.nHour);
        TextView txnSecond = findViewById(R.id.nSecond);

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
