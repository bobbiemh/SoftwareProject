
package softwareproject;

import java.util.*;

public class ParseArgs{
    	//need a function to see in the args
	//separate
	//print out error if there's not enough or to many
	
	private Map<String, Integer> map;
	private int numberOfArgs;
	private int numberOfKeys;
	private int helpmessage;
	
	public ParseArgs() {
	        map = new HashMap<String, Integer>();
			numberOfArgs = 0;
			numberOfKeys = 0;
			helpmessage = 0;
	}
	
	public void addArgs(String userInput)
	{
		map.put(userInput, 0);
		numberOfKeys++;
	}
	
	public void checkSizeofArgs(String[] args){
		if(args.length == 0)
			throw new IllegalArgumentException("Error: the following arguements are required: length, width, height");
		else if(args.length == 1)
			throw new IllegalArgumentException("Error: the following arguements are required: width, height");
		else if(args.length == 2)
			throw new IllegalArgumentException("Error: the following argeument are required : height");
		//will later make it get a key and value and return that
		else if (args.length > 3)
		{
			int i = args.length - 1;
			String temp = args[i];
			System.out.println("usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: unrecognized arguments: " + temp);
		}//will later make it return all args that we don't need
	}
	
	public void parse(String[] args) //need to edit to look for illegal arguements
	{
		if(args.length == 1 && args[0] == "-h")
		{
			System.out.println("\nusage: java VolumeCalculator length width height \nCalculate the volume of a box. \npositional arguments: \n\tlength the length of the box\n\twidth the width of the box\n\theight the height of the box");
			helpmessage++;
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
					int temp = Integer.parseInt(args[i]);
					map.put(key, temp);
				}
			}
			catch(NumberFormatException e){}
		}
	}
	
	public int doesHelpWork(){
		return helpmessage;
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
