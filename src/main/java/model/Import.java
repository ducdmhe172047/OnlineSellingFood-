package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Import {

    private Integer importID;
    private Integer staffID;
    private Integer warehouseID;
    private Integer supplierID;
    private LocalDateTime time;

    public Import() {
    }

    public Import(Integer importID, Integer staffID, Integer warehouseID, Integer supplierID, LocalDateTime time) {
        this.importID = importID;
        this.staffID = staffID;
        this.warehouseID = warehouseID;
        this.supplierID = supplierID;
        this.time = time;
    }

    public Integer getImportID() {
        return importID;
    }

    public void setImportID(Integer importID) {
        this.importID = importID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }



}
