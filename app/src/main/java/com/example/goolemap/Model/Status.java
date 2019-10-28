package com.example.goolemap.Model;

public class Status {
    private String renter,owner;

    public Status(String renter, String owner) {
        this.renter = renter;
        this.owner = owner;
    }

    public Status() {
    }

    public String getRenter() {
        return renter;
    }

    public String getOwner() {
        return owner;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
