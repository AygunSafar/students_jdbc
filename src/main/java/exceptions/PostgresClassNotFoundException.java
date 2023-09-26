package exceptions;

public class PostgresClassNotFoundException extends RuntimeException{
    public PostgresClassNotFoundException(String message) {
        super(message);
    }
}
