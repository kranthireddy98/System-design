The flyweight Pattern is a structural design pattern that is used to reduce memory usage
when we have a large number of similar objects.

Instead of creating thousands of duplicate objects, we share common data between them.

### Real-Life Example 
Imagine typing a document in Microsoft Word:
* If you type 10,000 characters
* Word does not create 10,000 font objects
* It shares font properties like:
  * Font families
  * Size
  * Style
So:
  * Character -> Unique
  * Font -> Shared

That shared font object = Flyweight

### Core Idea
Separate object data into:

1. Intrinsic State
* Common data
* Stored inside flyweight object
* Example
  * Font Style
  * Color
  * Character type
2. Extrinsic State (unique)
* Passed from outside
* Not stored in flyweight
* Example
  * Position (x,y)
  * Context-specific data

### Structure 
1. flyweight (interface)
2. ConcreteFlyweight
3. FlyweightFactory
4. Client

### UML Structure
``` 
Client
   ↓
FlyweightFactory → creates & manages Flyweight objects
   ↓
ConcreteFlyweight (shared objects)
```

### Java Example (Game With Trees)

Imagine building a game like Minecraft

In a forest:
* 10,000 trees
* But only 3 types of trees

We don't want 10,000 tree objects fully stored in memory.

1. Flyweight interface
```java
interface Tree {
    void display(int x, int y);
}
```
2. Concrete FlyWeight
```java
class TreeType implements Tree {
    private String name;      // intrinsic
    private String color;     // intrinsic
    private String texture;   // intrinsic

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Tree: " + name + " at (" + x + "," + y + ")");
    }
}
```
3. Flyweight Factory
```java
import java.util.HashMap;
import java.util.Map;

class TreeFactory {
    private static Map<String, TreeType> treeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + color + texture;

        if (!treeMap.containsKey(key)) {
            treeMap.put(key, new TreeType(name, color, texture));
        }

        return treeMap.get(key);
    }
}
```
4. Client Code
```java
public class Forest {
    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            TreeType tree = TreeFactory.getTreeType("Oak", "Green", "Rough");
            tree.display(i, i);
        }
    }
}
```
**What Actually Happened?**

Even though we created 10,000 trees:

* Only ONE TreeType object created
* Shared across all
* Saved memory

### When to use Flyweight?

Use it when:
* you have large number of objects
* Most Objects data is similar
* Memory usage is high
* Object creation cost is high

### When Not to use
* If objects are very different 
* If extrinsic state becomes too complex
* If sharing creates thread-safety issues

### Real-World Examples
* String Pool in Java
* Integer caching (-128 to 127)
* Font rendering engines
* Game development (bullets, trees, enemies)

### Comparison With Other Patterns

| Pattern   | Purpose                        |
| --------- | ------------------------------ |
| Factory   | Object creation                |
| Singleton | Single instance                |
| Prototype | Clone objects                  |
| Flyweight | Share objects to reduce memory |

**Flyweight pattern is used to minimize memory usage by sharing common object state 
across multiple objects. it separated intrinsic (shared) and extrinsic (unique) states, and 
uses a factory to reuse existing objects instead of creating new ones.**



























