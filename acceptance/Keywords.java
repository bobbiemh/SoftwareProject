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
		p.addPos("length", "", Argument.Type.STRING);
		p.addPos("width", "", Argument.Type.STRING);
		p.addPos("height", "", Argument.Type.STRING);
		lengthOfArgs = args.length;
        try{
            p.parse(args);
        }catch(IllegalArgumentException e){}
	}
    
    public void StartVolumeCalculatorDashArguments(String[] args){
        p = new ParseArgs();
        p.addPos("length", "", Argument.Type.STRING);
        p.addPos("width", "", Argument.Type.STRING);
        p.addPos("height", "", Argument.Type.STRING);        
        p.addOpt("type", "box", Argument.Type.STRING, false);
        p.addOpt("digit", 4, Argument.Type.INT, false);
        
        p.setShortHand("type", "-t");
        p.setShortHand("digit", "-d");
        
        p.parse(args);
    }
	public void StartProgramWithFloatArguments(String[] args){
        p = new ParseArgs();
        p.addPos("length", "the length of the box(float)", Argument.Type.STRING);
        p.addPos("width", "the width of the box(float)", Argument.Type.STRING);
	    p.addPos("height","the height of the box(float)", Argument.Type.STRING);		
		p.programInfo("VolumeCalculator", "Calculate the volume of a box.");
        try{
            p.parse(args);
        }catch(IllegalArgumentException e){}
    }
    
	public void StartProgramWithArguments(String[] args){
		p = new ParseArgs();
		p.addPos("length", "the length of the box", Argument.Type.STRING);
		p.addPos("width", "the width of the box", Argument.Type.STRING);
	    p.addPos("height","the height of the box", Argument.Type.STRING);		
		p.programInfo("VolumeCalculator", "Calculate the volume of a box.");
        try{
            p.parse(args);
        }catch(IllegalArgumentException e){}
	}
	
	public void StartAbsurdProgramWithArguments(String[] args){
		p = new ParseArgs();
		p.addPos("pet", "", Argument.Type.STRING);
		p.addPos("number", "", Argument.Type.STRING);
		p.addPos("rainy", "", Argument.Type.STRING);
		p.addPos("bathrooms", "", Argument.Type.STRING);
		p.parse(args);
	}
	
    public String getType(){
        Argument temp = new Argument();
        temp = p.getArg("type");
        Object o = temp.getValue();
        return (String)o;
    }
    
    public String getDigits(){
        Argument temp = new Argument();
        temp = p.getArg("digit");
        Object o = temp.getValue();
        return o.toString();
    }
    
	public String getPet(){
		Argument temp = new Argument();
		temp = p.getArg("pet");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getNumber(){
		Argument temp = new Argument();
		temp = p.getArg("number");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getRainy(){
		Argument temp = new Argument();
		temp = p.getArg("rainy");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getBathrooms(){
		Argument temp = new Argument();
		temp = p.getArg("bathrooms");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getLength(){
		Argument temp = new Argument();
		temp = p.getArg("length");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getWidth(){
		Argument temp = new Argument();
		temp = p.getArg("width");
		Object o = temp.getValue();
		return (String)o;
	}

	public String getHeight(){
		Argument temp = new Argument();
		temp = p.getArg("height");
		Object o = temp.getValue();
		return (String)o;
	}
	
	public String getProgramOutput(String[] args)
	{
		if(p.doesHelpWork() == true)
		{
			return p.getHelpMessage();
		}
        if(lengthOfArgs > p.numberOfPositionalKeys())
            return "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: unrecognized arguments: 43";
		int volume = 0;
		int length = 0, width = 0, height = 0;
		try{
			length = Integer.parseInt(getLength());
		}catch(NumberFormatException e){
			return "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: argument width: invalid int value: " + getLength();
		}
		try{
			width = Integer.parseInt(getWidth());
		}catch(NumberFormatException e){
			return "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: argument width: invalid int value: " + getWidth();
		}
		try{
			height = Integer.parseInt(getHeight());
		}catch(NumberFormatException e){
			return "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: argument width: invalid int value: " + getHeight();
		}
		volume = length * width * height;
		return Integer.toString(volume);
	}
}