
package softwareproject;

import java.util.*;

public class ParseArgs {
    	//need a function to see in the args
	//separate
	//print out error if there's not enough or to many
	
	Map<String, Integer> map = new HashMap<String, Integer>();
	
	public void addArgs(String userInput)
	{
		map.put(userInput, 0);
	}
	public void parse(String[] args)
	{
		int[] Args = new int[args.length];
		//Iterator it = map.entrySet().iterator();
		for(int i = 0; i < args.length; i++)
		{
			/*Map.Entry pair = (Map.Entry)it.next();
			String[] strings = map.keySet().toArray(new String[map.size()]);
			Args[i] = Integer.parseInt(args[i]);
			map.put(strings[i], Args[i]);
			
			System.out.println(pair.getKey() + " = " + pair.getValue());*/
		}
		
	}/*
	public int getArgs(String whatUserWants)
	{
		return 0;
	}*/
	
	public int printArgs()
	{
		return 0;
	}
}
