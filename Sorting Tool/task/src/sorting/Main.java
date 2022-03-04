package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Long> collection = new ArrayList<>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            collection.add(number);
        }
        long max = Collections.max(collection);
        long frequency = Collections.frequency(collection, max);
        System.out.println("Total numbers: " + collection.size() + ".");
        System.out.println("The greatest number: " + max + " (" + frequency + " time(s))");
    }
}
