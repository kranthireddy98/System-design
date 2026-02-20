The Facade pattern is a structural design pattern that provides a simplified interface to a complex subsystem.

In Simple words:

Instead of talking to 10 different classes directly, you talk to one single class that handles everything internally.

### Technical Definition
Facade provides a unified, higher-level interface that makes a subsystem easier to use.

It:
* Simplifies usage
* Reduces dependency
* improves readability
* Hides internal complexity

### Without Facade
```java
class CPU {
    void start() { System.out.println("CPU started"); }
}

class Memory {
    void load() { System.out.println("Memory loaded"); }
}

class HardDrive {
    void read() { System.out.println("Hard drive read"); }
}

public class ComputerClient {
    public static void main(String[] args) {
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hd = new HardDrive();

        cpu.start();
        memory.load();
        hd.read();
    }
}
```

### With Facade
```java
class ComputerFacade {

    private CPU cpu;
    private Memory memory;
    private HardDrive hd;

    public ComputerFacade() {
        cpu = new CPU();
        memory = new Memory();
        hd = new HardDrive();
    }

    public void startComputer() {
        cpu.start();
        memory.load();
        hd.read();
        System.out.println("Computer started successfully");
    }
}

public class Client {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
}
```

### When to use Facade?
Use it when:
* System is complex
* Many dependencies between classes
* You want to reduce coupling
* You want clean API for clients

### Facade VS Adapter Vs Proxy

| Pattern     | Purpose                           |
| ----------- | --------------------------------- |
| **Facade**  | Simplifies interface              |
| **Adapter** | Converts one interface to another |
| **Proxy**   | Controls access                   |

Quick memory trick:
* Facade -> make it easy
* Adapter -> Make it compatible
* Proxy -> Make it controlled

### Real Spring Boot Example

``` 
@RestController
class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public String placeOrder() {
        orderService.placeOrder();
        return "Success";
    }
}
```
Inside OrderService, you might

* Validate user
* Check inventory
* Process payment
* Save order
* Send email

OrderService acts as Facade for the controller


**Facade is a structural design pattern that provides a simplified interface to a complex subsystem. 
It hides internal complexity and reduces coupling between client and subsystem. 
It is useful when we want to expose only limited functionality to clients while keeping the subsystem loosely coupled.**