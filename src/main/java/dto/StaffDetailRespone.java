package dto;

import java.time.LocalDateTime;

public class StaffDetailRespone {
    private int roleID;
    private int statusID,gender;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime birth;
    private int salary;
    private int warehouseID;
    private String nameWarehouse;

    public StaffDetailRespone() {}

    public StaffDetailRespone(int roleID, int statusID, int gender, String name, String email, String phoneNumber, String address, LocalDateTime birth, int salary, int warehouseID, String nameWarehouse) {
        this.roleID = roleID;
        this.statusID = statusID;
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birth = birth;
        this.salary = salary;
        this.warehouseID = warehouseID;
        this.nameWarehouse = nameWarehouse;
    }

    public int getRoleID() {
        return roleID;
   }
   public void setRoleID(int roleID) {
        this.roleID = roleID;
   }
   public int getStatusID() {
        return statusID;
   }
   public void setStatusID(int statusID) {
        this.statusID = statusID;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

   public int getWarehouseID() {
        return warehouseID;
   }
   public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
   }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNameWarehouse() {
        return nameWarehouse;
    }

    public void setNameWarehouse(String nameWarehouse) {
        this.nameWarehouse = nameWarehouse;
    }
}
