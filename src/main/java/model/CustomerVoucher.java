package model;

public class CustomerVoucher {
    private Integer customerID;
    private Integer voucherID;

    public CustomerVoucher() {
    }

    public CustomerVoucher(Integer customerID, Integer voucherID) {
        this.customerID = customerID;
        this.voucherID = voucherID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Integer voucherID) {
        this.voucherID = voucherID;
    }
}
