package httpserver.exceptions;

public class EmptyHttpStatusCodeException extends RuntimeException {
    public EmptyHttpStatusCodeException(String message) {
        super(message);
    }
}
