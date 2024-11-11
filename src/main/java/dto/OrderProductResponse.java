package dto;

import java.util.Date;

public class OrderProductResponse {
    private String imgLink;
    private int productID;
    private String productName;
    private int price,quantity;
    private Date time;
    private String statusDetail;
    private int statusID;
    private int discountVoucher;
    private int totalPrice;
    private int totalPriceAfterVoucher;

    public OrderProductResponse() {
    }


    public OrderProductResponse(int productID, String productName, int price, int quantity, Date time, String statusDetail) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.time = time;
        this.statusDetail = statusDetail;
    }

    public OrderProductResponse(String imgLink, int productID, String productName, int price, Date time, int quantity, String statusDetail, int statusID, int discountVoucher, int totalPrice, int totalPriceAfterVoucher) {
        this.imgLink = imgLink;
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.time = time;
        this.quantity = quantity;
        this.statusDetail = statusDetail;
        this.statusID = statusID;
        this.discountVoucher = discountVoucher;
        this.totalPrice = totalPrice;
        this.totalPriceAfterVoucher = totalPriceAfterVoucher;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public int getDiscountVoucher() {
        return discountVoucher;
    }

    public void setDiscountVoucher(int discountVoucher) {
        this.discountVoucher = discountVoucher;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPriceAfterVoucher() {
        return totalPriceAfterVoucher;
    }

    public void setTotalPriceAfterVoucher(int totalPriceAfterVoucher) {
        this.totalPriceAfterVoucher = totalPriceAfterVoucher;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
}
