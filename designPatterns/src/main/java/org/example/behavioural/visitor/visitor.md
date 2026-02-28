The Visitor Pattern is a behavioral design pattern that lets you add new operations to existing
object structures without modifying those classes.

it follows the principle:

Separate algorithms from the objects on which they operate.

Problem:

Suppose you have a structure like;
* Circle
* Rectangle
* Triangle

Now you want to:
* Calculate area
* Export to JSON
* Export to XML
* Apply discount
* Validate

If you add these methods inside each class:
```java
class Circle {
    double calculateArea() {}
    String toJson() {}
    String toXml() {}
    void applyDiscount() {}
}
```
Everytime a new operation comes, you modify all shape classes.

This violates Open/Closed principle (Open for extension, closed for modification).

### Solution: Visitor Pattern

instead of modifying shape classes, you:
1. Create a visitor interface
2. Create concrete visitors for each operation
3. Shapes accept visitors

``` 
Visitor
   ↑
ConcreteVisitor

Element
   ↑
ConcreteElement
```

### Real-world Example

Think of:
* An e-commerce cart
* Different product types
* Multiple operations like tax, discount, shipping

Products stay same

Operations change frequently.

Perfect use case for visitor.

### Example: Shopping Cart System
Step 1: Element Interface
```java
public interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}
```

Step 2: concrete Elements
```java
public class Book implements ItemElement {
    private int price;
    private String isbn;

    public Book(int price, String isbn) {
        this.price = price;
        this.isbn = isbn;
    }

    public int getPrice() { return price; }
    public String getIsbn() { return isbn; }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}
```
```java
public class Fruit implements ItemElement {
    private int pricePerKg;
    private int weight;
    private String name;

    public Fruit(int pricePerKg, int weight, String name) {
        this.pricePerKg = pricePerKg;
        this.weight = weight;
        this.name = name;
    }

    public int getPricePerKg() { return pricePerKg; }
    public int getWeight() { return weight; }
    public String getName() { return name; }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}
```
Step 3: visitor Interface
```java
public interface ShoppingCartVisitor {
    int visit(Book book);
    int visit(Fruit fruit);
}
```
Step 4: Concrete Visitor
```java
public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    @Override
    public int visit(Book book) {
        int cost = book.getPrice();
        if(cost > 50) {
            cost -= 5; // discount
        }
        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        return fruit.getPricePerKg() * fruit.getWeight();
    }
}
```
Step 5: Client
```java
public class ShoppingCartClient {

    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[]{
                new Book(100, "1234"),
                new Book(40, "5678"),
                new Fruit(10, 2, "Banana"),
                new Fruit(5, 5, "Apple")
        };

        int total = 0;
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();

        for(ItemElement item : items) {
            total += item.accept(visitor);
        }

        System.out.println("Total Cost = " + total);
    }
}
```
### How it works

This is called Double Dispatch.

Instead of:
``` 
visitor.visit(item);
```
We use;
``` 
item.accept(visitor);
```

1. Runtime decides which object (Book or Fruit)
2. That object calls correct `visit(this)`
3. Visitor method overload gets selected

Two dispatches happen -> Double Dispatch.

### When to Use Visitor

use it when:
* you have a stable object structure
* you need to add many operations
* You don't want to modify existing classes

### When NOT to Use
* If object structure changes frequently
* If adding new element types often

Because every new element requires updating visitor interface.

### Visitor Vs Strategy 

| Visitor              | Strategy             |
| -------------------- | -------------------- |
| Adds new operations  | Changes algorithm    |
| Uses double dispatch | Uses single dispatch |
| Structure stable     | Behavior changeable  |

### Real-World Framework Examples

Visitor-like behavior is seen in:
* Java AST processing (compiler)
* Spring Expression Language
* JSON parsing tress
* Hibernate Criteria processing

**Visitor pattern lets you define new operations on object structure without modifying the structure classes. it uses 
double dispatch to achieve runtime method resolution.**










































