
package softwareproject;

import java.util.*;
import java.lang.*;


public class ParseArgs{
	private Map<String, Argument> map;
	private List<String> keys;
	private boolean messageTrue;
	private boolean illegalArgs;
	private String programName;
	private String programDescription;
	private String helpMessage;
	public enum Datatype {STRING, INT, BOOLEAN, FLOAT, NONE};
	
	public ParseArgs() {
	        map = new HashMap<String, Argument>();
			keys = new ArrayList<String>();
			
			messageTrue = false;
			illegalArgs = false;
			helpMessage = "usage: java ";
	}
	
	public void addArgs(String userInput, String Description, Datatype datatype)
	{
		Argument temp = new Argument();
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
		        String exceptionMessage = "Error: the following Argument are required: ";
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
			        
			                for(int i = args.length-1; i < getNumberOfKeys(); i++)
			                {
				                exceptionMessage = exceptionMessage +" "+ getKey(i);
			                }
		                        throw new IllegalArgumentException(exceptionMessage);
			        
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
			        exceptionMessage = exceptionMessage + programName + ".java: error: unrecognized Argument: "+temp;
				throw new IllegalArgumentException(exceptionMessage);
			}
			putToMap(args);
		}
	}
	
	private void putToMap(String[] args){
		int i = 0;
		for(i = 0; i < getNumberOfKeys() && !args[0].equals("-h"); i++){
			
			String key = keys.get(i);			
			Argument temp = new Argument();
			
			temp = getArgs(key);
			Datatype datatype = temp.getDataType();
			
			int x;
			boolean b;
			float f;
			String s;
			
			if(datatype == Datatype.INT){
				try{
					x = convertToInt(args[i]);
					temp.setValue(x);
				} catch(NumberFormatException e){
					throw new NumberFormatException("usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: argument width: invalid int value: " + args[i]);
				}
			}
			else if(datatype == Datatype.BOOLEAN){
				try{
					b = convertToBoolean(args[i]);
					temp.setValue(b);
				} catch(NumberFormatException e){
					throw new NumberFormatException("usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: argument width: invalid boolean value: " + args[i]);
				}
			}
			else if(datatype == Datatype.FLOAT){
				try{
					f = convertToFloat(args[i]);
					temp.setValue(f);
				} catch(NumberFormatException e){
					throw new NumberFormatException("usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: argument width: invalid float value: " + args[i]);
				}
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
	
	private String convertToString(String args){
		return args;
	}
	
	private int convertToInt(String args){
		int num = Integer.parseInt(args);
		return num;
	}
	
	private boolean convertToBoolean(String args){
		boolean b = Boolean.parseBoolean(args);
		return b;
	}
	
	private float convertToFloat(String args){
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
	
	public Argument getArgs(String key)
	{
		Argument temp = new Argument();
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
			Argument temp = new Argument();
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
