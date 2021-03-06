package softwareproject;

import java.util.*;
import java.lang.*;

    /**
     * The Argument class sets and retrieves an object's value, description, type, 
     * shorthand, and specifies whether it is required or not. It also includes an 
     * enum Type that specifies whether the argument is a string, int, boolean, or 
     * float.
     *
     * @author CS 310 XXY Team
     * @version Fall 2015
     */
public class Argument {
    protected Object value;
    protected String description;
    protected Type type;
    protected String shorthand;
    protected boolean required;
    public enum Type {STRING, INT, BOOLEAN, FLOAT};
    
    /**
     * Creates a new Argument. 
     *
     */
    public Argument() {
        value = "";
        description = "";
        type = Type.STRING; 
        shorthand = "";
        required = true;
    }
    
    /**
     * Sets the argument to the specified value. 
     *
     * @param  value the value to be set
     */
    public void setValue(Object value){
        this.value = value;
    }
    
    /**
     * Gets the value of the argument. 
     *
     * @return the value of the argument
     */
    public <T> T getValue(){
        if(type == Argument.Type.INT) return (T)(Integer.valueOf((String)value));
        else if(type == Argument.Type.BOOLEAN) return (T)(Boolean.valueOf((String)value));
        else if(type == Argument.Type.FLOAT) return (T)(Float.valueOf((String)value));
        else if(type == Argument.Type.STRING) return (T)String.valueOf(value);
        return (T)value;
    }
    
    /**
     * Sets the type to the specified enum. 
     *
     * @param  type enum that tells the program the datatype of the argument
     */
    public void setType(Type type){
        this.type = type;
    }
    
    /**
     * Returns the type of the argument. 
     *
     * @return type the Type of the argument 
     */
    public Type getType(){
        return type;
    }
    
    /**
     * Sets the description of the argument. 
     *
     * @param  description  the description of the argument
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
     * Returns the description of the argument. 
     *
     * @return the description of the argument
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets the shorthand of the argument. 
     *
     * @param  shorthand the shorthand of the argument to be set
     */    
    public void setShortHand(String shorthand){
        this.shorthand = shorthand;
    }
    
    /**
     * Returns the shorthand of the argument.
     *
     * @return the shorthand of the argument
     */
    public String getShortHand() {
        return shorthand;
    }
    
    /**
     * Sets the default value of the argument if it has not already been set.
     *
     * @param  defaultValue the default value of the argument
     */    
    public void setDefault(Object defaultValue){
        value = defaultValue;
    }
}
