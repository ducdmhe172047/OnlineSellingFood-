package model;

public class PaymentStatement {
    private Integer paymentStatementID;
    private String name;

    public PaymentStatement() {
    }

    public Integer getPaymentStatementID() {
        return paymentStatementID;
    }

    public void setPaymentStatementID(Integer paymentStatementID) {
        this.paymentStatementID = paymentStatementID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
