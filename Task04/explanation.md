# TASK 04 – DESIGN CLASSES, OBJECTS AND CONSTRUCTORS

## 1. Introduction

Object-oriented programming is used to represent real-world entities as classes and objects. In the Campus Smart-Parking System, classes are designed to represent vehicles, parking tickets, parking facilities and users.

A class defines the properties and behaviours of an object. An object is an instance of a class that contains actual data.

Constructors are special methods used to initialize objects when they are created. Java supports default constructors, parameterised constructors, overloaded constructors and constructor chaining.

## 2. Identification of Classes, Fields and Methods

The following classes are designed for the Campus Smart-Parking System.

### A. Vehicle Class

The Vehicle class represents a vehicle entering the parking facility.

**Fields:**
- registrationNumber – Stores the vehicle registration number.
- vehicleType – Stores the category of the vehicle.
- ownerName – Stores the vehicle owner's name.

**Methods:**
- getRegistrationNumber() – Returns the registration number.
- getVehicleType() – Returns the vehicle category.
- displayDetails() – Displays vehicle information.

### B. ParkingTicket Class

The ParkingTicket class represents the ticket issued when a vehicle enters the parking facility.

**Fields:**
- ticketNumber – Stores the unique ticket number.
- registrationNumber – Stores the vehicle registration number.
- entryTime – Stores the vehicle entry date and time.
- floorNumber – Stores the allocated floor.
- slotNumber – Stores the allocated slot.

**Methods:**
- getTicketNumber() – Returns the ticket number.
- getEntryTime() – Returns the entry time.
- displayTicket() – Displays ticket information.

### C. ParkingFacility Class

The ParkingFacility class manages the parking floors and available slots.

**Fields:**
- slots – Stores the parking slot information.
- totalFloors – Stores the number of floors.

**Methods:**
- allocateSlot() – Allocates an available parking slot.
- releaseSlot() – Releases an occupied parking slot.
- checkAvailability() – Checks available parking spaces.

### D. ParkingUser Class

The ParkingUser class represents students, staff members and visitors.

**Fields:**
- userId – Stores the user identification number.
- userName – Stores the user's name.
- userType – Stores the user category.
- hasValidPass – Indicates whether the user has a valid monthly pass.

**Methods:**
- validatePass() – Checks the validity of the parking pass.
- displayUserDetails() – Displays user information.

## 3. Types of Constructors

A constructor is a special member of a class that initializes an object. It has the same name as the class and does not have a return type.

### A. Default Constructor

A default constructor is a constructor without parameters. If no constructor is declared, Java automatically provides a default constructor. A programmer can also define an explicit no-argument constructor.

Example:

```java
class ParkingTicket {

    String ticketNumber;
    String registrationNumber;

    ParkingTicket() {
        ticketNumber = "NOT-ISSUED";
        registrationNumber = "NOT-ASSIGNED";
    }
}
```

**Usage:**

It can be used to create a draft ticket object before actual ticket details are assigned.

### B. Parameterised Constructor

A parameterised constructor accepts values as arguments and initializes the object with the supplied information.

Example:

```java
class ParkingTicket {

    String ticketNumber;
    String registrationNumber;

    ParkingTicket(String ticketNumber, String registrationNumber) {
        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
    }
}
```

**Usage:**

It is used when a vehicle enters the parking facility and a ticket must be created with actual vehicle details.

### C. Constructor Overloading

Constructor overloading means defining multiple constructors in the same class with different parameter lists.

Example:

```java
class ParkingTicket {

    String ticketNumber;
    String registrationNumber;

    ParkingTicket() {
        ticketNumber = "NOT-ISSUED";
        registrationNumber = "NOT-ASSIGNED";
    }

    ParkingTicket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
        registrationNumber = "NOT-ASSIGNED";
    }

    ParkingTicket(String ticketNumber, String registrationNumber) {
        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
    }
}
```

**Usage:**

Different constructors allow tickets to be created with different amounts of information.

## 4. Use of the this Keyword

The `this` keyword refers to the current object.

It is mainly used to distinguish instance variables from constructor parameters when they have the same names.

Example:

```java
class ParkingTicket {

    String ticketNumber;

    ParkingTicket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
```

Here:

- `this.ticketNumber` refers to the instance variable.
- `ticketNumber` refers to the constructor parameter.

This makes the program easier to understand and prevents naming confusion.

## 5. Constructor Chaining

Constructor chaining is the process of calling one constructor from another constructor using the `this()` keyword.

It avoids repeating initialization statements in multiple constructors.

Example:

