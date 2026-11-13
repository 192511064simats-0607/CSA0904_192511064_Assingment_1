
import java.time.LocalDateTime;

// Interface for electric vehicle charging
interface Chargeable {
    double chargingFee(int kWh);
}

// Abstract Vehicle class
abstract class Vehicle {

    private final String regNo;

    Vehicle(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Invalid registration number");
        }
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    protected abstract double ratePerHour();

    public double calculateFee(int minutes) {

        if (minutes < 0) {
            throw new IllegalArgumentException(
                    "Invalid parking duration");
        }

        int hours = (minutes + 59) / 60;
        return hours * ratePerHour();
    }
}

// Two-wheeler
class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

// Car
class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

// Electric car
class ElectricCar extends Car implements Chargeable {

    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double chargingFee(int kWh) {

        if (kWh < 0) {
            throw new IllegalArgumentException(
                    "Invalid charging units");
        }

        return kWh * 12;
    }
}

// Parking slot
class ParkingSlot {

    private final int floorNumber;
    private final int slotNumber;
    private Vehicle vehicle;

    ParkingSlot(int floorNumber, int slotNumber) {
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
    }

    public boolean isFree() {
        return vehicle == null;
    }

    public void occupy(Vehicle vehicle) {
        if (!isFree()) {
            throw new IllegalStateException(
                    "Slot already occupied");
        }
        this.vehicle = vehicle;
    }

    public void release() {
        vehicle = null;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }
}

// Parking ticket
class ParkingTicket {

    private final String ticketNumber;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final LocalDateTime entryTime;
    private final boolean monthlyPass;

    ParkingTicket(String ticketNumber, Vehicle vehicle,
            ParkingSlot slot, boolean monthlyPass) {

        this.ticketNumber = ticketNumber;
        this.vehicle = vehicle;
        this.slot = slot;
        this.monthlyPass = monthlyPass;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public boolean hasMonthlyPass() {
        return monthlyPass;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}

// Complete parking management system
class ParkingSystem {

    private final ParkingSlot[][] slots;
    private final ParkingTicket[] activeTickets;

    private int ticketCounter = 1001;
    private double dailyCollection = 0;

    ParkingSystem(int[] slotsPerFloor) {

        slots = new ParkingSlot[slotsPerFloor.length][];

        int totalSlots = 0;

        for (int f = 0; f < slotsPerFloor.length; f++) {

            if (slotsPerFloor[f] <= 0) {
                throw new IllegalArgumentException(
                        "Invalid slot count");
            }

            slots[f] = new ParkingSlot[slotsPerFloor[f]];
            totalSlots += slotsPerFloor[f];

            for (int s = 0; s < slotsPerFloor[f]; s++) {
                slots[f][s] = new ParkingSlot(f + 1, s + 1);
            }
        }

        activeTickets = new ParkingTicket[totalSlots];
    }

    private Vehicle createVehicle(String regNo, String type) {

        if (type == null) {
            throw new IllegalArgumentException(
                    "Unknown vehicle type");
        }

        switch (type.toUpperCase()) {
            case "BIKE":
                return new TwoWheeler(regNo);

            case "CAR":
                return new Car(regNo);

            case "EV":
                return new ElectricCar(regNo);

            default:
                throw new IllegalArgumentException(
                        "Unknown vehicle type: " + type);
        }
    }

    private int findTicket(String regNo) {

        for (int i = 0; i < activeTickets.length; i++) {

            if (activeTickets[i] != null &&
                    activeTickets[i].getVehicle()
                            .getRegNo().equals(regNo)) {
                return i;
            }
        }

        return -1;
    }

    public void parkVehicle(String regNo, String type,
            int floor, int slot) {

        parkVehicle(regNo, type, floor, slot, false);
    }

    public void parkVehicle(String regNo, String type,
            int floor, int slot,
            boolean monthlyPass) {

        if (findTicket(regNo) != -1) {
            throw new IllegalArgumentException(
                    "Duplicate registration number");
        }

        Vehicle vehicle = createVehicle(regNo, type);

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
            throw new IllegalStateException(
                    "Slot already occupied");
        }

        int index = -1;

        for (int i = 0; i < activeTickets.length; i++) {
            if (activeTickets[i] == null) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IllegalStateException(
                    "Parking facility is full");
        }

        String ticketNo = "T" + ticketCounter++;

        parkingSlot.occupy(vehicle);

        activeTickets[index] = new ParkingTicket(
                ticketNo, vehicle, parkingSlot, monthlyPass);

        System.out.println("Vehicle Parked: " + regNo);
        System.out.println("Ticket Number: " + ticketNo);
        System.out.println("Floor: " + floor);
        System.out.println("Slot: " + slot);
        System.out.println("-------------------------");
    }

    public double exitVehicle(String regNo,
            int minutes, int kWh) {

        if (minutes < 0 || kWh < 0) {
            throw new IllegalArgumentException(
                    "Invalid duration or charging units");
        }

        int index = findTicket(regNo);

        if (index == -1) {
            throw new IllegalArgumentException(
                    "Active ticket not found");
        }

        ParkingTicket ticket = activeTickets[index];
        Vehicle vehicle = ticket.getVehicle();

        if (kWh > 0 && !(vehicle instanceof Chargeable)) {
            throw new IllegalArgumentException(
                    "Vehicle does not support charging");
        }

        double parkingFee = ticket.hasMonthlyPass()
                ? 0
                : vehicle.calculateFee(minutes);

        double chargingFee = 0;

        if (vehicle instanceof Chargeable) {
            chargingFee = ((Chargeable) vehicle).chargingFee(kWh);
        }

        double totalFee = parkingFee + chargingFee;

        ticket.getSlot().release();
        activeTickets[index] = null;

        dailyCollection += totalFee;

        System.out.println("Vehicle Exited: " + regNo);
        System.out.println("Parking Fee: Rs " + parkingFee);
        System.out.println("Charging Fee: Rs " + chargingFee);
        System.out.println("Total Fee: Rs " + totalFee);
        System.out.println("-------------------------");

        return totalFee;
    }

    public double getDailyCollection() {
        return dailyCollection;
    }
}

// Main class
public class ParkingSystemDemo {

