package com.example.admin.myclock;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import rm.com.clocks.Clock;
import rm.com.clocks.ClockDrawable;
import rm.com.clocks.Stroke;

public class AnalogView extends AppCompatActivity {

private DrawAnalog[] myClock;
private DrawAnalog clock;
private LinearLayout myLayout;
private Model clockModel= new Model(0,0,0);
private TimeChangeController changeTime;
private int count = 0;
private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analogclock);
        changeTime  = new TimeChangeController(clockModel,this);
        final EditText txnMinute_Day = findViewById(R.id.nMinute_Day);
        final EditText txnHour_Month = findViewById(R.id.nHour_Month);
        final EditText txnSecond_Year = findViewById(R.id.nSecond_Year);
        TextView CurrentDate = findViewById(R.id.Date);
        CurrentDate.setText(changeTime.CurrentDate());

        myClock = new DrawAnalog[30];
        for (int i = 0; i < 30; i++) {
            myClock[i] = new DrawAnalog(this, clockModel);
        }
        Button bChange_Time = findViewById(R.id.Change_Time);
        clock = new DrawAnalog(this);
        bChange_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    clockModel.setnHour(Integer.parseInt(txnHour_Month.getText().toString()));
                    clockModel.setnMinute(Integer.parseInt(txnMinute_Day.getText().toString()));
                    clockModel.setnSecond(Integer.parseInt(txnSecond_Year.getText().toString()));
                    clockModel.isCurrentTime(false);
                    changeTime.Tick();
                    myLayout = findViewById(R.id.myLayout);
                    myClock[count] = new DrawAnalog(v.getContext(), clockModel);
                    myClock[count].setPadding(31, 31, 31, 0);
                    myClock[count].setMinimumHeight(400);
                    myClock[count].setMinimumWidth(200);
                    myLayout.addView(myClock[count], position);
                    position++;
                    count++;
                    Model tmp;
                    changeTime.setqUndo(tmp = new Model(clockModel.getnHour(), clockModel.getnMinute(), clockModel.getnSecond()));
            }
        });

        Button bUndo = findViewById(R.id.bUndo);
        bUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this will remove what was last done and then set the time back to the previous obj
                if(changeTime.getqUndo() != null) {
                changeTime.setqRedo(changeTime.getqUndo());
                changeTime.undo();
                clockModel.setnHour(changeTime.getqRedo().getnHour());
                clockModel.setnMinute(changeTime.getqRedo().getnMinute());
                clockModel.setnSecond(changeTime.getqRedo().getnSecond());
                myClock[count] = new DrawAnalog(v.getContext(), clockModel);
                myClock[count].setPadding(31, 31, 31, 0);
                myClock[count].setMinimumHeight(400);
                myClock[count].setMinimumWidth(200);
                position = position - 1;
                count = count - 1;
                myLayout.removeView(myClock[count]);
                myLayout.addView(myClock[count], position);
            }

            }
        });

        Button bRedo = findViewById(R.id.bRedo);
        bRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(changeTime.getqRedo() != null) {
                    changeTime.setqUndo(changeTime.getqRedo());
                    changeTime.redo();
                    clockModel.setnSecond(changeTime.getqUndo().getnSecond());
                    clockModel.setnMinute(changeTime.getqUndo().getnMinute());
                    clockModel.setnHour(changeTime.getqUndo().getnHour());
                    myClock[count] = new DrawAnalog(v.getContext(), clockModel);
                    myClock[count].setPadding(31, 31, 31, 0);
                    myClock[count].setMinimumHeight(400);
                    myClock[count].setMinimumWidth(200);
                    position = position - 1;
                    count = count - 1;
                    myLayout.removeView(myClock[count]);
                    myLayout.addView(myClock[count], position);
                }
            }
        });

        Button bChange_Date = findViewById(R.id.Change_Date);
        bChange_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView CurrentDate = findViewById(R.id.Date);
                clockModel.setnDay(Integer.parseInt(txnMinute_Day.getText().toString()));
                clockModel.setnMonth(Integer.parseInt(txnHour_Month.getText().toString()));
                clockModel.setnYear(Integer.parseInt(txnSecond_Year.getText().toString()));
                CurrentDate.setText(changeTime.UpdateDate());
            }
        });


    }
}

