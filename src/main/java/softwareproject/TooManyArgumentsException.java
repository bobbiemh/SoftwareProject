package softwareproject;

/**
     * The TooManyArgumentsException is used to throw a message when there are too many arguments.
     *
     * @author CS 310 XXY Team
     * @version Fall 2015
     */
public class TooManyArgumentsException extends RuntimeException {

    /**
    *Used to throw a message when there are too many arguments.
    */
    public TooManyArgumentsException () {
        super ();
    }
    
    /**
    *Used to throw a message when there are too many arguments.    
    * @param   message the help message to be thrown
    */
    public TooManyArgumentsException (String message) {
        super (message);
    }
    
    /**
    *Used to throw a message when there are too many arguments.    
    * @param   message the help message to be thrown
    * @param   cause the cause of the exception
    */
    public TooManyArgumentsException (String message, Throwable cause) {
        super (message, cause);
    }
    
    /**
    *Used to throw a message when there are too many arguments.    
    * @param   cause the cause of the exception
    */
    public TooManyArgumentsException (Throwable cause) {
        super (cause);
    }
}