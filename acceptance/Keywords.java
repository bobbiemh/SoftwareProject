import softwareproject.*;

public class Keywords {
	private ParseArgs p;
	
	public void StartVolumeCalculatorWithArgs(String[] Args){
		p = new ParseArgs();
		p.addArgs("length");
		p.addArgs("width");
		p.addArgs("height");
		//fix parse before putting it in here
	}
	
	public void StartVolumeCalculatorWithUserArgs(String[] Args){
		p = new ParseArgs();
		p.addArgs("length");
		p.addArgs("width");
		p.addArgs("height");
		//fix parse before putting it in here
	}
	
	public void StartProgramWithArgs(String[] args){
		p = new ParseArgs();
		//parse args
	}
	
	public void StartAbsurdProgramWithArgs(String[] args){
		p = new ParseArgs();
		p.addArgs("pet");
		p.addArgs("number");
		p.addArgs("rainy");
		p.addArgs("bathrooms");
		//parse args
	}
	
	public void StartProgramWithFloatArgs(String[] args){
		p = new ParseArgs();
		p.addArg("length");
		p.addArg("width");
		p.addArgs("height");
		//parse args
	}
	
	public String getPet(){
		return p.getArgs("pet");
		//getArgs returns an int, need to change
	}
	
	public String getNumber(){
		return p.getArgs("number");
	}
	
	public Strnig getRainy(){
		return p.getArgs("rainy");
	}
	
	public String getBathrooms(){
		return p.getArgs("bathrooms");
	}
	
	public int getLength(){
		return p.getArgs("length");
	}
	
	public int getWidth(){
		return p.getArgs("width");
	}
	
	public int getHeight(){
		return p.getArgs("height");
	}
	
	/*public <T>Object getProgramOutput(){
		if()search to see if user enter in --h
		if()search to see if user enter in toomanyargs
		if()search to see if user enter in toofewargs
		if()search to see if user enter in invalid data type
		else volumecalculator height width length
	}
}