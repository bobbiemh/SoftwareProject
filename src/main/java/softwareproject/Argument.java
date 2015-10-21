package softwareproject;

import java.util.*;
import java.lang.*;

public class Argument {
        
        private Object value;
        private String description;
        private Datatype dataType;        
        public enum Datatype {STRING, INT, BOOLEAN, FLOAT};
        
        public void Argument() {
            value = "0";
            description = "There isn't one";
            dataType = Datatype.STRING;
        }
        
        public <T> void setValue(T value){
                this.value = value;
        }
        public <T> T getValue(){
            if(dataType == Datatype.INT) return (T)(Integer)value;
            else if(dataType == Datatype.BOOLEAN) return (T)(Boolean)value;
            else if(dataType == Datatype.FLOAT) return (T)(Float)value;
            else return (T)(String)value;
        }
        public void setDataType(Datatype dataType){
                this.dataType = dataType;
        }
        public Datatype getDataType(){
                return dataType;
        }
        public void setDescription(String description){
                this.description = description;
        }
        public String getDescription(){
                return description;
        }   
}