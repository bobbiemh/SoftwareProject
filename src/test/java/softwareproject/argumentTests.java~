package softwareproject;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class argumentTests {
        private ParseArgs p;
        @Before
        public final void setup() {
                p = new ParseArgs();
        }
        
        @Test
		public void enoughArguments() {
			assertEquals(p.getNumberOfKeys(), 0);
			p.addArgs("length");
			assertEquals(p.getNumberOfKeys(), 1);
			p.addArgs("width");
			assertEquals(p.getNumberOfKeys(), 2);
			p.addArgs("height");
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"0", "0", "0"};
			p.parse(args);
			assertEquals(p.getNumberOfArgs(args), 3);
        }
        @Test
		public void TooFewArgs(){
			String s = "";
			p.addArgs("length");
			p.addArgs("width");
			p.addArgs("height");
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"0","0"};
			s = p.parse(args);
			assertEquals(s, "Error: the following argeument are required : height");
		}
		
		@Test
		public void TooManyArgs(){
			String s = "";
			p.addArgs("length");
			p.addArgs("width");
			p.addArgs("height");
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"0", "0", "0", "0"};
			s = p.parse(args);
			assertEquals(s, "Error: unrecongnized arguements: " + "0");
		}
}

