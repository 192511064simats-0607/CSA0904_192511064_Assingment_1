
import java.util.ArrayList;
import java.util.HashMap;

public class LookupBenchmark {

    public static void main(String[] args) {

        int n = 10000;
        int repetitions = 500;

        ParkingTicket[] array = new ParkingTicket[n];

        ArrayList<ParkingTicket> list = new ArrayList<>();

        HashMap<String, ParkingTicket> map = new HashMap<>();

        ParkingSlot slot = new ParkingSlot(1, 1);

        for (int i = 0; i < n; i++) {

            String reg = "REG" + i;

            ParkingTicket ticket = new ParkingTicket(
                    "T" + i, new Car(reg), slot, false);

            array[i] = ticket;
            list.add(ticket);
            map.put(reg, ticket);
        }

        String target = "REG" + (n - 1);

        long start, end;
        ParkingTicket found;
        long checksum = 0;

        // Array search
        start = System.nanoTime();

        for (int r = 0; r < repetitions; r++) {
            found = null;

            for (ParkingTicket t : array) {
                if (t.getVehicle().getRegNo().equals(target)) {
                    found = t;
                    break;
                }
            }

            checksum += found.getTicketNumber().length();
        }

        end = System.nanoTime();
        System.out.println("Array: " + (end - start) + " ns");

        // ArrayList search
        start = System.nanoTime();

        for (int r = 0; r < repetitions; r++) {
            found = null;

            for (ParkingTicket t : list) {
                if (t.getVehicle().getRegNo().equals(target)) {
                    found = t;
                    break;
                }
            }

            checksum += found.getTicketNumber().length();
        }

        end = System.nanoTime();
        System.out.println("ArrayList: " + (end - start) + " ns");

        // HashMap search
        start = System.nanoTime();

        for (int r = 0; r < repetitions; r++) {
            found = map.get(target);
            checksum += found.getTicketNumber().length();
        }

        end = System.nanoTime();
        System.out.println("HashMap: " + (end - start) + " ns");

        System.out.println("Checksum: " + checksum);
    }
}
