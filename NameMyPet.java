import softwareproject.*;
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class NameMyPet {
    
    public static void main(String[] args) {
        
         ParseArgs p = new ParseArgs();
        
        p.programInfo("nameMyPet", "Generates pet names.");
        p.addPos("nameDog", "the name of the dog", Argument.Type.STRING);
        p.addPos("nameCat", "the name of the cat", Argument.Type.STRING);
        p.addPos("nameJBird", "the name of the jbird", Argument.Type.STRING);
        
        p.addOpt("color", "the color of the pet", Argument.Type.STRING);
        p.setShortHand("color", "c");
        p.addOpt("size", "the size of the pet", Argument.Type.INT);
        p.setShortHand("size", "s");
        
        p.parse(args);
        
        try{
        
        String file = "C:\\Users\\Shay\\Documents\\GitHub\\SoftwareProject\\NameMyPet.xml";
        p.readXML(file);
        
        } catch(IOException | SAXException | ParserConfigurationException e){
                throw new IllegalArgumentException(Integer.toString(p.numberOfPositionalKeys()));
            }
        
        if(p.getValue("nameDog").equals("Bob")) {
            System.out.println("That's a terrible name!");
        } else System.out.print("That's a great name!");
    }
    
}
