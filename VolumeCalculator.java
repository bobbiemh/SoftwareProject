import softwareproject.*;
/*import java.util.*;
import java.lang.*;*/

public class VolumeCalculator {
    
    public static void main(String[] args) {
        
        ParseArgs p = new ParseArgs();
        
        p.programInfo("volumeCalculator", "Calculate the volume of a box.");
        p.addPos("length", "the length of a box", "int");
        p.addPos("width", "the width of a box", "int");
        p.addPos("height", "the height of a box", "int");
        p.addOpt("digit", "int", 4, false);
        
        p.parse(args);
        int volumeOfBox = (p.getArg("length") * p.getArg("width") * p.getArg("height"));
        
        system.out.println(volumeOfBox + "");
    }
}