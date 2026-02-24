### Iterator Design Pattern

### What is iterator?

The iterator pattern provides a way to access elements of a collection sequentially without exposing its internal structure.

It separates:
* Traversal logic
* From collection structure

### Real world Example

Think about
* TV Remote -> You press Next to change channels
* You don't know how channels are stored internally

Remote = Iterator

TV = Collection

### Why do we need it?

Imagine you built:

``` 
class EmployeeList {
    Employee[] employees;
}
```
If tomorrow you change it to:
``` 
List<Employee> eployees;
```

All client code breaks.

Iterator solves this problem ny hiding internal storage.

### Structure of Iterator Pattern
``` 
Iterator (interface)
    hasNext()
    next()

ConcreteIterator

Aggregate (interface)
    createIterator()

ConcreteAggregate
```
### Example
Step 1: Iterator Interface
```java
interface Iterator<T> {
    boolean hasNext();
    T next();
}
```

Step 2: Aggregate Interface
```java
interface Container<T> {
    Iterator<T> getIterator();
}
```
Step 3: Concrete Collection
```java
class EmployeeRepository implements Container<String> {

    private String[] employees = {"Kranthi", "Ravi", "Anil"};

    @Override
    public Iterator<String> getIterator() {
        return new EmployeeIterator();
    }

    private class EmployeeIterator implements Iterator<String> {

        int index = 0;

        @Override
        public boolean hasNext() {
            return index < employees.length;
        }

        @Override
        public String next() {
            if(this.hasNext()) {
                return employees[index++];
            }
            return null;
        }
    }
}
```

Step 4: Client Code
```java
public class Main {
    public static void main(String[] args) {

        EmployeeRepository repo = new EmployeeRepository();
        Iterator<String> iterator = repo.getIterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

### Where Java uses Iterator Internally
* ArrayList
* HashSet
* HashMap
* LinkedList

```java
List<String> list = new ArrayList<>();
list.add("A");
list.add("B");

Iterator<String> it = list.iterator();

while(it.hasNext()) {
    System.out.println(it.next());
}
```



### When to Use

When you want to:
* Traverse different collections uniformly
* Hide internal data structure
* Support multiple traversal strategies

Don't Use if:
* Simple loop is enough
* No abstraction needed





























