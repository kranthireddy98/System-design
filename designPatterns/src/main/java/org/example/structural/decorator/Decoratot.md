The Decorator Pattern allows you to add new behavior to an object dynamically at runtime without modifying its structure

It follows:

Open/Closed Principle -> Open for extension, closed for modification.

### Real-World Analogy: Coffee Shop

You order a coffee and then:
* Add milk 
* Add sugar
* Add whipped cream

Instead of creating:
* coffeeWithMilk
* CoffeeWithMilkAndSUgar
* CoffeeWithMilkSugarAndCream

We wrap the base coffee and decorators

### Structure of Decorator Pattern
``` 
        Component (Interface)
              ↑
     ConcreteComponent
              ↑
         Decorator (abstract)
              ↑
     ConcreteDecoratorA
     ConcreteDecoratorB
```

### Example
Step 1:  Component Interface
```java 
public interface Coffee {
    String getDescription();
    double getCost();
}
```
Step 2: Concrete Component
```java
public class BasicCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double getCost() {
        return 100;
    }
}
```
Step 3: Abstract Decorator
```java
public abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}
```
Step 4: Concrete Decorators
```java
public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 20;
    }
}
```
```java
public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10;
    }
}
```
Step 5: Client Code
```java
public class Main {
    public static void main(String[] args) {

        Coffee coffee = new BasicCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
    }
}
```
### how it works internally

Each decorator:
* Implements same interface
* Wraps another object
* Adds behavior before/after delegating to wrapped object

This creates a chain of wrappers
```
SugarDecorator
   ↓
MilkDecorator
   ↓
BasicCoffee
```

### When to use Decorator?
* When you want runtime behavior addition
* When subclass explosion is happening
* When you want flexible feature combinations
* When you follow SOLID principles

### When NOT to Use
* If object behavior never changes
* If too many tiny decorators make code unreadable


### Decorator Vs Inheritance

| Inheritance           | Decorator        |
| --------------------- | ---------------- |
| Compile-time behavior | Runtime behavior |
| Class explosion       | Flexible         |
| Static structure      | Dynamic wrapping |

### Real Java Example You Already Use

Java IO classes:

``` 
BufferedReader br = 
    new BufferedReader(
        new InputStreamReader(
            new FileInputStream("file.txt")
        )
    );
```
Here: 
* FileInputStream -> Base
* InputStreamReader -> Decorator
* BufferedReader -> Decorator

Same concept!

**The Decorator Pattern is a structural design pattern that allows behavior to be added to objects dynamically at runtime by wrapping them in decorator classes. 
It avoids subclass explosion and follows the Open/Closed Principle.**


























