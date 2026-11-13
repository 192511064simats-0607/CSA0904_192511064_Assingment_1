
package Task10.parking.model;

public class ParkingSlot {

    private Integer slotNumber;
    private boolean occupied;

    public ParkingSlot(Integer slotNumber) {
        setSlotNumber(slotNumber);
        occupied = false;
    }

    public void setSlotNumber(Integer number) {

        if (number == null || number <= 0) {
            throw new IllegalArgumentException(
                "Slot number must be positive");
        }

        slotNumber = number;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupySlot() {
        occupied = true;
    }

    public void releaseSlot() {
        occupied = false;
    }
}
