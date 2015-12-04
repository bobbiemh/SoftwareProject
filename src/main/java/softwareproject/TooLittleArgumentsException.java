package softwareproject;

/**
     * The TooLittleArgumentsException is used to throw a message when there are too few arguments.
     *
     * @author CS 310 XXY Team
     * @version Fall 2015
     */
public class TooLittleArgumentsException extends RuntimeException {

    /**
    *Used to throw a message when there are too few arguments.
    */
    public TooLittleArgumentsException () {
        super ();
    }
    
    /**
    *Used to throw a message when there are too few arguments.    
    * @param   message the help message to be thrown
    */
    public TooLittleArgumentsException (String message) {
        super (message);
    }
    
    /**
    *Used to throw a message when there are too few arguments.    
    * @param   message the help message to be thrown
    * @param   cause the cause of the exception
    */
    public TooLittleArgumentsException (String message, Throwable cause) {
        super (message, cause);
    }
    
    /**
    *Used to throw a message when there are too few arguments.    
    * @param   cause the cause of the exception
    */
    public TooLittleArgumentsException (Throwable cause) {
        super (cause);
    }
}