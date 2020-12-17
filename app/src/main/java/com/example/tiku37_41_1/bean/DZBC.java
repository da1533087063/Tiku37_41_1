package com.example.tiku37_41_1.bean;

import java.util.List;

public class DZBC {
//    id": 1,
//            "busline": [
//            "光谷金融街",
//            "戎军南路",
//            "长河公园",
//            "南湖商厦"
//            ],
//            "fares": 8,
//            "mileage": 20,
//            "time": "06:45~19:45"
//},
    private int id;
    private List<String> busline;
    private int fares,mileage;
    private String time;

    public DZBC(int id, List<String> busline, int fares, int mileage, String time) {
        this.id = id;
        this.busline = busline;
        this.fares = fares;
        this.mileage = mileage;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getBusline() {
        return busline;
    }

    public void setBusline(List<String> busline) {
        this.busline = busline;
    }

    public int getFares() {
        return fares;
    }

    public void setFares(int fares) {
        this.fares = fares;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
