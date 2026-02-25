### State Design Pattern

The State Pattern allows an object to change its behavior when its internal state changes.

The Object appears to change its class.

Instead of writing:
``` 
if(state == "NEW") { ... }
else if(state == "PAID") { ... }
else if(state == "SHIPPED") { ... }
```
We delegate behavior to separate state classes.

### Real Life Example: Vending Machine
A vending machine behaves differently depending on its state:
* No coin
* Has coin
* Dispensing
* Out Of Stock

Same machine -> different behavior.

### Structure Of State Pattern
1. State interface

```java
public  interface State{
    void insertCoin();
    void pressButton();
    void dispense();
}
```
2. Concrete States

```java
public class NoCoinState implements State{
    private VendingMachine machine;
    
    public NoCoinState(VendingMachine machine){
        this.machine = machine;
    }
    
    @Override
    public void insertCoin(){
        System.out.println("Coin inserted");
        machine.setState(machine.getHasCoinState());
    }
    
    @Override
    public void pressButton(){
        System.out.println("Insert coin first");
    }
    
    @Override
    public void dispense(){
        System.out.println("No coin inserted");
    }
}
```
3. Context (Main Object)
```java
public class vendingMachine{
    private State noCoinState;
    private State hasCoinState;
    private State currentState;
    
    public VendingMachine() {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HashCoinState(this);
        currentState = noCoinState;
    }
    
    public void insertCoin(){
        currentState.insertCoin;
    }
    
    public void pressButton(){
        currentState.insertCoin();
    }
    
    public void setState(State state){
        this.currentState = state;
    }
    
    public State getHasCoinState(){
        return hashCoinState;
    }
}
```

### Problem Without State Pattern
```java
if(state == 0) { ... }
else if(state == 1) { ... }
else if(state == 2) { ... }
```
Issues:
* Violates Open/Closed Principle
* Large conditional blocks
* Hard to add new states
* High coupling
* Messy transitions

### Advantages 
* Removes complex `if-else`
* Each state has isolated logic
* Easy to add new states
* Follows Single Responsibility Principle
* Follows Open/Closed Principle

### Real Examples

1. Order Processing System 

States:
* CREATED
* PAID
* SHIPPED
* DELIVERED
* CANCELLED

Each state controls:
* can cancel?
* Can refund?
* Can ship?

2. Authentication Flow

States:
* Logged Out
* OTP_Verified
* LoggedIn
* SessionExpired 

3. ATM Machine

States:
* NoCard
* HasCard
* PinVerified
* Transaction

### State Vs Strategy

| State                                    | Strategy                          |
| ---------------------------------------- | --------------------------------- |
| Changes behavior based on internal state | Changes algorithm based on choice |
| Object transitions automatically         | Client selects strategy           |
| Has state transitions                    | No transitions                    |

State = object behavior changes internally

Strategy = client chooses algorithm

### When to Use

Use State Pattern when:
* Object behavior depends on state
* Many conditional statements exist
* State transitions are frequent
* You want clean, extensible code.

**State pattern allows an object to alter its behavior when its internal state changes by delegating state-specific behavior to separate classes**


















