package model;

public class Warehouse {
    private Integer warehouseID;
    private Integer contactInformationID;
    private Integer statusID;
    private String name;

    public Warehouse() {
    }

    public Warehouse(Integer warehouseID, Integer contactInformationID, Integer statusID, String name) {
        this.warehouseID = warehouseID;
        this.contactInformationID = contactInformationID;
        this.statusID = statusID;
        this.name = name;
    }
    public Warehouse(Integer contactInformationID, Integer statusID, String name) {
        this.warehouseID = warehouseID;
        this.contactInformationID = contactInformationID;
        this.statusID = statusID;
        this.name = name;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public Integer getContactInformationID() {
        return contactInformationID;
    }

    public void setContactInformationID(Integer contactInformationID) {
        this.contactInformationID = contactInformationID;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
