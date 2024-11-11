package model;

public class Customer {
    private Integer customerID;
    private Integer accountID;
    private Integer point;
    private Integer level;


    public Customer() {
    }

    public Customer(Integer customerID, Integer accountID, Integer point, Integer level) {
        this.customerID = customerID;
        this.accountID = accountID;
        this.point = point;
        this.level = level;
    }
    public Customer(Integer accountID, Integer point, Integer level) {
        this.accountID = accountID;
        this.point = point;
        this.level = level;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
