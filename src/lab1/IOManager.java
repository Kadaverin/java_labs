package lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class IOManager {
    private static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public static String getLine(String promptMsg) {
        String res = null;

        if(promptMsg != null) {
            System.out.println(promptMsg);
        }

        try {
            res = inputReader.readLine();
        } catch (IOException e) {
            System.err.println("An IOException was caught :" + e.getMessage());
        }

        return res;
    }

    public static List<String> getLines(int amount, String promtMsg)  {
        System.out.println(promtMsg);

        List<String> res = new ArrayList<>();

        try {
            for( int i = 0; i < amount; i++ ) {
                res.add(inputReader.readLine());
            }
        } catch (IOException e) {
            System.err.println("An IOException was caught :" + e.getMessage());
        }

        return res;
    }

    public static int getPositiveInteger(String promtMsg) {
        int inputInt = -1;
        System.out.println(promtMsg);

        while(inputInt < 0) {
            try {
                System.out.print("Please, enter the positive integer: ");
                inputInt = Integer.parseInt(inputReader.readLine());
              } catch(NumberFormatException nfe ) {

            } catch (IOException e) {
                System.err.println("An IOException was caught :"+e.getMessage());
            }
        }

        return inputInt;
    }

    public static void printLineWithLength(String line, String caption) {
        System.out.println(String.format(
                "%s \"%s\", length: %d",
                caption,
                line,
                line.length()
                )
        );
    }

    public static void printLine(String line, String lineCaption) {
        System.out.println(String.format(
                "%s \"%s\"",
                lineCaption,
                line
                )
        );
    }

    public static void printLine(String line) {
        System.out.println(line);
    }

    public static void printLines(List<String> lines, String lineCaption, String mainCaption, boolean showLength) {
        if (mainCaption.length() > 0) {
            System.out.println(mainCaption);
        }

        BiConsumer<String, String> methodToCall = showLength
                ? IOManager::printLineWithLength
                : IOManager::printLine;

        lines.forEach(line -> methodToCall.accept(line, lineCaption));
    }
}
