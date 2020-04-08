package lab2;

import lab1.IOManager;

public class Main {
    public static void main(String[] args) {
        StringRecognizer recognizer = StringRecognizer.getInstance();

        String input = IOManager.getLine("Enter your string");
        String inputType = recognizer.getInputType(input).orElse("Unknown type");

        IOManager.printLine("Your input is: " + inputType);
    }
}
