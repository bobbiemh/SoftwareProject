import softwareproject.*;
import java.util.*;

public class Keywords {
	private ParseArgs p;
	int lengthOfArgs;
	
	public void StartVolumeCalculatorWithArguments(String[] args){
		p = new ParseArgs();
		p.addArgs("length");
		p.addArgs("width");
		p.addArgs("height");
		lengthOfArgs = args.length;
		p.parse(args);
	}
	
	public void StartAbsurdProgramWithArguments(String[] args){
		p = new ParseArgs();
		p.addArgs("pet");
		p.addArgs("number");
		p.addArgs("rainy");
		p.addArgs("bathrooms");
		p.parse(args);
	}
	
	public String getPet(){
		return p.getArgs("pet");
	}
	
	public String getNumber(){
		return p.getArgs("number");
	}
	
	public String getRainy(){
		return p.getArgs("rainy");
	}
	
	public String getBathrooms(){
		return p.getArgs("bathrooms");
	}
	
	public String getLength(){
		return p.getArgs("length");
	}
	
	public String getWidth(){
		return p.getArgs("width");
	}
	
	public String getHeight(){
		return p.getArgs("height");
	}
	
	public String getProgramOutput(String[] args)
	{
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
			int length = Integer.parseInt(getLength());
			int width = Integer.parseInt(getWidth());
			int height = Integer.parseInt(getHeight());
			volume = length * width * height;
			return Integer.toString(volume);
		}
		return Integer.toString(p.getNumberOfKeys());
	}
}