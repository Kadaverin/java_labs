package concurrency;

import common.exceptions.EmptyInputException;
import common.utils.IOManager;
import lab1.StringAnalyzer;
import lab2.StringType;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Phaser;


public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();

        new Lab2Thread(phaser);
        new Lab1Thread(phaser);
    }

    public static class Lab1Thread implements Runnable {
        private Phaser phaser;

        Lab1Thread(Phaser phaser) {
            this.phaser = phaser;
            phaser.register();
            new Thread(this).start();
        }

        @Override
        public void run() {
            System.out.println("\nLab1 Phase " + phaser.getPhase());

            IOManager ioManager = IOManager.getInstance();

            ioManager.printLine("Enter number of lines");
            int amount = ioManager.getPositiveInteger();

            phaser.arriveAndAwaitAdvance();
            System.out.println("\nLab1 Phase " + phaser.getPhase());

            phaser.arriveAndAwaitAdvance();
            System.out.println("\nLab1 Phase " + phaser.getPhase());

            ioManager.printLine("Enter your lines");
            List<String> lines = ioManager.getLines(amount);

            String longestLine = StringAnalyzer.getLongestLine(lines);

            phaser.arriveAndDeregister();
            ioManager.printLine("\nLab1 Phase " + phaser.getPhase() + "\nThe longest line is: " + longestLine);
        }
    }

    public static class Lab2Thread implements Runnable {
        private Phaser phaser;

        Lab2Thread(Phaser phaser) {
            this.phaser = phaser;
            phaser.register();
            new Thread(this).start();
        }

        @Override
        public void run() {
            IOManager ioManager = IOManager.getInstance();

            System.out.println("\nLab2 Phase " + phaser.getPhase());

            phaser.arriveAndAwaitAdvance();
            System.out.println("\nLab2 Phase " + phaser.getPhase());

            String input = null;


            while (input == null || input.trim().isEmpty()) {
                try {
                    input = ioManager.getLine("Enter your string for type recognition");
                } catch (IOException | EmptyInputException err) {
                    ioManager.printLine("An error occurred: " + err.getMessage());
                }
            }

            phaser.arriveAndAwaitAdvance();
            System.out.println("\nLab2 Phase " + phaser.getPhase());

            String inputType = StringType.getInputType(input).toString();

            phaser.arriveAndAwaitAdvance();
            System.out.println("\nLab2 Phase " + phaser.getPhase());

            ioManager.printLine("Your input is: " + inputType);
            phaser.arriveAndDeregister();
        }
    }
}
