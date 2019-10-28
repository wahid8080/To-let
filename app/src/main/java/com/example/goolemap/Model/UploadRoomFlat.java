package com.example.goolemap.Model;

public class UploadRoomFlat {

    private String family, bachelor, other,razuk, get, internet, genarator,
            water,image1,image2,image3,image4,price,houseName,area,roadNo,houseNumber,details,phoneNumber,roomFlatNo,uId;

    private double latitute,longatitute;

    private String bookStatus,customerId;


    public UploadRoomFlat() {

    }


    public UploadRoomFlat(String family, String bachelor, String other, String razuk, String get, String internet,
                          String genarator, String water, String image1, String image2, String image3, String image4,
                          String price, String houseName, String area, String roadNo, String houseNumber, String details,
                          String phoneNumber, double latitute, double longatitute,String roomFlatNo,
                          String uId, String bookStatus, String customerId) {
        this.family = family;
        this.bachelor = bachelor;
        this.other = other;
        this.razuk = razuk;
        this.get = get;
        this.internet = internet;
        this.genarator = genarator;
        this.water = water;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.price = price;
        this.houseName = houseName;
        this.area = area;
        this.roadNo = roadNo;
        this.houseNumber = houseNumber;
        this.details = details;
        this.phoneNumber = phoneNumber;
        this.roomFlatNo = roomFlatNo;
        this.uId = uId;
        this.latitute = latitute;
        this.longatitute = longatitute;
        this.bookStatus = bookStatus;
        this.customerId = customerId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }


    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRoomFlatNo() {
        return roomFlatNo;
    }

    public void setRoomFlatNo(String roomFlatNo) {
        this.roomFlatNo = roomFlatNo;
    }

    public double getLatitute() {
        return latitute;
    }

    public double getLongatitute() {
        return longatitute;
    }

    public String getFamily() {
        return family;
    }

    public String getBachelor() {
        return bachelor;
    }

    public String getOther() {
        return other;
    }

    public String getRazuk() {
        return razuk;
    }

    public String getGet() {
        return get;
    }

    public String getInternet() {
        return internet;
    }

    public String getGenarator() {
        return genarator;
    }

    public String getWater() {
        return water;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public String getImage4() {
        return image4;
    }

    public String getPrice() {
        return price;
    }

    public String getHouseName() {
        return houseName;
    }

    public String getArea() {
        return area;
    }

    public String getRoadNo() {
        return roadNo;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getDetails() {
        return details;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setBachelor(String bachelor) {
        this.bachelor = bachelor;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setRazuk(String razuk) {
        this.razuk = razuk;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public void setGenarator(String genarator) {
        this.genarator = genarator;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setRoadNo(String roadNo) {
        this.roadNo = roadNo;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public void setLongatitute(double longatitute) {
        this.longatitute = longatitute;
    }
}
