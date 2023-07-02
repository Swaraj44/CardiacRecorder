package com.example.cardiacrecorder;

import java.io.Serializable;

public class DATA implements Serializable {
    private String date, time, systolic_pressure, diastolic_pressure, heart_rate, comment;

    public DATA() {

    }

    public DATA(String date, String time, String systolic_pressure, String diastolic_pressure, String heart_rate,String comment) {
        this.date = date;
        this.time = time;
        this.systolic_pressure = systolic_pressure;
        this.diastolic_pressure = diastolic_pressure;
        this.heart_rate = heart_rate;
        this.comment = comment;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSystolic_pressure() {
        return systolic_pressure;
    }

    public void setSystolic_pressure(String systolic_pressure) {
        this.systolic_pressure = systolic_pressure;
    }

    public String getDiastolic_pressure() {
        return diastolic_pressure;
    }

    public void setDiastolic_pressure(String diastolic_pressure) {
        this.diastolic_pressure = diastolic_pressure;
    }

    public String getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
