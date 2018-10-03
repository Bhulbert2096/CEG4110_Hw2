package com.example.admin.myclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        setContentView(R.layout.digitalclock);

        Button bAnalogClock = findViewById(R.id.Analog_Clock);
        Button DigitalClock = findViewById(R.id.Digital_Clock);
        //use set on action for these and set them equal to nHour,nMinute,and nSecond
        Button ChangeTime = findViewById(R.id.Change_Time);
        final TextView txnMinute = findViewById(R.id.nMinute);
        final TextView txnHour = findViewById(R.id.nHour);
        final TextView txnSecond = findViewById(R.id.nSecond);
        ChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setnHour(Integer.parseInt(txnHour.getText().toString()));
                setnMinute(Integer.parseInt(txnMinute.getText().toString()));
                setnSecond(Integer.parseInt(txnSecond.getText().toString()));
            }
        });


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
