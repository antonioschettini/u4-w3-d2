package antonioschettini.exceptions;

public class NotFoundLocation extends RuntimeException {
    public NotFoundLocation(String message) {
        super(message);
    }
}
