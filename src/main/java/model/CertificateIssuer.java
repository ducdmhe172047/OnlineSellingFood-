package model;

public class CertificateIssuer {
    private Integer certificateIssuerID;
    private String name;
    private String detail;

    public CertificateIssuer() {
    }

    public CertificateIssuer(Integer certificateIssuerID, String name, String detail) {
        this.certificateIssuerID = certificateIssuerID;
        this.name = name;
        this.detail = detail;
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
}
