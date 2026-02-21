The Proxy Pattern provides a surrogate or placeholder object that controls
access to another object.

In Simple words:

Instead of directly calling the real object, you call a proxy, and the proxy decides what to do
before/after delegating to the real object.

### Why Do we need Proxy?

* Control access 
* Add logging
* Add caching 
* Lazy initialization
* Remote method invocation
* Reduce heavy object creation cost

### Types of Proxy

1. Virtual Proxy

Example:

Loading a high-resolution image only when required

2. Protection Proxy

Controls access based on roles/permissions.

Example:

Only ADMIN can delete users.

3. Remote Proxy

Represent an object in another address space

Example:

RMI in java.

4. Smart Proxy

Adds extra functionality like logging, counting references, caching

### UML Structure 
![img.png](img.png)


### Structure

``` 
Client -> Proxy -> RealSubject
```
* Subject -> Common interface
* RealSubject -> Actual business logic
* Proxy -> Controls access & delegates

### Java Example

Step 1: Subject Interface

```java
public interface Image {
    void display();
}
```

Step 2: Real Object
```java
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(); // heavy operation
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
```

Step 3: Proxy
```java
public class ProxyImage implements Image {

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {

        if (realImage == null) {
            realImage = new RealImage(fileName); // lazy loading
        }

        realImage.display();
    }
}
```

Step 4: Client
```java
public class Main {
    public static void main(String[] args) {

        Image image = new ProxyImage("test.jpg");

        image.display(); // loads from disk
        image.display(); // does NOT load again
    }
}
```

### What happens Internally?
1. client creates `ProxyImage`
2. Real Object NOT created yet
3. First call -> proxy creates RealImage
4. Second call -> Direct delegation (no heavy load)


### Spring & Java

1. Spring AOP

Spring creates proxy objects for:
* Transactional
* Cacheable
* Async

It wraps your original class with a proxy.

2. Hibernate Lazy Loading

When you fetch an entity:
```java
User user = entityManager.find(User.class,1L);
```
Hibernate may return a proxy object instead of actual entity.

3. Java Dynamic Proxy

Using `java.lang.reflect.proxy`

```java
InvocationHandler handler = new MyInvocationHandler(realObject);

MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
        realObject.getClass().getClassLoader(),
        realObject.getClass().getInterfaces(),
        handler);
```

### Proxy Vs Decorator

| Proxy                  | Decorator                          |
| ---------------------- | ---------------------------------- |
| Controls access        | Adds new behavior                  |
| Usually same interface | Same interface                     |
| Focus on access        | Focus on functionality enhancement |

Proxy = Gate Keeper

Decorator = feature enhancer

