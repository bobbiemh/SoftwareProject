package softwareproject;

public class TooLittleArgumentsException extends RuntimeException {

    public TooLittleArgumentsException () {
        super ();
    }
    
    public TooLittleArgumentsException (String message) {
        super (message);
    }
    
    public TooLittleArgumentsException (String message, Throwable cause) {
        super (message, cause);
    }
    
    public TooLittleArgumentsException (Throwable cause) {
        super (cause);
    }
}