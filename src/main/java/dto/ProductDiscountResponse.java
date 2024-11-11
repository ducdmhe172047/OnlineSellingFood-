package dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class ProductDiscountResponse {
    public int productID,discountID;
    public String name,categoryName;
    public int price;
    public int discountPercent;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public ProductDiscountResponse(int productID,int discountID, String name, String categoryName, int price, int discountPercent, LocalDateTime startTime, LocalDateTime endTime) {
        this.productID = productID;
        this.discountID = discountID;
        this.name = name;
        this.categoryName = categoryName;
        this.price = price;
        this.discountPercent = discountPercent;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    public int getDiscountID() {
        return discountID;
    }
    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
