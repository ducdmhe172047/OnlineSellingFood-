package dto;

import java.sql.Date;

public class StaffListResponse {
    private int accountID;
    private String name;
    private String email;
    private String detail;
    private Date time;
    public StaffListResponse(int accountID,String name, String email, String detail, Date time) {
        this.accountID = accountID;
        this.name = name;
        this.email = email;
        this.detail = detail;
        this.time = time;

    }

    public StaffListResponse() {
    }
     public int getAccountID() {
        return accountID;
     }
     public void setAccountID(int accountID) {
        this.accountID = accountID;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}