import softwareproject.ParseArgs;
import java.util.*;

public class main {


	public static void main(String[] args)
	{
		ParseArgs p = new ParseArgs();
		int s;
		
		p.addArgs("Length");
		p.addArgs("Width");
		p.addArgs("Height");
		p.parse(args);
	}
}