package com.vladislavtachev.cscb763;

/**
 * Created by Vladislav Tachev on 6/29/2015.
 * All rights reserved.
 */

public class Sighting {
    private long id;
    private String location;
    private long time;
    private String bName;
    private int bCount;

    public Sighting(String loc, long time, String bName, int bCount){
        setLocation(loc);
        setTime(time);
        setbName(bName);
        setbCount(bCount);
    }

    public Sighting(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public int getbCount() {
        return bCount;
    }

    public void setbCount(int bCount) {
        this.bCount = bCount;
    }

    @Override
    public String toString(){
        return bName;
    }
}
