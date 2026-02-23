package org.example.structural.bridge;

public class StripeGateway implements PaymentGateway{

    @Override
    public void processPayment(double amount){
        System.out.println("Processing via Stripe: " + amount);
    }
}
