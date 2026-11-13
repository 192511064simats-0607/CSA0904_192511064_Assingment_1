# TASK 09 – INTRODUCE ABSTRACTION

## 1. Introduction

Abstraction is an important concept in Object-Oriented Programming. It hides implementation details and displays only the essential features of an object.

In the Campus Smart-Parking System, abstraction is used to define common vehicle operations while allowing individual vehicle categories to implement their own behaviour.

Java supports abstraction using:
1. Abstract classes.
2. Interfaces.

## 2. Abstract Class – Vehicle

The Vehicle class should be declared as an abstract class because all vehicles share common properties and behaviours.

Common properties include:
- Registration number
- Parking fee calculation
- Vehicle identification

The Vehicle class contains an abstract method called ratePerHour().

Each subclass provides its own implementation of this method.

Example:

```java
abstract class Vehicle {

    private final String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public abstract double ratePerHour();
}
```

The abstract class cannot be directly instantiated. It provides a common structure for all vehicle categories.

## 3. Interface – Chargeable

An interface is used to represent a capability rather than a common vehicle identity.

The Chargeable interface represents vehicles that require charging facilities.

Example:

```java
interface Chargeable {

    double chargingFee(int kWh);
}
```

ElectricCar implements the Chargeable interface because it requires a separate charging fee.

TwoWheeler and ordinary Car do not need to implement this interface.

## 4. Difference Between Abstract Class and Interface

| Abstract Class | Interface |
|---|---|
| Uses the abstract keyword | Uses the interface keyword |
| Can have instance variables | Cannot have ordinary instance variables |
| Can have constructors | Cannot have constructors |
| Can contain abstract and concrete methods | Can contain abstract, default and static methods |
| A class extends only one class | A class can implement multiple interfaces |
| Represents common identity and state | Represents additional capabilities |

An abstract class is suitable for Vehicle because all vehicles share registration information and common parking operations.

An interface is suitable for Chargeable because only certain vehicles need charging functionality.

## 5. Multiple Behaviours Through Interfaces

Java does not support multiple inheritance of classes. However, a class can implement multiple interfaces.

For example:

```java
interface Chargeable {
    double chargingFee(int kWh);
}

interface Payable {
    double paymentAmount();
}

class ElectricCar extends Car
        implements Chargeable, Payable {
    
    // Implement both interface methods
}
```

The ElectricCar class inherits common vehicle behaviour from Car and obtains additional capabilities through Chargeable and Payable.

This provides flexibility without multiple class inheritance.

## 6. Default and Static Methods in Interfaces

### A. Default Method

A default method provides a common implementation inside an interface.

Example:

```java
default void showChargingMessage() {
    System.out.println("Vehicle requires charging");
}
```

This method can be used by all classes implementing Chargeable without requiring each class to rewrite it.

### B. Static Method

A static method belongs to the interface itself and can be called using the interface name.

Example:

```java
static double standardRate() {
    return 12.0;
}
```

It can be called using:

```java
Chargeable.standardRate();
```

A static method is useful for providing common utility calculations.

## 7. Alternative Concept – Replacing Abstract Class with Interface

In the original design:
- Vehicle is an abstract class.
- Chargeable is an interface.

In the alternative design:
- Vehicle becomes an interface with default methods.
- Chargeable becomes an abstract class.

Example:

```java
interface Vehicle {
    default void display() {
        System.out.println("Vehicle Details");
    }
}

abstract class Chargeable {
    public abstract double chargingFee(int kWh);
}
```

However, this alternative creates limitations.

### Limitations

1. An interface cannot have a normal constructor to initialise registration numbers.
2. An interface cannot maintain ordinary instance state for each vehicle.
3. An abstract class cannot be inherited alongside another class because Java supports only single class inheritance.
4. A vehicle requiring charging behaviour must extend Chargeable, which prevents it from extending another class.

Therefore, the alternative design is less suitable for this parking system.

## 8. Complexity Analysis

Let n represent the number of vehicle objects.

### Original Design

- Method execution: O(1) for constant-time operations.
- Object storage: O(n) for n vehicle objects.
- Method dispatch: O(1) for ordinary virtual method dispatch.

### Alternative Design

- Default method execution: O(1) for constant-time operations.
- Object storage: O(n) for n vehicle objects.
- Method dispatch: O(1) for ordinary interface method calls.

Both designs have similar basic runtime complexity.

However, the original design is better because it supports common vehicle state through constructors and allows additional capabilities through multiple interfaces.

## 9. Conclusion

Abstraction improves the structure and flexibility of the Campus Smart-Parking System.

The abstract Vehicle class provides common properties and operations, while the Chargeable interface provides additional charging functionality.

Default and static interface methods reduce repeated code and provide reusable behaviour.

Therefore, combining abstract classes and interfaces is a suitable approach for designing an extensible Smart-Parking System.
