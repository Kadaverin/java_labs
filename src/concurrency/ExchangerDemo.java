package concurrency;

import common.utils.IOManager;
import lab1.StringAnalyzer;
import lab2.StringType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<List<String>> ex = new Exchanger<>();

        new Thread(new StringStatisticsThread(ex)).start();
        new Thread(new StringTypeRecognizerThread(ex)).start();
    }

    static class StringStatisticsThread implements Runnable {
        Exchanger<List<String>> exchanger;

        StringStatisticsThread(Exchanger<List<String>> ex) {
            this.exchanger = ex;
        }

        public void run() {
            try {
                IOManager ioManager = IOManager.getInstance();
                ioManager.printLine("Enter number of lines");

                int amount = ioManager.getPositiveInteger();

                ioManager.printLine("Enter your lines");
                List<String> lines = ioManager.getLines(amount);

                exchanger.exchange(lines);

                String longestLine = StringAnalyzer.getLongestLine(lines);
                ioManager.printLine("The longest line is: " + longestLine);

                List<String> types = exchanger.exchange(lines);

                for (int i = 0; i < types.size(); i++) {
                    ioManager.printLine("Line \"" + lines.get(i) + "\" has type: " + types.get(i));
                }

            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static class StringTypeRecognizerThread implements Runnable {
        Exchanger<List<String>> exchanger;

        StringTypeRecognizerThread(Exchanger<List<String>> ex) {
            this.exchanger = ex;
        }

        public void run() {
            try {
                List<String> strings = exchanger.exchange(new ArrayList<>());

                List<String> types = strings.stream()
                        .map(StringType::getInputType)
                        .map(StringType::toString)
                        .collect(Collectors.toList());

                exchanger.exchange(types);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
