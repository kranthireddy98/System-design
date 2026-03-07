# What is Singleton?
--> Creational design pattern

--> Singleton ensures that a class has only ONE instance and provide a global access point to it

### intent
1. Exactly one object
2. Controlled access
3. Share state


## Classic Definition (GOF)

Ensure a class has only one instance and provide a global point of access to it

## Why do we need singleton?

Common real-world use cases:
1. Configuration manager
2. Cache
3. Logger
4. Database connect Pool
5. Thread pool
6. Spring Beans (by default)

# Core Rules of singleton
1. Private constructor
2. Private static instance
3. Public static getter

## Eager Initialization
```java
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}

```
* Pros
1. Thread-safe
2. Simple 
3. No Synchronization overhead
* Cons
1. Instance created even if never used

## Lazy Initialization (NOT Thread-safe)
```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

```
* Problem
1. Race Condition
2. Multiple threads can create multiple objects

## Synchronized method (Thread-safe but slow)

```java
public class Singleton
{
    private static Singleton instance;
    
    private Singleton(){}
    public synchronized static Singleton getInstance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }
}
```

* Pros
1. Thread-safe
* Cons
1. Synchronization on every call(Performance hit)

## Double-checked Locking 
```java
public  class Singleton {
    private static volatile singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance()
    {
        if(instance == null)
        {
            synchronized (Singleton.class)
            {
                if (instance ==null)
                {
                    instance =new Singleton();
                }
            }
        }
        return instance;
    }
}
```
* Why volatile?
1. Prevents instruction reordering
2. Ensures visibility across threads 

# Bill pugh Singleton 
```java
public class Singleton {
    private Singleton() {}
    
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    public static Singleton getInstance()
    {
        return Holder.INSTANCE;
    }
}
```
* Advantages 
1. Lazy initialization
2. Thread-safe
3. No synchronization
4. Clean & efficient

## Enum Singleton 

```java
public enum Singleton {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("Single via Enum");
    }
}
```
* Handles
1. Serialization
2. Reflection
3. Thread safety
* Downside
1. Less flexible
2. Not always liked in enterprise cde


# How Singleton can be Broken
1.Breaking Singleton Using Reflection

Reflection allows access to private constructors.

Even if the constructor is private, reflection can still create objects
```java
import java.lang.reflect.Constructor;

class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}

public class Test {

    public static void main(String[] args) throws Exception {

        Singleton s1 = Singleton.getInstance();

        Constructor<Singleton> constructor =
                Singleton.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        Singleton s2 = constructor.newInstance();

        System.out.println(s1);
        System.out.println(s2);
    }
}
```
Two different objects -> Singleton broken

**How to Prevent Reflection Attack**

add a constructor guard.
```java
class Singleton {

    private static Singleton instance;

    private Singleton() {

        if(instance != null) {
            throw new RuntimeException("Singleton already created");
        }
    }

    public static Singleton getInstance() {

        if(instance == null)
            instance = new Singleton();

        return instance;
    }
}
```
Best prevention:
```java
enum Singleton {
    INSTANCE
}
```
Reflection cannot create enum instances.

2. Breaking Singleton using Serialization.
Serialization writes objects to disk and recreates it.
During deserialization, JVM creates a new object instance.
```java
import java.io.*;

class Singleton implements Serializable {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

```java
Singleton s1 = Singleton.getInstance();

ObjectOutputStream out =
        new ObjectOutputStream(new FileOutputStream("file.txt"));
out.writeObject(s1);

ObjectInputStream in =
        new ObjectInputStream(new FileInputStream("file.txt"));

Singleton s2 = (Singleton) in.readObject();

System.out.println(s1 == s2);
```

**Fix Serialization Problem**
```java
class Singleton implements Serializable {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }

    protected Object readResolve() {
        return instance;
    }
}
```
3. Breaking Singleton using Cloning

If a Singleton implements `Cloneable`, clone creates new instance.

```java
class Singleton implements Cloneable {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```
```java
Singleton s1 = Singleton.getInstance();
Singleton s2 = (Singleton) s1.clone();

System.out.println(s1 == s2);
```
**Fix**
```java
@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
}
```

4. Breaking Singleton using Multiple ClassLoaders

Each classloader loads its own copy.

Therefore each classloader creates its own Singleton instance.

Happens In:
* Application servers
* OSGi
* Plugin systems
* Hot deployment environments

Example environments:
* Tomcat
* WebSphere
* JBoss

This is why  JVM-side Singleton is difficult in enterprise systems.

5. Breaking Singleton using Unsafe API
JVM internal class `unasfe` can create objects without constructor execution

```java
Unsafe unsafe = getUnsafe();

Singleton s = (Singleton) unsafe.allocateInstance(Singleton.class);
```

Constructor not called -> instance created.

This bypasses all protections.

6. Breaking Singleton Using Multiple JVMs
7. Breaking Singleton Using Spring Context
8. BulletProof Singleton
```java
public enum Singleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Hello");
    }
}
```

# Interview Questions You’ll Face
1. Why volatile is required?
2. How does JVM ensure thread safety in Bill Pugh?
3. Enum vs Bill Pugh?
4. Difference between Spring Singleton & Java Singleton?
5. Can Singleton be garbage collected?
6. How Singleton behaves in multi-classloader environment?