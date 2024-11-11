package model;

public class EventType {
    private Integer evenTypeID;
    private String detail;

    public EventType() {
    }

    public EventType(Integer evenTypeID, String detail) {
        this.evenTypeID = evenTypeID;
        this.detail = detail;
    }

    public Integer getEvenTypeID() {
        return evenTypeID;
    }

    public void setEvenTypeID(Integer evenTypeID) {
        this.evenTypeID = evenTypeID;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
