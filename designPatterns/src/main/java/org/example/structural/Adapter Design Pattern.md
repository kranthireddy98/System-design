Adapter converts the interface of a class into another interface the client expects -without changing
existing code.

### THe Core Problem 
You already have working code, but:
* client expects interface A
* You have a legacy / third-party class exposing interface B
* You cannot modify that class (library, vendor code, legacy system)

Without Adapter
* You rewrite client code
* You fork or modify library
* You introduce ugly if/else hacks

Adapter solves this mismatch cleanly

### Real-World Analogy
* Laptop needs USB-C
* Power socket gives 3-pin
* You use a power adapter

Socket == Adaptee

Laptop == client

Adapter == Adapter Pattern

USB-C interface = Target Interface

### Structure 

| Role        | Meaning                     |
| ----------- | --------------------------- |
| **Target**  | Interface client expects    |
| **Adaptee** | Existing incompatible class |
| **Adapter** | Converts Adaptee → Target   |
| **Client**  | Uses Target                 |


### Type 1 : Object Adaptor 
Uses composition 

Example: Payment Gateway Integration

Step 1: Target Interface
```java
public interface PaymentProcessor{
    
    void pay(int amount);
}
```

Step 2: Adaptee (3rd-party/legacy)
```java
public class StripePaymentGateway{
    public void makePayment(double amount){
        System.out.println("Paid "+ amount+" using Stripe");
    }
}
```
Step 3: Adapter
```java
public class StripeAdapter implements  PaymentPRocessor{
    private final StripePaymentGateway stripeGateway;
    
    public StripeAdapter(StripePaymentGateway stripeGateway){
        this.stripeGateway = stripeGateway;
    }
    
    public void pay(int amount){
        //Adapter converts int -> double
        stripeGateway.makePayment((double) amount);
    }
}
```
Step 4: Client Code
```java
public class CheckoutService {

    private final PaymentProcessor paymentProcessor;

    public CheckoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(int amount) {
        paymentProcessor.pay(amount);
    }
}
```
Step 5: Main
```java
public class Main {
    public static void main(String[] args) {
        StripePaymentGateway stripe = new StripePaymentGateway();
        PaymentProcessor processor = new StripeAdapter(stripe);

        CheckoutService checkout = new CheckoutService(processor);
        checkout.checkout(500);
    }
}
```
**Client is fully  decoupled from Stripe**

### Type 2: Class Adapter 
Use inheritance
```java
public class StripeClassAdapter extends StripePaymentGateway 
        implements PaymentProcessor {

    @Override
    public void pay(int amount) {
        makePayment(amount);
    }
}
```
* Not preferred in java

### When SHOULD You use Adapter?
* Integrating third-party libraries
* Migrating legacy systems
* Building plug-and-play architecture
* Normalizing multiple APIs
* Wrapping SDKs

### When NOT to use Adapter
* If you control both interfaces -> refractor instead
* If conversation logic becomes complex business logic
* If it hides poor design instead of fixing it


### How Adapter pattern Gets BROKEN
Violation 1 :  Adapter Contains Business Logic
```java
public void pay(int amount) {
    if(amount > 10000) {
        applyDiscount(); //  wrong
    }
    stripeGateway.makePayment(amount);
}
```
Adapter should only translate, not decide.

Violation 2: Client Knows Adaptee
```java
StripePaymentGateway stripe = new StripePaymentGateway();
stripe.makePayment(100); //  Adapter bypassed
```
Violation 3: Adapter Explosion
* One adapter per tiny variation
* Indicates wrong abstraction

### Adapter vs Similar Patterns

| Pattern       | Difference                                |
| ------------- | ----------------------------------------- |
| **Adapter**   | Makes incompatible interfaces work        |
| **Decorator** | Adds behavior                             |
| **Facade**    | Simplifies complex subsystem              |
| **Bridge**    | Decouples abstraction from implementation |

Adapter = compatability

Facade = simplification















