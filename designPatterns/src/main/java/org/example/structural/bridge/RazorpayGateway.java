package org.example.structural.bridge;

public class RazorpayGateway implements PaymentGateway{

    @Override
    public void processPayment(double amount){
        System.out.println("Processing via Razorpay: "+amount);
    }
}
