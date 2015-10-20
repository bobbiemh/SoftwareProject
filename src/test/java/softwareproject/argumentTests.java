package softwareproject;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.rules.ExpectedException;
import java.lang.*;

public class ArgumentTests {
        private ParseArgs p;
        private Argument a;
        @Before
        public final void setup() {
                p = new ParseArgs();
				a = new Argument();
        }
        
        @Rule
                public ExpectedException expectedEx = ExpectedException.none();
        
        @Test
		public void enoughArgument() {
			assertEquals(p.getNumberOfKeys(), 0);
			p.addArgs("length","", ParseArgs.Datatype.NONE);
			assertEquals(p.getNumberOfKeys(), 1);
			p.addArgs("width","", ParseArgs.Datatype.NONE);
			assertEquals(p.getNumberOfKeys(), 2);
			p.addArgs("height","", ParseArgs.Datatype.NONE);
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"0", "0", "0"};
			p.parse(args);
			assertEquals(p.getNumberOfArgs(args), 3);
        }
        @Test(expected = IllegalArgumentException.class)
		public void TooFewArgs(){
			p.addArgs("length", "", ParseArgs.Datatype.NONE);
			p.addArgs("width", "", ParseArgs.Datatype.NONE);
			p.addArgs("height", "", ParseArgs.Datatype.NONE);
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"0","0"};
			p.parse(args);
		}
		
	@Test (expected = IllegalArgumentException.class)
		public void TooManyArgs(){
			p.addArgs("length", "", ParseArgs.Datatype.NONE);
			p.addArgs("width", "", ParseArgs.Datatype.NONE);
			p.addArgs("height", "", ParseArgs.Datatype.NONE);
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"7", "5", "2", "3"};
			p.parse(args);
		}
		
	@Test
		public void returnArgumentValue(){
			p.addArgs("length", "", ParseArgs.Datatype.NONE);
			p.addArgs("width", "", ParseArgs.Datatype.NONE);
			p.addArgs("height", "", ParseArgs.Datatype.NONE);
			
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
	        p.addArgs("height","", ParseArgs.Datatype.INT);
			String [] args = {"0"};
			p.parse(args);
			a = p.getArgs("height");
	        assertEquals(a.getDataType(), ParseArgs.Datatype.INT);
	    }
    
    @Test
		public void testArgumentInt() {
			p.addArgs("length", "", ParseArgs.Datatype.INT);
            String[] args = {"7"};
            p.parse(args);		
			a = p.getArgs("length");
            assertEquals(a.getValue(), 7);
        }
    
    @Test
        public void testArgumentFloat() {
            p.addArgs("height", "", ParseArgs.Datatype.FLOAT);
            String[] args = {"7.5"};
            p.parse(args);	
			a = p.getArgs("height");
            assertEquals(a.getValue(), 7.5f);
        }  

    @Test
        public void testArgumentBoolean() {
            p.addArgs("TodayIsWednesday", "", ParseArgs.Datatype.BOOLEAN);
            String[] args = {"true"};
            p.parse(args);
			a = p.getArgs("TodayIsWednesday");
            assertEquals(a.getValue(), true);
        }

    @Test
        public void testArgumentDefaultString() {
            p.addArgs("message", "", ParseArgs.Datatype.STRING);
            String[] args = {"hello"};
            p.parse(args);
			a = p.getArgs("message");
            assertEquals(a.getValue(), "hello");
        }        
    
	@Test
	        public void testArgumentDescription(){
	                p.addArgs("height","height the height of the box", ParseArgs.Datatype.NONE);
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
	        p.addArgs("length","the length of the box", ParseArgs.Datatype.NONE);
			p.addArgs("width", "the width of the box", ParseArgs.Datatype.NONE);
	        p.addArgs("height","the height of the box", ParseArgs.Datatype.NONE);
	        p.programInfo("volumeCalculator","Calculate the volume of a box.");
	        String[] args = {"-h"};
	        p.parse(args);
	        }
    @Test
        public void incorrectArgType(){
			expectedEx.expect(NumberFormatException.class);
				expectedEx.expectMessage("usage: java VolumeCalculator length width height\nVolumeCalcultor.java: error: argument width: invalid int value: something");
            p.addArgs("length", "", ParseArgs.Datatype.INT);
            p.addArgs("width", "", ParseArgs.Datatype.INT);
            p.addArgs("height", "", ParseArgs.Datatype.INT);
                       
            String[] args = {"0", "something", "0"};
            p.parse(args);
        }
}
