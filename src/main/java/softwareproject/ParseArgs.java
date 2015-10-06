
package softwareproject;

import java.util.*;

public class ParseArgs{
	
	private Map<String, String> map;
	private boolean helpmessage;
	
	public ParseArgs() {
	        map = new HashMap<String, String>();
			helpmessage = false;
	}
	
	public void addArgs(String userInput)
	{
		map.put(userInput, "");
	}
	
	public void checkSizeofArgs(String[] args){
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
			System.out.println("usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: unrecognized arguments: " + temp);
		}//will later make it return all args that we don't need
	}
	
	public void parse(String[] args) //edit so we call a function to convert to what we need (starts string) convert to int, float, etc
	{
		if(args.length == 1 && args[0] == "-h")
		{
			System.out.println("\nusage: java VolumeCalculator length width height \nCalculate the volume of a box. \npositional arguments: \n\tlength the length of the box\n\twidth the width of the box\n\theight the height of the box");
			helpmessage = true;
		}
		else if(args.length < 3 || args.length > 3)
		{
			checkSizeofArgs(args);
		}
		else{
			try{
				Iterator it = map.keySet().iterator();
				for(int i = 2; it.hasNext(); i--)
				{
					String key = it.next().toString();
					String temp = args[i];
					map.put(key, temp);
				}
			}
			catch(NumberFormatException e){}
		}
	}
	
	public boolean doesHelpWork(){
		if(helpmessage == true){
			return helpmessage;
		}
		else
			return false;
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
