
package Task10.parking.model;

public class Vehicle {

    private String registrationNumber;
    private Integer parkingDuration;

    public Vehicle(String registrationNumber) {
        setRegistrationNumber(registrationNumber);
        setParkingDuration(0);
    }

    // Validating setter
    public void setRegistrationNumber(String regNo) {

        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Invalid registration number");
        }

        registrationNumber = regNo;
    }

    // Validating setter with wrapper class
    public void setParkingDuration(Integer duration) {

        if (duration == null || duration < 0) {
            throw new IllegalArgumentException(
                "Parking duration cannot be negative");
        }

        parkingDuration = duration;
    }

    // Getters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Integer getParkingDuration() {
        return parkingDuration;
    }

    public void display() {
        System.out.println(
            "Vehicle Number: " + registrationNumber);

        System.out.println(
            "Parking Duration: " + parkingDuration);
    }
}
