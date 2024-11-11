package model;

public class OrderStatus {
    private Integer statusID;
    private String detail;

    public OrderStatus(Integer statusID, String detail) {
        this.statusID = statusID;
        this.detail = detail;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
