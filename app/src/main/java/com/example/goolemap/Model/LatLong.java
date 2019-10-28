package com.example.goolemap.Model;

public class LatLong {

    private double latitute,longatitute;
    private String houseName;
    public LatLong() {
    }


    public LatLong(double latitute, double longatitute, String houseName) {
        this.latitute = latitute;
        this.longatitute = longatitute;
        this.houseName = houseName;
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLongatitute() {
        return longatitute;
    }

    public void setLongatitute(double longatitute) {
        this.longatitute = longatitute;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
