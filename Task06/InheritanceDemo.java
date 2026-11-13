
package Task06;

// Superclass
class Vehicle {
    protected String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
        System.out.println("Vehicle Constructor Called");
    }

    public String getRegNo() {
        return regNo;
    }

    public void display() {
        System.out.println("Registration Number: " + regNo);
    }
}

// Hierarchical inheritance
class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
        System.out.println("TwoWheeler Constructor Called");
    }
}

// Single inheritance
class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
        System.out.println("Car Constructor Called");
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Vehicle Type: Car");
    }
}

// Multilevel inheritance
class ElectricCar extends Car {

    ElectricCar(String regNo) {
        super(regNo);
        System.out.println("ElectricCar Constructor Called");
    }

    public double chargingFee(int units) {
        return units * 12.0;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Vehicle Type: Electric Car");
    }
}

// Main class
public class InheritanceDemo {

    public static void main(String[] args) {

        System.out.println("CREATING TWO WHEELER");
        TwoWheeler bike = new TwoWheeler("TN01A1");
        bike.display();

        System.out.println("\nCREATING CAR");
        Car car = new Car("TN02B2");
        car.display();

        System.out.println("\nCREATING ELECTRIC CAR");
        ElectricCar electricCar = new ElectricCar("TN03C3");

        electricCar.display();

        System.out.println("Charging Fee: Rs "
                + electricCar.chargingFee(10));
    }
}
