package softwareproject;

    /**
     * The Positional class extends the Argument class. 
     *
     * @author CS XXY Team
     * @version Fall 2015
     */
public class Positional extends Argument {
    
    /**
     * Creates a new Positional argument. 
     *
     */
    public Positional(){
    }
    public <T> void setValue(T value){
        this.value = value;
    }
    public Object getValue(){
        /*if(type == Argument.Type.INT) return (T)(Integer)value;
        else if(type == Argument.Type.BOOLEAN) return (T)(Boolean)value;
        else if(type == Argument.Type.FLOAT) return (T)(Float)value;
        else return (T)(String)value;*/
        return value;
    }
    public void setType(Type type){
        this.type = type;
    }
    public Type getType(){
        return type;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }  
    public boolean getRequired(){
        return true;
    }
}