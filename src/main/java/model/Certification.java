package model;

public class Certification {
    private Integer certificationID;
    private Integer certificateIssuerID;
    private String name;
    private String detail;
    private Integer imgID;

    public Certification() {
    }

    public Certification(Integer certificateIssuerID, Integer certificationID, String name, String detail, Integer imgID) {
        this.certificateIssuerID = certificateIssuerID;
        this.certificationID = certificationID;
        this.name = name;
        this.detail = detail;
        this.imgID = imgID;
    }

    public Integer getCertificationID() {
        return certificationID;
    }

    public void setCertificationID(Integer certificationID) {
        this.certificationID = certificationID;
    }

    public Integer getCertificateIssuerID() {
        return certificateIssuerID;
    }

    public void setCertificateIssuerID(Integer certificateIssuerID) {
        this.certificateIssuerID = certificateIssuerID;
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

    public Integer getImgID() {
        return imgID;
    }

    public void setImgID(Integer imgID) {
        this.imgID = imgID;
    }
}
