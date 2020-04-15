package lab2;

import common.utils.IOManager;
import common.exceptions.EmptyInputException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        IOManager ioManager = IOManager.getInstance();

        String input = null;

        while (input == null || input.trim().isEmpty()) {
            try {
                input = ioManager.getLine("Enter your string");
            } catch (IOException | EmptyInputException err) {
                ioManager.printLine("An error occurred: " + err.getMessage());
            }
        }

        String inputType = StringType.getInputType(input).toString();

        ioManager.printLine("Your input is: " + inputType);
    }
}
