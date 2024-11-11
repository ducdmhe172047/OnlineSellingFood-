package model;

public class ProductImg {
    private Integer productID;
    private Integer imgID;
    private Integer isDefault;

    public ProductImg() {
    }

    public ProductImg(Integer productID, Integer imgID, Integer isDefault) {
        this.productID = productID;
        this.imgID = imgID;
        this.isDefault = isDefault;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getImgID() {
        return imgID;
    }

    public void setImgID(Integer imgID) {
        this.imgID = imgID;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
