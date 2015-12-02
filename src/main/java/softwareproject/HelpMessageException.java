package softwareproject;

public class HelpMessageException extends Exception {
    
    public HelpMessageException (String message) {
        super (message);
    }
    
    public HelpMessageException (Throwable cause) {
        super (cause);
    }
}