package com.example.admin.myclock;

public class DateModel {

    private int nYear;
    private int nWeek;
    private int nMonth;
    private int nDay;

    public DateModel(int day,int month,int year){
        this.nYear = year;
        this.nMonth = month;
        this.nDay = day;
    }
    public int getnDay() {
        return nDay;
    }

    public void setnDay(int nDay) {
        this.nDay = nDay;
    }

    public int getnMonth() {
        return nMonth;
    }

    public int getnWeek() {
        return nWeek;
    }

    public int getnYear() {
        return nYear;
    }

    public void setnMonth(int nMonth) {
        this.nMonth = nMonth;
    }

    public void setnWeek(int nWeek) {
        this.nWeek = nWeek;
    }

    public void setnYear(int nYear) {
        this.nYear = nYear;
    }
}
