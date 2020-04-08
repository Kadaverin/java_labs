package lab1;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        int amount = IOManager.getPositiveInteger("Enter number of lines");

        List<String> lines = IOManager.getLines(amount, "Enter your lines");

        String longestLine = StringAnalyzer.getLongestLine(lines);

        IOManager.printLines(lines, "", "Your lines is:", false);

        IOManager.printLineWithLength(longestLine, "\n The longest line is: \n");

        List<String> sortedLines = StringAnalyzer.sortLinesByLength(lines, true);

        IOManager.printLines(sortedLines, "", "\n Sorted by length:", true);

        double averageLength = StringAnalyzer.getAverageLinesLength(lines);

        List<String> shorterThanAverage = StringAnalyzer.findLinesThatShorterThan(lines, ((int) averageLength));

        IOManager.printLines(shorterThanAverage, "", "\n Shorter than average length:", true);

    }
}
