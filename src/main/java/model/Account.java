package model;

import java.time.LocalDateTime;

public class Account {
    private Integer accountID;
    private Integer roleID;
    private String email;
    private String name;
    private Integer genderID;
    private LocalDateTime birth;
    private String password;
    private LocalDateTime time;
    private Integer statusID;

    private String timestring;
    private String birthdaystring;
    public Account() {
    }


    public Account(Integer accountID, Integer roleID, String email, String name, Integer genderID, String password, LocalDateTime birth, LocalDateTime time, Integer statusID) {
        this.accountID = accountID;
        this.roleID = roleID;
        this.email = email;
        this.name = name;
        this.genderID = genderID;
        this.password = password;
        this.birth = birth;
        this.time = time;
        this.statusID = statusID;
    }

    public Account(Integer roleID, String email, String name, Integer genderID, String password, LocalDateTime birth, LocalDateTime time, Integer statusID) {
        this.roleID = roleID;
        this.email = email;
        this.name = name;
        this.genderID = genderID;
        this.password = password;
        this.birth = birth;
        this.time = time;
        this.statusID = statusID;
    }

    public Account(Integer accountID, String email, Integer roleID, String name, Integer genderID, LocalDateTime birth, Integer statusID) {
        this.accountID = accountID;
        this.email = email;
        this.roleID = roleID;
        this.name = name;
        this.genderID = genderID;
        this.birth = birth;
        this.statusID = statusID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGenderID() {
        return genderID;
    }

    public void setGenderID(Integer genderID) {
        this.genderID = genderID;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getTimestring() {
        return timestring;
    }

    public String getBirthdaystring() {
        return birthdaystring;
    }

    public void setBirthdaystring(String birthdaystring) {
        this.birthdaystring = birthdaystring;
    }

    public void setTimestring(String timestring) {
        this.timestring = timestring;
    }

}
