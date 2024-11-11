package model;

public class Voucher {
    private Integer voucherID;
    private Integer discountID;
    private Integer quantity;
    private Integer inventory;

    public Voucher() {
    }

    public Voucher(Integer voucherID, Integer discountID, Integer quantity, Integer inventory) {
        this.voucherID = voucherID;
        this.discountID = discountID;
        this.quantity = quantity;
        this.inventory = inventory;
    }

    public Voucher(Integer discountID, Integer quantity, Integer inventory) {
        this.discountID = discountID;
        this.quantity = quantity;
        this.inventory = inventory;
    }

    public Integer getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Integer voucherID) {
        this.voucherID = voucherID;
    }

    public Integer getDiscountID() {
        return discountID;
    }

    public void setDiscountID(Integer discountID) {
        this.discountID = discountID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
