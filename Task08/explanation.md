# TASK 08 – APPLY POLYMORPHISM

## 1. Introduction

Polymorphism is an important concept in Object-Oriented Programming. It allows one method name or reference to represent different behaviours depending on the arguments or actual object.

Java supports two types of polymorphism:

1. Compile-time polymorphism (Method Overloading).
2. Runtime polymorphism (Method Overriding and Dynamic Method Dispatch).

In the Campus Smart-Parking System, polymorphism allows different vehicle categories to calculate their parking fees using a common method.

## 2. Compile-Time Polymorphism

Compile-time polymorphism is achieved through method overloading.

Method overloading means defining multiple methods with the same name but different parameter lists.

Example:

```java
double calculateFee(int minutes)
double calculateFee(LocalTime in, LocalTime out)
```

The first method accepts parking duration in minutes, while the second method accepts entry and exit times.

The compiler selects the correct method based on the arguments supplied during the method call.

Example:

```java
vehicle.calculateFee(130);

vehicle.calculateFee(
    LocalTime.of(9, 0),
    LocalTime.of(11, 30)
);
```

Both methods have the same name but accept different parameters.

## 3. Runtime Polymorphism

Runtime polymorphism is achieved through method overriding.

A superclass reference can refer to an object of its subclass.

Example:

```java
Vehicle v = new Car("TN02B2");
```

Here:
- Declared reference type: Vehicle
- Actual object type: Car

When the calculateFee() method is called, Java executes the overridden method belonging to the actual object at runtime.

This process is called dynamic method dispatch.

## 4. Upcasting Using Vehicle Array

Upcasting means assigning a subclass object to a superclass reference.

A single Vehicle array can store different types of vehicle objects.

Example:

```java
Vehicle[] parked = {
    new TwoWheeler("TN01A1"),
    new Car("TN02B2"),
    new ElectricCar("TN03C3")
};
```

The array contains:
- TwoWheeler object
- Car object
- ElectricCar object

All three objects can be accessed using Vehicle references.

This makes the parking system easier to manage because different vehicle categories can be processed through one common array.

## 5. Dynamic Method Dispatch

Dynamic method dispatch determines which overridden method should execute at runtime.

For example:

```java
Vehicle v = new Car("TN02B2");

double fee = v.calculateFee(130);
```

Execution trace:

| Property | Value |
|---|---|
| Declared type | Vehicle |
| Actual object type | Car |
| Method called | calculateFee(130) |
| Executed rate method | Car.ratePerHour() |
| Hourly rate | Rs. 30 |
| Billable hours | 3 |
| Parking fee | Rs. 90 |

Although the reference type is Vehicle, Java executes the Car-specific rate method because the actual object is a Car.

## 6. Advantages of Polymorphism

Polymorphism avoids writing long if-else statements to identify vehicle categories.

Without polymorphism, the program must check whether the vehicle is a two-wheeler, car or electric car before calculating the fee.

With polymorphism, each subclass provides its own implementation, and Java automatically selects the correct method.

Advantages:

1. Reduces repeated code.
2. Improves code reusability.
3. Supports easy addition of new vehicle categories.
4. Improves maintainability.
5. Supports flexible object-oriented design.

## 7. Explanation of equals() Output

The supplied program compares:

```java
new Car("TN02B2").equals(
    new ElectricCar("TN02B2")
);
```

The equals() method compares the registration numbers.

Both objects contain the same registration number, TN02B2. Therefore, the output is:

```text
true
```

This is the behaviour of the supplied implementation.

However, in a real parking system, this may not be appropriate if vehicle category is also part of vehicle identity. A Car and ElectricCar with the same registration number should not represent two separate registered vehicles.

The system should also enforce unique registration numbers when vehicles enter.

## 8. Alternative Concept – instanceof and Casting

Instead of dynamic method dispatch, the program can use instanceof checks to identify each vehicle category.

Example:

```java
if (v instanceof TwoWheeler) {
    fee = minutes * 10.0 / 60;
}
else if (v instanceof Car) {
    fee = minutes * 30.0 / 60;
}
else if (v instanceof ElectricCar) {
    fee = minutes * 30.0 / 60;
}
```

The program checks the object type and performs the corresponding calculation.

### Comparison

| Feature | Dynamic Method Dispatch | instanceof and Casting |
|---|---|---|
| Method selection | Automatic at runtime | Manual type checking |
| Code structure | Uses overridden methods | Uses conditional statements |
| Adding vehicle types | Add a new subclass implementation | Modify existing conditions |
| Maintenance | Easier | More difficult as types increase |
| Flexibility | High | Limited |
| Time per call | O(1) dispatch | Up to O(k) type checks |

Here, k represents the number of vehicle categories.

## 9. Time and Space Complexity Analysis

Let N represent the number of parked vehicles and k represent the number of vehicle categories.

### Dynamic Method Dispatch

- Method dispatch: O(1) per call.
- Fee calculation: O(1), assuming a constant-time calculation.
- Processing N vehicles: O(N).
- Storage: O(N) for the array references, excluding the vehicle objects themselves.

### instanceof and Casting

- Type checking: O(k) in the worst case.
- Fee calculation after identifying the type: O(1).
- Processing N vehicles: O(N × k) in the worst case.
- Storage: O(N) for the array references.

Both approaches store the same number of vehicle objects. However, dynamic method dispatch provides a cleaner and more extensible design.

## 10. Conclusion

Polymorphism improves the flexibility of the Campus Smart-Parking System.

Method overloading provides compile-time polymorphism, while method overriding provides runtime polymorphism.

Upcasting allows different vehicle objects to be stored in a single Vehicle array. Dynamic method dispatch automatically executes the correct vehicle-specific method.

Therefore, polymorphism is a suitable approach for managing different vehicle categories without writing long conditional statements.
