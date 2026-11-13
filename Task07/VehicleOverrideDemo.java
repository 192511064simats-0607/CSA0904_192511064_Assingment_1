
package Task07;

// Superclass
abstract class Vehicle {

    private final String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public final String getRegNo() {
        return regNo;
    }

    // Subclasses provide their own hourly rate
    protected abstract double ratePerHour();

    // Common fee calculation
    public double calculateFee(int minutes) {

        if (minutes < 0) {
            throw new IllegalArgumentException(
                    "Minutes cannot be negative");
        }

        int hours = minutes / 60;

        if (minutes % 60 != 0) {
            hours++;
        }

        return hours * ratePerHour();
    }

    // Override toString()
    @Override
    public String toString() {
        return "Vehicle Registration: " + regNo;
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Vehicle))
            return false;

        Vehicle other = (Vehicle) obj;

        return regNo.equals(other.regNo);
    }

    // Override hashCode()
    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

// TwoWheeler subclass
class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

// Car subclass
class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

// Main class
public class VehicleOverrideDemo {

    public static void main(String[] args) {

        Vehicle bike = new TwoWheeler("TN01A1");
        Vehicle car = new Car("TN02B2");

        System.out.println("PARKING FEE DETAILS");

        System.out.println(bike);
        System.out.println("Fee: Rs " +
                bike.calculateFee(130));

        System.out.println("-------------------------");

        System.out.println(car);
        System.out.println("Fee: Rs " +
                car.calculateFee(130));

        System.out.println("-------------------------");

        Vehicle car1 = new Car("TN02B2");
        Vehicle car2 = new Car("TN02B2");

        System.out.println("Vehicle Comparison: "
                + car1.equals(car2));

        System.out.println("Hash Code Comparison: "
                + (car1.hashCode() == car2.hashCode()));

        System.out.println("Final Registration Number: "
                + car.getRegNo());
    }
}
