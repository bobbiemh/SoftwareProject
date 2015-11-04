package softwareproject;

public class Positional extends Argument {
    
    public Positional(){
    }
    public <T> void setValue(T value){
        this.value = value;
    }
    public <T> T getValue(){
        if(type == Argument.Type.INT) return (T)(Integer)value;
        else if(type == Argument.Type.BOOLEAN) return (T)(Boolean)value;
        else if(type == Argument.Type.FLOAT) return (T)(Float)value;
        else return (T)(String)value;
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