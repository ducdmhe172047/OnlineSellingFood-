package model;

public class ContactInformation {
    private Integer contactInformationID;
    private String address;
    private String phoneNumber;

    public ContactInformation() {
    }

    public ContactInformation(Integer contactInformationID, String address, String phoneNumber) {
        this.contactInformationID = contactInformationID;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public ContactInformation(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer getContactInformationID() {
        return contactInformationID;
    }

    public void setContactInformationID(Integer contactInformationID) {
        this.contactInformationID = contactInformationID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
