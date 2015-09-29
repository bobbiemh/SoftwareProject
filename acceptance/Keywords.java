import softwareproject.*;

public class Keywords {
	private ParseArgs p = new ParseArgs();
	
	public void AddArgs(String key, String[] args){
		p.addArgs(key);
		p.parse(args);
	}
}