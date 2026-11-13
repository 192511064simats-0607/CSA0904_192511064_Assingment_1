# TASK 10 – ENFORCE ENCAPSULATION THROUGH PACKAGES AND ACCESS MODIFIERS

## 1. Introduction

Encapsulation is an important concept in Object-Oriented Programming. It combines data and methods into a single class and protects the internal data from direct external access.

In the Campus Smart-Parking System, encapsulation protects vehicle registration numbers, parking duration and slot information.

## 2. Package Structure

The project is organised into three main packages:

- parking.model – Contains Vehicle and ParkingSlot classes.
- parking.service – Contains ParkingService class.
- parking.app – Contains ParkingDemo main class.

This structure separates data, business logic and application execution.

## 3. Access Modifiers

Java provides four main access modifiers.

| Modifier | Purpose |
|---|---|
| private | Accessible only within the same class |
| default | Accessible within the same package |
| protected | Accessible within the same package and subclasses |
| public | Accessible from any package |

### Access Modifier Selection

- Private fields protect registration numbers, parking duration and slot status.
- Public constructors allow objects to be created from other packages.
- Public getters and setters provide controlled access to data.
- Protected methods can be used for validation or extension by subclasses.
- Default methods can be used for internal package-level operations.

## 4. Getters and Setters with Validation

Setters are used to update private fields safely.

Examples:

```java
public void setParkingDuration(Integer duration) {

    if (duration == null || duration < 0) {
        throw new IllegalArgumentException(
            "Invalid parking duration");
    }

    parkingDuration = duration;
}
```

This validation prevents negative parking durations.

Similarly, parking slot numbers must be positive.

Validation protects the object from invalid data and maintains data consistency.

## 5. Wrapper Classes and Autoboxing

Java wrapper classes convert primitive data types into objects.

Examples:

| Primitive | Wrapper Class |
|---|---|
| int | Integer |
| double | Double |
| char | Character |
| boolean | Boolean |
| long | Long |

Autoboxing automatically converts a primitive value into its corresponding wrapper object.

Example:

```java
Integer duration = 120;
```

Here, the primitive integer value is automatically converted into an Integer object.

Unboxing converts a wrapper object into a primitive value.

Example:

```java
int actualDuration = duration;
```

Here, the Integer object is converted into an int value.

## 6. Risk of Comparing Integer Objects Using ==

The == operator compares object references rather than the numerical values of two Integer objects.

Example:

```java
Integer a = Integer.valueOf(1000);
Integer b = Integer.valueOf(1000);

System.out.println(a == b);
System.out.println(a.equals(b));
```

Output:

```text
false
true
```

The == operator returns false because the two objects have different references.

The equals() method returns true because both objects contain the same numerical value.

Therefore, equals() should be used when comparing Integer object values.

## 7. Alternative Concept – Public Fields

In an alternative design, all fields could be declared public and validation could be performed by the calling code.

Example:

```java
public class Vehicle {

    public String registrationNumber;
    public int parkingDuration;
}
```

The calling code would directly assign values:

```java
vehicle.parkingDuration = -100;
```

This allows invalid data to enter the system.

### Problems with Public Fields

1. External classes can directly modify object data.
2. Invalid values may be stored.
3. Validation logic must be repeated in different places.
4. Data consistency becomes difficult to maintain.
5. Future changes become more difficult.

## 8. Time Complexity Analysis

Let n represent the number of vehicles and m represent the number of places where vehicle data is updated.

### Encapsulated Design

- Getter access: O(1).
- Setter access and validation: O(1) for constant-time checks.
- Total validation across m updates: O(m).
- Storage for n vehicle objects: O(n).

### Public Field Design

- Direct field access: O(1).
- Validation in calling code: O(1) per update.
- Total validation across m updates: O(m).
- Storage for n vehicle objects: O(n).

Both designs have similar basic time and space complexity.

However, encapsulation centralises validation in one place. The public-field design requires repeated validation in different parts of the program and increases the risk of errors.

## 9. Conclusion

Encapsulation improves security, reliability and maintainability in the Campus Smart-Parking System.

Packages organise the application into separate modules, while access modifiers control the visibility of classes and members.

Getters and setters validate data before updating object states. Wrapper classes and autoboxing simplify the handling of numerical values.

Therefore, encapsulation is essential for protecting parking records and maintaining accurate system data.
