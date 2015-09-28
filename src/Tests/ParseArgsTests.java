package softwareproject;

public class ParseArgsTests{
	public ParseArgs p;
	
	@Before
	public void setup(){
		p = new ParseArgs();
	}
	
	@Test
	public void threeArgs(){
		//test to see if there are three args
		
		//can we get size of map? or pass args 
		//would map have size if we run jacoco test?
	}
	
	@Test
	public void moreThanThreeArgs(){
		//print out an error if there are more than 3
	}
	
	@Test
	public void lessThanThreeArgs(){
		//print out an error if there are less than 3
	}
	
	@Test
	public void illegalArgs(){
		//print out an error if they enter in an illegal arg
	}
	
	@Test
	public void getValue(){
		//testing the getArgs function
	}
}