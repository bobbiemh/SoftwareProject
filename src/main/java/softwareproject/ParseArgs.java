
package softwareproject;

import java.util.*;
import java.lang.*;


public class ParseArgs{
	private Map<String, Arguments> map;
	private List<String> keys;
	private boolean messageTrue;
	private boolean illegalArgs;
	private String programName;
	private String programDescription;
	private String helpMessage;
	
	public ParseArgs() {
	        map = new HashMap<String, Arguments>();
			keys = new ArrayList<String>();
			
			messageTrue = false;
			illegalArgs = false;
			helpMessage = "usage: java ";
	}
	
	public void addArgs(String userInput, String Description, String datatype)
	{
		Arguments temp = new Arguments();
		keys.add(userInput);
		temp.setDescription(Description);
		temp.setDataType(datatype);
		map.put(userInput, temp);
	}
	
	public void parse(String[] args)
	{
		if(args[0].equals("-h"))
		{
			messageTrue = true;
			throw new IllegalArgumentException(helpMessage);
		}
		else
		{
		        String exceptionMessage = "Error: the following arguments are required: ";
			if(args.length < getNumberOfKeys() || args.length > getNumberOfKeys())
				illegalArgs = true;
			if(args.length == 0 && illegalArgs){
			        for(int i = 0; i < getNumberOfKeys(); i++)
			        {
				        exceptionMessage = exceptionMessage +" "+ getKey(i);
			        }
			        throw new IllegalArgumentException(exceptionMessage);
		        }
			else if(args.length < getNumberOfKeys() && illegalArgs)
			{
			        {
			                for(int i = args.length-1; i < getNumberOfKeys(); i++)
			                {
				                exceptionMessage = exceptionMessage +" "+ getKey(i);
			                }
		                        throw new IllegalArgumentException(exceptionMessage);
			        }
		        }
			else if (args.length > getNumberOfKeys())
			{
				int a = args.length - 1;
				String temp = args[a];
				exceptionMessage = "usage: java "+programName;
				for(int i = 0; i < getNumberOfKeys(); i++)
			        {
				        exceptionMessage = exceptionMessage +" "+ getKey(i);
			        }
			        exceptionMessage = exceptionMessage + programName + ".java: error: unrecognized arguments: "+temp;
				throw new IllegalArgumentException(exceptionMessage);
			}
			
			putToMap(args);
		}
	}
	
	public void putToMap(String[] args){
		int i = 0;
		for(i = 0; i < getNumberOfKeys(); i++){
			
			String key = keys.get(i);			
			Arguments temp = new Arguments();
			
			temp = getArgs(key);
			String datatype = temp.getDataType();
			
			int x;
			boolean b;
			float f;
			String s;
			
			if(datatype == "int"){
				x = convertToInt(args[i]);
				temp.setValue(x);
			}
			else if(datatype == "boolean"){
				b = convertToBoolean(args[i]);
				temp.setValue(b);
			}
			else if(datatype == "float"){
				f = convertToFloat(args[i]);
				temp.setValue(f);
			}
			else{
				s = convertToString(args[i]);
				temp.setValue(s);
			}
			map.put(key, temp);
		}
		
		while(i <= args.length && i > getNumberOfKeys())
		{
			String str = args[args.length - 1];
		}
	}
	
	public String convertToString(String args){
		return args;
	}
	
	public int convertToInt(String args){
		int num = Integer.parseInt(args);
		return num;
	}
	
	public boolean convertToBoolean(String args){
		boolean b = Boolean.parseBoolean(args);
		return b;
	}
	
	public float convertToFloat(String args){
		float f = Float.valueOf(args);
		return f;
	}
	
	public String getHelpMessage() {
		return helpMessage;
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
		Arguments temp = new Arguments();
		temp = map.get(key);
		return temp;
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
			String key = "";
			Arguments temp = new Arguments();
			String[] keyDescription = new String[getNumberOfKeys()];
			for(int i = 0; i < getNumberOfKeys(); i++)
			{
				key = key + " " + getKey(i);
				temp = getArgs(getKey(i));
				keyDescription[i] = temp.getDescription();
			}
	        this.programName = name;
	        this.programDescription = description;			
			helpMessage = helpMessage + name + key + "\n"+description;
			
			helpMessage = helpMessage + "\nPositional arguments:";
			
			for(int i = 0; i < getNumberOfKeys(); i++)
			{
				helpMessage = helpMessage + "\n" + getKey(i) + " " + keyDescription[i];
			}
	}
}
