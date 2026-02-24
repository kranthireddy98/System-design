The observer Pattern is a behavioral pattern where:

When one Object (Subject) changes its state, all its dependent objects (Observers) are 
automatically notified and updated.

It's heavily used in event-driven systems, message brokers, and UI frameworks.

It is used internally in event listeners, messaging systems, and reactive programming.

### Real-life Example

Youtube Chanel Subscription model

* A Youtube channel uploads a new video.
* All subscribers get notified

Here;
* Channel -> Subject
* Subscribers -> Observers
* Notification -> Update mechanism

### Core Concept

There are 4 main parts:
1. Subject (Publisher)
   * Maintains list of observers
   * Notifies them when state changes
2. Observer (subscriber interface)
   * Defines update() method
3. ConcreteSubject
   * Actual object with state
4. ConcreteObserver
   * Reacts to changes

### Problem Without Observer 

Suppose we write tight-coupled code:

```java
import java.util.concurrent.Flow;

class YoutubeChannel {
    private Subscriber sub1 = new Subscriber();
    private Subscriber sub2 = new Subscriber();
    
    public void uploadVideo(){
        sub1.notifyUser();
        sub2.notifyUser();
    }
}
```
Problems:
* Tight coupling
* Cannot dynamically add/remove subscribers
* Violates Open/Closed Principle
* Hard to scale

### proper Observer Implementation

Step 1: Observer Interface
```java
interface Observer {
    void update(String videoTitle);
}
```
Step 2: Subject Interface
```java
interface Subject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers();
}
```
Step 3: Concrete Subject
```java
import java.util.ArrayList;
import java.util.List;

class YouTubeChannel implements Subject {

    private List<Observer> subscribers = new ArrayList<>();
    private String videoTitle;

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
    }

    public void uploadVideo(String title) {
        this.videoTitle = title;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : subscribers) {
            observer.update(videoTitle);
        }
    }
}
```
Step 4: Concrete Observer
```java
class Subscriber implements Observer {

    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(name + " received notification: New video - " + videoTitle);
    }
}
```

Step 5: Client Code
```java
public class Main {
    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        Subscriber s1 = new Subscriber("Kranthi");
        Subscriber s2 = new Subscriber("Ravi");

        channel.subscribe(s1);
        channel.subscribe(s2);

        channel.uploadVideo("Observer Pattern Explained");
    }
}
```

### Internal Working
When uploadvideo() is called

``` 
State change -> notifyObservers() -> loop -> update() on each subscriber
```
It is:
* Runtime dynamic
* Loosely coupled
* Easily extensible

### Where it is used in real Backend Systems

| Technology              | Observer Usage             |
| ----------------------- | -------------------------- |
| Java EventListener      | Built-in observer          |
| Spring ApplicationEvent | Event-based system         |
| Kafka                   | Publisher-Subscriber model |
| RabbitMQ                | Pub-Sub pattern            |
| React useEffect         | Reacts to state change     |

### Observer Vs Pub-Sub

| Observer                | Pub-Sub                |
| ----------------------- | ---------------------- |
| Direct reference        | Through message broker |
| Synchronous usually     | Async mostly           |
| Tight binding in memory | Decoupled via broker   |
| In-process              | Distributed            |

**Observer pattern defines a one-to-may dependency between objects so that when one subject changes 
state, all its dependents are notified automatically. it promotes loose coupling by depending on abstraction
rather than concrete implementations.**

### Drawbacks
* Memory leak if observers not removed
* Can cause cascade updates
* Difficult debugging in complex systems





























