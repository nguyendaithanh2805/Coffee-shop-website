package org.example.coffeeshopwebsite.model;

public class Payment {
    private int paymentId;
    private String name;

    public Payment() {
    }

    public Payment(int paymentId, String name) {
        this.paymentId = paymentId;
        this.name = name;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
