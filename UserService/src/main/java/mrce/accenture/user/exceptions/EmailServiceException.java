package mrce.accenture.user.exceptions;

public class EmailServiceException extends RuntimeException{
    public EmailServiceException(String message) {
        super(message);
    }
}
