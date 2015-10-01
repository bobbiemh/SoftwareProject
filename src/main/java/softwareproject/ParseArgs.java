
package softwareproject;

import java.util.*;

public class ParseArgs{
    	//need a function to see in the args
	//separate
	//print out error if there's not enough or to many
	
	private Map<String, Integer> map;
	private int numberOfArgs;
	private int numberOfKeys;
	
	public ParseArgs() {
	        map = new HashMap<String, Integer>();
			numberOfArgs = 0;
			numberOfKeys = 0;
	}
	
	public void addArgs(String userInput)
	{
		map.put(userInput, 0);
		numberOfKeys++;
	}
	
	public String parse(String[] args) throws argumentsException//need to edit to look for illegal arguements
	{
	        String s = "";
		if(args.length == 3)
		{
			try{
				Iterator it = map.keySet().iterator();
				for(int i = 2; it.hasNext(); i--)
				{
					String key = it.next().toString();
					int temp = Integer.parseInt(args[i]);
					map.put(key, temp);
				}
			}
			catch(IndexOutOfBoundsException ex){}	
		}
		else if (args.length < 3)
		{
			if(args.length == 0)
				throw new argumentsException("Error: the following arguements are required: length, width, height");
			else if(args.length == 1)
				throw new argumentsException("Error: the following arguements are required: width, height");
			else
				throw new argumentsException("Error: the following argeument are required : height");
		}//will later make it get a key and value and return that
		else if (args.length > 3)
		{
			int i = args.length - 1;
			String temp = args[i];
			throw new argumentsException("Error: unrecongnized arguements: " + temp);
		}//will later make it return all args that we don't need
		return s;
	}
	public int getArgs(String key)
	{
		int i = map.get(key);
		return i;
	}
	
	public int getNumberOfArgs(String[] args){
		numberOfArgs = map.size();
		return numberOfArgs;
	}
	
	public int getNumberOfKeys(){
		return numberOfKeys;
	}
	
	public String[] printArgs()
	{
		String[] keyAndValue = new String[3];
		int i = 0;
		for(String name: map.keySet())
		{
			String key = name.toString();
			int value = map.get(key);
			keyAndValue[i] = "\nKey: " + key + "\nValue: " + value;
			i++;
		}
		return keyAndValue;
	}

}
