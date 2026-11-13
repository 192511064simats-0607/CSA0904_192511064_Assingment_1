
package Task4;

import java.time.LocalDateTime;

public class ParkingTicket {

    private String ticketNumber;
    private String registrationNumber;
    private LocalDateTime entryTime;
    private int floorNumber;
    private int slotNumber;

    // Default Constructor
    public ParkingTicket() {
        this("NOT-ISSUED", "NOT-ASSIGNED",
                LocalDateTime.now(), 0, 0);
    }

    // Parameterised Constructor
    public ParkingTicket(String ticketNumber,
            String registrationNumber,
            LocalDateTime entryTime,
            int floorNumber,
            int slotNumber) {

        if (ticketNumber == null || ticketNumber.trim().isEmpty()
                || registrationNumber == null
                || registrationNumber.trim().isEmpty()
                || entryTime == null
                || floorNumber < 0 || slotNumber < 0) {

            throw new IllegalArgumentException(
                    "Invalid parking ticket details");
        }

        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
        this.entryTime = entryTime;
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
    }

    // Overloaded Constructor
    public ParkingTicket(String ticketNumber,
            String registrationNumber,
            int floorNumber,
            int slotNumber) {

        this(ticketNumber, registrationNumber,
                LocalDateTime.now(), floorNumber, slotNumber);
    }

    // Copy Constructor
    public ParkingTicket(ParkingTicket original) {

        this(original.ticketNumber,
                original.registrationNumber,
                original.entryTime,
                original.floorNumber,
                original.slotNumber);
    }

    // Display Ticket Details
    public void displayTicket() {

        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Vehicle Number: " + registrationNumber);
        System.out.println("Entry Time: " + entryTime);
        System.out.println("Floor Number: " + floorNumber);
        System.out.println("Slot Number: " + slotNumber);

        System.out.println("-------------------------");
    }

    // Main Method
    public static void main(String[] args) {

        // Default Constructor
        ParkingTicket t1 = new ParkingTicket();

        // Parameterised Constructor
        ParkingTicket t2 = new ParkingTicket(
                "T101",
                "TN01AB1234",
                LocalDateTime.now(),
                1,
                5);

        // Overloaded Constructor
        ParkingTicket t3 = new ParkingTicket(
                "T102",
                "TN02CD5678",
                2,
                10);

        // Copy Constructor
        ParkingTicket t4 = new ParkingTicket(t2);

        System.out.println("DEFAULT CONSTRUCTOR");
        t1.displayTicket();

        System.out.println("PARAMETERISED CONSTRUCTOR");
        t2.displayTicket();

        System.out.println("OVERLOADED CONSTRUCTOR");
        t3.displayTicket();

        System.out.println("COPY CONSTRUCTOR");
        t4.displayTicket();
    }
}
