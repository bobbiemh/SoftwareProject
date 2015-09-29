
package softwareproject;

import java.util.*;

public class ParseArgs{
    	//need a function to see in the args
	//separate
	//print out error if there's not enough or to many
	
	public Map<String, Integer> map = new HashMap<String, Integer>();
	
	public void addArgs(String userInput)
	{
		map.put(userInput, 0);
	}
	public String parse(String[] args)//need to edit to look for illegal arguements
	{
		String s = "";
		if(args.length == 3)
		{
			try{
				Iterator it = map.keySet().iterator();
				for(int i = 0; it.hasNext(); i++)
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
				s = "Error: the following arguements are required: length, width, height";
			else if(args.length == 1)
				s = "Error: the following arguements are required: width, height";
			else
				s = "Error: the following argeument are required : height";
		}//will later make it get a key and value and return that
		else if (args.length > 3)
		{
			int i = args.length - 1;
			String temp = args[i];
			s = "Error: unrecongnized arguements: " + temp;
		}//will later make it return all args that we don't need
		return s;
	}
	public int getArgs(String key)
	{
		int i = map.get(key);
		return i;
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