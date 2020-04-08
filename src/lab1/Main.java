package lab1;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        IOManager.printLine("Enter number of lines");
        int amount = IOManager.getPositiveInteger();

        IOManager.printLine("Enter your lines");
        List<String> lines = IOManager.getLines(amount);

        String longestLine = StringAnalyzer.getLongestLine(lines);

        IOManager.printLine("Your lines is:");
        IOManager.printLines(lines,  false);

        IOManager.printLine("\n The longest line is: \n");
        IOManager.printLineWithLength(longestLine);

        List<String> sortedLines = StringAnalyzer.sortLinesByLength(lines, true);

        IOManager.printLine("\n Sorted by length:");
        IOManager.printLines(sortedLines, true);

        double averageLength = StringAnalyzer.getAverageLinesLength(lines);

        List<String> shorterThanAverage = StringAnalyzer.findLinesThatShorterThan(lines, averageLength);

        IOManager.printLine("\n Shorter than average length:");
        IOManager.printLines(shorterThanAverage,   true);

    }
}
