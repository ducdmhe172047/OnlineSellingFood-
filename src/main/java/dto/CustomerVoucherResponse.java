package dto;

public class CustomerVoucherResponse {
    private int customerID;
    private String customerName;
    private int point,level;


    public CustomerVoucherResponse() {
    }

    public CustomerVoucherResponse(int customerID, String customerName, int point, int level) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.point = point;
        this.level = level;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
