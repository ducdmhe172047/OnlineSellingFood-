package model;

import java.time.LocalDateTime;

public class Otp {
    private Integer accountID;
    private String code;
    private LocalDateTime expiryDateTime;

    public Otp() {
    }

    public Otp(Integer accountID, String code, LocalDateTime expiryDateTime) {
        this.accountID = accountID;
        this.code = code;
        this.expiryDateTime = expiryDateTime;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }
}
