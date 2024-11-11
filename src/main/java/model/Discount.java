package model;

import java.time.LocalDateTime;

public class Discount {
    private Integer discountID;
    private Integer discountPercent;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Discount() {
    }

    public Discount(Integer discountID, Integer discountPercent, LocalDateTime startTime, LocalDateTime endTime) {
        this.discountID = discountID;
        this.discountPercent = discountPercent;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getDiscountID() {
        return discountID;
    }

    public void setDiscountID(Integer discountID) {
        this.discountID = discountID;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
