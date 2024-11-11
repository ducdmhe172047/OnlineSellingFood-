package model;

public class Supplier {
    private Integer supplierID;
    private Integer contactInformationID;
    private String name;
    private String note;

    public Supplier() {
    }

    public Supplier(Integer supplierID, Integer contactInformationID, String name, String note) {
        this.supplierID = supplierID;
        this.contactInformationID = contactInformationID;
        this.name = name;
        this.note = note;
    }

    public Supplier(Integer contactInformationID, String name, String note) {
        this.supplierID = supplierID;
        this.contactInformationID = contactInformationID;
        this.name = name;
        this.note = note;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Integer getContactInformationID() {
        return contactInformationID;
    }

    public void setContactInformationID(Integer contactInformationID) {
        this.contactInformationID = contactInformationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
