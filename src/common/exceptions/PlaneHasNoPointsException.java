package common.exceptions;

public class PlaneHasNoPointsException extends RuntimeException {
    public PlaneHasNoPointsException(String errorMessage) {
        super(errorMessage);
    }
}