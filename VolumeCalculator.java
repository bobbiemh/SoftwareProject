import softwareproject.*;

public class VolumeCalculator {
    
    public static void main(String[] args) {
        
        ParseArgs p = new ParseArgs();
        
        p.programInfo("volumeCalculator", "Calculate the volume of a box.");
        p.addPos("length", "the length of a box", Argument.Type.INT);
        p.addPos("width", "the width of a box", Argument.Type.INT);
        p.addPos("height", "the height of a box", Argument.Type.INT);
        
        p.parse(args);
        
        int length = p.getValue("length");
        int width = p.getValue("width");
        int height = p.getValue("height");
        
        int volumeOfBox = length * width * height;
                
        System.out.println(volumeOfBox + "");
    }
}
