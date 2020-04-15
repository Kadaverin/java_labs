package common.exceptions;

public class PlaneHasNoPointsException extends IllegalArgumentException {
    public PlaneHasNoPointsException(String errorMessage) {
        super(errorMessage);
    }
}