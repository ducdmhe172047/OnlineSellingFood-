package dto;

import java.time.LocalDateTime;

public class VoucherResponse {
    public int voucherID,discountID,discountPercent;
    public LocalDateTime startTime,endTime;
    public int quantity,inventory;

    public VoucherResponse() {
    }

    public VoucherResponse(int voucherID, int discountID, int discountPercent, LocalDateTime startTime, LocalDateTime endTime, int quantity, int inventory) {
        this.voucherID = voucherID;
        this.discountID = discountID;
        this.discountPercent = discountPercent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.quantity = quantity;
        this.inventory = inventory;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
