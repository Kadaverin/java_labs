package lab1;

import java.util.List;
import java.util.stream.Collectors;

public class StringAnalyzer {

    public static String getLongestLine(List<String> listOfStrings) {
        return listOfStrings
                .stream()
                .reduce((longest, current) -> current.length() > longest.length()
                        ? current
                        : longest)
                .get();
    }

    public static List<String> sortLinesByLength(List<String> listOfStrings, boolean asc) {
        return listOfStrings
                .stream()
                .sorted((str1, str2) -> asc
                        ? str1.length() - str2.length()
                        : str2.length() - str1.length())
                .collect(Collectors.toList());

    }

    public static double getAverageLinesLength(List<String> lines) {
        return lines
                .stream()
                .mapToInt(str -> str.length())
                .average()
                .orElse(0);
    }

     public static List<String> findLinesThatShorterThan(List<String> lines, double num) {
        return lines
                .stream()
                .filter((str) -> str.length() < num )
                .collect(Collectors.toList());
     }
}
