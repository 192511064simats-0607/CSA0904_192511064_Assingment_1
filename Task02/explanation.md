# TASK 02 – CHOOSE THE DATA REPRESENTATION

## 1. Introduction

Data representation is an important part of designing the Campus Smart-Parking System. Appropriate Java data types and array structures are required to store vehicle details, parking slots, entry times, parking duration and fees.

The selected data structures should support efficient parking slot allocation, vehicle searching and future expansion of the parking facility.

## 2. Selection of Java Data Types

The following Java data types are selected for storing the information required by the system.

### A. Slot Number – int

The `int` data type is used to store the parking slot number because slot numbers are whole numbers and do not require decimal values.

Example:

```java
int slotNumber = 15;
```

### B. Vehicle Registration Number – String

The `String` data type is used to store vehicle registration numbers because they contain letters, numbers and sometimes special characters.

Example:

```java
String regNo = "TN01AB1234";
```

Using an integer would be unsuitable because registration numbers may contain letters and leading zeros.

### C. Entry Time – LocalDateTime

The `LocalDateTime` class from the Java Time API is used to store the vehicle entry date and time accurately.

Example:

```java
import java.time.LocalDateTime;

LocalDateTime entryTime = LocalDateTime.now();
```

It provides convenient date and time operations for calculating parking duration.

### D. Parking Duration – long

The `long` data type is selected to store parking duration in minutes. It supports large values and avoids unnecessary decimal calculations when measuring elapsed whole minutes.

Example:

```java
long durationMinutes = 135;
```

### E. Parking Fee – BigDecimal

The `BigDecimal` class is selected to represent parking fees because financial calculations require accurate decimal values.

Example:

```java
import java.math.BigDecimal;

BigDecimal parkingFee = new BigDecimal("50.00");
```

It avoids common floating-point precision problems that may occur with float and double.

## 3. Representation of Floors and Parking Slots

The parking facility contains multiple floors, and each floor contains a fixed number of parking slots. The number of floors and slots may increase as the facility expands.

A jagged two-dimensional array is selected because different floors may contain different numbers of parking slots.

### A. Jagged Array Representation

Example:

```java
String[][] parkingSlots = {
    new String[20],
    new String[50],
    new String[30]
};
```

Here:

- Floor 0 contains 20 parking slots.
- Floor 1 contains 50 parking slots.
- Floor 2 contains 30 parking slots.

Each array element represents one parking slot.

A null value can indicate an available slot, while a stored registration number indicates an occupied slot.

**Advantages:**

1. Different floors can contain different numbers of slots.
2. Each floor can be accessed using its index.
3. Slot availability can be checked directly.
4. It supports future changes in the number of floors and slots.

### B. Accessing a Parking Slot

A particular parking slot can be accessed using its floor number and slot number.

Example:

```java
int floor = 1;
int slot = 10;

parkingSlots[floor][slot] = "TN01AB1234";
```

This stores the vehicle registration number in the selected parking slot.

## 4. Type Conversion and Casting

Type conversion is required when calculating parking duration and converting minutes into hours for billing.

The system stores parking duration in minutes but may calculate parking fees based on hourly rates.

### A. Incorrect Integer Division

Consider a vehicle parked for 90 minutes.

Incorrect calculation:

```java
long durationMinutes = 90;

long hours = durationMinutes / 60;

System.out.println(hours);
```

Output:

```text
1
```

The result is 1 instead of 1.5 because integer division removes the fractional part.

This may result in an incorrect parking fee.

### B. Correct Conversion Using Double

To preserve the fractional part, one operand must be converted to a decimal value.

```java
long durationMinutes = 90;

double hours = durationMinutes / 60.0;

System.out.println(hours);
```

Output:

```text
1.5
```

This produces the correct decimal representation of the parking duration.

### C. Correct Hour Calculation for Billing

If the billing policy charges for every started hour, the system must round the duration upward.

Example:

```java
long durationMinutes = 90;

long billableHours =
    (long) Math.ceil(durationMinutes / 60.0);

System.out.println(billableHours);
```

Output:

```text
2
```

Here, 90 minutes is billed as 2 hours.

The use of `60.0` avoids integer division, and `Math.ceil()` rounds the result upward.

The rounding policy is an assumption for this example and should be adjusted if the university specifies a different billing rule.

## 5. Reading User Input

The `Scanner` class is selected to read input from the user because the parking system requires interactive data such as vehicle registration number, vehicle category, floor number and slot number.

