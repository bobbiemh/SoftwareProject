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
			assertEquals(p.numberOfPositionalKeys(), 0);
			p.addPos("length","", Argument.Type.STRING);
			assertEquals(p.numberOfPositionalKeys(), 1);
			p.addPos("width","", Argument.Type.STRING);
			assertEquals(p.numberOfPositionalKeys(), 2);
			p.addPos("height","", Argument.Type.STRING);
			assertEquals(p.numberOfPositionalKeys(), 3);
			String[] args = {"0", "0", "0"};
			p.parse(args);
			assertEquals(p.numberOfArgs(), 3);
        }
        @Test(expected = IllegalArgumentException.class)
		public void TooFewArgs(){
			p.addPos("length", "", Argument.Type.STRING);
			p.addPos("width", "", Argument.Type.STRING);
			p.addPos("height", "", Argument.Type.STRING);
            p.addOpt("type", "", Argument.Type.STRING, false);
            p.addOpt("digit", "", Argument.Type.STRING, false);
			assertEquals(p.numberOfTotalKeys(), 5);
			String[] args = {"0","0"};
			p.parse(args);
		}
		
	@Test (expected = IllegalArgumentException.class)
		public void TooManyArgs(){
			p.addPos("length", "", Argument.Type.STRING);
			p.addPos("width", "", Argument.Type.STRING);
			p.addPos("height", "", Argument.Type.STRING);
			assertEquals(p.numberOfPositionalKeys(), 3);
			String[] args = {"7", "5", "2", "3"};
			p.parse(args);
		}
		
	@Test
		public void returnArgumentValue(){
			p.addPos("length", "", Argument.Type.STRING);
			p.addPos("width", "", Argument.Type.STRING);
			p.addPos("height", "", Argument.Type.STRING);
			
			String[] args = {"7", "5", "2"};
			p.parse(args);			

			a = p.getArg("length");
			assertEquals(a.getValue(), "7");
			a = p.getArg("width");
			assertEquals(a.getValue(), "5");
			a = p.getArg("height");
			assertEquals(a.getValue(), "2");
		}
		
	@Test
	    public void testArgumentType(){
	        p.addPos("height","", Argument.Type.INT);
			String [] args = {"0"};
			p.parse(args);
			a = p.getArg("height");
	        assertEquals(a.getType(), Argument.Type.INT);
	    }
    
    @Test
		public void testArgumentInt() {
			p.addPos("length", "", Argument.Type.INT);
            String[] args = {"7"};
            p.parse(args);		
			a = p.getArg("length");
            int v = a.getValue();
            assertEquals(v, 7);
        }
    
    @Test
        public void testArgumentFloat() {
            p.addPos("height", "", Argument.Type.FLOAT);
            String[] args = {"7.5"};
            p.parse(args);	
			a = p.getArg("height");
            assertEquals(a.getValue(), 7.5f);
        }  

    @Test
        public void testArgumentBoolean() {
            p.addPos("TodayIsWednesday", "", Argument.Type.BOOLEAN);
            String[] args = {"true"};
            p.parse(args);
			a = p.getArg("TodayIsWednesday");
            assertEquals(a.getValue(), true);
        }

    @Test
        public void testArgumentDefaultString() {
            p.addPos("message", "", Argument.Type.STRING);
            String[] args = {"hello"};
            p.parse(args);
			a = p.getArg("message");
            assertEquals(a.getValue(), "hello");
        }        
    
	@Test
	    public void testArgumentDescription(){
	        p.addPos("height","height the height of the box", Argument.Type.STRING);
            a = p.getArg("height");
	        assertEquals(a.getDescription(), "height the height of the box");
	    }
        
	@Test (expected = IllegalArgumentException.class)
		public void helpMessageWorking(){
			String[] args = {"--help"};
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
	        p.addPos("length","the length of the box", Argument.Type.STRING);
			p.addPos("width", "the width of the box", Argument.Type.STRING);
	        p.addPos("height","the height of the box", Argument.Type.STRING);
	        p.programInfo("volumeCalculator","Calculate the volume of a box.");
	        String[] args = {"--help"};
	        p.parse(args);
	        }
    @Test
        public void incorrectArgType(){
			expectedEx.expect(NumberFormatException.class);
				expectedEx.expectMessage("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: argument width: invalid int value: something");
            p.programInfo("VolumeCalculator","");
            p.addPos("length", "", Argument.Type.INT);
            p.addPos("width", "", Argument.Type.INT);
            p.addPos("height", "", Argument.Type.INT);
                       
            String[] args = {"0", "something", "0"};
            p.parse(args);
        }
        
    @Test
        public void DefaultDashArguments(){
            p.addPos("length", "", Argument.Type.INT);
            p.addPos("width", "", Argument.Type.INT);
            p.addPos("height", "", Argument.Type.INT);
            p.addOpt("type", "", Argument.Type.STRING, false);
            p.addOpt("digit", "", Argument.Type.INT, false);
            
            p.addDefaultTypes("box", 4, false, 4.5f);
            
            String[] args = {"7", "5", "2"};
            
            p.parse(args);
            a = p.getArg("type");
            assertEquals(a.getValue(), "box");
            a = p.getArg("digit");
            assertEquals(a.getValue(), 4);
        }
        
    @Test
        public void checkDashType(){
            p.addPos("length", "", Argument.Type.STRING);
			p.addPos("width", "", Argument.Type.STRING);
			p.addPos("height", "", Argument.Type.STRING);
			p.addOpt("type", "", Argument.Type.STRING, false);
            p.addOpt("digit", "", Argument.Type.INT, false);            
            
            p.addDefaultTypes("box", 4, false, 4.5f);
            
            String[] args = {"7", "5", "2", "--type", "ellipsoid"};
            
            p.parse(args);
            a = p.getArg("type");
            assertEquals(a.getValue(), "ellipsoid");
        }
        
    @Test
        public void checkDashDigit(){
            p.addPos("length", "", Argument.Type.STRING);
			p.addPos("width", "", Argument.Type.STRING);
			p.addPos("height", "", Argument.Type.STRING);
			p.addOpt("type", "", Argument.Type.STRING, false);
            p.addOpt("digit", "", Argument.Type.INT, false);
            
            p.addDefaultTypes("box", 4, false, 4.5f);
            
            String[] args = {"7", "5", "2", "--digit", "3"};
            
            p.parse(args);
            a = p.getArg("digit");
            assertEquals(a.getValue(), 3);
        }
        
    @Test
        public void checkForMultipleDashDashArguments() {
            p.addPos("length", "", Argument.Type.STRING);
			p.addPos("width", "", Argument.Type.STRING);
			p.addPos("height", "", Argument.Type.STRING);
			p.addOpt("type", "", Argument.Type.STRING, false);
            p.addOpt("digit", "", Argument.Type.INT, false);
            
            p.addDefaultTypes("box", 4, false, 4.5f);
            
			String[] args = {"7", "5", "2", "--type", "ellipsoid", "--digit", "4"};
            
			p.parse(args);	
            a = p.getArg("type");
            assertEquals(a.getValue(), "ellipsoid");
            a = p.getArg("digit");
            assertEquals(a.getValue(), 4);
        }
        
    @Test
        public void testForMixedDashArgumentsTypeFirst() {
            p.addPos("length", "", Argument.Type.INT);
			p.addPos("width", "", Argument.Type.INT);
			p.addPos("height", "", Argument.Type.INT);
			p.addOpt("type", "", Argument.Type.STRING, false);
            p.addOpt("digit", "", Argument.Type.INT, false);
            
            p.addDefaultTypes("box", 4, false, 4.5f);
            
			String[] args = {"7","--type", "ellipsoid", "5","--digit", "4", "2",};
            
			p.parse(args);
            a = p.getArg("length");
            assertEquals(a.getValue(), 7);
			a = p.getArg("width");
			assertEquals(a.getValue(), 5);	
            a = p.getArg("height");
            assertEquals(a.getValue(), 2);
            a = p.getArg("type");
            assertEquals(a.getValue(), "ellipsoid");
            a = p.getArg("digit");
            assertEquals(a.getValue(), 4);
        }
    @Test
        public void testForMixedDashArgumentsDigitFirst(){
            p.addPos("length", "", Argument.Type.INT);
			p.addPos("width", "", Argument.Type.INT);
			p.addPos("height", "", Argument.Type.INT);
			p.addOpt("type", "", Argument.Type.STRING, false);
            p.addOpt("digit", "", Argument.Type.INT, false);
            
            p.addDefaultTypes("box", 4, false, 4.5f);
            
            String[] args = {"--digit", "4","7","5","--type", "ellipsoid", "2",};
            
			p.parse(args);
            a = p.getArg("length");
            assertEquals(a.getValue(), 7);
            a = p.getArg("width");
            assertEquals(a.getValue(), 5);
			a = p.getArg("height");
			assertEquals(a.getValue(), 2);
            a = p.getArg("type");
            assertEquals(a.getValue(), "ellipsoid");
            a = p.getArg("digit");
            assertEquals(a.getValue(), 4);
        }
        
    
}
