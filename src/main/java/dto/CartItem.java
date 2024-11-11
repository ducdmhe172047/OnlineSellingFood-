package dto;

public class CartItem {
    private int productID,price,quantity,unitID;

    public CartItem() {
    }

    public CartItem(int productID, int price, int quantity, int unitID) {
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
        this.unitID = unitID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}
