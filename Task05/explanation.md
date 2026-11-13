# TASK 05 – APPLY STATIC MEMBERS AND THE OBJECT LIFE CYCLE

## 1. Introduction

Static members belong to the class rather than individual objects. They are shared by all objects of that class.

In the Campus Smart-Parking System, static members are useful for maintaining a unique parking ticket counter and tracking the total number of tickets issued.

The object life cycle describes how objects are created, used and eventually become eligible for garbage collection.

## 2. Static Variables

A static variable is declared using the `static` keyword. Only one shared copy exists for the class.

Example:

```java
class ParkingTicket {
    static int ticketCounter = 0;
}
```

In the parking system, a static ticket counter is useful because every newly issued ticket must have a unique ticket number.

If an instance variable were used, each object would maintain a separate counter, which could generate duplicate ticket numbers.

**Advantages:**
- Shared data between objects.
- Avoids maintaining separate counters.
- Helps generate unique ticket numbers within the application.

## 3. Static Methods

Static methods belong to the class and can be called without creating an object.

Example:

```java
class ParkingTicket {
    static int ticketCounter = 0;

    static int getTotalTickets() {
        return ticketCounter;
    }
}
```

Usage:

```java
System.out.println(ParkingTicket.getTotalTickets());
```

In the parking system, static methods can be used to display the total number of tickets issued.

## 4. Static Block

A static block executes once when the class is initialized. It is used to initialize static variables or perform one-time class-level initialization.

Example:

```java
class ParkingTicket {

    static int ticketCounter;

    static {
        ticketCounter = 1000;
        System.out.println("Parking System Initialized");
    }
}
```

The static block initializes the ticket counter before objects are created.

## 5. Object Life Cycle

The object life cycle describes the different stages of an object's existence.

### A. Object Creation

An object is created using the `new` keyword.

```java
ParkingTicket ticket = new ParkingTicket();
```

Memory is allocated for the object in heap memory.

### B. Object Usage

The object is used to store and access parking information.

```java
ticket.displayTicket();
```

### C. Object Becomes Eligible for Garbage Collection

When a vehicle leaves the parking facility, its active parking ticket may no longer be required.

If no reachable reference points to the ticket object, it becomes eligible for garbage collection.

Example:

```java
ticket = null;
```

However, garbage collection is not guaranteed to happen immediately. The JVM decides when to reclaim unused objects.

## 6. Passing Objects to Methods

Java passes object references by value. A method receives a copy of the reference and can access the same object.

Example:

```java
static void displayTicket(ParkingTicket ticket) {
    ticket.displayTicket();
}
```

Usage:

```java
displayTicket(ticket);
```

This allows methods to access and process parking ticket information without creating another ticket object.

## 7. Returning Objects from Methods

A method can return an object to the calling method.

Example:

```java
static ParkingTicket createTicket() {
    return new ParkingTicket();
}
```

Usage:

```java
ParkingTicket ticket = createTicket();
```

In the parking system, a ticket creation method can return a newly generated parking ticket after a vehicle enters the facility.

## 8. Alternative Concept and Complexity Analysis

The assignment uses a static ticket counter. An alternative is to use an instance variable inside the ParkingFacility class.

### A. Static Counter

A static counter is shared by all objects of the class.

For example, two facilities operating at the north and south gates can use one shared counter to generate globally unique ticket numbers within the application.

### B. Instance Counter

If the ticket counter is an instance variable, each ParkingFacility object maintains its own counter.

Example:

```java
class ParkingFacility {
    int ticketCounter = 1000;
}
```

If two facilities are created, both counters may start at 1000, resulting in duplicate ticket numbers unless a facility identifier is included.

### C. Comparison

| Feature | Static Counter | Instance Counter |
|---|---|---|
| Ownership | Class | Object |
| Number of counters | One shared counter | One per facility |
| Shared between facilities | Yes | No |
| Space for k facilities | O(1) | O(k) |
| Unique tickets | Shared sequence within the application | Requires separate facility identification or coordination |

### D. Complexity Analysis

Let:

- k = number of parking facilities.
- n = number of parking tickets issued.

**Static Counter:**
- Counter storage: O(1).
- Increment operation: O(1).
- Total counter storage remains constant regardless of the number of facilities.

**Instance Counter:**
- Counter storage: O(k).
- Increment operation: O(1) per facility.
- Each facility maintains its own counter.

The parking ticket objects themselves require O(n) total storage while they remain reachable, regardless of which counter design is used.

### E. Design Decision

For the Campus Smart-Parking System, a static counter is suitable when all facilities share one application and require a globally unique ticket sequence.

If the facilities operate independently, a facility-specific prefix combined with a local counter is another suitable solution.

## 9. Conclusion

Static variables and methods are useful for managing class-level information in the Campus Smart-Parking System.

A static ticket counter helps generate unique ticket numbers shared across parking facilities operating within the same application.

Static blocks perform one-time initialization, while object references allow parking tickets to be passed to and returned from methods.

After a vehicle exits, its ticket object can become eligible for garbage collection when no reachable references remain.

Therefore, static members and a proper understanding of the object life cycle improve memory management and the reliability of the parking application.
