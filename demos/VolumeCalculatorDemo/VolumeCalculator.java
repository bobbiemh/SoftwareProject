import softwareproject.*;
/*import java.util.*;
import java.lang.*;*/

public class VolumeCalculator {
    
    public static void main(String[] args) {
        
        ParseArgs p = new ParseArgs();
        
        p.programInfo("volumeCalculator", "Calculate the volume of a box.");
        p.addPos("length", "the length of a box", Argument.Type.INT);
        p.addPos("width", "the width of a box", Argument.Type.INT);
        p.addPos("height", "the height of a box", Argument.Type.INT);
        p.addOpt("digit", "-d", 4, Argument.Type.INT, false);
        
        p.parse(args);
        int volumeOfBox = ((int)p.getValue("length") * (int)p.getValue("width") * (int)p.getValue("height"));
        
        
        
        System.out.println(volumeOfBox + "");
    }
}