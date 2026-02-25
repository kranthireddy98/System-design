The Command Pattern is a behavioral design pattern that turns a request into a 
standalone object containing all details about the request.

It encapsulates a request as an object so that you can parameterize, queue, log, undo or execute it later.

### Real-World Analogy: TV Remote

You press a button on a remote:

* The remote doesn't know how the TV turns on.
* It just calls a command like `TurnOnCommand`
* The TV performs the action

So:
* Remote -> Invoker
* TurnOnCommand -> command
* TV -> Receiver

### Structure of Command Pattern

1. command (Interface)
```java
public interface Command{
    void execute();
}
```
2. Receiver (Actual business logic)
```java
public class Light{
    public void turnOn(){
        System.out.println("Light is ON");
    }
    public void turnOff(){
        System.out.println("Light id OFF");
    }
}
```
3. Concrete Command
```java
public class TurnOnCommand implements Command{
    private Light light;
    
    public TurnOnCommand(Light light){
        this.light = light;
    }
    
    @Override
    public void execute(){
        light.turnOn();
    }
}
```
4. Invoker
```java
public class RemoteControl {
    private Command command;
    
    public void setCommand(Command command){
        this.command = command;
    }
    
    public void pressButton(){
        command.execute();
    }
    
}
```
5. Client
```java
public class Main{
    public static void main(String[] args) {
        Light light = new Light();
        
        Command turnOn = new TurnOnCommand(light);
        
        RemoteControl remote = new RemoteControl();
        
        remote.pressButton();
    }
}
```
### Before Vs After (Problem & Solution)
Without Command Pattern

```java
if(action.equals("ON")){
    light.turnOn();
}
```
Problems:
* Tight coupling
* Hard to extend
* No Undo
* No Logging/queueing

### With command Pattern
* Decouples sender and receiver
* Easy to add new commands
* Supports undo
* Supports logging
* Supports queueing(like kafka style event processing -- useful in microservices)

### In Real systems
* Spring Framework internally uses command-style abstractions
* Job queues (like Kafka consumers)
* GUI button actions
* Transaction rollback systems
* Undo/Redo in editors

Example:

In a banking app:
* TransferMoneyCommand
* withdrawCommand
* DepositCommand

Each can be:
* Logged 
* Retried
* Rolled back

### Add Undo Support
```java
public interface Command{
    void execute();
    void undo();
}
```
concrete Command:
```java

@Override
public void undo(){
    light.turnOff();
}
```
* Maintain a stack
* Push executed commands
* Pop to undo

### Why Command Pattern?
* It follows Open/Closed Principle
* Reduces coupling between sender & Receiver
* Enables queueing, scheduling, logging
* Makes undo/redo possible
* Helps in implementing Saga pattern compensating actions

### Command vs Strategy
| Command              | Strategy               |
| -------------------- | ---------------------- |
| Encapsulates request | Encapsulates algorithm |
| Can be queued/logged | Usually stateless      |
| Supports undo        | No undo                |
| Focus on action      | Focus on behavior      |


### When Not to Use
* Very Simple logic
* No need for decoupling
* No undo/logging/queueing required

**Command Pattern encapsulates a request as an object, allowing
you to parameterize clients with different request, queue them, log them and support undoable operations**















