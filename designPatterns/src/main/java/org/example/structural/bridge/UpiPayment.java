package org.example.structural.bridge;

public class UpiPayment extends Payment{

    public UpiPayment(PaymentGateway gateway){
        super(gateway);
    }

    public void makePayment(double amount){
        System.out.println("UPI payment Initialized");
        gateway.processPayment(amount);
    }
}
