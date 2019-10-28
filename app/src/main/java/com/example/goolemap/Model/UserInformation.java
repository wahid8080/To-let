package com.example.goolemap.Model;

public class UserInformation {
    private String UserName, Phone,Area,Road,HouseNo,owner,image;

    public UserInformation() {
    }

    public UserInformation(String userName, String phone, String area,
                           String road, String houseNo, String owner, String image) {
        this.UserName = userName;
        this.Phone = phone;
        this.Area = area;
        this.Road = road;
        this.HouseNo = houseNo;
        this.owner = owner;
        this.image = image;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPhone() {
        return Phone;
    }

    public String getArea() {
        return Area;
    }

    public String getRoad() {
        return Road;
    }

    public String getHouseNo() {
        return HouseNo;
    }

    public String getOwner() {
        return owner;
    }

    public String getImage() {
        return image;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setArea(String area) {
        Area = area;
    }

    public void setRoad(String road) {
        Road = road;
    }

    public void setHouseNo(String houseNo) {
        HouseNo = houseNo;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
