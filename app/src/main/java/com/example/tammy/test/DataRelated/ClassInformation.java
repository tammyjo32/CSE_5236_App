package com.example.tammy.test.DataRelated;

import java.util.Date;

/**
 * Created by Seth on 11/8/16.
 */

public class ClassInformation {

    String name;
    Double latitude, longitude, radius;
    Date start_time, end_time;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public void setLongitude(double longitude){this.longitude = longitude;}

    public double getLongitude(){
        return this.longitude;
    }

    public void setRadius(double radius){this.radius = radius;}

    public double getRadius(){
        return this.radius;
    }

    public void setStart_time(Date start_time){
        this.start_time = start_time;
    }

    public Date getStart_time(){
        return this.start_time;
    }

    public void setEnd_time_time(Date end_time){
        this.end_time = end_time;
    }

    public Date getEnd_time(){
        return this.end_time;
    }


}
