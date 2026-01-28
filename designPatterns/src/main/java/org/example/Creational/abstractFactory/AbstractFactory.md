## Abstract Factory Design Pattern

### Core Rule of Abstract Factory 
Provides an interface for creating families of related or dependent objects without specifying their concrete classes.

Key Word : FAMiLIES
* If you're not creating related products together, you  probably don't need Abstract Factory.

### The problem
Imagine you're building a system that supports multiple environments/vendors/themes.

Example:
* UI Framework -> `Button`,`Checkbox`
* Database Layer -> `Connection`,`Transaction`
* Cloud providers -> `EC2`,`S3`

Without Abstract Factory
```java
if (env.equals("WINDOWS")) {
    button = new WindowsButton();
    checkbox = new WindowsCheckbox();
} else if (env.equals("MAC")) {
    button = new MacButton();
    checkbox = new MacCheckbox();
}
```
**Problems**
* Tight coupling 
* Violation of Open-Closed Principle
* Every new family = change everywhere
* Hard to test & extend

### What Abstract Factory Solves
* Groups related objects
* Enforces consistency (windows button + windows checkbox)
* Removes f-else hell
* Easy to add new families
* Clean dependency inversion


### Structure of Abstract Factory

There are 4 roles 
1. Abstract Factory -> creates products
2. Concrete Factory -> implements creation
3. Abstract Products -> interfaces
4. Concrete products -> actual objects

### Full Working Example

step 1: abstracts products
```java
interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

```
Step 2: Concrete Products (Windows Family)
```java
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering Windows Button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering Windows Checkbox");
    }
}
```
Step 3: Concrete Products (Mac Family)
```java
class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering Mac Button");
    }
}

class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering Mac Checkbox");
    }
}
```
Step 4: Abstract Factory
```java
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
```
Step 5: Concrete Factories
```java
class WindowsUIFactory implements UIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
```
```java
class MacUIFactory implements UIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```
Step 6: Client Code
```java
class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(UIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    void render() {
        button.paint();
        checkbox.paint();
    }
}
```
Step 7: Main
```java
public class Main {
    public static void main(String[] args) {

        UIFactory factory;

        String os = "WINDOWS"; // change to MAC

        if (os.equalsIgnoreCase("WINDOWS")) {
            factory = new WindowsUIFactory();
        } else {
            factory = new MacUIFactory();
        }

        Application app = new Application(factory);
        app.render();
    }
}
```

### Abstract Factory Vs Factory Method

| Aspect     | Factory Method         | Abstract Factory                      |
| ---------- | ---------------------- | ------------------------------------- |
| Purpose    | Create **one product** | Create **product families**           |
| Complexity | Low                    | Medium                                |
| Products   | Single                 | Multiple related                      |
| Example    | `createLogger()`       | `createButton()` + `createCheckbox()` |


Rule of thumb;
* One product -> Factory method
* Related products -> Abstract Factory

### Variations of Abstract factory
1. factory returning Factory 
```java
class FactoryProducer {
    static UIFactory getFactory(String os) {
        if (os.equals("WINDOWS")) return new WindowsUIFactory();
        return new MacUIFactory();
    }
}
```
2. Abstract Factory + Dependency Injection (Spring Boot)
```java
@Component
class WindowsUIFactory implements UIFactory {}
```
```java
@Autowired
UIFactory factory;
```
no if-else at all. Spring decides

3. Abstract Factory with Enum
```java
enum OS { WINDOWS, MAC }
```


### How break Abstract Factory
Mistake 1: Mixing Product families
```java
class BrokenFactory implements UIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox(); //  BREAK
    }
}
```
Family consistency broken

Mistake 2: Client using concrete classes
```java
WindowsButton button = new WindowsButton(); 
```
Bypasses the factory -> pattern destroyed

Mistake 3: Adding new product type
```java
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
    Menu createMenu(); //  forces all factories to change
}
```
Violates Open–Closed Principle

### How to Detect Abstract Factory is Broken
Ask these questions:
1. is client aware of concrete classes?
2. Are product families mixed
3. Does adding a new product force changes everywhere?
4. Are if-else checks inside business logic?

### Real world usage
* JDBC Drivers
* Spring BeanFactory
* UI Frameworks
* Cloud SDks
* Cross-Db Support

### When NOT to use Abstract Factory 
* Only one product
* No family relationship
* Too Many product variations
* Over-engineering small systems

“Abstract Factory is used when a system needs to be independent of how its related objects are created and must enforce consistency across product families.”




















