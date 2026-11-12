# TASK 03 – DESIGN THE CONTROL LOGIC

## 1. Introduction

Control logic determines how the Campus Smart-Parking System makes decisions and performs operations using conditional statements and loops.

The two main operations are parking slot allocation and parking fee calculation.

The system searches for a suitable free parking slot when a vehicle enters. When the vehicle leaves, it calculates the parking fee based on the parking duration and applicable fee rules.

Java control statements such as `if-else`, `for`, `break`, `continue` and `return` are used to implement these operations efficiently.

## 2. Parking Slot Allocation Logic

The parking facility contains multiple floors, and each floor may have a different number of parking slots.

The system searches the floors one by one and checks every slot on each floor.

The allocation process follows these steps:

1. Receive the vehicle registration number.
2. Start searching from the first floor.
3. Check each parking slot on the current floor.
4. If a slot is occupied, skip it using `continue`.
5. If a free slot is found, store the vehicle registration number.
6. Return the floor number and slot number immediately.
7. Stop searching because a suitable slot has already been allocated.
8. If all slots are occupied, return `null` to indicate that parking is unavailable.

### Pseudocode – Allocate Parking Slot

```text
ALGORITHM AllocateSlot(vehicleNo)

BEGIN
    FOR each floor f
        FOR each slot s in floor f

            IF slot[f][s] is occupied THEN
                CONTINUE
            END IF

            slot[f][s] ← vehicleNo

            RETURN (f, s)

        END FOR
    END FOR

    RETURN NULL
END
```

### Explanation

The outer loop searches through all floors, while the inner loop searches through the slots on each floor.

The `continue` statement skips occupied slots and moves to the next slot.

The `return` statement immediately terminates the method after allocating a free slot.

If no free slot is available after checking every floor, the method returns `null`.

## 3. Parking Fee Calculation Logic

Parking fees depend on the vehicle category and the duration of parking.

The system calculates the total parking fee using predefined fee slabs.

For example, the following sample fee structure is assumed for explaining the control logic:

- First hour: Rs. 20.
- Each additional started hour: Rs. 10.
- Maximum daily parking fee: Rs. 100.
- Valid monthly pass for students and staff: Free parking.

These are illustrative rates because the assignment scenario does not specify exact parking charges.

### Fee Calculation Rules

1. Check whether the user has a valid monthly pass.
2. If the pass is valid, return zero parking fee.
3. Calculate the billable hours from the parking duration.
4. Apply the first-hour charge.
5. Apply additional-hour charges for the remaining hours.
6. Check the daily maximum fee.
7. Return the final parking fee.

### Pseudocode – Calculate Parking Fee

```text
ALGORITHM CalculateFee(durationMinutes, hasValidPass)

BEGIN

    IF hasValidPass = TRUE THEN
        RETURN 0
    END IF

    billableHours ← CEILING(durationMinutes / 60)

    IF billableHours = 0 THEN
        RETURN 0

    ELSE IF billableHours = 1 THEN
        fee ← 20

    ELSE
        fee ← 20 + (billableHours - 1) × 10
    END IF

    IF fee > 100 THEN
        fee ← 100
    END IF

    RETURN fee

END
```

### Example Calculation

Consider a visitor who parks a car for 150 minutes.

Step 1: Calculate billable hours.

150 / 60 = 2.5 hours.

Since every started hour is charged in full, the billable duration is 3 hours.

Step 2: Calculate the parking fee.

First hour = Rs. 20.

Additional hours = 2 × Rs. 10 = Rs. 20.

Total fee = Rs. 20 + Rs. 20.

Final parking fee = Rs. 40.

The daily maximum is Rs. 100, so the final charge remains Rs. 40.

## 4. Use of Control Statements

The following Java control statements are used in the parking system.

### A. if-else

The `if-else` statement is used to make decisions based on conditions.

Examples:

- Checking whether a parking slot is free.
- Checking whether a monthly pass is valid.
- Selecting the correct parking fee slab.
- Checking whether a parking position is valid.

### B. for Loop

The nested `for` loops are used to search through multiple floors and parking slots.

The outer loop represents floors, and the inner loop represents slots.

### C. continue

The `continue` statement skips the current iteration when a parking slot is already occupied.

This avoids unnecessary processing for occupied slots.

### D. break

The `break` statement can terminate a loop when a condition is satisfied.

For example, it can stop searching when the first suitable free slot is found if the result is stored and returned after the loop.

In the selected implementation, `return` is used directly because it terminates the entire allocation method.

### E. return

The `return` statement immediately exits a method and sends a result to the calling method.

It is used to:

- Return the allocated floor and slot.
- Return `null` when no free slot exists.
- Return `true` when a slot is successfully released.
- Return `false` when a release operation is invalid.
- Return the calculated parking fee.

## 5. Handling Parking Unavailability

When all parking slots are occupied, the allocation method must not assign an already occupied slot to another vehicle.

The system checks every floor and slot before deciding that parking is full.

If no suitable free slot is found:

1. The allocation method returns `null`.
2. No existing vehicle allocation is changed.
3. The system displays a message indicating that parking is unavailable.
4. The vehicle must wait or use another parking facility.

This prevents duplicate slot allocation and maintains the correctness of the parking records.

## 6. Java Implementation – ParkingFacility.java

The following Java program implements parking slot allocation and release using a jagged two-dimensional array.

