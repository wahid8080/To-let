package com.example.goolemap.Model;

public class UploadClintInfoModel {

    private String name,Phone,Area,Distric,Nid,render,image;

    public UploadClintInfoModel() {

    }

    public UploadClintInfoModel(String name, String phone, String area,
                                String distric, String nid, String render, String image) {
        this.name = name;
        this.Phone = phone;
        this.Area = area;
        this.Distric = distric;
        this.Nid = nid;
        this.render = render;
        this.image= image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getDistric() {
        return Distric;
    }

    public void setDistric(String distric) {
        Distric = distric;
    }

    public String getNid() {
        return Nid;
    }

    public void setNid(String nid) {
        Nid = nid;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
