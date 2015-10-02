import softwareproject.*;

public class HKeywords {
	private ParseArgs p;
	
    public void StartProgramWithArguments(String[] Args){
		p = new ParseArgs();
		p.addArgs("-h");
        String[] args = {"-h"};
        p.parse(args);
        //fix parse before putting it in here
	}
    
    public String GetProgramOutput() {
        int i = p.getArgs("-h");
        String j = "" + i;
		return j;
    }
	
}