Example:

```java
import java.util.Scanner;

public class ParkingInput {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter vehicle number: ");
        String regNo = sc.nextLine();

        System.out.print("Enter vehicle type: ");
        String vehicleType = sc.nextLine();

        System.out.print("Enter floor number: ");
        int floor = sc.nextInt();

        System.out.println("Vehicle Number: " + regNo);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Floor Number: " + floor);

        sc.close();
    }
}
```

**Why Scanner is selected:**

1. It supports interactive input.
2. It can read different data types.
3. It is easy to implement and understand.
4. It is suitable for a menu-driven parking application.

Command-line arguments are another option, but Scanner is more convenient when the user needs to enter multiple details interactively.

## 6. Alternative Concept and Complexity Analysis

Instead of using a jagged two-dimensional array, parking slots can also be represented using a one-dimensional array or an ArrayList of arrays.

### A. One-Dimensional Array Representation

A one-dimensional array can store all parking slots in a single sequence.

The index is calculated using:

**Index = (Floor × SlotsPerFloor) + Slot**

Example:

```java
String[] parkingSlots = new String[150];

int floor = 1;
int slotsPerFloor = 50;
int slot = 10;

int index = floor * slotsPerFloor + slot;

parkingSlots[index] = "TN01AB1234";
```

This representation is suitable when every floor has the same number of parking slots.

However, when floors contain different numbers of slots, a single fixed slots-per-floor formula does not work directly. Additional offset information or prefix sums are required.

### B. ArrayList of Arrays Representation

An `ArrayList<String[]>` can store separate arrays for different floors.

Example:

```java
import java.util.ArrayList;

ArrayList<String[]> floors = new ArrayList<>();

floors.add(new String[20]);
floors.add(new String[50]);
floors.add(new String[30]);
```

Here, each floor has its own array, and the number of slots can differ between floors.

This representation is flexible because new floors can be added dynamically.

### C. Comparison of the Three Representations

**Jagged Two-Dimensional Array:**

- Supports different numbers of slots on each floor.
- Provides direct access using floor and slot indexes.
- The total capacity is fixed when the arrays are created.

**One-Dimensional Array:**

- Stores all slots in a single array.
- Uses a mathematical formula to calculate the index.
- Works efficiently when all floors have equal capacity.
- Requires additional mapping when floor capacities differ.

**ArrayList of Arrays:**

- Supports different slot counts on different floors.
- Allows floors to be added or removed dynamically.
- Provides convenient access using the floor index and slot index.
- Requires additional memory management compared with a fixed array.

## 7. Time and Space Complexity Analysis

Let:

- F = Number of floors.
- S = Number of slots per floor.
- N = Total number of parking slots.

### A. Slot Access Complexity

**Jagged Array:**

Accessing a slot using its floor and slot indexes takes O(1) time.

**One-Dimensional Array:**

Calculating the index using the formula and accessing the slot takes O(1) time when the mapping is known.

**ArrayList of Arrays:**

Accessing a floor using its index and a slot using its array index takes O(1) time.

### B. Searching for a Free Slot

If the system checks every slot until it finds an available one, the worst-case search time is O(N) for all three representations.

### C. Space Complexity

All three representations require O(N) space for storing N parking slots.

However, the one-dimensional array may require additional mapping information when floor capacities differ. ArrayList also has some additional storage overhead for its internal management.

## 8. Final Design Decision

For the Campus Smart-Parking System, a jagged two-dimensional array is selected because different floors may contain different numbers of parking slots.

The `int` data type is used for slot numbers, `String` for vehicle registration numbers, `LocalDateTime` for entry time, `long` for parking duration and `BigDecimal` for parking fees.

The Scanner class is used for interactive input. Decimal conversion is performed carefully to avoid incorrect billing caused by integer division.

The selected design provides direct slot access, supports different floor capacities and is suitable for the current parking system. For a facility requiring frequent dynamic changes, an `ArrayList<String[]>` would also be a suitable alternative.

## 9. Conclusion

Appropriate data types and array structures are essential for developing a reliable Campus Smart-Parking System.

The jagged array provides flexibility for floors with different parking capacities, while suitable Java data types ensure accurate storage of vehicle details, time and fees.

Correct type conversion prevents billing errors, and Scanner makes the application interactive. The selected design provides an efficient foundation for implementing and extending the parking system.
