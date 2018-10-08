package com.example.admin.myclock;

import android.graphics.Paint;
import android.graphics.Rect;

/*
This will be just setters and getters for the clock class.
 * */
public class ClockModel {

    private int nHour;
    private int nSecond;
    private int nMinute;

    private Paint myPaint;
    private boolean isCurrentTime = true;
    private int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12};
    private int nHeight = 0;
    private int nWidth = 0;
    private int nPadding = 0;
    private int nHandTrunc, nHourHandTrunc = 0;
    private int nRadius = 0;
    private int fontSize = 0;
    private int nSpacing = 0;
    private Rect rect = new Rect();

    public ClockModel(int hour, int minute, int second){
        this.nHour = hour;
        this.nSecond = second;
        this.nMinute = minute;
    }

    public ClockModel(){

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

    /**
     * Analog Clock getters and setters
     */

    public Paint getMyPaint() {
        return myPaint;
    }

    public void setMyPaint(Paint myPaint) {
        this.myPaint = myPaint;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public int getnHeight() {
        return nHeight;
    }

    public void setnHeight(int nHeight) {
        this.nHeight = nHeight;
    }

    public int getnWidth() {
        return nWidth;
    }

    public void setnWidth(int nWidth) {
        this.nWidth = nWidth;
    }

    public int getnPadding() {
        return nPadding;
    }

    public void setnPadding(int nPadding) {
        this.nPadding = nPadding;
    }

    public int getnHandTrunc() {
        return nHandTrunc;
    }

    public void setnHandTrunc(int nHandTrunc) {
        this.nHandTrunc = nHandTrunc;
    }

    public int getnHourHandTrunc() {
        return nHourHandTrunc;
    }

    public void setnHourHandTrunc(int nHourHandTrunc) {
        this.nHourHandTrunc = nHourHandTrunc;
    }

    public int getnRadius() {
        return nRadius;
    }

    public void setnRadius(int nRadius) {
        this.nRadius = nRadius;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getnSpacing() {
        return nSpacing;
    }

    public void setnSpacing(int nSpacing) {
        this.nSpacing = nSpacing;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public boolean isCurrentTime() { return isCurrentTime; }
    public void isCurrentTime(boolean b){
        isCurrentTime = b;
    }
}
