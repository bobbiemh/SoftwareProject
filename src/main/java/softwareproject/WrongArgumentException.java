package softwareproject;

public class WrongArgumentException extends RuntimeException {

    public WrongArgumentException () {
        super ();
    }
    
    public WrongArgumentException (String message) {
        super (message);
    }
    
    public WrongArgumentException (String message, Throwable cause) {
        super (message, cause);
    }
    
    public WrongArgumentException (Throwable cause) {
        super (cause);
    }
}