package dto;


import java.time.LocalDateTime;

public class ImportRespone {
    private Integer importID;
    private String accountName;
    private String warehouseName;
    private String supplierName;
    private LocalDateTime importTime;

    public ImportRespone(Integer importID) {
        this.importID = importID;
    }

    public ImportRespone(Integer importID, String accountName, String warehouseName, String supplierName, LocalDateTime importTime) {
        this.importID = importID;
        this.accountName = accountName;
        this.warehouseName = warehouseName;
        this.supplierName = supplierName;
        this.importTime = importTime;
    }

    public Integer getImportID() {
        return importID;
    }

    public void setImportID(Integer importID) {
        this.importID = importID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public LocalDateTime getImportTime() {
        return importTime;
    }

    public void setImportTime(LocalDateTime importTime) {
        this.importTime = importTime;
    }
}
