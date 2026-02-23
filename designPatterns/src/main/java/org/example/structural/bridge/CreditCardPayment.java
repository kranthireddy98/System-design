package org.example.structural.bridge;

public class CreditCardPayment extends Payment{


    public CreditCardPayment(PaymentGateway gateway) {
        super(gateway);
    }

    public void makePayment(double amount){
        System.out.println("Credit Card Payment initialized");
        gateway.processPayment(amount);
    }
}
