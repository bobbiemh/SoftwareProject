package softwareproject;

/**
     * The WrongArgumentException is used to throw a message when the wrong type of argument is given.
     *
     * @author CS 310 XXY Team
     * @version Fall 2015
     */
public class WrongArgumentException extends RuntimeException {

    /**
    *Used to throw a message when the wrong type of argument is given.
    */
    public WrongArgumentException () {
        super ();
    }
    
    /**
    *Used to throw a message when the wrong type of argument is given.
    */
    public WrongArgumentException (String message) {
        super (message);
    }
    
    /**
    *Used to throw a message when the wrong type of argument is given.
    */
    public WrongArgumentException (String message, Throwable cause) {
        super (message, cause);
    }
    
    /**
    *Used to throw a message when the wrong type of argument is given.
    */
    public WrongArgumentException (Throwable cause) {
        super (cause);
    }
}