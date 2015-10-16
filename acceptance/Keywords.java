import softwareproject.*;
import java.util.*;
import java.lang.*;

public class Keywords {
	private ParseArgs p;
	int lengthOfArgs;
	
	public void StartVolumeCalculatorWithArguments(String[] args){
		p = new ParseArgs();
		p.addArgs("length", "", "");
		p.addArgs("width", "", "");
		p.addArgs("height", "", "");
		lengthOfArgs = args.length;
		p.parse(args);
	}
	
	public void StartProgramWithArguments(String[] args){
		p = new ParseArgs();
		p.addArgs("length", "\nthe length of the box", "");
		p.addArgs("width", "\nthe width of the box", "");
	    p.addArgs("height","\nthe height of the box", "");		
		p.programInfo("VolumeCalculator", "Calculate the volume of a box.");
		p.parse(args);
	}
	
	public void StartAbsurdProgramWithArguments(String[] args){
		p = new ParseArgs();
		p.addArgs("pet", "", "");
		p.addArgs("number", "", "");
		p.addArgs("rainy", "", "");
		p.addArgs("bathrooms", "", "");
		p.parse(args);
	}
	
	public String getPet(){
		Arguments temp = new Arguments();
		temp = p.getArgs("pet");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getNumber(){
		Arguments temp = new Arguments();
		temp = p.getArgs("number");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getRainy(){
		Arguments temp = new Arguments();
		temp = p.getArgs("rainy");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getBathrooms(){
		Arguments temp = new Arguments();
		temp = p.getArgs("bathrooms");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getLength(){
		Arguments temp = new Arguments();
		temp = p.getArgs("length");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getWidth(){
		Arguments temp = new Arguments();
		temp = p.getArgs("width");
		Object o = temp.getValue();
		return (String)o;
	}

	public String getHeight(){
		Arguments temp = new Arguments();
		temp = p.getArgs("height");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getProgramOutput(String[] args)
	{
		if(p.doesHelpWork() == true)
		{
			return p.getHelpMessage();
		}
		if(p.getIllegalArgs() == true)
		{
			if(lengthOfArgs == 0)
				return "Error: the following arguments are required: length, width, height";
			else if(lengthOfArgs == 1)
				return "Error: the following arguments are required: width, height";
			else if(lengthOfArgs == 2)
				return "Error: the following argument are required : height";
			else if (lengthOfArgs > 3)
			{
				int i = lengthOfArgs;
				return "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: unrecognized arguments: 43";
			}
		}
		else{
			int volume = 0;
			int length = 0, width = 0, height = 0;
			try{
				length = Integer.parseInt(getLength());
			}catch(NumberFormatException e){
				return "usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: argument width: invalid int value: " + getLength();
			}
			try{
				width = Integer.parseInt(getWidth());
			}catch(NumberFormatException e){
				return "usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: argument width: invalid int value: " + getWidth();
			}
			try{
				height = Integer.parseInt(getHeight());
			}catch(NumberFormatException e){
				return "usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: argument width: invalid int value: " + getHeight();
			}
			volume = length * width * height;
			return Integer.toString(volume);
		}
		return Integer.toString(p.getNumberOfKeys());
	}
}