# TASK 12 – RE-EVALUATE AND ADAPT THE DESIGN

## A. Impact Analysis (2 Marks)

### 1. Changes in the Requirements

The original Campus Smart-Parking System supported two-wheelers, cars and electric vehicles.

The new requirements introduce:

1. E-bikes with a fixed parking fee.
2. Delivery vans charged at double the car rate between 9 AM and 5 PM.
3. One-day parking passes for visitors.
4. Expansion of the parking facility from three floors to five floors, with different slot capacities.

### 2. Affected Classes and Methods

| Class | Changes |
|---|---|
| Vehicle | Retained as the abstract parent class |
| EBike | New subclass for e-bike vehicles |
| DeliveryVan | New subclass for delivery vans |
| OneDayPass | New class to manage one-day passes |
| PricingRule | New interface for pricing abstraction |
| StandardPricingRule | New centralised pricing implementation |
| ParkingTicket | Modified to store one-day pass details |
| ParkingSystem | Modified to support new vehicle types and five floors |
| ParkingSystemDemo | Modified to test new requirements |

### 3. Impact of Different Floor Capacities

A normal two-dimensional array assumes equal numbers of slots in every row when created using a rectangular structure.

However, the new parking facility has different slot capacities on each floor.

A jagged array is suitable because each floor can have a different number of slots.

Example:

```java
ParkingSlot[][] slots = new ParkingSlot[5][];

int[] capacities = {3, 4, 2, 5, 3};

for (int i = 0; i < 5; i++) {
    slots[i] = new ParkingSlot[capacities[i]];
}
```

This allows independent slot allocation on every floor.

### 4. Classes Requiring Modification

ParkingSystem, ParkingTicket and ParkingSystemDemo require modifications to support the new requirements.

The existing vehicle classes and parking slot class can remain unchanged.

---

## B. Evaluation of the Original Design (2 Marks)

### 1. Adding New Vehicle Categories

The original inheritance structure allows new vehicle categories to be represented through subclasses of Vehicle.

For example, EBike and DeliveryVan extend Vehicle.

However, the original vehicle creation switch statement must also be updated to recognise the new categories.

### 2. Pricing Rule Management

In the original implementation, vehicle-specific hourly rates were defined within vehicle classes.

The new implementation introduces a PricingRule interface and StandardPricingRule class.

This centralises pricing decisions and separates pricing logic from vehicle information.

### 3. Reusability of Existing Classes

The existing classes Vehicle, TwoWheeler, Car, ElectricCar, Chargeable and ParkingSlot can be retained without changes.

The new design extends the existing inheritance structure instead of replacing the complete system.

Therefore, the original design is reusable but requires selected modifications to support the changed requirements.

---

## C. Adaptation or Redesign (2 Marks)

### 1. Final Design Decision

The selected approach is MODIFY.

A complete redesign is unnecessary because the original object-oriented structure is suitable for the expanded parking system.

### 2. Revised Class Structure

The revised design contains:

- Vehicle – Abstract parent class.
- TwoWheeler – Two-wheeler category.
- Car – Car category.
- ElectricCar – Electric vehicle category.
- EBike – New e-bike category.
- DeliveryVan – New delivery van category.
- Chargeable – Charging interface.
- PricingRule – Pricing abstraction.
- StandardPricingRule – Centralised pricing implementation.
- OneDayPass – Visitor pass management.
- ParkingSlot – Parking slot management.
- ParkingTicket – Ticket information.
- ParkingSystem – Parking operations.

### 3. New Pricing Rules

| Vehicle / Pass | Pricing Rule |
|---|---|
| E-bike | Flat fee of Rs 20 |
| Delivery van | Double car rate from 9 AM to 5 PM |
| Visitor with valid one-day pass | Parking fee waived |
| Electric vehicle | Normal parking fee plus charging fee |

The e-bike fee is an implementation assumption because the assignment does not specify its amount. The one-day pass purchase price is also unspecified and is not included in parking collection.

### 4. Five-Floor Implementation

The facility uses the following capacities:

| Floor | Number of Slots |
|---|---:|
| Floor 1 | 3 |
| Floor 2 | 4 |
| Floor 3 | 2 |
| Floor 4 | 5 |
| Floor 5 | 3 |
| Total | 17 |

A jagged array supports these unequal floor capacities.

---

## D. Evidence-Based Defence (2 Marks)

### 1. Number of Modified Classes

Three existing classes are modified:

1. ParkingSystem
2. ParkingTicket
3. ParkingSystemDemo

New classes and an interface are introduced for the additional vehicle types, pass management and pricing abstraction.

### 2. Ease of Adding Future Vehicle Types

The abstract Vehicle class provides a common structure for all vehicle categories.

A new vehicle type can be created as a subclass without modifying the existing vehicle subclasses.

However, its registration in the vehicle factory logic and any new pricing behaviour must also be added to the appropriate centralised components.

### 3. Test Results

The following tests are performed:

| Test | Expected Result |
|---|---|
| E-bike parking | Flat fee of Rs 20 |
| Delivery van at 10 AM | Double car rate |
| Delivery van outside 9 AM–5 PM | Normal car rate |
| Valid one-day pass | Parking fee waived |
| EV parking | Normal parking fee |
| EV charging | Separate charging fee |
| Five-floor allocation | Different capacities supported |
| Invalid floor | Operation rejected |
| Duplicate registration | Operation rejected |

### 4. Trade-offs

**Advantages:**

- Supports new vehicle categories.
- Centralises pricing decisions.
- Supports different slot capacities on each floor.
- Reuses existing vehicle classes.
- Separates visitor pass handling from vehicle information.

**Limitations:**

- The vehicle creation switch must be updated when new categories are introduced.
- Pricing rules must be extended for additional special pricing conditions.
- The current implementation uses a simple date-based one-day pass validation.

### 5. Final Design Decision

The modified design is more suitable than the original implementation because it supports the expanded parking facility and new pricing requirements.

It retains the existing inheritance structure while introducing pricing abstraction, visitor pass management and a jagged array.

Therefore, modifying the original design is the most appropriate solution because it improves extensibility without unnecessarily rewriting the complete application.

## Conclusion

The Campus Smart-Parking System has been adapted to support e-bikes, delivery vans, one-day visitor passes and a five-floor parking facility.

The revised design demonstrates inheritance, abstraction, interfaces, encapsulation and flexible data representation.

The final design is reusable, maintainable and suitable for future expansion.
