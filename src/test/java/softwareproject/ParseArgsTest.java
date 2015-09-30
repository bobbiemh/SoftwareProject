package softwareproject;

import org.junit.*;
import static org.junit.Assert.*;

public class ParseArgsTest {
	
	private ParseArgs p;
	
	@Before
	public void setUp() {
		p = new ParseArgs();
	}
	
	@Test
	public void returnMapValueTest() {
		p.addArgs("Tomato");
        assertEquals((Integer) 0, p.returnValue("Tomato"));
    }
}