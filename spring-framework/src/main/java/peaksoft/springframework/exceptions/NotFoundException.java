package peaksoft.springframework.exceptions;

/**
 * @author Beksultan
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
