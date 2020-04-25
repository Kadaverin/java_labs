package concurrency;

import common.exceptions.EmptyInputException;
import common.utils.IOManager;
import lab1.StringAnalyzer;
import lab2.StringType;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Semaphore;


public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        new Lab1Thread(sem);
        new Lab2Thread(sem);
    }

    public static abstract class SemaphoreThread implements Runnable {
        protected Semaphore sem;
        protected String name;

        public SemaphoreThread(Semaphore sem, String name) {
            this.sem = sem;
            this.name = name;
            new Thread(this).start();
        }

        public void doAcquire() {
            try {
                sem.acquire();
                System.out.println("Thread " + name + " acquired semaphore");
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }

        public void doRelease() {
            try {
                System.out.println("Thread " + name + " released semaphore \n");
                sem.release();
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }

        abstract public void run();
    }

    public static class Lab1Thread extends SemaphoreThread {
        Lab1Thread(Semaphore sem) {
            super(sem, "Lab1");
        }

        @Override
        public void run() {
            doAcquire();
            IOManager ioManager = IOManager.getInstance();
            ioManager.printLine("Enter number of lines");
            int amount = ioManager.getPositiveInteger();
            doRelease();

            doAcquire();
            ioManager.printLine("Enter your lines");
            List<String> lines = ioManager.getLines(amount);
            String longestLine = StringAnalyzer.getLongestLine(lines);
            doRelease();

            doAcquire();
            ioManager.printLine("The longest line is: " + longestLine);
            doRelease();
        }
    }

    public static class Lab2Thread extends SemaphoreThread {
        Lab2Thread(Semaphore sem) {
            super(sem, "Lab2");
        }

        @Override
        public void run() {
            doAcquire();
            IOManager ioManager = IOManager.getInstance();
            String input = null;

            while (input == null || input.trim().isEmpty()) {
                try {
                    input = ioManager.getLine("Enter your string for type recognition");
                } catch (IOException | EmptyInputException err) {
                    ioManager.printLine("An error occurred: " + err.getMessage());
                }
            }

            String inputType = StringType.getInputType(input).toString();
            doRelease();

            doAcquire();
            ioManager.printLine("Your input is: " + inputType);
            doRelease();
        }
    }
}