    public static void main(String[] args) {

        ParkingSystem system = new ParkingSystem(new int[] { 2, 2 });

        System.out.println("SMART PARKING SYSTEM");
        System.out.println("=========================");

        // Normal parking operations
        system.parkVehicle("TN01A1", "BIKE", 1, 1);
        system.parkVehicle("TN02B2", "CAR", 1, 2);
        system.parkVehicle("TN03C3", "EV", 2, 1);

        // Last available slot
        system.parkVehicle("TN04D4", "BIKE", 2, 2);

        // Invalid test cases
        System.out.println("INVALID CASE TESTING");

        try {
            system.parkVehicle("TN05E5", "TRUCK", 1, 1);
        } catch (Exception e) {
            System.out.println("Unknown Type: " + e.getMessage());
        }

        try {
            system.parkVehicle("TN01A1", "CAR", 1, 1);
        } catch (Exception e) {
            System.out.println("Duplicate: " + e.getMessage());
        }

        try {
            system.parkVehicle("TN05E5", "CAR", 3, 1);
        } catch (Exception e) {
            System.out.println("Invalid Slot: " + e.getMessage());
        }

        System.out.println("-------------------------");
        System.out.println("VEHICLE EXIT TESTING");

        // Normal two-wheeler exit
        system.exitVehicle("TN01A1", 60, 0);

        // Exactly one-hour fee boundary
        system.exitVehicle("TN02B2", 60, 0);

        // EV parking plus charging
        system.exitVehicle("TN03C3", 60, 2);

        // Zero-minute parking
        system.exitVehicle("TN04D4", 0, 0);

        // Daily collection
        System.out.println("DAILY COLLECTION");
        System.out.println(
                "Total: Rs " + system.getDailyCollection());

        // Monthly pass demonstration
        System.out.println("-------------------------");
        system.parkVehicle(
                "TN05E5", "CAR", 1, 1, true);

        system.exitVehicle("TN05E5", 120, 0);
    }
}
