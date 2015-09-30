
package softwareproject;

import java.util.*;

public class argumentsException extends RuntimeException {
        public argumentsException(){
                super("the following arguments are required: "+ParseArgs.printMissingArgs());
               //usage: java "+Thread.currentThread().getStackTrace()[1].getClassName()+" "+ParseArgs.printNeededArgs()
        }
}
