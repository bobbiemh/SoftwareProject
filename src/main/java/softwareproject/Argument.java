package softwareproject;

import java.util.*;
import java.lang.*;

public class Argument {
		
        private Object value;
        private String description;
		private ParseArgs.Datatype dataType;
		
		public void Argument() {
			value = "0";
			description = "There isn't one";
			dataType = ParseArgs.Datatype.NONE;
		}
        
        public void setValue(Object value){
                this.value = value;
        }
        public Object getValue(){
                return value;
        }
        public void setDataType(ParseArgs.Datatype dataType){
                this.dataType = dataType;
        }
        public ParseArgs.Datatype getDataType(){
                return dataType;
        }
        public void setDescription(String description){
                this.description = description;
        }
        public String getDescription(){
                return description;
        }	
}