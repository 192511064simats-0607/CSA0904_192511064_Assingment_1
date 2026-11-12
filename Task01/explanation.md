# TASK 01 – UNDERSTAND THE PROBLEM AND IDENTIFY THE OBJECTS

## 1. Problem Analysis

The Campus Smart-Parking System is designed to manage vehicles entering and leaving a multi-level university parking facility. The facility contains multiple floors, and each floor has a fixed number of parking slots. It supports three main vehicle categories: two-wheelers, cars and electric vehicles (EVs).

When a vehicle enters the parking facility, the system searches for a suitable available parking slot and allocates it to the vehicle. A unique parking ticket is generated, containing the vehicle registration number and entry time. When the vehicle leaves, the system calculates the parking fee based on the vehicle category and parking duration, releases the occupied slot and updates the availability.

Students and staff members with valid monthly passes are eligible for free parking, whereas visitors must pay the applicable parking charges. Electric vehicles may also use charging slots, for which separate charging fees are applied.

The main objective is to develop an object-oriented Java application that manages parking operations efficiently and maintains accurate parking records.

## 2. Inputs and Outputs

### Inputs

The system receives the following inputs:

1. Vehicle registration number.
2. Vehicle type (Two-wheeler, Car or Electric Vehicle).
3. User category (Student, Staff or Visitor).
4. Monthly pass validity details.
5. Vehicle entry time and exit time.
6. Number of floors and parking slots available on each floor.
7. Electricity consumption for electric vehicle charging.

### Outputs

The system produces the following outputs:

1. Allocated floor number and parking slot number.
2. Unique parking ticket number.
3. Vehicle registration number and entry time.
4. Total parking duration.
5. Calculated parking fee.
6. Separate EV charging fee, if applicable.
7. Updated parking slot availability.
8. Messages indicating successful parking allocation, vehicle exit or parking unavailability.

## 3. Identification of Candidate Classes

In object-oriented programming, nouns in a problem statement are identified as candidate classes, while verbs represent the operations or methods performed by those classes.

| Candidate Class | Responsibility |
|---|---|
| Vehicle | Stores common vehicle information such as registration number and vehicle details. |
| TwoWheeler | Represents a two-wheeler and defines its parking rate. |
| Car | Represents a car and defines its parking rate. |
| ElectricVehicle | Represents an electric vehicle and supports charging operations. |
| ParkingSlot | Maintains slot number, availability and occupying vehicle details. |
| ParkingFloor | Manages all parking slots available on a particular floor. |
| ParkingFacility | Manages multiple floors and handles vehicle allocation and release. |
| ParkingTicket | Stores ticket number, vehicle details, entry time and exit time. |
| User | Represents students, staff members and visitors using the facility. |
| MonthlyPass | Stores pass details and validates monthly pass availability and validity. |

These classes are required because each represents a separate entity or responsibility in the parking system. Separating responsibilities improves modularity, reusability and maintainability.

## 4. Nouns and Verbs Identification

### Nouns – Candidate Classes

- Vehicle
- Two-wheeler
- Car
- Electric vehicle
- Parking slot
- Parking floor
- Parking facility
- Parking ticket
- User
- Monthly pass

### Verbs – Corresponding Methods

| Verb | Method Name |
|---|---|
| Enter vehicle | enterVehicle() |
| Allocate slot | allocateSlot() |
| Release slot | releaseSlot() |
| Generate ticket | generateTicket() |
| Calculate fee | calculateFee() |
| Record entry time | recordEntryTime() |
| Record exit time | recordExitTime() |
| Validate pass | validatePass() |
| Check availability | checkAvailability() |
| Calculate charging fee | calculateChargingFee() |

These methods represent the main operations performed by the system to manage parking activities.

## 5. Responsibilities of Each Class

**1. Vehicle Class**

The Vehicle class stores common vehicle information such as registration number and vehicle category. It acts as a superclass for different vehicle types and provides common vehicle-related operations.

**2. TwoWheeler Class**

This class represents two-wheelers and provides the parking rate applicable to two-wheelers.

**3. Car Class**

This class represents cars and defines the parking rate applicable to cars.

**4. ElectricVehicle Class**

This class represents electric vehicles and supports additional charging-related operations. It also calculates separate charging fees.

**5. ParkingSlot Class**

This class maintains the slot number, occupancy status and vehicle currently occupying the slot. It ensures that only one vehicle occupies a slot at a time.

**6. ParkingFloor Class**

This class manages the parking slots on a particular floor and checks whether suitable slots are available.

**7. ParkingFacility Class**

This class manages all floors in the parking facility. It handles vehicle allocation, slot release and overall parking availability.

**8. ParkingTicket Class**

This class generates and maintains a unique ticket number, vehicle registration number, entry time and exit time. It also supports parking fee calculation.

**9. User Class**

