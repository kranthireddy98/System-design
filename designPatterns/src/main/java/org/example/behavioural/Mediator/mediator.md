The Mediator Pattern is a behavioral design pattern that reduces tight coupling between
objects by introducing a mediator object that handles communication between them.

Instead of object talking to each other directly, they communicate through a mediator.

### Why Mediator?

Whe many objects interact directly:
* High coupling
* Hard to maintain
* Changes in one class affect many others
* Complex communication graph (N^2 relationships)

Mediator centralizes communication:
* Loose coupling
* Better maintainability
* Clear communication flow
* Single Responsibility principle respected

### Real-World Example: Air Traffic control

Imagine airplane at an airport.
* Planes do NOT talk to each other
* They communicate with the Central Tower
* Control Tower coordinates takeoff and landing

Here:
* Planes = colleagues
* Control tower = mediator

### Practical Software Example

Chat Room System

Instead of users directly messaging each other:
* Users send message to ChatRoom
* ChatRoom distributes to other users

``` 
Mediator (interface)
   ↑
ConcreteMediator

Colleague (abstract)
   ↑
ConcreteColleague
```
### Java Implementation 
1. Mediator Interface
```java
public interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
```

2. Concrete Mediator
```java
import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {

    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}
```
3. Colleague (user)
```java
public abstract class User {

    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}
```
4. Concrete User
```java
public class ChatUser extends User {

    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(name + " Sending: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(name + " Received: " + message);
    }
}
```
5. Client Code
```java
public class Main {
    public static void main(String[] args) {

        ChatMediator mediator = new ChatRoom();

        User user1 = new ChatUser(mediator, "Kranthi");
        User user2 = new ChatUser(mediator, "Ravi");
        User user3 = new ChatUser(mediator, "Sneha");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);

        user1.send("Hello everyone!");
    }
}
```

Instead of:
``` 
user1 → user2
user1 → user3
user2 → user3
```
we now have:
``` 
user → mediator → other users
```
Reduced coupling drastically

### When to Use Mediator
Use When:
* many objects communicate in complex ways
* Logic is scattered across multiple classes
* You want centralized control
* You want to reduce dependencies

### Whe not to use
* If mediator becomes too large
* If communication is simple and limited

### Mediator vs Observer

| Mediator                    | Observer                   |
| --------------------------- | -------------------------- |
| Controls interaction logic  | Notifies subscribers       |
| Bidirectional communication | One-to-many notification   |
| Central coordinator         | Publisher-subscriber model |


### Use Cases
Mediator appears:
* Spring MVC DispatchServlet
* Workflow engines
* Chat applications
* Event orchestration layer
* Microservice orchestration (not choreography)












