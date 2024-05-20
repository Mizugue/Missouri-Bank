package exceptions;



public class PasswordError extends RuntimeException {

    public PasswordError(String message) {
        super(message);
    }

}
