package softwareproject;

 /**
     * The HelpMessageException is used to throw the help message.
     *
     * @author CS 310 XXY Team
     * @version Fall 2015
     */
public class HelpMessageException extends RuntimeException {

    /**
    *Used to throw help message.
    */
    public HelpMessageException () {
        super ();
    }
    
    /**
    *Used to throw help message.
    * @param   message the help message to be thrown
    */
    public HelpMessageException (String message) {
        super (message);
    }
    
    /**
    *Used to throw help message. 
    * @param   message the help message to be thrown
    * @param   cause the cause of the exception
    */
    public HelpMessageException (String message, Throwable cause) {
        super (message, cause);
    }
    
    /**
    *Used to throw help message.
    * @param   cause the cause of the exception
    */
    public HelpMessageException (Throwable cause) {
        super (cause);
    }
}