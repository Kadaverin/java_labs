package common.utils;

import common.exceptions.EmptyInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IOManager {
    private BufferedReader inputReader;
    private static volatile IOManager instance;

    private IOManager() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }

        inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static IOManager getInstance() {
        if (instance == null) {
            synchronized (IOManager.class) {
                if (instance == null) {
                    instance = new IOManager();
                }
            }
        }

        return instance;
    }

    public String getLine(String promptMsg) throws EmptyInputException, IOException {
        String res;

        if (promptMsg != null) {
            System.out.println(promptMsg);
        }

        res = inputReader.readLine();

        if (res.trim().isEmpty()) {
            throw new EmptyInputException("You entered a blank line");
        }

        return res;
    }

    public String getLine() throws EmptyInputException, IOException {
        return getLine(null);
    }

    public String getLineSafely() {
        try {
            return getLine(null);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            return null;
        }
    }

    public List<String> getLines(int amount) {
        List<String> res = new ArrayList<>();

        while (amount != 0) {
            try {
                String str = getLine();
                res.add(str);
                amount--;
            } catch (IOException ioException) {
                System.err.println("An IOException was caught :" + ioException.getMessage());
            } catch (EmptyInputException emptyInputException) {
                System.err.println(emptyInputException.getMessage());
            }
        }

        return res;
    }

    public int getPositiveInteger() {
        int inputInt = -1;

        while (inputInt < 0) {
            try {
                inputInt = Integer.parseInt(getLine("Please, enter the positive integer: "));
            } catch (NumberFormatException nfe) {
                System.err.println("Your input is not an integer");
            } catch (IOException e) {
                System.err.println("An IOException was caught :" + e.getMessage());
            } catch (EmptyInputException emptyInputException) {
                System.err.println(emptyInputException.getMessage());
            }
        }

        return inputInt;
    }

    public void printLineWithLength(String line) {
        System.out.println(String.format(
                "\"%s\", length: %d",
                line,
                line.length()
                )
        );
    }

    public void printLine(String line) {
        System.out.println(line);
    }

    public void printLines(List<String> lines, boolean showLength) {
        Consumer<String> methodToCall = showLength
                ? this::printLineWithLength
                : this::printLine;

        lines.forEach(line -> methodToCall.accept(line));
    }

}
