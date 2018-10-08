package com.example.admin.myclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class AnalogView extends View {
//    private Paint myPaint;
   private boolean isBeginning = true;
//    private int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12};
//    private int nHeight = 0;
//    private int nWidth = 0;
//    private int nPadding = 0;
//    private int nHandTrunc, nHourHandTrunc = 0;
//    private int nRadius = 0;
//    private int fontSize = 0;
//    private int nSpacing = 0;
//    private Rect rect = new Rect();
    private Canvas myCanvas = new Canvas();

    private ClockModel myClock = new ClockModel();
    private TimeChangeController changeTime;
    private int count = 0;

    public AnalogView(Context context,ClockModel clock) {
        super(context);
        this.myClock = clock;
    }

    public AnalogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnalogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initialClock() {
        changeTime = new TimeChangeController(myClock,this);
        myClock.setnHeight(500);
        myClock.setnWidth(600);
        myClock.setnPadding(myClock.getnSpacing() + 50);
        myClock.setFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));
        int nMinimum = Math.min(myClock.getnHeight(), myClock.getnWidth());
        myClock.setnRadius(nMinimum /2 - myClock.getnPadding());
        myClock.setnHandTrunc(nMinimum /20);
        myClock.setnHourHandTrunc(nMinimum /7);
        myClock.setMyPaint(new Paint());
       isBeginning = false;
    }
    private void Clock(){
        myClock = new ClockModel(myClock.getnHour(),myClock.getnMinute(),myClock.getnSecond());
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        if (isBeginning) {
            initialClock();
        }

         if(!myClock.isCurrentTime()) {
             Clock();
             drawCircle(canvas);
             drawCenter(canvas);
             drawNumeral(canvas);
             drawHand(canvas, myClock.getnHour(), true);
             drawHand(canvas, myClock.getnMinute(), false);
             drawHand(canvas, myClock.getnSecond(), false);
             changeTime.Tick();
             postInvalidateDelayed(1000);
         }else{
             drawCircle(canvas);
             drawCenter(canvas);
             drawNumeral(canvas);
             drawHands(canvas);

             postInvalidateDelayed(500);
             invalidate();
         }

    }

    public void myDraw(Canvas canvas){
        this.myCanvas = canvas;
    }
    private void drawHand(Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int nHandRadius = isHour ? myClock.getnRadius() - myClock.getnHandTrunc() - myClock.getnHourHandTrunc() : myClock.getnRadius() - myClock.getnHandTrunc();
        canvas.drawLine(myClock.getnWidth() / 2, myClock.getnHeight() / 2,
                (float) (myClock.getnWidth() / 2 + Math.cos(angle) * nHandRadius),
                (float) (myClock.getnHeight() / 2 + Math.sin(angle) * nHandRadius),
                myClock.getMyPaint());

    }

    private void drawHands(Canvas canvas) {
        //in here we will call on the methods from controller
        //the method I will call here is the set time and will also get on button action.
        Calendar c = Calendar.getInstance();
      // Tick();
        //c.set(0000,00,00,6,0,Calendar.SECOND);
       // count++;
        float hour = c.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + c.get(Calendar.MINUTE) / 60) * 5f, true);
        drawHand(canvas, c.get(Calendar.MINUTE), false);
        drawHand(canvas,c.get(Calendar.SECOND), false);
    }

    private void drawNumeral(Canvas canvas) {
        myClock.getMyPaint().setTextSize(myClock.getFontSize());

        for (int number : myClock.getNumbers()) {
            String tmp = String.valueOf(number);
            myClock.getMyPaint().getTextBounds(tmp, 0, tmp.length(), myClock.getRect());
            double angle = Math.PI / 6 * (number - 3);
            int x = (int) (myClock.getnWidth() / 2 + Math.cos(angle) * myClock.getnRadius() - myClock.getRect().width() / 2);
            int y = (int) (myClock.getnHeight() / 2 + Math.sin(angle) * myClock.getnRadius() + myClock.getRect().height() / 2);
            canvas.drawText(tmp, x, y, myClock.getMyPaint());
        }
    }

    private void drawCenter(Canvas canvas) {
        myClock.getMyPaint().setStyle(Paint.Style.FILL);
        canvas.drawCircle(myClock.getnWidth() / 2, myClock.getnHeight() / 2, 12,  myClock.getMyPaint());
    }

    private void drawCircle(Canvas canvas) {
        myClock.getMyPaint().reset();
        myClock.getMyPaint().setColor(getResources().getColor(android.R.color.black));
        myClock.getMyPaint().setStrokeWidth(5);
        myClock.getMyPaint().setStyle(Paint.Style.STROKE);
        myClock.getMyPaint().setAntiAlias(true);
        canvas.drawCircle(myClock.getnWidth() / 2, myClock.getnHeight() / 2, myClock.getnRadius() + myClock.getnPadding() - 10,  myClock.getMyPaint());
    }

}

