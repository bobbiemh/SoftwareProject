package softwareproject;

import java.util.*;

public class Arguments {
	
        private String value;
        private String dataType;
        private String description; 
		
		public void Arguments() {
			value = "0";
			dataType = "String";
			description = "There isn't one";
		}
        
        public void setValue(String value){
                this.value = value;
        }
        public String getValue(){
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