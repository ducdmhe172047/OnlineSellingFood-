package dto;

import java.time.LocalDateTime;

public class ImportProductResponse {
    private int ImportID;
    private String ProductName;
    private LocalDateTime ManufactureDate;
    private LocalDateTime ExpireDate;
    private int Price;
    private int ImportQuantity;
    private int InventoryQuantity;
    private String UnitName;

    public ImportProductResponse() {
    }

    public ImportProductResponse(int importID, String productName, LocalDateTime manufactureDate, LocalDateTime expireDate,int price, int importQuantity, int inventoryQuantity, String unitName) {
        ImportID = importID;
        ProductName = productName;
        ManufactureDate = manufactureDate;
        ExpireDate = expireDate;
        Price = price;
        ImportQuantity = importQuantity;
        InventoryQuantity = inventoryQuantity;
        UnitName = unitName;
    }

    public int getImportID() {
        return ImportID;
    }

    public void setImportID(int importID) {
        ImportID = importID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public LocalDateTime getManufactureDate() {
        return ManufactureDate;
    }

    public void setManufactureDate(LocalDateTime manufactureDate) {
        ManufactureDate = manufactureDate;
    }

    public LocalDateTime getExpireDate() {
        return ExpireDate;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        ExpireDate = expireDate;
    }

    public int getImportQuantity() {
        return ImportQuantity;
    }

    public void setImportQuantity(int importQuantity) {
        ImportQuantity = importQuantity;
    }

    public int getInventoryQuantity() {
        return InventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        InventoryQuantity = inventoryQuantity;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
}
