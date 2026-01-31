## Prototype

Create a new object by cloning existing one instead of constructing them from scratch.

Don't build again, just copy me.

### The Problem
Imagine
* Object creation is expensive (DB calls, network calls,heavy calculations)
* OR object setup is complex (many fields, nested objects)
* OR you want to avoid tight coupling with concrete classes


Without Prototype:
```java
User user = new User();
user.setName("A");
user.setPermissions(loadFromDB());
user.setPreferences(loadFromService());
```
This run every time -> slow, messy, repetitive

### The solution (Prototype)
1. create a prototype object
2. Fully initialize it once 
3. Clone it whenever you need a new instance 

Structure

Participants
1. prototype (interface) - declares `clone()`
2. ConcretePrototype - implements cloning logic
3. Client - asks prototype to clone itself


### Example (shallow copy)
Step 1. Prototype Interface

```java
package org.example.Creational.prototype;

public interface prototype<T> {
    T clone();
}

```

Step 2: Concrete Prototype
```java
package org.example.Creational.prototype;

public class Employee implements prototype<Employee>{
    private String name;
    private String role;

    public void setName(String name) {
        this.name = name;
    }

    public Employee(String name, String role){
        this.name=name;
        this.role=role;
    }

    @Override
    public Employee clone() {
        return  new Employee(this.name,this.role);
    }

    @Override
    public String toString(){
        return name+ " - "+role;
    }
}
```
Step 3: Client
```java
package org.example.Creational.prototype;

public class PrototypeClient {

    public static void main(String[] args) {
        Employee prototype = new Employee("Kranthi","backend Engineer");

        Employee e1 = prototype.clone();
        Employee e2 = prototype.clone();


        e2.setName("Rajesh");
        System.out.println(e1);
        System.out.println(e2);
    }
}
```

Deep Copy Example 

Problem with Shallow Copy

if object has references, shallow copy is dangerous

```java
class Address {
    String city;
}
```
Deep Copy Prototype
```java
public class User implements Prototype<User> {

    private String name;
    private Address address;

    public User(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public User clone() {
        Address copiedAddress = new Address();
        copiedAddress.city = this.address.city;

        return new User(this.name, copiedAddress);
    }
}
```

Java Built-in Prototype (`Cloneable`) 

Java already supports prototype via:
```java
object.clone();
```
Example
```java
class Person implements Cloneable {

    String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

Why interviewers hate this
* cloneable is marker interface
* Clone() is protected
* No deep copy by default
* Breaks encapsulation

Preferred: custom clone() method or copy constructor

When to Use prototype

Use When:
* object creation is costly
* You need many similar objects
* Runtime object creation is needed
* Avoid `new` keyword everywhere

Avoid when 
* Objects are simple
* deep copy is extremely complex
* You don't control object internals

### How to break Prototype
Mutable shared references
```java
User u1 = prototype.clone();
u1.getAddress().city = "Delhi";

User u2 = prototype.clone();
// city also becomes Delhi 
```
* Not handling deep copy
* using Cloneable blindly
* Forgetting copy of collections

### How to detect Broken Prototype
* Changing one clone affects another
* Unexpected shared state
* Random bugs in concurrent code
* Fails under load/parallel execution


### Real-World Examples
* Spring Bean scopes (prototype)
* Game character cloning 
* Document templates 
* Cache-based object creation 
* UI component duplication























