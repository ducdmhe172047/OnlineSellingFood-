package dto;

public class ProductCheckoutResponse {
    private int productID;
    private String imgLink,name;
    private int quantity;
    private int price;
    private int discountPercent;
    private int priceAfterDiscount;
    private int averageStar,totalFeedback;

    public ProductCheckoutResponse() {
    }

    public ProductCheckoutResponse(int productID, String imgLink, String name, int quantity, int price, int discountPercent, int priceAfterDiscount, int averageStar, int totalFeedback) {
        this.productID = productID;
        this.imgLink = imgLink;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discountPercent = discountPercent;
        this.priceAfterDiscount = priceAfterDiscount;
        this.averageStar = averageStar;
        this.totalFeedback = totalFeedback;
    }

    public String getImgLink() {
        return imgLink;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(int priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public int getAverageStar() {
        return averageStar;
    }

    public void setAverageStar(int averageStar) {
        this.averageStar = averageStar;
    }

    public int getTotalFeedback() {
        return totalFeedback;
    }

    public void setTotalFeedback(int totalFeedback) {
        this.totalFeedback = totalFeedback;
    }
}
