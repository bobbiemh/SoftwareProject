
package softwareproject;

import java.util.*;

/*public class Argument{
        private String value;
        private String dataType;
        
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
}*/

public class ParseArgs{
	
	private Map<String, String> map;
	private List<String> keys;
	private boolean helpmessage;
	
	public ParseArgs() {
	        map = new HashMap<String, String>();
			keys = new ArrayList<String>();
			//Map<String, Argument> map;
			helpmessage = false;
	}
	
	public void addArgs(String userInput)
	{
		map.put(userInput, "");
		keys.add(userInput);
	     /*   arg = new Argument<value, type>();
	        arg.setType(type);
		map.put(argName, arg);*/
	}
	
	public void parse(String[] args) //edit so we call a function to convert to what we need (starts string) convert to int, float, etc
	{
		
		if(args.length == 1 && args[0] == "-h")
		{
			System.out.println("\nusage: java VolumeCalculator length width height \nCalculate the volume of a box. \npositional arguments: \n\tlength the length of the box\n\twidth the width of the box\n\theight the height of the box");
			helpmessage = true;
		}
		else if(args.length < getNumberOfKeys() || args.length > getNumberOfKeys())
		{
			if(args.length == 0)
				throw new IllegalArgumentException("Error: the following arguements are required: length, width, height");
			else if(args.length == 1)
				throw new IllegalArgumentException("Error: the following arguements are required: width, height");
			else if(args.length == 2)
				throw new IllegalArgumentException("Error: the following argeument are required : height");
			else if (args.length > 3)
			{
				int i = args.length - 1;
				String temp = args[i];
				throw new IllegalArgumentException("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: unrecognized arguments:" + temp);
			}
		}
		int i = 0;
				
		while(i <= getNumberOfKeys() - 1)
		{
			String key = keys.get(i);
			String value = args[i];
			map.put(key, value);
			i++;
		}
		if(args.length > getNumberOfKeys())
		{
			String temp = args[args.length - 1];
		}
	}
	
	public boolean doesHelpWork(){
		return helpmessage;
	}
	
	public String getArgs(String key)
	{
		String s = map.get(key);
		return s;
	}
	
	public int getNumberOfArgs(){
		return map.size();
	}
	
	public int getNumberOfKeys(){
		return map.size();
	}
	
	public String[] printArgs()
	{
		String[] keyAndValue = new String[3];
		int i = 0;
		for(String name: map.keySet())
		{
			String key = name.toString();
			String value = map.get(key);
			keyAndValue[i] = "\nKey: " + key + "\nValue: " + value;
			i++;
		}
		return keyAndValue;
	}

}
