The Template Method Pattern defines the skeleton of an algorithm in a base class and allows subclasses to override
specific steps without changing the overall algorithm structure.

It Promotes code reuse + controlled extension.

### Real-world Analogy -- Making Tea & coffee

Both tea and coffee follow the same process:
1. Boil water
2. Brew
3. Pour into cup
4. Add condiments

But the brewing and condiments differ.

Instead of duplicating the whole process, we define the template once and customize only the varying parts.

### Structure
* Abstract class
  * Contains
    * templateMethod() -> final
    * Abstract steps (to be implemented by subclass)
    * Optional hooks
* Concrete Subclass
  * Implements specific steps

### Example

Step 1: Abstract Class (Template)
```java
abstract class Beverage {

    // Template method (final to prevent override)
    public final void prepareRecipe() {
        boilWater();
        brew();          // abstract
        pourInCup();
        addCondiments(); // abstract
    }

    private void boilWater() {
        System.out.println("Boiling water");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup");
    }

    // Steps to be implemented
    protected abstract void brew();
    protected abstract void addCondiments();
}
```
Step 2: Concrete Classes
```java
class Tea extends Beverage {

    @Override
    protected void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon");
    }
}
```
```java
class Coffee extends Beverage {

    @Override
    protected void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}
```
Step 3: Client Code
```java
public class Main {
    public static void main(String[] args) {

        Beverage tea = new Tea();
        tea.prepareRecipe();

        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
```

### Why Template Method?
1. Avoid Code duplication
Common steps defined once.
2. Control Algorithm Flow
subclass cannot change flow.
3. Open/Closed Principle
Add new behavior without modifying base class.

### Hooks 
A hook is an optional method that subclass may override
```java
abstract class Beverage {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    protected boolean customerWantsCondiments() {
        return true; // default behavior
    }

    protected abstract void brew();
    protected abstract void addCondiments();
}
```
Now subclass can control behavior

### Template Method vs Strategy

| Template Method           | Strategy                  |
| ------------------------- | ------------------------- |
| Uses inheritance          | Uses composition          |
| Algorithm fixed in parent | Algorithm interchangeable |
| Compile-time behavior     | Runtime behavior          |
| Less flexible             | More flexible             |

if you want runtime switching -> use Strategy

If algorithm structure must stay fixed -> Template Method.

**Template Method defines the skeleton of an algorithm in a superclass and allows subclasses to override specific steps without changing its structure. It promotes code reuse and enforces algorithm consistency. 
It relies on inheritance and often marks the template method as final.**