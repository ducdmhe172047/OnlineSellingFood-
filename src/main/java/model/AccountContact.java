package model;

public class AccountContact {
    private Integer accountID;
    private Integer contactInformationID;
    private Integer isDefault;

    public AccountContact() {
    }

    public AccountContact(Integer accountID, Integer contactInformationID, Integer isDefault) {
        this.accountID = accountID;
        this.contactInformationID = contactInformationID;
        this.isDefault = isDefault;
    }

    public AccountContact(Integer accountID, Integer contactInformationID) {
        this.accountID = accountID;
        this.contactInformationID = contactInformationID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getContactInformationID() {
        return contactInformationID;
    }

    public void setContactInformationID(Integer contactInformationID) {
        this.contactInformationID = contactInformationID;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
