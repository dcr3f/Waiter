package com.example.jayempeesee.waiterapp;


import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class Spot{
    String name;
    String waitTime;
    String visitors;
    public Spot(){
        this.name = "";
        this.waitTime = "";
        this.visitors = "";
    }

    public Spot (String name, String visitors, String waitTime)
    {
        this.name = name;
        this.waitTime = waitTime;
        this.visitors = visitors;
    }

    public  String getName(){
        return name;
    }
    public String getWaitTime(){
        return waitTime;
    }
    public  String getVisitors(){
        return visitors;
    }
}

