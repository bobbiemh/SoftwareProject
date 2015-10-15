package softwareproject;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.rules.ExpectedException;
import java.lang.*;

public class argumentTests {
        private ParseArgs p;
	private Arguments a;
        @Before
        public final void setup() {
                p = new ParseArgs();
				a = new Arguments();
        }
        
        @Rule
                public ExpectedException expectedEx = ExpectedException.none();
        
        @Test
		public void enoughArguments() {
			assertEquals(p.getNumberOfKeys(), 0);
			p.addArgs("length","", "");
			assertEquals(p.getNumberOfKeys(), 1);
			p.addArgs("width","", "");
			assertEquals(p.getNumberOfKeys(), 2);
			p.addArgs("height","", "");
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"0", "0", "0"};
			p.parse(args);
			assertEquals(p.getNumberOfArgs(args), 3);
        }
        @Test(expected = IllegalArgumentException.class)
		public void TooFewArgs(){
			p.addArgs("length", "", "");
			p.addArgs("width", "", "");
			p.addArgs("height", "", "");
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"0","0"};
			p.parse(args);
		}
		
	@Test (expected = IllegalArgumentException.class)
		public void TooManyArgs(){
			p.addArgs("length", "", "");
			p.addArgs("width", "", "");
			p.addArgs("height", "", "");
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"7", "5", "2", "3"};
			p.parse(args);
		}
		
	@Test
		public void returnArgumentValue(){
			p.addArgs("length", "", "");
			p.addArgs("width", "", "");
			p.addArgs("height", "", "");
			
			String[] args = {"7", "5", "2"};
			p.parse(args);			

			a = p.getArgs("length");
			assertEquals(a.getValue(), "7");
			a = p.getArgs("width");
			assertEquals(a.getValue(), "5");
			a = p.getArgs("height");
			assertEquals(a.getValue(), "2");
		}
		
	@Test
	    public void testArgumentDataType(){
	        p.addArgs("height","", "int");
			String [] args = {"0"};
			p.parse(args);
			a = p.getArgs("height");
	        assertEquals(a.getDataType(), "int");
	    }
    
    @Test
		public void testArgumentInt() {
			p.addArgs("length", "", "int");
            String[] args = {"7"};
            p.parse(args);		
			a = p.getArgs("length");
            assertEquals(a.getValue(), 7);
        }
    
    @Test
        public void testArgumentFloat() {
            p.addArgs("height", "", "float");
            String[] args = {"7.5"};
            p.parse(args);	
			a = p.getArgs("height");
            assertEquals(a.getValue(), 7.5f);
        }  

    @Test
        public void testArgumentBoolean() {
            p.addArgs("TodayIsWednesday", "", "boolean");
            String[] args = {"true"};
            p.parse(args);
			a = p.getArgs("TodayIsWednesday");
            assertEquals(a.getValue(), true);
        }

    @Test
        public void testArgumentDefaultString() {
            p.addArgs("message", "", "String");
            String[] args = {"hello"};
            p.parse(args);
			a = p.getArgs("message");
            assertEquals(a.getValue(), "hello");
        }        
    
	@Test
	        public void testArgumentDescription(){
	                p.addArgs("height","height the height of the box", "");
					a = p.getArgs("height");
	                assertEquals(a.getDescription(), "height the height of the box");
	        }
	
	@Test (expected = IllegalArgumentException.class)
		public void helpMessageWorking(){
			String[] args = {"-h"};
			p.parse(args);
			assertEquals(p.doesHelpWork(), true);
		}
	@Test
	    public void helpMessageCorrect(){
	        expectedEx.expect(IllegalArgumentException.class);
                expectedEx.expectMessage("usage: java volumeCalculator length width height\n"+
	                                 "Calculate the volume of a box.\n"+
	                                 "Positional arguments:\n"+
	                                 "length the length of the box\n"+
					 "width the width of the box\n" +
	                                 "height the height of the box");
	        p.addArgs("length","the length of the box", "");
		p.addArgs("width", "the width of the box", "");
	        p.addArgs("height","the height of the box", "");
	        p.programInfo("volumeCalculator","Calculate the volume of a box.");
	        String[] args = {"-h"};
	        p.parse(args);
	        }
    /*@Test(expected = IllegalArgumentException.class)
                public void incorrectArgType(){
                        p.addArgs("length", "", "int");
                        p.addArgs("width");
                        p.addArgs("height");
                        
                        String[] args = {"0", "something", "0"};
                        p.parse(args);
                }*/
}
