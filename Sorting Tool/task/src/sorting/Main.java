package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static PrintWriter printWriter;
    private static boolean printToFile;


    public static void main(final String[] args) {
        List<String> arguments = Arrays.asList(args);
        List<String> validArguments = List.of("-sortingType", "-dataType",
                "natural", "byCount",
                "long", "word", "line",
                "-inputFile", "-outputFile");
        arguments.forEach(arg -> {
            if (!validArguments.contains(arg)) {
                System.out.println("\"" + arg + "\" is not a valid parameter. It will be skipped.");
            }
        });

        if (arguments.contains("-inputFile")) {
            int indexF = arguments.indexOf("-inputFile");
            String fileName = arguments.get(indexF + 1);
            try {
                scanner = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            scanner = new Scanner(System.in);
        }

        if (arguments.contains("-outputFile")) {
            int indexF = arguments.indexOf("-outputFile");
            String fileName = arguments.get(indexF + 1);
            try {
                printWriter = new PrintWriter(fileName);
                printToFile = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            printToFile = false;
        }

        String dataType = "word";
        int dtIndex = arguments.indexOf("-dataType");
        if (dtIndex >= 0) {
            try{
                dataType = arguments.get(dtIndex + 1);
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("No data type defined!");
                return;
            }
        }


        String sortingType = "natural";
        int stIndex = arguments.indexOf("-sortingType");
        if (stIndex >=0) {
            try{
                sortingType = arguments.get(stIndex + 1);
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("No sorting type defined!");
                return;
            }
        }

        switch(sortingType) {
            case "natural":
                naturalSorting(dataType);
                break;
            case "byCount":
                sortByCount(dataType);
                break;
        }

        scanner.close();
    }

    private static void naturalSorting(String dataType) {
        switch (dataType) {
            case "long":
                sortNumbers();
                break;
            case "word":
                sortWords();
                break;
            case "line":
                sortLines();
                break;
        }
    }

    private static void sortByCount(String dataType) {
        switch (dataType) {
            case "long":
                sortLongsByCount();
                break;
            case "word":
                sortWordsByCount();
                break;
            case "line":
                sortLinesByCount();
                break;
        }
    }

    private static void sortLongsByCount() {
        List<Long> numbers = new ArrayList<>();
        while (scanner.hasNext()) {
            numbers.add(scanner.nextLong());
        }

        Map<Long, Integer> valueFrequency = new HashMap<>();
        numbers.forEach(number -> valueFrequency.putIfAbsent(number, Collections.frequency(numbers, number)));

        List<Map.Entry<Long, Integer>> sortedList = new ArrayList<>(valueFrequency.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());


        if (printToFile) {
            printWriter.printf("Total numbers: %d.\n", numbers.size());
        } else {
            System.out.printf("Total numbers: %d.\n", numbers.size());
        }
        sortedList.forEach(entry -> {
            int percentage = (int) (((double) entry.getValue() / (double) numbers.size()) * 100);
            String output = entry.getKey() + ": " + entry.getValue() + " time(s), " + percentage + "%";
            if (printToFile) {
                printWriter.println(output);
            } else {
                System.out.println(output);
            }
        });
    }

    private static void sortWordsByCount() {
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }

        Map<String, Integer> wordFrequency = new HashMap<>();
        words.forEach(word -> wordFrequency.putIfAbsent(word, Collections.frequency(words, word)));

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFrequency.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator0 = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Integer>> comparator1 = Map.Entry.comparingByKey();

        sortedList.sort(comparator0.thenComparing(comparator1));

        if (printToFile) {
            printWriter.printf("Total words: %d.\n", words.size());
        } else {
            System.out.printf("Total words: %d.\n", words.size());
        }
        sortedList.forEach(entry -> {
            int percentage = (int) (((double) entry.getValue() / (double) words.size()) * 100);
            String output = entry.getKey() + ": " + entry.getValue() + " time(s), " + percentage + "%";
            if (printToFile) {
                printWriter.println(output);
            } else {
                System.out.println(output);
            }
        });
    }

    private static void sortLinesByCount() {
        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        Map<String, Integer> lineFrequency = new HashMap<>();
        lines.forEach(line -> lineFrequency.putIfAbsent(line, Collections.frequency(lines, line)));

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(lineFrequency.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator0 = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Integer>> comparator1 = Map.Entry.comparingByKey();

        sortedList.sort(comparator0.thenComparing(comparator1));

        if (printToFile) {
            printWriter.printf("Total lines: %d.\n", lines.size());
        } else {
            System.out.printf("Total lines: %d.\n", lines.size());
        }
        sortedList.forEach(entry -> {
            int percentage = (int) (((double) entry.getValue() / (double) lines.size()) * 100);
            String output = entry.getKey() + ": " + entry.getValue() + " time(s), " + percentage + "%";
            if (printToFile) {
                printWriter.println(output);
            } else {
                System.out.println(output);
            }
        });
    }

    private static void sortNumbers() {
        List<Long> numbers = new ArrayList<>();
        while (scanner.hasNext()) {
            long number = scanner.nextLong();
            numbers.add(number);
        }

        numbers.sort(Long::compare);
        StringBuilder res = new StringBuilder();
        numbers.forEach(number -> res.append(number).append(" "));

        String output = "Total numbers: " + numbers.size() + ".\n" +
                "Sorted data: " + res;
        if (printToFile) {
            printWriter.println(output);
        } else {
            System.out.println(output);
        }
    }

    private static void sortWords() {
        List<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.next());
        }

        words.sort(String::compareTo);
        StringBuilder res = new StringBuilder();
        words.forEach(word -> res.append(word).append(" "));

        String output = "Total words: " + words.size() + ".\n" +
                "Sorted data: " + res;
        if (printToFile) {
            printWriter.println(output);
        } else {
            System.out.println(output);
        }
    }

    private static void sortLines() {
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        lines.sort(String::compareTo);
        StringBuilder res = new StringBuilder();
        lines.forEach(line -> res.append(line).append("\n"));

        String output = "Total lines: " + lines.size() + ".\n" +
                "Sorted data:\n" + res;
        if (printToFile) {
            printWriter.println(output);
        } else {
            System.out.println(output);
        }
    }

}