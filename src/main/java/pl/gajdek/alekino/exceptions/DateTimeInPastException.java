package pl.gajdek.alekino.exceptions;

public class DateTimeInPastException extends RuntimeException {
    public DateTimeInPastException(String message) {
        super(message);
    }
}
