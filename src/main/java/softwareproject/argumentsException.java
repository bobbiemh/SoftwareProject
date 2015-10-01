package softwareproject;
// import ParseArgs.java;
import java.util.*;

public class argumentsException extends RuntimeException {
        public argumentsException(String msg){
                super(msg);
               //usage: java "+Thread.currentThread().getStackTrace()[1].getClassName()+" "+ParseArgs.printNeededArgs()
        }
}
