package com.example.jayempeesee.waiterapp;


import java.io.Serializable;

/**
 *
 */
public class Spot implements Serializable{
    private String name;
    private String waitTime;
    private String visitors;

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

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Number of Visitors: " + getVisitors() + "\n" +
                "Wait Time: " + getWaitTime();
    }
}

