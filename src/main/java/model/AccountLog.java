package model;

public class AccountLog {
    private Integer logID;
    private Integer eventTypeID;
    private Integer accountID;
    private String ipAddress;
    private String dataType;
    private String value;
    private String time;

    public AccountLog() {
    }

    public AccountLog(Integer logID, Integer eventTypeID, Integer accountID, String ipAddress, String dataType, String value, String time) {
        this.logID = logID;
        this.eventTypeID = eventTypeID;
        this.accountID = accountID;
        this.ipAddress = ipAddress;
        this.dataType = dataType;
        this.value = value;
        this.time = time;
    }

    public Integer getLogID() {
        return logID;
    }

    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public Integer getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(Integer eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}