import softwareproject.*;

public class Keywords +{
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
		p.addArgs("length");
		p.addArgs("width");
		p.addArgs("height");
		//parse args
	}
	
	public String getPet(){
        int i = p.getArgs("pet");
        String j = "" + i;
		return j;
        
		//getArgs returns an int, need to change
	}
    
    public String getNumber() {
        int i = p.getArgs("number");
        String j = "" + i;
		return j;
    }
    
    public String getRainy() {
        int i = p.getArgs("rainy");
        String j = "" + i;
		return j;
    }
    
    public String getBathrooms() {
        int i = p.getArgs("bathrooms");
        String j = "" + i;
		return j;
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
	
	// /*public <T>Object getProgramOutput(){
		// if()search to see if user enter in --h
		// if()search to see if user enter in toomanyargs
		// if()search to see if user enter in toofewargs
		// if()search to see if user enter in invalid data type
		// else volumecalculator height width length
	// }
    
    public void StartVolumeCalculatorWithArguments(String Args[]) {
        p = new ParseArgs();
		p.addArgs("length");
		p.addArgs("width");
		p.addArgs("height");
        String[] args = {"7", "5", "2"};
		p.parse(args);
    }
    
    public void StartAbsurdProgramWithArguments(String[] Args) {
        p = new ParseArgs();
        p.addArgs("pet");
        p.addArgs("number");
        p.addArgs("rainy");
        p.addArgs("bathrooms");
        String[] args = {"dog", "2", "true", "3.5"};
		p.parse(args);
    }
    
    public int GetProgramOutput(String[] args) {
        int result = getLength()*getWidth()*getHeight();
        return result;
    }
}