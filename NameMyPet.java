import softwareproject.*;

public class NameMyPet {
    
    public static void main(String[] args) {
        
         ParseArgs p = new ParseArgs();
        
        p.programInfo("nameMyPet", "Generates pet names.");
        p.addPos("nameDog", "the name of the dog", Argument.Type.STRING);
        p.addPos("nameCat", "the name of the cat", Argument.Type.STRING);
        p.addPos("nameJBird", "the name of the jbird", Argument.Type.STRING);
        
        p.addOpt("color", "the color of the pet", Argument.Type.STRING);
        p.addOpt("size", "the size of the pet", Argument.Type.STRING);
        
        p.parse(args);
        
        if(p.getValue("nameDog").equals("Bob")) {
            System.out.println("That's a terrible name!");
        } else System.out.print("That's a great name!");
    }
}
