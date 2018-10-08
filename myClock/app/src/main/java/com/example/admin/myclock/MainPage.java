package com.example.admin.myclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainPage extends AppCompatActivity {


    private TimeChangeController timeChange = new TimeChangeController(new ClockModel(0,0,0), this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        Button bDigitalClock = findViewById(R.id.Digital_Clock);
        Button bAnalogClock = findViewById(R.id.Analog_Clock);

        bDigitalClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),myDigitalClock.class);
                startActivity(startIntent);
            }
        });

        bAnalogClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),AnalogCreateView.class);
                startActivity(startIntent);
            }
        });
    }

}
