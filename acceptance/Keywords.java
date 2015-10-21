import softwareproject.*;
import java.util.*;
import java.lang.*;

public class Keywords {
	private ParseArgs p;
	int lengthOfArgs;
	
	private void Keywords(){
		p = new ParseArgs();
		lengthOfArgs = 0;
	}
	
	public void StartVolumeCalculatorWithArguments(String[] args){
		p = new ParseArgs();
		p.addArgs("length", "", Argument.Datatype.STRING);
		p.addArgs("width", "", Argument.Datatype.STRING);
		p.addArgs("height", "", Argument.Datatype.STRING);
		lengthOfArgs = args.length;
        try{
            p.parse(args);
        }catch(IllegalArgumentException e){}
	}
	
	public void StartProgramWithArguments(String[] args){
		p = new ParseArgs();
		p.addArgs("length", "the length of the box", Argument.Datatype.STRING);
		p.addArgs("width", "the width of the box", Argument.Datatype.STRING);
	    p.addArgs("height","the height of the box", Argument.Datatype.STRING);		
		p.programInfo("VolumeCalculator", "Calculate the volume of a box.");
        try{
            p.parse(args);
        }catch(IllegalArgumentException e){}
	}
	
	public void StartAbsurdProgramWithArguments(String[] args){
		p = new ParseArgs();
		p.addArgs("pet", "", Argument.Datatype.STRING);
		p.addArgs("number", "", Argument.Datatype.STRING);
		p.addArgs("rainy", "", Argument.Datatype.STRING);
		p.addArgs("bathrooms", "", Argument.Datatype.STRING);
		p.parse(args);
	}
	
	public String getPet(){
		Argument temp = new Argument();
		temp = p.getArgs("pet");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getNumber(){
		Argument temp = new Argument();
		temp = p.getArgs("number");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getRainy(){
		Argument temp = new Argument();
		temp = p.getArgs("rainy");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getBathrooms(){
		Argument temp = new Argument();
		temp = p.getArgs("bathrooms");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getLength(){
		Argument temp = new Argument();
		temp = p.getArgs("length");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getWidth(){
		Argument temp = new Argument();
		temp = p.getArgs("width");
		Object o = temp.getValue();
		return (String)o;
	}

	public String getHeight(){
		Argument temp = new Argument();
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
				return "Error: the following Arguments are required: length, width, height";
			else if(lengthOfArgs == 1)
				return "Error: the following Arguments are required: width, height";
			else if(lengthOfArgs == 2)
				return "Error: the following Arguments are required : height";
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