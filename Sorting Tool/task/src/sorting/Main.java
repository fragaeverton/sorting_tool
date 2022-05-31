package sorting;

import java.util.*;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    public static void main(final String[] args) {
        if (args.length > 0) {
            if (Arrays.asList(args).contains("-sortIntegers")) {
                processIntegers();
            }else if ("-dataType".equals(args[0])){
                switch (args[1]) {
                    case "long":
                        processLong();
                        break;
                    case "line":
                        processLine();
                        break;
                    case "word":
                        processWords();
                        break;
                }
            }


        }
    }

    public static void processLong() {
        List<Long> numberList = new ArrayList<>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            numberList.add(number);
        }
        long max = Collections.max(numberList);
        long count = Collections.frequency(numberList,max);
        double percent = ((double) count / numberList.size()) * 100;

        System.out.printf("Total numbers: %d.\n",numberList.size());
        System.out.printf("The greatest number: %d (%d time(s), %.0f%%).\n",max,count,percent);
    }

    public static void processLine() {
        List<String> lineList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lineList.add(scanner.nextLine());
        }
        int totalLines = lineList.size();
        String longestLine = Collections.max(lineList,Comparator.comparing(String::length));
        int count = Collections.frequency(lineList,longestLine);
        double percent = ((double) count / lineList.size()) * 100;
        System.out.printf("Total lines: %d\n",totalLines);
        System.out.printf("The longest line:\n%s\n",longestLine);
        System.out.printf("(%d times(s), %.0f%%).\n",count,percent);
    }

    public static void processWords() {
        List<String> wordsList = new ArrayList<>();
        while (scanner.hasNext()) {
            wordsList.add(scanner.next());
        }

        String longestWord = Collections.max(wordsList,Comparator.comparing(String::length));
        int count = Collections.frequency(wordsList,longestWord);
        double percent = ((double) count / wordsList.size()) * 100;
        System.out.printf("Total words: %d\n",wordsList.size());
        System.out.printf("The longest word: %s (%d times(s), %.0f%%).\n",longestWord,count,percent);

    }

    public static void processIntegers() {
        List<Integer> integerList = new ArrayList<>();
        while (scanner.hasNextInt()) {
            integerList.add(scanner.nextInt());
        }
        integerList.sort(Comparator.naturalOrder());
        System.out.printf("Total numbers: %d.\n",integerList.size());
        System.out.println("Sorted data: "+ getString(integerList));
    }

    public static StringBuilder getString(List<Integer> IntCollection){
        StringBuilder myString = new StringBuilder();
        for(int i : IntCollection){
            myString.append(i + " ");
        }
        return myString;
    }


}