This class maintains user information and identifies whether the user is a student, staff member or visitor.

**10. MonthlyPass Class**

This class stores monthly pass details and checks whether a student or staff member has a valid pass for free parking.

## 6. Business Rules

The following business rules must be maintained to ensure correct parking operations:

1. A parking slot can accommodate only one vehicle at a time.
2. A vehicle must be assigned only to a suitable available parking slot.
3. Every parking ticket must have a unique ticket number.
4. A ticket number must never be reused, even after a vehicle leaves.
5. A vehicle must not occupy multiple parking slots simultaneously.
6. A vehicle must have a valid registration number before entering the facility.
7. A parking slot can be released only if it is occupied and the given floor and slot numbers are valid.
8. Students and staff members with valid monthly passes are eligible for free parking.
9. Visitors must pay the applicable parking fee.
10. Electric vehicle charging charges must be calculated separately.
11. The system must not allocate a slot when no suitable free slot is available.
12. Parking duration and applicable fees must be calculated correctly when a vehicle exits.

## 7. Assumptions

The following assumptions are considered while designing the system:

1. Every vehicle has a unique registration number.
2. The parking facility has a predefined number of floors and parking slots.
3. Different floors may contain different numbers of parking slots.
4. Parking slots are allocated automatically based on availability.
5. The first suitable available slot is allocated to the entering vehicle.
6. Entry and exit times are recorded using the system clock.
7. Parking rates are predefined according to vehicle categories.
8. Monthly passes are valid only within their specified validity period.
9. Electric vehicle charging fees are calculated separately based on electricity consumption.
10. The system maintains active parking records until the corresponding vehicle exits.

The exact parking rates, charging rates and fee-rounding policy are not specified in the assignment scenario. Therefore, they are treated as configurable values.

## 8. Alternative Concept and Complexity Analysis

### A. Object-Based Approach

In the object-oriented approach, every vehicle is represented as an object of the Vehicle class or its subclasses.

Each object maintains its own information, such as registration number, vehicle type and entry time.

Example:

```java
Vehicle[] vehicles = new Vehicle[100];
```

In this approach, related data and behaviour are grouped into a single object. It improves encapsulation, code organization, reusability and extensibility.

### B. Parallel Arrays Approach

In the alternative approach, vehicle information is stored in separate arrays.

```java
String[] regNo = new String[100];
String[] type = new String[100];
String[] entryTime = new String[100];
```

All arrays use the same index to represent one vehicle. For example, index 0 in all three arrays represents the details of the first vehicle.

### C. Comparison of Both Approaches

| Feature | Object-Based Approach | Parallel Arrays Approach |
|---|---|---|
| Data storage | Each vehicle is stored as an object. | Vehicle details are stored in separate arrays. |
| Vehicle search | Linear search using registration number. | Linear search through regNo[]. |
| Search complexity | O(n) | O(n) |
| Adding a new attribute | Add a field to the Vehicle class. | Create another array and maintain matching indexes. |
| Data organization | Related data and methods are grouped. | Data is separated into multiple arrays. |
| Maintainability | Easier to maintain and extend. | More difficult as attributes increase. |
| Space complexity | O(n) overall for n vehicles. | O(n) overall for n vehicles. |

### D. Time Complexity Analysis

In both approaches, finding a vehicle by registration number requires checking the registration numbers one by one.

For n vehicles:

- Best case: O(1), when the vehicle is found at the first position.
- Average case: O(n), when several entries need to be checked.
- Worst case: O(n), when the vehicle is at the last position or is not present.

Therefore, both approaches have O(n) time complexity for linear searching.

### E. Space Complexity Analysis

In the object-oriented approach, memory is required for n vehicle objects and their associated references.

In the parallel arrays approach, memory is required for separate arrays containing registration numbers, vehicle types and entry times.

Both approaches require O(n) overall space for n vehicles, excluding implementation-dependent object and array overhead.

### F. Adding a New Vehicle Attribute

In the object-oriented approach, adding a new attribute such as fuel type requires adding a field to the Vehicle class and providing the necessary methods.

In the parallel arrays approach, a new array must be created for the additional attribute. The same index must be maintained across all arrays to ensure that the information belongs to the correct vehicle.

Therefore, the object-oriented approach is more suitable because it provides better encapsulation, maintainability and extensibility.

## 9. Conclusion

The analysis identifies the important objects, responsibilities, inputs, outputs and business rules required for the Campus Smart-Parking System.

The object-oriented approach is selected because it groups related data and operations into classes and supports important OOP principles such as encapsulation, inheritance and reusability.

Although both object-based storage and parallel arrays have O(n) search time and O(n) overall space complexity, the object-oriented approach is easier to maintain when new vehicle categories, attributes and parking rules are introduced.

Hence, an object-oriented design using Java is the most appropriate approach for developing a scalable and maintainable Campus Smart-Parking System.
