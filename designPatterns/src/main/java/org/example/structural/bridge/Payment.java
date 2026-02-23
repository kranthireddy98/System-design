package org.example.structural.bridge;

public abstract class Payment {

    protected PaymentGateway gateway;

    public Payment(PaymentGateway gateway){
        this.gateway=gateway;
    }

    public abstract void makePayment(double amount);

}
