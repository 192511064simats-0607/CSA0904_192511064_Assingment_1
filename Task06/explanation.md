# TASK 06 – DESIGN THE INHERITANCE HIERARCHY

## 1. Introduction

Inheritance is an important feature of Object-Oriented Programming in Java. It allows one class to acquire the properties and methods of another class.

In the Campus Smart-Parking System, different types of vehicles share common information such as vehicle registration number and parking details. Instead of writing the same code repeatedly, inheritance is used to create a common superclass named Vehicle.

## 2. Superclass and Subclasses

The superclass is Vehicle. It contains common properties and behaviours shared by all vehicles.

The subclasses are:

- TwoWheeler – represents motorcycles and scooters.
- Car – represents ordinary cars.
- ElectricCar – represents electric cars that require charging facilities.

### Class Responsibilities

| Class | Inherited Properties | Additional Responsibility |
|---|---|---|
| Vehicle | Base class | Stores registration number |
| TwoWheeler | Vehicle properties | Represents two-wheelers |
| Car | Vehicle properties | Represents cars |
| ElectricCar | Vehicle and Car properties | Supports electric charging |

## 3. Types of Inheritance Used

### A. Hierarchical Inheritance

Hierarchical inheritance occurs when multiple subclasses inherit from one superclass.

Example:

Vehicle → TwoWheeler

Vehicle → Car

Both TwoWheeler and Car inherit common properties from Vehicle.

### B. Multilevel Inheritance

Multilevel inheritance occurs when one subclass inherits from another subclass.

Example:

Vehicle → Car → ElectricCar

ElectricCar inherits the properties of Car and indirectly inherits the properties of Vehicle.

### C. Single Inheritance

Single inheritance occurs when one subclass directly inherits from one superclass.

For example, Car extends Vehicle.

Java supports these inheritance types through classes.

## 4. UML Class Diagram

```text
                 Vehicle
              -------------
              - regNo
              -------------
              + getRegNo()
              + display()
                    |
          +---------+---------+
          |                   |
      TwoWheeler             Car
      ----------          ----------
                            |
                            |
                        ElectricCar
                        -----------
                        + chargingFee()
```

The diagram represents hierarchical and multilevel inheritance.

## 5. Use of super Keyword

The super keyword refers to the immediate parent class.

It is used in three important ways.

### A. Accessing Parent Constructor

The super() statement calls the parent class constructor.

Example:

```java
class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }
}
```

### B. Accessing Parent Method

The super keyword can call a method defined in the parent class.

Example:

```java
super.display();
```

### C. Accessing Parent Field

The super keyword can access an inherited field when it is accessible in the subclass.

Example:

```java
super.vehicleType;
```

Private fields cannot be accessed directly using super. They should be accessed through suitable getter methods.

## 6. Constructor Execution Order

When an ElectricCar object is created, constructors execute from the topmost parent class to the child class.

The execution order is:

1. Vehicle constructor.
2. Car constructor.
3. ElectricCar constructor.

Example:

```java
ElectricCar e = new ElectricCar("TN03C3");
```

Output:

```text
Vehicle Constructor Called
Car Constructor Called
ElectricCar Constructor Called
```

This happens because each constructor calls its parent constructor using super().

## 7. Alternative Concept – Composition

Instead of inheritance, composition can be used.

Composition means that a Vehicle object contains another object called VehicleType, which stores the vehicle category and hourly rate.

Example:

```java
class VehicleType {
    String name;
    double rate;
}

class Vehicle {
    String regNo;
    VehicleType type;
}
```

Here, Vehicle has a VehicleType object instead of inheriting from separate vehicle subclasses.

### Comparison

| Feature | Inheritance | Composition |
|---|---|---|
| Relationship | Is-a | Has-a |
| Structure | Parent and child classes | Objects containing other objects |
| Adding vehicle types | Usually requires a new subclass | Can use a new VehicleType object |
| Fee calculation | Uses inherited or overridden methods | Uses the associated type's rate |
| Flexibility | Suitable for stable vehicle categories | More flexible for changing vehicle types |
| Code maintenance | Can become complex with many subclasses | Easier to modify vehicle properties |

## 8. Complexity Analysis

Let N represent the number of parked vehicles.

### Inheritance

- Fee calculation: O(1) when each vehicle has a constant-time fee method.
- Vehicle object storage: O(1) per vehicle, excluding shared class metadata.
- Total storage: O(N).

### Composition

- Fee calculation: O(1) when the VehicleType rate is accessed directly.
- Vehicle object storage: O(1) per vehicle, with an additional reference to VehicleType.
- Total storage: O(N + T), where T is the number of shared vehicle-type objects.

Both approaches provide constant-time fee calculation.

Composition may require fewer class changes when new vehicle categories are introduced. Inheritance is useful when vehicle categories have different behaviours and require specialised methods.

## 9. Conclusion

Inheritance provides code reusability and a clear relationship between different vehicle categories.

In the Campus Smart-Parking System, Vehicle is selected as the superclass, while TwoWheeler, Car and ElectricCar are subclasses.

Hierarchical and multilevel inheritance help organise the vehicle categories. The super keyword allows child constructors and methods to reuse parent-class functionality.

Inheritance is suitable for the initial vehicle hierarchy, while composition can provide additional flexibility when vehicle categories and pricing rules change.
