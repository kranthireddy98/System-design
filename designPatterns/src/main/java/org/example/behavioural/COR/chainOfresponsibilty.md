### Chain of Responsibility (COR) Design Pattern

The chain of Responsibility pattern lets you pass a request along a chain of handlers.

Each handler decides either:

* Handle the request
* Pass it to the next handler

This decouples sender from receiver.

### Real-world Example: ATM

When you withdraw 3800:
* 2000-not handler -> gives 1 note, passes remaining 1800
* 500-note handler -> gives 3 notes, passes remaining 300
* 100-note handler -> gives 3 notes

Each handler processes what it can, then forwards the rest.

### Structure
``` 
Client -> Handler1 -> Handler2 -> Handler3
```
Each handler has:
* Reference to next handler
* `handle(request)` method

### UML Structure (Conceptual)
```
Handler (interface)
    ↓
ConcreteHandlerA
ConcreteHandlerB
ConcreteHandlerC
```
### Practical Example (Approval System)
Scenario:

Leave approval system:
* TeamLead -> approves <= 2 days
* Manager -> approves <= 5 days
* Director -> approves anything

1. Handler Interface
```java
abstract class LeaveApprover {
    protected LeaveApprover nextApprover;

    public void setNextApprover(LeaveApprover nextApprover) {
        this.nextApprover = nextApprover;
    }

    public abstract void approveLeave(int days);
}
```
2. Concrete Handler
```java
class TeamLead extends LeaveApprover {
    public void approveLeave(int days) {
        if (days <= 2) {
            System.out.println("TeamLead approved " + days + " days leave.");
        } else if (nextApprover != null) {
            nextApprover.approveLeave(days);
        }
    }
}
```
```java
class Manager extends LeaveApprover {
    public void approveLeave(int days) {
        if (days <= 5) {
            System.out.println("Manager approved " + days + " days leave.");
        } else if (nextApprover != null) {
            nextApprover.approveLeave(days);
        }
    }
}
```
```java
class Director extends LeaveApprover {
    public void approveLeave(int days) {
        System.out.println("Director approved " + days + " days leave.");
    }
}
```
3. Client Code

```java
public class Main {
    public static void main(String[] args) {
        LeaveApprover teamLead = new TeamLead();
        LeaveApprover manager = new Manager();
        LeaveApprover director = new Director();

        teamLead.setNextApprover(manager);
        manager.setNextApprover(director);

        teamLead.approveLeave(4);
    }
}
```

### Where it's Used in Real Systems

1. Servlet Filters (Spring Boot)
In Spring Bott, filters work like CoR.

``` 
Request → AuthenticationFilter → LoggingFilter → ValidationFilter → Controller
```
Each filter:
* Processes request
* Calls `chain.ofFilter()`
This is pure Chain of Responsibility.

2. Spring Security
Authentication providers try one by one until one succeeds.

3. Exception Handling Chain
Multiple handlers try to handle an exception

### When to use
Use CoR when:
* Multiple objects can handle a request
* You don't know which one will handle it
* You want loose coupling
* You want dynamic chain creation at runtime

### Advantages
* Loose coupling
* Single Responsibility
* Open/Close principle
* Flexible order of handlers

### Disadvantages
* Request might go unhandled
* Debugging chain can be tricky
* Performance overhead if chain is long

| Pattern                     | Key Idea                               |
| --------------------------- | -------------------------------------- |
| **Chain of Responsibility** | Pass request along chain until handled |
| **Decorator**               | Add behavior dynamically               |
| **Strategy**                | Choose algorithm at runtime            |
| **Observer**                | Notify multiple subscribers            |

### Advanced Version
Instead of classes, you can chain lambdas:

```java
UnaryOperator<String> handler1 = s -> s.contains("A") ? s + " handled by A" : s;
UnaryOperator<String> handler2 = s -> s.contains("B") ? s + " handled by B" : s;

String result = handler1.andThen(handler2).apply("AB");
```

