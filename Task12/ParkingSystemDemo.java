
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Pricing interface
interface PricingRule {
    double calculate(Vehicle vehicle, LocalDateTime entry,
            int minutes, boolean monthlyPass,
            OneDayPass oneDayPass);
}

// Charging interface
interface Chargeable {
    double chargingFee(int units);
}

// Abstract Vehicle class
abstract class Vehicle {
    private String regNo;

    Vehicle(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid registration");
        }
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public abstract double getHourlyRate();
}

// Two-wheeler
class TwoWheeler extends Vehicle {
    TwoWheeler(String regNo) {
        super(regNo);
    }

    public double getHourlyRate() {
        return 10;
    }
}

// Car
class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }

    public double getHourlyRate() {
        return 30;
    }
}

// Electric car
class ElectricCar extends Car implements Chargeable {
    ElectricCar(String regNo) {
        super(regNo);
    }

    public double chargingFee(int units) {
        if (units < 0) {
            throw new IllegalArgumentException("Invalid units");
        }
        return units * 12;
    }
}

// New vehicle: E-bike
class EBike extends Vehicle {
    EBike(String regNo) {
        super(regNo);
    }

    public double getHourlyRate() {
        return 0;
    }
}

// New vehicle: Delivery van
class DeliveryVan extends Vehicle {
    DeliveryVan(String regNo) {
        super(regNo);
    }

    public double getHourlyRate() {
        return 30;
    }
}

// One-day visitor pass
class OneDayPass {
    private LocalDate validDate;

    OneDayPass(LocalDate validDate) {
        this.validDate = validDate;
    }

    public boolean isValid(LocalDate date) {
        return validDate.equals(date);
    }
}

// Pricing implementation
class StandardPricingRule implements PricingRule {

    private static final double EBIKE_FEE = 20;

    public double calculate(
            Vehicle vehicle, LocalDateTime entry,
            int minutes, boolean monthlyPass,
            OneDayPass oneDayPass) {

        if (minutes < 0) {
            throw new IllegalArgumentException("Invalid duration");
        }

        if (monthlyPass ||
                (oneDayPass != null &&
                        oneDayPass.isValid(entry.toLocalDate()))) {
            return 0;
        }

        if (vehicle instanceof EBike) {
            return EBIKE_FEE;
        }

        int hours = (minutes + 59) / 60;
        double rate = vehicle.getHourlyRate();

        if (vehicle instanceof DeliveryVan) {
            int hour = entry.getHour();

            if (hour >= 9 && hour < 17) {
                rate *= 2;
            }
        }

        return hours * rate;
    }
}

// Parking slot
class ParkingSlot {
    private int floor;
    private int number;
    private Vehicle vehicle;

    ParkingSlot(int floor, int number) {
        this.floor = floor;
        this.number = number;
    }

    public boolean isFree() {
        return vehicle == null;
    }

    public void occupy(Vehicle vehicle) {
        if (!isFree()) {
            throw new IllegalStateException("Slot occupied");
        }
        this.vehicle = vehicle;
    }

    public void release() {
        vehicle = null;
    }

    public int getFloor() {
        return floor;
    }

    public int getNumber() {
        return number;
    }
}

// Parking ticket
class ParkingTicket {
    private String ticketNumber;
    private Vehicle vehicle;
    private ParkingSlot slot;
    private LocalDateTime entryTime;
    private boolean monthlyPass;
    private OneDayPass oneDayPass;

    ParkingTicket(String ticketNumber, Vehicle vehicle,
            ParkingSlot slot, LocalDateTime entryTime,
            boolean monthlyPass, OneDayPass oneDayPass) {

        this.ticketNumber = ticketNumber;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
        this.monthlyPass = monthlyPass;
        this.oneDayPass = oneDayPass;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public boolean hasMonthlyPass() {
        return monthlyPass;
    }

    public OneDayPass getOneDayPass() {
        return oneDayPass;
    }
}

// Main parking system
class ParkingSystem {

    private ParkingSlot[][] slots;

    private Map<String, ParkingTicket> activeTickets = new HashMap<>();

    private PricingRule pricingRule = new StandardPricingRule();

    private int ticketCounter = 1001;
    private double dailyCollection = 0;

    ParkingSystem(int[] capacities) {

        slots = new ParkingSlot[capacities.length][];

        for (int i = 0; i < capacities.length; i++) {
            if (capacities[i] <= 0) {
                throw new IllegalArgumentException(
                        "Invalid capacity");
            }

            slots[i] = new ParkingSlot[capacities[i]];

            for (int j = 0; j < capacities[i]; j++) {
                slots[i][j] = new ParkingSlot(i + 1, j + 1);
            }
        }
    }

    private Vehicle createVehicle(String regNo, String type) {

        if (type == null) {
            throw new IllegalArgumentException("Unknown vehicle");
        }

        switch (type.toUpperCase()) {
            case "BIKE":
                return new TwoWheeler(regNo);
            case "CAR":
                return new Car(regNo);
            case "EV":
                return new ElectricCar(regNo);
            case "EBIKE":
                return new EBike(regNo);
            case "VAN":
                return new DeliveryVan(regNo);
            default:
                throw new IllegalArgumentException(
                        "Unknown vehicle type: " + type);
        }
    }

