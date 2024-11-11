package model;

import java.time.LocalDateTime;

public class OrderShip {
    private Integer orderID;
    private Integer staffID;
    private LocalDateTime finishTime;

    public OrderShip() {
    }

    public OrderShip(Integer orderID, Integer staffID, LocalDateTime finishTime) {
        this.orderID = orderID;
        this.staffID = staffID;
        this.finishTime = finishTime;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }
}
