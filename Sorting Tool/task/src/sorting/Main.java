package sorting;

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

    public static void main(final String[] args) {
        scanner = new Scanner(System.in);
        List<String> arguments = Arrays.asList(args);

        String dataType = "word";
        int dtIndex = arguments.indexOf("-dataType");
        if (dtIndex >= 0) {
            dataType = arguments.get(dtIndex + 1);
        }


        String sortingType = "natural";
        int stIndex = arguments.indexOf("-sortingType");
        if (stIndex >=0) {
            sortingType = arguments.get(stIndex + 1);
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


        System.out.printf("Total numbers: %d.\n", numbers.size());
        sortedList.forEach(entry -> {
            int percentage = (int) (((double) entry.getValue() / (double) numbers.size()) * 100);
            System.out.println(entry.getKey() + ": " + entry.getValue()  + " time(s), " + percentage + "%");
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

        System.out.printf("Total words: %d.\n", words.size());
        sortedList.forEach(entry -> {
            int percentage = (int) (((double) entry.getValue() / (double) words.size()) * 100);
            System.out.println(entry.getKey() + ": " + entry.getValue()  + " time(s), " + percentage + "%");
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


        System.out.printf("Total lines: %d.\n", lines.size());
        sortedList.forEach(entry -> {
            int percentage = (int) (((double) entry.getValue() / (double) lines.size()) * 100);
            System.out.println(entry.getKey() + ": " + entry.getValue()  + " time(s), " + percentage + "%");
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

        System.out.println("Total numbers: " + numbers.size() + ".\n" +
                "Sorted data: " + res);
    }

    private static void sortWords() {
        List<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.next());
        }

        words.sort(String::compareTo);
        StringBuilder res = new StringBuilder();
        words.forEach(word -> res.append(word).append(" "));

        System.out.println("Total words: " + words.size() + ".\n" +
                "Sorted data: " + res);
    }

    private static void sortLines() {
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        lines.sort(String::compareTo);
        StringBuilder res = new StringBuilder();
        lines.forEach(line -> res.append(line).append("\n"));

        System.out.println("Total lines: " + lines.size() + ".\n" +
                "Sorted data:\n" + res);
    }


}