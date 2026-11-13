
package Task09;

// Interface for electric vehicle charging
interface Chargeable {

    double chargingFee(int kWh);

    // Default method
    default void showChargingMessage() {
        System.out.println("Charging facility required");
    }

    // Static method
    static double standardRate() {
        return 12.0;
    }
}

// Interface for payment
interface Payable {

    double paymentAmount(double fee);
}

// Abstract superclass
abstract class Vehicle {

    private final String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    // Abstract method
    protected abstract double ratePerHour();

    // Concrete method
    public double calculateFee(int minutes) {

        if (minutes < 0) {
            throw new IllegalArgumentException(
                    "Invalid parking duration");
        }

        int hours = (minutes + 59) / 60;

        return hours * ratePerHour();
    }

    public void display() {
        System.out.println("Registration Number: " + regNo);
    }
}

// TwoWheeler class
class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

// Car class
class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

// ElectricCar uses multiple interfaces
class ElectricCar extends Car
        implements Chargeable, Payable {

    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * Chargeable.standardRate();
    }

    @Override
    public double paymentAmount(double fee) {
        return fee;
    }
}

// Main class
public class AbstractionDemo {

    public static void main(String[] args) {

        System.out.println("SMART PARKING ABSTRACTION");
        System.out.println("-------------------------");

        Vehicle bike = new TwoWheeler("TN01A1");

        Vehicle car = new Car("TN02B2");

        ElectricCar electricCar = new ElectricCar("TN03C3");

        // TwoWheeler
        bike.display();
        System.out.println(
                "Parking Fee: Rs " +
                        bike.calculateFee(130));

        System.out.println("-------------------------");

        // Car
        car.display();
        System.out.println(
                "Parking Fee: Rs " +
                        car.calculateFee(130));

        System.out.println("-------------------------");

        // ElectricCar
        electricCar.display();

        double parkingFee = electricCar.calculateFee(130);

        double chargingFee = electricCar.chargingFee(10);

        double total = electricCar.paymentAmount(
                parkingFee + chargingFee);

        electricCar.showChargingMessage();

        System.out.println(
                "Parking Fee: Rs " + parkingFee);

        System.out.println(
                "Charging Fee: Rs " + chargingFee);

        System.out.println(
                "Total Payment: Rs " + total);

        System.out.println("-------------------------");

        System.out.println(
                "Standard Charging Rate: Rs " +
                        Chargeable.standardRate());
    }
}
