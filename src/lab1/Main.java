package lab1;

import common.utils.IOManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IOManager ioManager = IOManager.getInstance();
        
        ioManager.printLine("Enter number of lines");
        int amount = ioManager.getPositiveInteger();

        ioManager.printLine("Enter your lines");
        List<String> lines = ioManager.getLines(amount);

        String longestLine = StringAnalyzer.getLongestLine(lines);

        ioManager.printLine("Your lines is:");
        ioManager.printLines(lines,  false);

        ioManager.printLine("\n The longest line is: \n");
        ioManager.printLineWithLength(longestLine);

        List<String> sortedLines = StringAnalyzer.sortLinesByLength(lines, true);

        ioManager.printLine("\n Sorted by length:");
        ioManager.printLines(sortedLines, true);

        double averageLength = StringAnalyzer.getAverageLinesLength(lines);

        List<String> shorterThanAverage = StringAnalyzer.findLinesThatShorterThan(lines, averageLength);

        ioManager.printLine("\n Shorter than average length:");
        ioManager.printLines(shorterThanAverage,   true);

    }
}
