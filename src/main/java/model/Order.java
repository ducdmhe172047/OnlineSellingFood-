package model;

import java.time.LocalDateTime;
import java.util.Date;

public class Order {
    private Integer orderID;
    private Integer customerID;
    private Integer paymentStatementID;
    private Integer contactInformationID;
    private Integer voucherID;
    private Integer price;
    private LocalDateTime orderTime;
    private Integer statusID;

    public Order(int customerID, int paymentStatementID, int contactInformationID, int voucherID, int price, Date date, int statusID) {
    }

    public Order(Integer orderID, Integer customerID, Integer paymentStatementID, Integer contactInformationID, Integer voucherID, Integer price, Integer statusID, LocalDateTime orderTime) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentStatementID = paymentStatementID;
        this.contactInformationID = contactInformationID;
        this.voucherID = voucherID;
        this.price = price;
        this.statusID = statusID;
        this.orderTime = orderTime;
    }

    public Order(Integer customerID, Integer paymentStatementID, Integer contactInformationID, Integer voucherID, Integer price, LocalDateTime orderTime, Integer statusID) {
        this.customerID = customerID;
        this.paymentStatementID = paymentStatementID;
        this.contactInformationID = contactInformationID;
        this.voucherID = voucherID;
        this.price = price;
        this.orderTime = orderTime;
        this.statusID = statusID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getPaymentStatementID() {
        return paymentStatementID;
    }

    public void setPaymentStatementID(Integer paymentStatementID) {
        this.paymentStatementID = paymentStatementID;
    }

    public Integer getContactInformationID() {
        return contactInformationID;
    }

    public void setContactInformationID(Integer contactInformationID) {
        this.contactInformationID = contactInformationID;
    }

    public Integer getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Integer voucherID) {
        this.voucherID = voucherID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
