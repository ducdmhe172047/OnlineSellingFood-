package dto;

public class ApplyVoucherToCheckoutResponse {
    private int voucherID,discountPercent,inventory;
    private String startDate,endDate;
    private int remainingDay;

    public ApplyVoucherToCheckoutResponse() {
    }

    public ApplyVoucherToCheckoutResponse(int voucherID, int discountPercent, int inventory, String startDate, String endDate, int remainingDay) {
        this.voucherID = voucherID;
        this.discountPercent = discountPercent;
        this.inventory = inventory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remainingDay = remainingDay;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getRemainingDay() {
        return remainingDay;
    }

    public void setRemainingDay(int remainingDay) {
        this.remainingDay = remainingDay;
    }
}
