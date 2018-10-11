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

public class DrawAnalog extends View {
   private boolean isBeginning = true;
    private boolean isFirst;

    private Model myClock = new Model();
    private TimeChangeController changeTime;

    public DrawAnalog(Context context) {
        super(context);
    }
    public DrawAnalog(Context context,Model clock) {
        super(context);
        this.myClock = clock;
    }

    public DrawAnalog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawAnalog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initialClock() {
        changeTime = new TimeChangeController(myClock,this);
        myClock.setnHeight(300);
        myClock.setnWidth(400);
        myClock.setnPadding(myClock.getnSpacing() + 50);
        myClock.setFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));
        int nMinimum = Math.min(myClock.getnHeight(), myClock.getnWidth());
        myClock.setnRadius(nMinimum /2 - myClock.getnPadding());
        myClock.setnHandTrunc(nMinimum /20);
        myClock.setnHourHandTrunc(nMinimum /7);
        myClock.setMyPaint(new Paint());
       isBeginning = false;
       isFirst = true;
    }
    private void Clock(){
        myClock.setnHeight(300);
        myClock.setnWidth(400);
        myClock.setnPadding(myClock.getnSpacing() + 50);
        myClock.setFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));
        int nMinimum = Math.min(myClock.getnHeight(), myClock.getnWidth());
        myClock.setnRadius(nMinimum /2 - myClock.getnPadding());
        myClock.setnHandTrunc(nMinimum /20);
        myClock.setnHourHandTrunc(nMinimum /7);
        myClock.setMyPaint(new Paint());
        isBeginning = false;
    }

    /*
     * This method will update everytime invalidate or postinvalidate is called and will draw the circle, numbers, center dot, and the hands
     * that correspond to their respective time.
     */
    @Override
    protected void onDraw(final Canvas canvas) {
        if (isBeginning) {
            initialClock();
        }

         if(!myClock.isCurrentTime()) {
            if(isFirst) {
                Clock();
                isFirst = false;
            }
             drawCircle(canvas);
             drawCenterDot(canvas);
             drawNumbers(canvas);
             drawHand(canvas, myClock.getnHour(), true);
             drawHand(canvas, myClock.getnMinute(), false);
             drawHand(canvas, myClock.getnSecond(), false);
                 changeTime.Tick();
             myClock.isCurrentTime(false);
             postInvalidateDelayed(1000);
         }else{
             drawCircle(canvas);
             drawCenterDot(canvas);
             drawNumbers(canvas);
             drawHands(canvas);

             postInvalidateDelayed(500);
             invalidate();
         }

    }

    /*
     * This will draw the hand at a specific angle and radius.  The hand is represented as  a line
     * which will be drawn based on the width and height of the canvas.
     */
    private void drawHand(Canvas canvas, double time, boolean isHour) {
        double nAngle = Math.PI * time / 30 - Math.PI / 2;
        int nHandRadius = isHour ? myClock.getnRadius() - myClock.getnHandTrunc() - myClock.getnHourHandTrunc() : myClock.getnRadius() - myClock.getnHandTrunc();
        canvas.drawLine(myClock.getnWidth() / 2, myClock.getnHeight() / 2,
                (float) (myClock.getnWidth() / 2 + Math.cos(nAngle) * nHandRadius),
                (float) (myClock.getnHeight() / 2 + Math.sin(nAngle) * nHandRadius),
                myClock.getMyPaint());

    }

    /*
     * This will draw the hands for the local time clock.  THis will call the draw hand method which is described above.
     */
    private void drawHands(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        float hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + calendar.get(Calendar.MINUTE) / 60) * 5f, true);
        drawHand(canvas, calendar.get(Calendar.MINUTE), false);
        drawHand(canvas,calendar.get(Calendar.SECOND), false);
    }

    /*
     * This will draw thhe numbers that corresponds to each number on the clock.  it will draw some
     * text at a specific x and y value given and calculated from the width, height and radius.
     */
    private void drawNumbers(Canvas canvas) {
        myClock.getMyPaint().setTextSize(myClock.getFontSize());

        for (int number : myClock.getNumbers()) {
            String tmp = String.valueOf(number);
            myClock.getMyPaint().getTextBounds(tmp, 0, tmp.length(), myClock.getRect());
            double angle = Math.PI / 6 * (number - 3);
            int xValue = (int) (myClock.getnWidth() / 2 + Math.cos(angle) * myClock.getnRadius() - myClock.getRect().width() / 2);
            int yValue = (int) (myClock.getnHeight() / 2 + Math.sin(angle) * myClock.getnRadius() + myClock.getRect().height() / 2);
            canvas.drawText(tmp, xValue, yValue, myClock.getMyPaint());
        }
    }

    /*
     *This will draw the center dot for the clock.
     */
    private void drawCenterDot(Canvas canvas) {
        myClock.getMyPaint().setStyle(Paint.Style.FILL);
        canvas.drawCircle(myClock.getnWidth() / 2, myClock.getnHeight() / 2, 12,  myClock.getMyPaint());
    }

    /*
     * This will draw the circle for the analog clock that goes around the numbers.
     */
    private void drawCircle(Canvas canvas) {
        myClock.getMyPaint().reset();
        myClock.getMyPaint().setColor(getResources().getColor(android.R.color.black));
        myClock.getMyPaint().setStrokeWidth(5);
        myClock.getMyPaint().setStyle(Paint.Style.STROKE);
        myClock.getMyPaint().setAntiAlias(true);
        canvas.drawCircle(myClock.getnWidth() / 2, myClock.getnHeight() / 2, myClock.getnRadius() + myClock.getnPadding() - 10,  myClock.getMyPaint());
    }

}

