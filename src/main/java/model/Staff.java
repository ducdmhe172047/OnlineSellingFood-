package model;

public class Staff {
    private Integer staffID;
    private Integer accountID;
    private Integer salary;
    private Integer warehouseID;

    public Staff() {
    }

    public Staff(Integer staffID) {
        this.staffID = staffID;
    }

    public Staff(Integer staffID, Integer accountID, Integer salary, Integer warehouseID) {
        this.staffID = staffID;
        this.accountID = accountID;
        this.salary = salary;
        this.warehouseID = warehouseID;
    }
    public Staff(Integer accountID, Integer salary, Integer warehouseID) {
        this.accountID = accountID;
        this.salary = salary;
        this.warehouseID = warehouseID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }
}
