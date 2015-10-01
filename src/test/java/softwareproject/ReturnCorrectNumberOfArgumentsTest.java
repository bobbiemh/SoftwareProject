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
        assertEquals(s, "usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: unrecognized arguments: 43");
    }
}