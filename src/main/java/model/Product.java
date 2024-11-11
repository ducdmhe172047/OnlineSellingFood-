package model;

public class Product {
    private Integer productID;
    private Integer price;
    private Integer discountID;
    private Integer weight;
    private Integer categoryID;
    private Integer manufacturerID;
    private Integer originID;
    private Integer unitID;
    private Integer certificationID;
    private Integer statusID;
    private String name;
    private String detail;

    public Product() {
    }

    public Product(Integer productID, Integer price, Integer discountID, Integer weight, Integer categoryID, Integer manufacturerID, Integer originID, Integer unitID, Integer certificationID, Integer statusID, String name, String detail) {
        this.productID = productID;
        this.price = price;
        this.discountID = discountID;
        this.weight = weight;
        this.categoryID = categoryID;
        this.manufacturerID = manufacturerID;
        this.originID = originID;
        this.unitID = unitID;
        this.certificationID = certificationID;
        this.statusID = statusID;
        this.name = name;
        this.detail = detail;
    }

    public Product(Integer price, Integer discountID, Integer weight, Integer categoryID, Integer manufacturerID, Integer originID, Integer unitID, Integer certificationID, Integer statusID, String name, String detail) {
        this.price = price;
        this.discountID = discountID;
        this.weight = weight;
        this.categoryID = categoryID;
        this.manufacturerID = manufacturerID;
        this.originID = originID;
        this.unitID = unitID;
        this.certificationID = certificationID;
        this.statusID = statusID;
        this.name = name;
        this.detail = detail;
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

    public Integer getDiscountID() {
        return discountID;
    }

    public void setDiscountID(Integer discountID) {
        this.discountID = discountID;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(Integer manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public Integer getOriginID() {
        return originID;
    }

    public void setOriginID(Integer originID) {
        this.originID = originID;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public Integer getCertificationID() {
        return certificationID;
    }

    public void setCertificationID(Integer certificationID) {
        this.certificationID = certificationID;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}