```java
class ParkingTicket {

    String ticketNumber;
    String registrationNumber;

    ParkingTicket() {
        this("NOT-ISSUED", "NOT-ASSIGNED");
    }

    ParkingTicket(String ticketNumber) {
        this(ticketNumber, "NOT-ASSIGNED");
    }

    ParkingTicket(String ticketNumber, String registrationNumber) {
        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
    }
}
```

**Explanation:**

1. The default constructor calls the two-parameter constructor.
2. The one-parameter constructor also calls the two-parameter constructor.
3. The final constructor performs the actual initialization.

In Java, a `this()` constructor call must be the first statement in the constructor.

**Advantages:**

- Avoids duplicate code.
- Makes initialization consistent.
- Improves code readability.
- Makes maintenance easier.

## 6. Copy Constructor

A copy constructor creates a new object by copying the information from an existing object.

In Java, a copy constructor is a programmer-defined constructor that accepts another object of the same class.

Example:

```java
class ParkingTicket {

    String ticketNumber;
    String registrationNumber;

    ParkingTicket(String ticketNumber, String registrationNumber) {
        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
    }

    ParkingTicket(ParkingTicket original) {
        this.ticketNumber = original.ticketNumber;
        this.registrationNumber = original.registrationNumber;
    }
}
```

**Usage in the parking system:**

A copy constructor can be useful when a duplicate physical ticket needs to be printed because the original ticket is damaged or lost.

The duplicate must retain the same ticket number and vehicle details so that it refers to the same parking transaction.

It should not create a second active parking transaction.

## 7. Complete Java Implementation – ParkingTicket.java

The following program demonstrates default, parameterised, overloaded and copy constructors, along with `this` and constructor chaining.

```java
import java.time.LocalDateTime;

public class ParkingTicket {

    private String ticketNumber;
    private String registrationNumber;
    private LocalDateTime entryTime;
    private int floorNumber;
    private int slotNumber;

    // Default constructor
    public ParkingTicket() {
        this("NOT-ISSUED", "NOT-ASSIGNED",
             LocalDateTime.now(), 0, 0);
    }

    // Parameterised constructor
    public ParkingTicket(String ticketNumber,
                         String registrationNumber,
                         LocalDateTime entryTime,
                         int floorNumber,
                         int slotNumber) {

        if (ticketNumber == null || ticketNumber.isBlank()
                || registrationNumber == null
                || registrationNumber.isBlank()
                || entryTime == null
                || floorNumber < 0 || slotNumber < 0) {

            throw new IllegalArgumentException(
                    "Invalid parking ticket details");
        }

        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
        this.entryTime = entryTime;
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
    }

    // Overloaded constructor
    public ParkingTicket(String ticketNumber,
                         String registrationNumber,
                         int floorNumber,
                         int slotNumber) {

        this(ticketNumber, registrationNumber,
             LocalDateTime.now(), floorNumber, slotNumber);
    }

    // Copy constructor
    public ParkingTicket(ParkingTicket original) {

        this(original.ticketNumber,
             original.registrationNumber,
             original.entryTime,
             original.floorNumber,
             original.slotNumber);
    }

    // Display ticket details
    public void displayTicket() {

        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Vehicle Number: " + registrationNumber);
        System.out.println("Entry Time: " + entryTime);
        System.out.println("Floor Number: " + floorNumber);
        System.out.println("Slot Number: " + slotNumber);

        System.out.println("-------------------------");
    }

    public static void main(String[] args) {

        // Default constructor
        ParkingTicket t1 = new ParkingTicket();

        // Parameterised constructor
        ParkingTicket t2 = new ParkingTicket(
                "T101",
                "TN01AB1234",
                LocalDateTime.now(),
                1,
                5
        );

        // Overloaded constructor
        ParkingTicket t3 = new ParkingTicket(
                "T102",
                "TN02CD5678",
                2,
                10
        );

        // Copy constructor
        ParkingTicket t4 = new ParkingTicket(t2);

        System.out.println("DEFAULT CONSTRUCTOR");
        t1.displayTicket();

        System.out.println("PARAMETERISED CONSTRUCTOR");
        t2.displayTicket();

        System.out.println("OVERLOADED CONSTRUCTOR");
        t3.displayTicket();

        System.out.println("COPY CONSTRUCTOR");
        t4.displayTicket();
    }
}
```

## 8. Explanation of Object Creation and Memory Allocation

Java uses stack and heap memory while creating and managing objects.

### A. Stack Memory

Stack memory stores local variables, method calls and reference variables.

Example:

```java
ParkingTicket t1 = new ParkingTicket();
```

The local reference variable `t1` is stored in the method's stack frame.

### B. Heap Memory

Heap memory stores objects created using the `new` keyword.

Example:

```java
ParkingTicket t1 = new ParkingTicket();
```

The actual ParkingTicket object is created in heap memory.

