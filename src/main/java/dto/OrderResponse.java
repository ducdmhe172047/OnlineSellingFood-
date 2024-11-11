package dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class OrderResponse {
    private Integer orderID;
    private String customerName;
    private LocalDateTime orderDate;
    private Integer price;
    private String orderStatusName;
    private String orderPaymentName;




    public OrderResponse(int orderID, String customerName, LocalDateTime orderDate, int price, String orderStatusName, String orderPaymentName) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.price = price;
        this.orderStatusName = orderStatusName;
        this.orderPaymentName = orderPaymentName;
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


    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getOrderPaymentName() {
        return orderPaymentName;
    }

    public void setOrderPaymentName(String orderPaymentName) {
        this.orderPaymentName = orderPaymentName;
    }
}
