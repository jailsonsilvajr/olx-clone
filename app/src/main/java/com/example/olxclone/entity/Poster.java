package com.example.olxclone.entity;

import java.util.Calendar;

public class Poster {

    private  int id;
    private String title;
    private int price;
    private int day;
    private int month;
    private int hour;
    private int minute;
    private String location;

    public Poster(int id, String title, int price, int day, int month, int hour, int minute, String location){

        this.id = id;
        this.title = title;
        this.price = price;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
