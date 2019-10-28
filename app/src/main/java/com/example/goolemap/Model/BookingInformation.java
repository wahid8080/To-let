package com.example.goolemap.Model;

public class BookingInformation {

    private String roomFlatNo,customerName,customerPhone,customerAge,stay_month,bookingStatus,productType,houseName;

    public BookingInformation() {
    }

    public BookingInformation(String roomFlatNo, String customerName, String customerPhone, String customerAge,String stay_month,String bookingStatus,String productType,String houseName) {
        this.roomFlatNo = roomFlatNo;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAge = customerAge;
        this.stay_month = stay_month;
        this.bookingStatus = bookingStatus;
        this.productType = productType;
        this.houseName = houseName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getStay_month() {
        return stay_month;
    }

    public void setStay_month(String stay_month) {
        this.stay_month = stay_month;
    }

    public String getRoomFlatNo() {
        return roomFlatNo;
    }

    public void setRoomFlatNo(String roomFlatNo) {
        this.roomFlatNo = roomFlatNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(String customerAge) {
        this.customerAge = customerAge;
    }
}
