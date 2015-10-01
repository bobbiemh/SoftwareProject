package softwareproject;

import org.junit.*;
import static org.junit.Assert.*;

public class ReturnCorrectNumberOfArgumentsTest {
	
	private ParseArgs p;
	
	@Before
	public void setUp() {
		p = new ParseArgs();
	}
    
    @Test
	public void hreturnscorrectUsageInformationTest() {
     String[] args = {"-h"};
        String s = p.parse(args);
        assertEquals(s, "usage: java VolumeCalculator length width height \n Calculate the volume of a box. \n positional arguments: \n length the length of the box \n width the width of the box \n height the height of the box");
    }
}