package org.example.structural.bridge;

public class BridgeClient {
    public static void main(String[] args) {
        RazorpayGateway razorpayGateway = new RazorpayGateway();
        StripeGateway stripeGateway = new StripeGateway();

        Payment payment1 = new CreditCardPayment(razorpayGateway);
        payment1.makePayment(1000);

        Payment payment2 = new UpiPayment(stripeGateway);
        payment2.makePayment(24344);
    }
}