    public void parkVehicle(
            String regNo, String type, int floor, int slot,
            LocalDateTime entry, boolean monthlyPass,
            OneDayPass oneDayPass) {

        if (activeTickets.containsKey(regNo)) {
            throw new IllegalArgumentException(
                    "Duplicate registration number");
        }

        Vehicle vehicle = createVehicle(regNo, type);

        if (entry == null) {
            throw new IllegalArgumentException("Invalid entry time");
        }

        if (floor < 1 || floor > slots.length) {
            throw new IllegalArgumentException(
                    "Invalid floor number");
        }

        if (slot < 1 || slot > slots[floor - 1].length) {
            throw new IllegalArgumentException(
                    "Invalid slot number");
        }

        ParkingSlot parkingSlot = slots[floor - 1][slot - 1];

        if (!parkingSlot.isFree()) {
            throw new IllegalStateException("Slot occupied");
        }

        String ticketNo = "T" + ticketCounter++;

        parkingSlot.occupy(vehicle);

        ParkingTicket ticket = new ParkingTicket(
                ticketNo, vehicle, parkingSlot, entry,
                monthlyPass, oneDayPass);

        activeTickets.put(regNo, ticket);

        System.out.println("Vehicle Parked: " + regNo);
        System.out.println("Type: " + type);
        System.out.println("Ticket: " + ticketNo);
        System.out.println("Floor: " + floor);
        System.out.println("Slot: " + slot);
        System.out.println("-------------------------");
    }

    public void exitVehicle(
            String regNo, int minutes, int chargingUnits) {

        if (minutes < 0 || chargingUnits < 0) {
            throw new IllegalArgumentException(
                    "Invalid duration or charging units");
        }

        ParkingTicket ticket = activeTickets.get(regNo);

        if (ticket == null) {
            throw new IllegalArgumentException(
                    "Active ticket not found");
        }

        Vehicle vehicle = ticket.getVehicle();

        if (chargingUnits > 0 &&
                !(vehicle instanceof Chargeable)) {
            throw new IllegalArgumentException(
                    "Vehicle does not support charging");
        }

        double parkingFee = pricingRule.calculate(
                vehicle, ticket.getEntryTime(), minutes,
                ticket.hasMonthlyPass(), ticket.getOneDayPass());

        double chargingFee = 0;

        if (vehicle instanceof Chargeable) {
            chargingFee = ((Chargeable) vehicle).chargingFee(chargingUnits);
        }

        double total = parkingFee + chargingFee;

        ticket.getSlot().release();
        activeTickets.remove(regNo);

        dailyCollection += total;

        System.out.println("Vehicle Exited: " + regNo);
        System.out.println("Parking Fee: Rs " + parkingFee);
        System.out.println("Charging Fee: Rs " + chargingFee);
        System.out.println("Total Fee: Rs " + total);
        System.out.println("-------------------------");
    }

    public double getDailyCollection() {
        return dailyCollection;
    }

    public void displayFloorCapacity() {
        System.out.println("FLOOR CAPACITY");

        for (int i = 0; i < slots.length; i++) {
            System.out.println(
                    "Floor " + (i + 1) + ": " +
                            slots[i].length + " slots");
        }
    }
}

// Main class
public class ParkingSystemDemo {

    public static void main(String[] args) {

        ParkingSystem system = new ParkingSystem(new int[] { 3, 4, 2, 5, 3 });

        LocalDateTime entry = LocalDateTime.of(2026, 11, 13, 10, 0);

        System.out.println("SMART PARKING SYSTEM");
        System.out.println("=========================");

        system.displayFloorCapacity();

        System.out.println("-------------------------");
        System.out.println("NEW VEHICLE TESTING");

        // E-bike
        system.parkVehicle(
                "TN01EB1234", "EBIKE", 1, 1,
                entry, false, null);

        // Delivery van
        system.parkVehicle(
                "TN02DV5678", "VAN", 2, 1,
                entry, false, null);

        // Visitor with one-day pass
        OneDayPass pass = new OneDayPass(entry.toLocalDate());

        system.parkVehicle(
                "TN03CP9012", "CAR", 3, 1,
                entry, false, pass);

        // Electric vehicle
        system.parkVehicle(
                "TN04EV3456", "EV", 4, 1,
                entry, false, null);

        System.out.println("VEHICLE EXIT TESTING");

        system.exitVehicle("TN01EB1234", 120, 0);
        system.exitVehicle("TN02DV5678", 120, 0);
        system.exitVehicle("TN03CP9012", 120, 0);
        system.exitVehicle("TN04EV3456", 60, 2);

        // Delivery van outside peak hours
        LocalDateTime evening = LocalDateTime.of(2026, 11, 13, 18, 0);

        system.parkVehicle(
                "TN05DV9999", "VAN", 5, 1,
                evening, false, null);

        system.exitVehicle("TN05DV9999", 60, 0);

        System.out.println("DAILY COLLECTION");
        System.out.println(
                "Total: Rs " + system.getDailyCollection());
    }
}
