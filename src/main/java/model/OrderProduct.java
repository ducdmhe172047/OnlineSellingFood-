package model;

public class OrderProduct {
    private Integer orderID;
    private Integer importID;
    private Integer productID;
    private Integer price;
    private Integer quantity;
    private Integer unitID;

    public OrderProduct() {
    }

    public OrderProduct(Integer orderID, Integer importID, Integer productID, Integer price, Integer quantity, Integer unitID) {
        this.orderID = orderID;
        this.importID = importID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
        this.unitID = unitID;
    }

    public OrderProduct(Integer orderID, Integer productID, Integer price, Integer quantity, Integer unitID) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
        this.unitID = unitID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getImportID() {
        return importID;
    }

    public void setImportID(Integer importID) {
        this.importID = importID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }
}
