package softwareproject;

import java.util.*;
import java.lang.*;

public class Arguments {
		
        private Object value;
        private String dataType;
        private String description; 
		private Object obj;
		
		public void Arguments() {
			value = "0";
			dataType = "String";
			description = "There isn't one";
			obj = new Object();
		}
        
        public void setValue(Object value){
                this.value = value;
        }
        public Object getValue(){
                return value;
        }
        public void setDataType(String dataType){
                this.dataType = dataType;
        }
        public String getDataType(){
                return dataType;
        }
        public void setDescription(String description){
                this.description = description;
        }
        public String getDescription(){
                return description;
        }	
}