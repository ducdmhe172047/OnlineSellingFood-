package model;

public class Img {
    private Integer imgID;
    private String imglink;

    public Img() {
    }

    public Img(Integer imgID, String imglink) {
        this.imglink = imglink;
        this.imgID = imgID;
    }

    public Img(String imglink) {
        this.imglink = imglink;
    }

    public Integer getImgID() {
        return imgID;
    }

    public void setImgID(Integer imgID) {
        this.imgID = imgID;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }
}
