package softwareproject;

import java.util.*;
import java.lang.*;

public class Argument {
    protected Object value;
    protected String description;
    protected Type type;
    protected String shorthand;
    protected boolean required;
    protected Object defaultValue;
    public enum Type {STRING, INT, BOOLEAN, FLOAT};
    
    public Argument() {
        value = "";
        description = "There isn't one";
        type = Type.STRING;
        shorthand = "";
        required = true;
        defaultValue = "";
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
        
    public void setDefault(Object defaultValue){
        this.defaultValue = defaultValue;
    }
    
    public Object getDefault(){
        return defaultValue;
    }
}