```java
public class ParkingFacility {

    // Stores vehicle numbers; null means the slot is free
    private final String[][] slots;

    // Constructor
    public ParkingFacility(int[] slotsPerFloor) {

        slots = new String[slotsPerFloor.length][];

        for (int f = 0; f < slotsPerFloor.length; f++) {

            slots[f] = new String[slotsPerFloor[f]];

        }
    }

    // Allocate the first available parking slot
    public int[] allocate(String vehicleNo) {

        for (int f = 0; f < slots.length; f++) {

            for (int s = 0; s < slots[f].length; s++) {

                if (slots[f][s] != null) {
                    continue;
                }

                slots[f][s] = vehicleNo;

                return new int[]{f, s};
            }
        }

        return null;
    }

    // Release an occupied parking slot
    public boolean release(int floor, int slot) {

        if (floor < 0 || floor >= slots.length) {
            return false;
        }

        if (slot < 0 || slot >= slots[floor].length) {
            return false;
        }

        if (slots[floor][slot] == null) {
            return false;
        }

        slots[floor][slot] = null;

        return true;
    }

    public static void main(String[] args) {

        // Floor 0 has 2 slots and floor 1 has 1 slot
        ParkingFacility p =
                new ParkingFacility(new int[]{2, 1});

        System.out.println(
                java.util.Arrays.toString(
                        p.allocate("TN01AB1111")));

        System.out.println(
                java.util.Arrays.toString(
                        p.allocate("TN02CD2222")));

        System.out.println(
                java.util.Arrays.toString(
                        p.allocate("TN03EF3333")));

        System.out.println(
                java.util.Arrays.toString(
                        p.allocate("TN04GH4444")));

        System.out.println(
                p.release(0, 1) + " " +
                p.release(0, 1) + " " +
                p.release(5, 0));

        System.out.println(
                java.util.Arrays.toString(
                        p.allocate("TN04GH4444")));
    }
}
```

## 7. Program Execution and Expected Output

The program creates a parking facility with two floors.

- Floor 0 contains two parking slots.
- Floor 1 contains one parking slot.
- Three vehicles can be parked initially.
- The fourth vehicle cannot be allocated because all slots are occupied.
- After releasing one slot, the fourth vehicle can be allocated.

### Expected Output

```text
[0, 0]
[0, 1]
[1, 0]
null
true false false
[0, 1]
```

### Output Explanation

1. The first vehicle is allocated to floor 0, slot 0.
2. The second vehicle is allocated to floor 0, slot 1.
3. The third vehicle is allocated to floor 1, slot 0.
4. The fourth vehicle receives `null` because all slots are occupied.
5. The first release operation returns `true` because the slot is occupied.
6. The second release operation returns `false` because the slot is already free.
7. The third release operation returns `false` because floor 5 does not exist.
8. The fourth vehicle is successfully allocated to the newly released slot at floor 0, slot 1.

## 8. Alternative Concept and Complexity Analysis

The selected approach searches every floor and slot sequentially to find an available parking slot.

An alternative approach is to maintain a count of free slots on each floor and a stack containing the free slot numbers.

### A. Selected Approach – Sequential Search

In the sequential search approach:

- The system checks every floor.
- It checks each slot until a free slot is found.
- The first available slot is allocated.
- If all slots are occupied, the method returns `null`.

**Time Complexity:**

- Best case: O(1), when the first slot is free.
- Worst case: O(N), when all slots must be checked.

Here, N represents the total number of parking slots.

**Space Complexity:**

The parking array stores N slot references.

Therefore, the space complexity is O(N).

### B. Alternative Approach – Free-Slot Stack

Instead of searching every slot, the system can maintain a separate stack of free slots for each floor.

Each stack stores the available slot numbers of its corresponding floor.

**Allocation Logic:**

1. Check the free-slot count of each floor.
2. Skip floors that have no free slots.
3. Select the first floor containing a free slot.
4. Remove the top slot number from that floor's stack.
5. Decrease the free-slot count.
6. Return the allocated floor and slot.

**Release Logic:**

1. Validate the floor and slot numbers.
2. Check whether the slot is already free.
3. Push the released slot number into the corresponding stack.
4. Increase the free-slot count.
5. Return success.

### C. Complexity Comparison

**Sequential Search Approach:**

- Allocation: O(N) worst case.
- Release: O(1), when floor and slot indexes are known.
- Space: O(N).

**Free-Slot Stack Approach:**

- Allocation: O(F) when checking floors, plus O(1) to remove a slot.
- Release: O(1) for a valid release.
- Space: O(N + F), including free-slot stacks and per-floor counts.

Here, F represents the total number of floors and N represents the total number of parking slots.

If a separate structure of non-empty floors is maintained, allocation can also be optimized further.

### D. Comparison and Justification

The sequential search approach is simple and suitable for a small parking facility. It is easy to understand and implement.

The free-slot stack approach reduces the time required to locate an available slot when the facility becomes large. However, it requires additional data structures and careful updates during allocation and release.

Therefore, sequential searching is selected for the initial implementation because of its simplicity, while the free-slot stack approach is a suitable optimization for a larger parking facility.

## 9. Conclusion

Control statements and looping structures are essential for implementing the Campus Smart-Parking System.

Nested loops are used to search parking floors and slots, while `if-else` statements handle parking conditions and fee calculations. The `continue`, `break` and `return` statements help avoid unnecessary processing and terminate operations correctly.

The Java implementation successfully allocates available slots, handles full parking conditions and releases occupied slots safely.

The sequential search method is suitable for the initial system, while a free-slot stack can improve allocation performance when the parking facility grows.
