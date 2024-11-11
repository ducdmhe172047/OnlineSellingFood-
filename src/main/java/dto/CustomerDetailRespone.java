package dto;

import java.time.LocalDateTime;

public class CustomerDetailRespone {
    private int statusID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime birth;
    private int point;
    private int level;

    public CustomerDetailRespone() {
    }

    public CustomerDetailRespone(int statusID, String name, String email, String phoneNumber, String address, LocalDateTime birth, int point, int level) {
        this.statusID = statusID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birth = birth;
        this.point = point;
        this.level = level;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
