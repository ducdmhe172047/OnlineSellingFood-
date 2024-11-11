package dto;

import java.util.Date;

public class CustomerOrderResponse {
    private int orderID;
    private String customerName,email;
    private int price;
    private Date orderTime;
    private int statusID;
    private String statusDetail;
    private String phoneNumber,address;

    public CustomerOrderResponse() {
    }

    public CustomerOrderResponse(int orderID, String customerName, String email, int price, Date orderTime, int statusID, String statusDetail, String phoneNumber, String address) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.email = email;
        this.price = price;
        this.orderTime = orderTime;
        this.statusID = statusID;
        this.statusDetail = statusDetail;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
