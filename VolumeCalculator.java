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
        Argument a = p.getArg("length");//in parse create a getValue (calls getArg, returns an object)
        int length = a.getValue();
        a = p.getArg("width");
        int width = a.getValue();
        a = p.getArg("height");
        int height = a.getValue();
        int volumeOfBox = (length * width * height);
        //later int volumeOfBox = (p.getValue(key) * p.getValue(key) * p.getValue(key));
        
        
        
        System.out.println(volumeOfBox + "");
    }
}