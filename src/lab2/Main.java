package lab2;

import lab1.IOManager;
import lab1.exceptions.EmptyInputException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringRecognizer recognizer = StringRecognizer.getInstance();

        String input = null;

        while(input == null || input.trim().isEmpty()) {
            try {
                input = IOManager.getLine("Enter your string");
            } catch (IOException | EmptyInputException err) {
                IOManager.printLine("An error occurred: " + err.getMessage());
            }
        }

        String inputType = recognizer.getInputType(input).orElse("Unknown type");

        IOManager.printLine("Your input is: " + inputType);
    }
}
