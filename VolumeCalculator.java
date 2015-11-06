import softwareproject.*;
import java.util.*;
import java.lang.*;

public class VolumeCalculator {
    
    public static void main(String[] args) {
        
        ParseArgs p = new ParseArgs();
        
        p.programInfo("volumeCalculator", "Calculate the volume of a box.");
        p.addPos("length", "the length of a box", Argument.Type.INT);
        p.addPos("width", "the width of a box", Argument.Type.INT);
        p.addPos("height", "the height of a box", Argument.Type.INT);
        p.addPos("boolTest", "", Argument.Type.BOOLEAN);
        p.addOpt("isLoud", false, Argument.Type.BOOLEAN, false);
        p.setShortHand("isLoud", "l");
        
        
        p.parse(args);
        
        int length = p.getValue("length");
        int width = p.getValue("width");
        int height = p.getValue("height");
        
        int volumeOfBox = length * width * height;
        
        if(p.getValue("isLoud") == true)
        {
            System.out.println("Wrong kind of volume, man\n");
        }
        
        System.out.println(volumeOfBox + "");
        System.out.println("boolTest = " + p.getValue("boolTest"));
    }
}
