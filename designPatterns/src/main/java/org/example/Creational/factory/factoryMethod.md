## Factory Method pattern 

### Core Rule of Factory Method

Define an interface for creating an object, but let subclasses decide which class to instantiate.

**Key idea**
* Object creation is delegated to subclasses
* Client code never uses `new` directly
* Creation logic is decoupled from usage

### Problem Before factory Methods (why it exists)
**Problematic Code**
```java
public class NotificationService{
    
    public void send(String type){
        if(type.equals("EMAIL")){
            EmailNotification notification = new EmailNotification();
            notification.send();
        } else if (type.equals("SMS")) {
            SmsNotification notification = new SmsNotification();
            notification.send();
        }
    }
}
```
**What's wrong here?**
1. Tight coupling (`new EmailNotification()`)
2. Violates Open-Closed Principle
3. Every new type = modify this class
4. Hard to test and extend
5. Logic explosion with `if-else`

This is where Factory Method enters.

### Factory Method-Standard Structure

| Role            | Responsibility             |
| --------------- | -------------------------- |
| Product         | Interface / abstract class |
| ConcreteProduct | Actual implementation      |
| Creator         | Declares factory method    |
| ConcreteCreator | Overrides factory method   |

Step 1: Product Interface
```java
public interface Notification {
    void send();
}
```
Step 2: Concrete Products
```java
public class EmailNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Sending Email Notification");
    }
}
```
```java
public class SmsNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Sending SMS Notification");
    }
}
```
Step 3: Creator (Abstract class)

This is where the factory Method lives
```java
public abstract class NotificationCreator {

    // FACTORY METHOD
    protected abstract Notification createNotification();

    // Business logic uses factory method
    public void sendNotification() {
        Notification notification = createNotification();
        notification.send();
    }
}
```
Step 4: Concrete Creators
````java
public class EmailNotificationCreator extends NotificationCreator {

    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}
````
```java
public class SmsNotificationCreator extends NotificationCreator {

    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}
```
Step 5: Client Code
```java
public class Main {
    public static void main(String[] args) {

        NotificationCreator creator =
                new EmailNotificationCreator();
        creator.sendNotification();

        creator = new SmsNotificationCreator();
        creator.sendNotification();
    }
}
```
### Why this is Production-ready
* Open for extension
* Closed for modification
* Creation logic isolated
* Easy to test (mock creator)
* Aligns with SOLID principles


### Variation 1 : Factory method using enum

Factory with ENUM Selector
```java
public enum NotificationType {
    EMAIL, SMS
}
```
```java
public abstract class NotificationCreator {
    public abstract Notification create(NotificationType type);
}
```
```java
public class DefaultNotificationCreator extends NotificationCreator {

    @Override
    public Notification create(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailNotification();
            case SMS -> new SmsNotification();
        };
    }
}
```
This starts drifting towards Simple Factory, not pure Factory Method.

### factory Method vs Simple Factory

| Feature          | Simple Factory | Factory Method |
| ---------------- | -------------- | -------------- |
| Inheritance      | ❌              | ✅              |
| Polymorphism     | ❌              | ✅              |
| OCP              | ❌              | ✅              |
| Subclass decides | ❌              | ✅              |
| GOF Pattern      | ❌              | ✅              |


### How factory Method can be Broken

Breaking Rule # 1: Using `new` inside business logic

```java
public void sendNotification() {
    EmailNotification notification = new EmailNotification(); // ❌
    notification.send();
}
```
Pattern is broken -> Tight coupling is back

Breaking Rule #2: Factory method returns concrete class
```java
protected EmailNotification createNotification() {
    return new EmailNotification(); // ❌
}
```
* Compiles
* Violated abstraction
* Harder to refractor

Breaking Rule #3: Static factory method
```java
public static Notification create() {
    return new EmailNotification();
}
```
* Static methods cannot be overridden
* Kills polymorphism
* Not factory Method anymore

### How to Know the pattern is Broken
Ask these questions:
* Is object creation delegated to subclass
* Are `new` keywords leaking into client/business code?
* Is the factory method static?
* Is `if-else` explosion back?

If appears pattern is broken

### Real-world examples

Spring Framework
* BeanFactory
* FactoryBean<T>

Java API
* Iterator() -> factory method inside collections
* Executors.newFixedThreadPool() (simple factory style)

