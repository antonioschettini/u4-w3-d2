package antonioschettini.exceptions;

public class NotFoundPartecipazione extends RuntimeException {
    public NotFoundPartecipazione(String message) {
        super(message);
    }
}
