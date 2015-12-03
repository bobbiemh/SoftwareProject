package softwareproject;

public class HelpMessageException extends RuntimeException {

    public HelpMessageException () {
        super ();
    }
    
    public HelpMessageException (String message) {
        super (message);
    }
    
    public HelpMessageException (String message, Throwable cause) {
        super (message, cause);
    }
    
    public HelpMessageException (Throwable cause) {
        super (cause);
    }
}