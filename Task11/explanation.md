# TASK 11 – IMPLEMENT, TEST AND DEFEND THE DESIGN

## 1. Introduction

The Campus Smart-Parking System is implemented as a complete Java application using Object-Oriented Programming concepts.

The system manages vehicle entry, parking slot allocation, ticket generation, parking fee calculation, charging fees and vehicle exit.

The implementation supports three vehicle categories:
- Two-wheelers
- Cars
- Electric vehicles

## 2. System Implementation

The system contains the following classes and interface:

| Class / Interface | Responsibility |
|---|---|
| Vehicle | Abstract parent class for all vehicles |
| TwoWheeler | Calculates two-wheeler parking fees |
| Car | Calculates car parking fees |
| ElectricCar | Supports parking and charging fees |
| Chargeable | Defines charging capability |
| ParkingSlot | Maintains parking slot availability |
| ParkingTicket | Stores ticket and entry details |
| ParkingSystem | Manages parking operations |
| ParkingSystemDemo | Executes the application |

## 3. Testing and Validation

The system is tested using normal, boundary and invalid inputs.

### Test Cases

| Test | Input / Condition | Expected Result |
|---|---|---|
| TC01 | Two-wheeler enters | Parking successful |
| TC02 | Car enters | Parking successful |
| TC03 | Electric vehicle enters | Parking successful |
| TC04 | Last free slot allocated | Successful allocation |
| TC05 | Zero-minute parking | Zero parking fee |
| TC06 | Exactly 60 minutes | One-hour fee |
| TC07 | Unknown vehicle type | Rejected |
| TC08 | Duplicate registration | Rejected |
| TC09 | Invalid floor/slot | Rejected |
| TC10 | Vehicle exits | Slot released |
| TC11 | Monthly pass holder | Parking fee is zero |

### Fee Calculation

The hourly rates are:

- Two-wheeler: Rs 10
- Car: Rs 30
- Electric car: Rs 30
- EV charging: Rs 12 per kWh

Every started hour is charged as one complete hour. Zero-minute parking is free.

### Daily Collection Calculation

The test scenario uses four vehicles.

| Vehicle | Duration | Parking Fee | Charging Fee |
|---|---:|---:|---:|
| Two-wheeler | 60 minutes | Rs 10 | Rs 0 |
| Car | 60 minutes | Rs 30 | Rs 0 |
| Electric car | 60 minutes | Rs 30 | Rs 24 |
| Two-wheeler | 0 minutes | Rs 0 | Rs 0 |
| **Total** | | **Rs 70** | **Rs 24** |

Total daily collection:

Rs 70 + Rs 24 = **Rs 94**

The calculated total is compared with the program output.

## 4. Object-Oriented Concepts

### Encapsulation

Private fields are used to protect vehicle, ticket and parking slot data. Public methods provide controlled access.

### Inheritance

TwoWheeler and Car inherit common properties and behaviour from Vehicle. ElectricCar inherits from Car.

### Polymorphism

The calculateFee() method is overridden in vehicle subclasses. Java selects the appropriate implementation at runtime.

### Abstraction

Vehicle is an abstract class that defines the common structure of vehicles. Chargeable is an interface that defines charging functionality.

### Limitation

The current implementation uses a fixed-size array for active parking tickets. Its capacity cannot grow dynamically during execution. A dynamic collection such as ArrayList or HashMap would be more suitable for a larger parking facility.

## 5. Alternative Concept – Array, ArrayList and HashMap

The current implementation stores active parking tickets in a fixed-size array.

### Array

The program searches each array position to find the registration number during vehicle exit.

- Search time: O(n)
- Space complexity: O(n)
- Capacity is fixed.

### ArrayList

An ArrayList can store ParkingTicket objects dynamically.

Example:

```java
ArrayList<ParkingTicket> tickets =
    new ArrayList<>();
```

The exit operation searches the list using a loop.

- Search time: O(n)
- Space complexity: O(n)
- Dynamic capacity.

### HashMap

A HashMap stores tickets using registration numbers as keys.

Example:

```java
HashMap<String, ParkingTicket> tickets =
    new HashMap<>();

tickets.get("TN01A1");
```

The registration number directly identifies the ticket.

- Average search time: O(1)
- Worst-case search time: O(n)
- Space complexity: O(n)

### Comparison

| Feature | Array | ArrayList | HashMap |
|---|---|---|---|
| Search | O(n) | O(n) | O(1) average |
| Storage | O(n) | O(n) | O(n) |
| Capacity | Fixed | Dynamic | Dynamic |
| Key-based lookup | No | No | Yes |
| Best use | Fixed capacity | Flexible list | Fast ticket lookup |

HashMap is more suitable when fast vehicle exit processing is important. However, it requires additional memory for its internal structure.

## 6. Design Evaluation

The implementation demonstrates the major Object-Oriented Programming concepts and validates normal, boundary and invalid parking operations.

The fixed-size ticket array is simple and suitable for a small parking facility. For a larger system, HashMap would improve average ticket lookup performance.

The system can be extended with additional vehicle types and pricing rules through inheritance and interfaces.

## 7. Conclusion

The Campus Smart-Parking System successfully demonstrates a complete object-oriented Java implementation.

Testing confirms vehicle entry, ticket generation, parking fee calculation, EV charging, vehicle exit and parking slot release.

The comparison of arrays, ArrayList and HashMap identifies the performance and storage trade-offs involved in selecting an appropriate data structure.
