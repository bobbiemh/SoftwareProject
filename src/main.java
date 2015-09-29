import softwareproject.ParseArgs;
import java.util.*;

public class main {


	public static void main(String[] args)
	{
		ParseArgs p = new ParseArgs();
		String s;
		
		p.addArgs("Length");
		p.addArgs("Width");
		p.addArgs("Height");
		s = p.parse(args);
		System.err.println(s);
	}
}