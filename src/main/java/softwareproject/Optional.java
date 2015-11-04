package softwareproject;

public class Optional extends Argument{
    
    private String shorthand;
    private boolean required;
    
    public Optional(){
        shorthand = "";
        required = true;
    }
    
    public void setShortHand(String shorthand){
        this.shorthand = shorthand;
    }
    
    public String getShortHand() {
        return shorthand;
    }
    
    public void setRequired(boolean required){
        this.required = required;
    }
    
    public boolean getRequired() {
        return required;
    }
}