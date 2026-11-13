# TASK 07 – OVERRIDE AND SPECIALISE BEHAVIOUR

## 1. Introduction

Method overriding is an important feature of Object-Oriented Programming in Java. It allows a subclass to provide its own implementation of a method already defined in its superclass.

In the Campus Smart-Parking System, different vehicle categories have different parking charges. Therefore, method overriding is used to calculate the appropriate parking fee based on the vehicle category.

## 2. Methods Overridden in the Parking System

The Vehicle class defines common behaviour, while subclasses provide specialised implementations.

| Class | Overridden Method | Purpose |
|---|---|---|
| TwoWheeler | ratePerHour() | Returns Rs. 10 per hour |
| Car | ratePerHour() | Returns Rs. 30 per hour |
| ElectricCar | chargingFee() | Calculates charging cost |

The calculateFee() method in Vehicle calls ratePerHour(). Therefore, the correct vehicle-specific rate is selected at runtime.

This avoids writing separate fee calculation logic for every vehicle category.

## 3. Use of @Override Annotation

The @Override annotation informs the compiler that a method is intended to override a method from its superclass.

Example:

```java
@Override
protected double ratePerHour() {
    return 10;
}
```

### Rules of Method Overriding

1. The method name must be the same.
2. The parameter list must be the same.
3. The return type must be the same or a compatible covariant return type.
4. The access level cannot be more restrictive than the overridden method.
5. Static methods are hidden rather than overridden.
6. Private methods cannot be overridden.

The @Override annotation helps identify incorrect method declarations during compilation.

## 4. Use of final Keyword

The final keyword prevents unwanted modifications in Java.

### A. Final Variable

A final variable cannot be reassigned after initialization.

Example:

```java
private final String regNo;
```

The vehicle registration number should remain unchanged after the vehicle object is created.

### B. Final Method

A final method cannot be overridden by subclasses.

Example:

```java
public final String getRegNo() {
    return regNo;
}
```

This ensures that subclasses cannot change the registration number retrieval behaviour.

However, calculateFee() should not be final when subclasses need to customise that calculation directly.

### C. Final Class

A final class cannot be extended by another class.

Example:

```java
final class ParkingReceipt {
}
```

A class such as Vehicle should not be final because new vehicle categories must inherit from it.

## 5. Overriding Object Class Methods

Java provides three important methods in the Object class: toString(), equals() and hashCode().

### A. toString()

The toString() method returns a readable string representation of an object.

Example:

```java
@Override
public String toString() {
    return "Vehicle Registration: " + regNo;
}
```

It helps display vehicle information easily.

### B. equals()

The equals() method compares two objects logically.

In the parking system, two Vehicle objects with the same registration number are considered equal.

Example:

```java
@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;

    if (!(obj instanceof Vehicle))
        return false;

    Vehicle other = (Vehicle) obj;
    return regNo.equals(other.regNo);
}
```

### C. hashCode()

The hashCode() method returns an integer value representing the object for hash-based collections.

Example:

```java
@Override
public int hashCode() {
    return regNo.hashCode();
}
```

If two objects are equal according to equals(), they must return the same hash code.

These methods help identify vehicles and avoid duplicate registration records.

## 6. Alternative Concept – Switch Statement

Instead of method overriding, a single calculateFee() method can use a switch statement to identify the vehicle category.

Example:

```java
switch (vehicleType) {
    case "TwoWheeler":
        rate = 10;
        break;

    case "Car":
        rate = 30;
        break;

    case "ElectricCar":
        rate = 30;
        break;

    default:
        rate = 0;
}
```

The parking fee is then calculated using the selected rate.

### Comparison

| Feature | Method Overriding | Switch Statement |
|---|---|---|
| Design | Uses inheritance and specialised methods | Uses one central method |
| Vehicle-specific behaviour | Implemented in subclasses | Implemented using cases |
| Adding a new vehicle | Add a new subclass | Modify the switch statement |
| Code maintenance | Easier to extend | Becomes more difficult with many types |
| Runtime selection | Dynamic method dispatch | Conditional selection |
| Extensibility | Better | Limited |

## 7. Time and Space Complexity Analysis

Let k represent the number of vehicle categories.

### Method Overriding

- Method dispatch: O(1) per call.
- Fee calculation: O(1), assuming a constant-time rate calculation.
- Additional storage: O(1) per vehicle for its object-specific data, excluding shared class metadata.

The correct subclass method is selected automatically at runtime.

### Switch Statement

- Vehicle type selection: O(k) in the worst case when checking k vehicle categories.
- Fee calculation: O(1) after selecting the rate.
- Additional storage: O(1) per vehicle for the required fields.

When a new vehicle category is introduced, the central switch statement must be modified.

### Maintenance Analysis

With method overriding, a new vehicle category can be introduced by creating a new subclass and implementing its specialised behaviour.

With a switch-based approach, the existing calculation method must be modified whenever a new category is added.

Therefore, method overriding provides better extensibility and maintainability for the Smart-Parking System.

## 8. Conclusion

Method overriding allows different vehicle categories to implement their own parking behaviour.

The @Override annotation improves code correctness, while the final keyword protects important data from unwanted modification.

Overriding toString(), equals() and hashCode() provides readable object information and reliable vehicle comparison.

Compared with a switch-based solution, method overriding provides a more flexible and maintainable design when new vehicle categories are introduced.
