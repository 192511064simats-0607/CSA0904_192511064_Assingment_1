
package Task5;

public class StaticTicketDemo {

    // Static nested class
    static class ParkingTicket {

        // Static variable shared by all ticket objects
        private static int ticketCounter;

        // Instance variable
        private final String ticketNumber;
        private final String vehicleNumber;

        // Static block
        static {
            ticketCounter = 1000;
            System.out.println("Parking System Initialized");
        }

        // Constructor
        public ParkingTicket(String vehicleNumber) {
            ticketCounter++;
            this.ticketNumber = "T" + ticketCounter;
            this.vehicleNumber = vehicleNumber;
        }

        // Static method
        public static int getTotalTickets() {
            return ticketCounter - 1000;
        }

        // Instance method
        public void displayTicket() {
            System.out.println("Ticket Number: " + ticketNumber);
            System.out.println("Vehicle Number: " + vehicleNumber);
            System.out.println("-------------------------");
        }
    }

    // Passing an object to a method
    public static void processExit(ParkingTicket ticket) {
        System.out.println("Processing Vehicle Exit");
        ticket.displayTicket();
    }

    // Returning an object from a method
    public static ParkingTicket createTicket(String vehicleNumber) {
        return new ParkingTicket(vehicleNumber);
    }

    public static void main(String[] args) {

        // Creating ticket objects
        ParkingTicket ticket1 = createTicket("TN01AB1234");

        ParkingTicket ticket2 = createTicket("TN02CD5678");

        ParkingTicket ticket3 = createTicket("TN03EF9012");

        System.out.println("\nPARKING TICKETS");

        ticket1.displayTicket();
        ticket2.displayTicket();
        ticket3.displayTicket();

        // Static method call
        System.out.println("Total Tickets Issued: "
                + ParkingTicket.getTotalTickets());

        // Passing object to method
        processExit(ticket1);

        // Removing one reference
        ticket1 = null;

        System.out.println(
                "Ticket1 reference removed. "
                        + "Object may now be eligible for garbage collection.");

        System.out.println("Ticket2 is still active:");
        ticket2.displayTicket();

        System.out.println("Total Tickets Issued: "
                + ParkingTicket.getTotalTickets());
    }
}
