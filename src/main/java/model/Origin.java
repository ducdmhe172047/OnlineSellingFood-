package model;

public class Origin {
    private Integer originID;
    private String name;

    public Origin() {
    }

    public Origin(Integer originID, String name) {
        this.originID = originID;
        this.name = name;
    }

    public Integer getOriginID() {
        return originID;
    }

    public void setOriginID(Integer originID) {
        this.originID = originID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
