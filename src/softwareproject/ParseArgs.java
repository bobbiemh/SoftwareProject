
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
	public void parse(String[] args)
	{
		try{
			int[] Args = new int[args.length];
			Iterator it = map.keySet().iterator();
			for(int i = 0; it.hasNext(); i++)
			{
				String key = it.next().toString();
				int temp = Integer.parseInt(args[i]);
				map.put(key, temp);
			}
			for(String name: map.keySet())
			{
				String key = name.toString();
				int value = map.get(key);
				System.out.println("\nKey: " + key + "\nValue: " + value);
			}
		}
		catch(IndexOutOfBoundsException ex){}		
	}
	public int getArgs(String key)
	{
		int i = map.get(key);
		return i;
	}
	
	public int printArgs()
	{
		return 0;
	}
}
