Bridge Pattern decouples abstraction from implementation so both can vary independently.

Instead of tightly coupling class hierarchy, we separate:
* Abstraction layer
* Implementation layer


### payment Processing System

imagine you're building a payment service in spring boot.

You support:

* Payment Types:
  * Credit Card
  * UPI
  * Net Banking

And you also support:
* payment Gateways:
  * Razorpay
  * stripe
  * PayPal

**Without Bridge Pattern**

if you combine both dimensions:
``` 
CreditCardRazorpayPayment
CreditCardStripePayment
CreditCardPaypalPayment
UpiRazorpayPayment
UpiStripePayment
UpiPaypalPayment
NetBankingRazorpayPayment
NetBankingStripePayment
NetBankingPaypalPayment
```

Explosion of classes

If tomorrow:
* Add new gateway -> create many new classes
* Add new payment type -> Create many new classes

This violates Open/Closed Principle.

### With Bridge Pattern

We separate:
* Abstraction -> payment
* Implementation -> Gateway

``` 
Payment (abstract)
        |
        --> CreditCardPayment
        --> UpiPayment
        
PaymentGateway (interface)
        |
        --> RazorpayGateway
        --> StripeGateway
```
**Payment HAS-A PaymentGateway**

This is the bridge.

### UML Style Understanding
``` 
Payment  -----> PaymentGateway
(Abstraction)   (implementation)
```
Instead of inheritance explosion -> We use composition



### Code Example
1. Implementation Layer
```java
// Implementor
public interface PaymentGateway {
  void processPayment(double amount);
}
```
```java
public class RazorpayGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing via Razorpay: " + amount);
    }
}
```
```java
public class StripeGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing via Stripe: " + amount);
    }
}
```
2. Abstraction Layer

```java
// Abstraction
public abstract class Payment {

    protected PaymentGateway gateway;

    public Payment(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public abstract void makePayment(double amount);
}
```
3. Refined Abstraction
```java
public class CreditCardPayment extends Payment {

    public CreditCardPayment(PaymentGateway gateway) {
        super(gateway);
    }

    public void makePayment(double amount) {
        System.out.println("Credit Card Payment Initiated");
        gateway.processPayment(amount);
    }
}
```
```java
public class UpiPayment extends Payment {

    public UpiPayment(PaymentGateway gateway) {
        super(gateway);
    }

    public void makePayment(double amount) {
        System.out.println("UPI Payment Initiated");
        gateway.processPayment(amount);
    }
}
```
4. Client Code
```java
public class Main {
    public static void main(String[] args) {

        PaymentGateway razorpay = new RazorpayGateway();
        PaymentGateway stripe = new StripeGateway();

        Payment payment1 = new CreditCardPayment(razorpay);
        payment1.makePayment(5000);

        Payment payment2 = new UpiPayment(stripe);
        payment2.makePayment(2000);
    }
}
```

### Why this is Powerful
1. Avoids class explosion
2. Follows Open/Closed Principle
3. Supports runtime switching of implementation
4. Promotes composition over inheritance
5. Scalable for microservices

### Spring Boot version

In real projects:
* payment -> Service layer
* paymentGateway -> Strategy injected via Spring.

```java
@Service
public class PaymentService {

    private final PaymentGateway gateway;

    public PaymentService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void pay(double amount) {
        gateway.processPayment(amount);
    }
}
```
**Spring decides implementation using:**
* @Qaulifier
* Profiles
* Factory
* Conditional Beans

### In Real Life
* Database drivers (JDBC API vs MySql/Postgres drivers)
* Logging frameworks (SLF4J -> Log4j/Logback)
* Messaging abstraction (Spring -> Kafka/RabbitMQ)
* Payment integration layers
* Multi-cloud deployment


**Bridge Pattern Separates abstraction from implementation so that both cab evolve
independently using composition instead of inheritance**

























