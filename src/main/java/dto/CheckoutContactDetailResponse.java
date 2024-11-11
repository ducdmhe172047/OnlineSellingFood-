package dto;

public class CheckoutContactDetailResponse {
    private int contactInformationID;
    private String name,phone,address;
    private int isDefault;

    public CheckoutContactDetailResponse(int contactInformationID, String name, String phone, String address, int isDefault) {
        this.contactInformationID = contactInformationID;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isDefault = isDefault;
    }

    public CheckoutContactDetailResponse() {
    }

    public int getContactInformationID() {
        return contactInformationID;
    }

    public void setContactInformationID(int contactInformationID) {
        this.contactInformationID = contactInformationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
