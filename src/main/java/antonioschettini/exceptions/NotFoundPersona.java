package antonioschettini.exceptions;

public class NotFoundPersona extends RuntimeException {
    public NotFoundPersona(String message) {
        super(message);
    }
}
