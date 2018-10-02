package com.example.admin.myclock;

/*
This will be just setters and getters for the clock class.
 * */
public class ClockModel {

    private int nHour;
    private int nSecond;
    private int nMinute;

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