### C. Reference Variable

A reference variable stores the reference used to access an object.

Example:

```java
ParkingTicket t1 = new ParkingTicket();

ParkingTicket t2 = t1;
```

Here, `t1` and `t2` refer to the same object. No second object is created by the assignment.

### D. Memory Representation

```text
             STACK MEMORY
        -----------------------
        | t1 --------------+   |
        | t2 --------------|---|
        -------------------|---
                           |
                           v
              HEAP MEMORY
        -----------------------
        |   ParkingTicket      |
        |----------------------|
        | Ticket Number        |
        | Registration Number |
        | Entry Time           |
        | Floor Number         |
        | Slot Number          |
        -----------------------
```

When a new object is created using `new`, memory is allocated in the heap.

Java's garbage collector automatically reclaims heap objects that are no longer reachable.

## 9. Alternative Concept and Complexity Analysis

The selected design uses overloaded constructors and constructor chaining to initialize objects.

An alternative is to create objects using a default constructor followed by setter methods or static factory methods.

### A. Default Constructor Followed by Setters

Example:

```java
class ParkingTicket {

    String ticketNumber;
    String registrationNumber;

    ParkingTicket() {
    }

    void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
```

Object creation:

```java
ParkingTicket ticket = new ParkingTicket();

ticket.setTicketNumber("T101");
ticket.setRegistrationNumber("TN01AB1234");
```

**Advantages:**

- Values can be assigned individually.
- Useful when data arrives in multiple steps.
- Easy to modify non-essential information.

**Disadvantages:**

- An object may temporarily contain incomplete data.
- Every setter must perform validation.
- Incorrect setter usage may result in invalid object states.

### B. Static Factory Methods

A static factory method is a static method that creates and returns an object instead of calling a constructor directly from the client code.

Example:

```java
class ParkingTicket {

    private String ticketNumber;
    private String registrationNumber;

    private ParkingTicket(String ticketNumber,
                          String registrationNumber) {

        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
    }

    public static ParkingTicket createTicket(
            String ticketNumber,
            String registrationNumber) {

        if (ticketNumber == null || ticketNumber.isBlank()
                || registrationNumber == null
                || registrationNumber.isBlank()) {

            throw new IllegalArgumentException(
                    "Invalid ticket details");
        }

        return new ParkingTicket(ticketNumber,
                                 registrationNumber);
    }
}
```

Object creation:

```java
ParkingTicket ticket =
        ParkingTicket.createTicket("T101", "TN01AB1234");
```

**Advantages:**

- Provides a meaningful method name.
- Allows validation before object creation.
- Controls how objects are created.
- Can return different implementations when required.

### C. Comparison of Object Creation Methods

**Constructor Chaining:**

- Initializes the object in a structured way.
- Avoids repeated initialization code.
- Helps maintain consistent object state.

**Default Constructor with Setters:**

- Allows values to be assigned separately.
- May create incomplete objects.
- Requires validation in multiple setter methods.

**Static Factory Methods:**

- Provides controlled object creation.
- Allows validation before returning the object.
- Offers more flexibility in object creation.

### D. Time and Space Complexity

Let N represent the number of ParkingTicket objects created.

| Approach | Time Complexity | Space Complexity |
|---|---|---|
| Constructor chaining | O(N) | O(N) |
| Default constructor with setters | O(N) | O(N) |
| Static factory methods | O(N) | O(N) |

Each approach creates N objects, so the total object storage requirement is O(N).

For one ticket, initialization takes O(1) time when a fixed number of fields is assigned.

The actual space required per object depends on its fields and JVM memory representation.

The setter approach may require additional validation operations, but its overall initialization complexity remains O(N) for a fixed number of fields.

## 10. Final Design Decision

For the Campus Smart-Parking System, overloaded constructors and constructor chaining are selected because they provide a clear and consistent way to initialize parking ticket objects.

The default constructor supports draft ticket creation, while parameterised constructors initialize tickets with actual vehicle details.

The copy constructor is useful for producing a duplicate physical copy of an existing ticket without creating a new parking transaction.

Private fields and constructor validation help prevent invalid ticket information.

Static factory methods are a useful alternative when object creation requires more complex validation or different creation rules.

## 11. Conclusion

Classes and objects provide the foundation for the Campus Smart-Parking System.

Constructors initialize objects with the required information, while constructor overloading provides flexibility in object creation.

The `this` keyword and constructor chaining reduce repeated code and improve maintainability. Copy constructors allow existing ticket information to be copied when necessary.

Understanding stack memory, heap memory and reference variables helps explain how Java manages objects.

Therefore, the selected constructor-based design provides a structured and reliable method for creating parking ticket objects.
