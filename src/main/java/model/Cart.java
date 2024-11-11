package model;

public class Cart {
    private Integer customerID;
    private Integer productID;
    private Integer quantity;

    public Cart() {
    }

    public Cart(Integer customerID, Integer productID, Integer quantity) {
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
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
}
