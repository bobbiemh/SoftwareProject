import softwareproject.*;
import java.util.*;

public class Keywords {
	private ParseArgs p;
	
	public void StartVolumeCalculatorWithArguments(String[] args){
		p = new ParseArgs();
		p.addArgs("length");
		p.addArgs("width");
		p.addArgs("height");
		p.parse(args);
	}
	
	public void StartProgramWithArguments(String[] args){
		p = new ParseArgs();
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
		int volume = 0;
		int length = Integer.parseInt(getLength());
		int width = Integer.parseInt(getWidth());
		int height = Integer.parseInt(getHeight());
		volume = length * width * height;
		return Integer.toString(volume);
	}
	
	/*public <T>Object getProgramOutput(){
		if()search to see if user enter in --h
		if()search to see if user enter in toomanyargs
		if()search to see if user enter in toofewargs
		if()search to see if user enter in invalid data type
		else volumecalculator height width length
	}*/
}