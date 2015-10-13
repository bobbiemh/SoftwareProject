
package softwareproject;

import java.util.*;



public class ParseArgs{
	
	private Arguments a;
	private Map<String, Arguments> map;
	private List<String> keys;
	private boolean messageTrue;
	private boolean illegalArgs;
	private String programName;
	private String programDescription;
	public String helpMessage;
	
	public ParseArgs() {
			a = new Arguments();
	                map = new HashMap<String, Arguments>();
			keys = new ArrayList<String>();
			
			messageTrue = false;
			illegalArgs = false;
			helpMessage = "usage: java "+programName+"\n"+programDescription;
	}
	
	public void addArgs(String userInput, String Description)
	{
		keys.add(userInput);
		a.setDescription(Description);
		helpMessage = helpMessage +"\n"+userInput+" "+Description;
		map.put(userInput, a);
	}
	
	public void parse(String[] args) //edit so we call a function to convert to what we need (starts string) convert to int, float, etc
	{
		
		if(args.length == 1 && args[0] == "-h")
		{
			messageTrue = true;
			throw new IllegalArgumentException(helpMessage);
		}
		else
		{
			if(args.length < getNumberOfKeys() || args.length > getNumberOfKeys())
				illegalArgs = true;
			if(args.length == 0 && illegalArgs)
				throw new IllegalArgumentException("Error: the following arguments are required: length, width, height");
			else if(args.length == 1 && illegalArgs)
				throw new IllegalArgumentException("Error: the following arguments are required: width, height");
			else if(args.length == 2 && illegalArgs)
				throw new IllegalArgumentException("Error: the following argument are required : height");
			else if (args.length > getNumberOfKeys())
			{
				int i = args.length - 1;
				String temp = args[i];
				throw new IllegalArgumentException("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: unrecognized arguments:" + temp);
			}
			
			int i = 0;
					
			while(i <= getNumberOfKeys() - 1)
			{
				String key = keys.get(i);
				String value = args[i];
				Arguments temp = new Arguments();
				temp = getArgs(key);
				temp.setValue(args[i]);
				map.put(key, temp);
				i++;
			}
			while(i <= args.length && i > getNumberOfKeys())
			{
				String temp = args[args.length - 1];
			}
		}
	}
	
	public boolean doesHelpWork(){
		return messageTrue;
	}
	
	public boolean getIllegalArgs()
	{
		return illegalArgs;
	}
	
	public Arguments getArgs(String key)
	{
		a = map.get(key);
		return a;
	}
	
	public String getKey(int where)
	{
		String s = keys.get(where);
		return s;
	}
	
	public int getNumberOfArgs(String[] args){
		return args.length;
	}
	
	public int getNumberOfKeys(){
		return keys.size();
	}
	public void programInfo(String name, String description){
	        this.programName = name;
	        this.programDescription = description;
	}
}
