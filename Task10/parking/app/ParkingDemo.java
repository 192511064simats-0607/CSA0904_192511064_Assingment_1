
package Task10.parking.app;

import Task10.parking.model.Vehicle;
import Task10.parking.model.ParkingSlot;
import Task10.parking.service.ParkingService;

public class ParkingDemo {

    public static void main(String[] args) {

        System.out.println("SMART PARKING SYSTEM");
        System.out.println("-------------------------");

        // Create vehicle
        Vehicle vehicle =
            new Vehicle("TN01AB1234");

        vehicle.setParkingDuration(120);

        vehicle.display();

        System.out.println("-------------------------");

        // Create parking slot
        ParkingSlot slot = new ParkingSlot(5);

        // Parking service
        ParkingService service =
            new ParkingService();

        service.parkVehicle(vehicle, slot);

        System.out.println("-------------------------");

        // Wrapper class and autoboxing
        Integer duration = 120;

        int actualDuration = duration;

        System.out.println(
            "Wrapper Integer: " + duration);

        System.out.println(
            "Unboxed int: " + actualDuration);

        System.out.println("-------------------------");

        // Integer comparison demonstration
        Integer a = Integer.valueOf(1000);
        Integer b = Integer.valueOf(1000);

        System.out.println(
            "Using == : " + (a == b));

        System.out.println(
            "Using equals(): " + a.equals(b));

        System.out.println("-------------------------");

        // Release parking slot
        service.releaseVehicle(slot);

        System.out.println(
            "Slot Occupied: " + slot.isOccupied());
    }
}
