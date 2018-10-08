package com.example.admin.myclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AnalogCreateView extends AppCompatActivity {

private AnalogView[] myClock;
private LinearLayout myLayout;
private ClockModel clockModel= new ClockModel(0,0,0);
private int count = 0;
private ArrayList<AnalogView> analogList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analogclock);
    //Canvas myCanvas = new Canvas();
        myLayout = findViewById(R.id.main);
        final EditText txnMinute_Day = findViewById(R.id.nMinute_Day);
        final EditText txnHour_Month = findViewById(R.id.nHour_Month);
        final EditText txnSecond_Year = findViewById(R.id.nSecond_Year);
        TextView tx = new TextView(this);
        myClock = new AnalogView[20];
        for (int i = 0; i < 20; i++){
            myClock[i] = new AnalogView(this,clockModel);
        }
//        newTextView = new TextView[10];
//        for (int i  = 0; i < 10; i++){
//            newTextView[i] = new TextView(this);
//
//        }

    AnalogView c = new AnalogView(this,clockModel);
   // myLayout.addView(myClock);
    Button bChange_Time = findViewById(R.id.Change_Time);
    clockModel.isCurrentTime(true);
    bChange_Time.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clockModel.setnHour(Integer.parseInt(txnHour_Month.getText().toString()));
            clockModel.setnMinute(Integer.parseInt(txnMinute_Day.getText().toString()));
            clockModel.setnSecond(Integer.parseInt(txnSecond_Year.getText().toString()));
            clockModel.isCurrentTime(false);
            //= new AnalogView(this);
           analogList.add( new AnalogView(v.getContext(),clockModel));
            myLayout.addView(analogList.get(count));
            count++;
        }
    });



//        myLayout.addView(b);
//    myLayout.addView(myClock);
//
//    //myLayout.addView(myClock);
//    //myLayout.addView(myClock);
//
//    myLayout.addView(tx);

    //myClock.(myCanvas);

    }

//    public void runAnalogClock(){
//        Thread th = new Thread(){
//            @Override
//            public void run() {
//                try {
//                    while(true) {
//                        sleep(1000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                // TextView tx = findViewById(R.id.CurrentTime);
//                                //tx.setText(t.obtainCurrentTime());
//                                //  clockDrawable.setHours(1);
//                                //clockDrawable.setMinutes(clockDrawable.getMinutes() + count);
////                                clockDrawable.animateToTime(clockDrawable.getHours(),clockDrawable.getMinutes()+count);
////                               count++;
//                                myClock[0].Tick();
//                                //  clockDrawable.animateToTime(clockDrawable.getHours(), clockDrawable.getMinutes());
//                            }
//                        });
//                   }
//                }catch(Exception e){}
//            }
//        };
//        th.start();
//    }
}
