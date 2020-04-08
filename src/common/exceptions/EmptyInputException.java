package common.exceptions;

public class EmptyInputException extends RuntimeException {
    public EmptyInputException(String errorMessage) {
        super(errorMessage);
    }
}
