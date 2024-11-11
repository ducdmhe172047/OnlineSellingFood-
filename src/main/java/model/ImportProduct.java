package model;

import java.time.LocalDateTime;

public class ImportProduct {
    private Integer importID;
    private Integer productID;
    private Integer price;
    private Integer importQuantity;
    private Integer inventoryQuantity;
    private LocalDateTime mfg;
    private LocalDateTime exp;
    private Integer unitID;

    public ImportProduct() {
    }

    public ImportProduct(Integer importID, Integer productID, Integer price, Integer importQuantity, Integer inventoryQuantity, LocalDateTime mfg, LocalDateTime exp, Integer unitID) {
        this.importID = importID;
        this.productID = productID;
        this.price = price;
        this.importQuantity = importQuantity;
        this.inventoryQuantity = inventoryQuantity;
        this.mfg = mfg;
        this.exp = exp;
        this.unitID = unitID;
    }

    public Integer getImportID() {
        return importID;
    }

    public void setImportID(Integer importID) {
        this.importID = importID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getImportQuantity() {
        return importQuantity;
    }

    public void setImportQuantity(Integer importQuantity) {
        this.importQuantity = importQuantity;
    }

    public Integer getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(Integer inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public LocalDateTime getMfg() {
        return mfg;
    }

    public void setMfg(LocalDateTime mfg) {
        this.mfg = mfg;
    }

    public LocalDateTime getExp() {
        return exp;
    }

    public void setExp(LocalDateTime exp) {
        this.exp = exp;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }
}
