package softwareproject;

public class TooManyArgumentsException extends RuntimeException {

    public TooManyArgumentsException () {
        super ();
    }
    
    public TooManyArgumentsException (String message) {
        super (message);
    }
    
    public TooManyArgumentsException (String message, Throwable cause) {
        super (message, cause);
    }
    
    public TooManyArgumentsException (Throwable cause) {
        super (cause);
    }
}