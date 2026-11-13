
package Task10.parking.service;

import Task10.parking.model.Vehicle;
import Task10.parking.model.ParkingSlot;

public class ParkingService {

    public void parkVehicle(
            Vehicle vehicle, ParkingSlot slot) {

        if (vehicle == null || slot == null) {
            throw new IllegalArgumentException(
                "Vehicle and slot are required");
        }

        if (slot.isOccupied()) {
            System.out.println("Slot is already occupied");
            return;
        }

        slot.occupySlot();

        System.out.println("Vehicle parked successfully");
        System.out.println(
            "Allocated Slot: " + slot.getSlotNumber());
    }

    public void releaseVehicle(ParkingSlot slot) {

        if (slot == null) {
            throw new IllegalArgumentException(
                "Invalid parking slot");
        }

        slot.releaseSlot();

        System.out.println("Parking slot released");
    }
}
