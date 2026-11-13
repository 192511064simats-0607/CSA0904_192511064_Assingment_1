
package Task08;

import java.time.Duration;
import java.time.LocalTime;

// Interface
interface Chargeable {
    double chargingFee(int kWh);
}

// Abstract superclass
abstract class Vehicle {

    private final String regNo;

    protected Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    protected abstract double ratePerHour();

    // Method overloading - integer duration
    public double calculateFee(int minutes) {

        if (minutes < 0) {
            throw new IllegalArgumentException(
                    "Minutes cannot be negative");
        }

        int hours = (minutes + 59) / 60;

        return hours * ratePerHour();
    }

    // Method overloading - entry and exit time
    public double calculateFee(
            LocalTime in, LocalTime out) {

        long minutes = Duration.between(in, out).toMinutes();

        if (minutes < 0) {
            minutes += 24 * 60;
        }

        return calculateFee((int) minutes);
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Vehicle)) {
            return false;
        }

        Vehicle other = (Vehicle) obj;

        return regNo.equals(other.regNo);
    }

    // Override hashCode()
    @Override
    public int hashCode() {
        return regNo.hashCode();
    }

    // Override toString()
    @Override
    public String toString() {
        return "Vehicle Registration: " + regNo;
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

// ElectricCar subclass
class ElectricCar extends Car implements Chargeable {

    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double chargingFee(int kWh) {

        return kWh * 12.0;
    }
}

// Main class
public class FeeDemo {

    public static void main(String[] args) {

        // Upcasting and polymorphism
        Vehicle[] parked = {

                new TwoWheeler("TN01A1"),
                new Car("TN02B2"),
                new ElectricCar("TN03C3")
        };

        System.out.println("SMART PARKING FEE DETAILS");
        System.out.println("-------------------------");

        for (Vehicle v : parked) {

            double fee = v.calculateFee(130);

            // Java-compatible instanceof syntax
            if (v instanceof Chargeable) {

                Chargeable c = (Chargeable) v;

                fee += c.chargingFee(10);
            }

            System.out.println(
                    v.getClass().getSimpleName()
                            + " " + v.getRegNo()
                            + " -> Rs " + fee);
        }

        System.out.println("-------------------------");

        // Method overloading demonstration
        Vehicle car = new Car("TN04D4");

        System.out.println(
                "Fee using time: Rs "
                        + car.calculateFee(
                                LocalTime.of(9, 0),
                                LocalTime.of(11, 30)));

        System.out.println("-------------------------");

        // equals() demonstration
        Vehicle car1 = new Car("TN02B2");
        Vehicle car2 = new ElectricCar("TN02B2");

        System.out.println(
                "Vehicle Comparison: "
                        + car1.equals(car2));

        System.out.println(
                "Hash Code Comparison: "
                        + (car1.hashCode() == car2.hashCode()));
    }
}
