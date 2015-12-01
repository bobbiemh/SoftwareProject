package softwareproject;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import org.junit.rules.ExpectedException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
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
			assertEquals(p.numberOfTotalKeys(), 3);
        }
        @Test(expected = IllegalArgumentException.class)
		public void TooFewArgs(){
			p.addPos("length", "", Argument.Type.STRING);
			p.addPos("width", "", Argument.Type.STRING);
			p.addPos("height", "", Argument.Type.STRING);
			assertEquals(p.numberOfTotalKeys(), 3);
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
            Object v = a.getValue();
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
				expectedEx.expectMessage("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: argument width: invalid INT value: something");
            p.programInfo("VolumeCalculator","");
            p.addPos("length", "", Argument.Type.INT);
            p.addPos("width", "", Argument.Type.INT);
            p.addPos("height", "", Argument.Type.INT);
                       
            String[] args = {"0", "something", "0"};
            p.parse(args);
            p.getValue("width");
        }
        
    /*@Test        OBSOLETEOBSOLETEOBSOLETEOBSOLETEOBSOLETEOBSOLETEOBSOLETEOBSOLETEOBSOLETEOBSOLETEOBSOLETEOBSOLETE
        public void incorrectArgTypeBoolean(){ 
            expectedEx.expect(NumberFormatException.class);
                expectedEx.expectMessage("usage: java BooleanTool todayIsFriday\nBooleanTool.java: error: argument todayIsFriday: invalid boolean value: something");
            p.programInfo("BooleanTool", "");
            p.addPos("todayIsFriday", "", Argument.Type.BOOLEAN);
            
            String[] args = {"something"};
            p.parse(args);
        }
        */
        
    @Test
        public void incorrectArgTypeFloat(){
			expectedEx.expect(NumberFormatException.class);
				expectedEx.expectMessage("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: argument width: invalid FLOAT value: something");
            p.programInfo("VolumeCalculator","");
            p.addPos("length", "", Argument.Type.FLOAT);
            p.addPos("width", "", Argument.Type.FLOAT);
            p.addPos("height", "", Argument.Type.FLOAT);
                       
            String[] args = {"0.0", "something", "0.0"};
            p.parse(args);
        }
        
    @Test
        public void DefaultDashArguments(){
            p.addPos("length", "", Argument.Type.INT);
            p.addPos("width", "", Argument.Type.INT);
            p.addPos("height", "", Argument.Type.INT);
            p.addOpt("type", "box", Argument.Type.STRING);
            p.addOpt("digit", "4", Argument.Type.INT);
                        
            String[] args = {"7", "5", "2"};
            
            p.parse(args);
            Object o = p.getValue("length");
            assertEquals(o, 7);
            o = p.getValue("digit");
            assertEquals(o, 4);      
        }
        
    @Test
        public void checkDashType(){
            p.addPos("length", "", Argument.Type.STRING);
			p.addPos("width", "", Argument.Type.STRING);
			p.addPos("height", "", Argument.Type.STRING);
            p.addOpt("type", "box", Argument.Type.STRING);
            p.addOpt("digit", "4", Argument.Type.INT);  
            
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
            p.addOpt("type", "box", Argument.Type.STRING);
            p.addOpt("digit", "4", Argument.Type.INT);
            
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
            p.addOpt("type", "box", Argument.Type.STRING);
            p.addOpt("digit", "4", Argument.Type.INT);
            
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
            p.addOpt("type", "box", Argument.Type.STRING);
            p.addOpt("digit", "4", Argument.Type.INT);
            
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
            p.addOpt("type", "box", Argument.Type.STRING);
            p.addOpt("digit", 4, Argument.Type.INT);
            
            String[] args = {"--digit", "4","7","5","--type", "ellipsoid", "2",};
            
			p.parse(args);
            Object o = p.getValue("length");
            assertEquals(o, 7);
            o = p.getValue("width");
            assertEquals(o, 5);
			o = p.getValue("height");
			assertEquals(o, 2);
            o = p.getValue("type");
            assertEquals(o, "ellipsoid");
            o = p.getValue("digit");
            assertEquals(o, 4);
        }
    @Test  
        public void addShortHand(){
            p.addOpt("type", "box", Argument.Type.STRING);
            p.addOpt("float", 4.5f, Argument.Type.FLOAT);
            p.addOpt("bool", false, Argument.Type.BOOLEAN);
            
            String[] args = {"-t", "ellipsoid", "-f", "5.5f"};
            
            p.setShortHand("type", "t");
            p.setShortHand("float", "f");
            p.setShortHand("bool", "b");
            
            p.parse(args);
            
            Argument a = p.getArg("type");
            assertEquals(a.getShortHand(), "-t");
            a = p.getArg("float");
            assertEquals(a.getShortHand(), "-f");
        }
    @Test
        public void defaultShortHand(){
            p.addOpt("type", "box", Argument.Type.STRING);
            p.addOpt("digit", 4, Argument.Type.INT);
            p.addOpt("gorilla", false, Argument.Type.BOOLEAN);
            
            String[] args = {"-t", "ellipsoid", "-d", "1"};
            
            p.parse(args);
            
            Argument a = p.getArg("type");
            assertEquals(a.getShortHand(), "-t");
            a = p.getArg("digit");
            assertEquals(a.getShortHand(), "-d");
        }
    @Test
        public void dashDashHelpAndDashH() {
            expectedEx.expect(IllegalArgumentException.class);
				expectedEx.expectMessage("usage: java volumeCalculator\n"+
	                                     "Calculate the volume of a box\n" + "Positional arguments:");
            p.addOpt("digit", 4, Argument.Type.INT);
            p.programInfo("volumeCalculator", "Calculate the volume of a box");
            
            String [] args = {"-h"};
            
            p.parse(args);
        }
    @Test
        public void noOtherArgCanBeNamedH() {
            expectedEx.expect(IllegalArgumentException.class);
				expectedEx.expectMessage("-h is used for only help");
            p.addOpt("happy", "feeling", Argument.Type.STRING);
            String[] args = {"-h"};
            p.setShortHand("happy", "h");
            p.parse(args);
        }
    @Test
        public void testXMLArgs(){
            p.readXML("src/test/java/softwareproject/VolumeCalculator.xml");
        
            String[] args = {"7", "5", "2"};
            
            p.parse(args);
            
		    assertEquals(p.numberOfPositionalKeys(), 3);
		    assertEquals(p.numberOfOptionalKeys(), 2);
        }
    @Test
        public void testXMLParsing() {
            p.readXML("src/test/java/softwareproject/VolumeCalculator.xml");
            String[] args = {"7", "5", "2", "--type", "pizza", "-d", "1"};
            
            p.parse(args);
			assertEquals(p.getValue("length"), 7);
			assertEquals(p.getValue("width"), 5);
			assertEquals(p.getValue("height"), 2);
            assertEquals(p.getValue("type"), "pizza");
            assertEquals(p.getValue("digits"), 1);
        }
    @Test
        public void testXMLHelp() {
        expectedEx.expect(IllegalArgumentException.class);
                expectedEx.expectMessage("usage: java VolumeCalculator length width height\n"+
	                                     "Calculate the volume of a box.\n"+
	                                     "Positional arguments:\n"+
	                                     "length the length of the box\n"+
									     "width the width of the box\n" +
	                                     "height the height of the box");
	                                     
            p.readXML("src/test/java/softwareproject/VolumeCalculator.xml");
	        p.programInfo("VolumeCalculator","Calculate the volume of a box.");
	        String[] args = {"--help"};
            p.parse(args); 
        }
    @Test
        public void textXMLWritePOS() {
            p.addPos("length", "", Argument.Type.INT);
		    p.addPos("width", "", Argument.Type.INT);
		    p.addPos("height", "", Argument.Type.STRING);
		
            String[] args = {"7", "5", "2"};
		    
		    p.saveToXML("VolumeCalculatorToXML.xml");
		    
            ParseArgs p2 = new ParseArgs();
		    p2.readXML("VolumeCalculatorToXML.xml");
		    p2.parse(args);			

		    a = p2.getArg("length");
		    assertEquals(a.getValue(), 7);
		    a = p2.getArg("width");
		    assertEquals(a.getValue(), 5);
		    a = p2.getArg("height");
		    assertEquals(a.getValue(), "2");
        }
    @Test
        public void textXMLWriteOPT() {
            p.addOpt("type", "box", Argument.Type.STRING);
            p.addOpt("digit", "4", Argument.Type.INT);
            p.addOpt("gorilla", "4.5f", Argument.Type.FLOAT);
		
            String[] args = {"-t", "ellipsoid", "-d", "5", "-g", "5.5f"};
		    
		    p.saveToXML("VolumeCalculatorToXML.xml");
		    
            ParseArgs p2 = new ParseArgs();
		    p2.readXML("VolumeCalculatorToXML.xml");
		    p2.parse(args);			

		    a = p2.getArg("type");
		    assertEquals(a.getValue(), "ellipsoid");
		    a = p2.getArg("digit");
		    assertEquals(a.getValue(), 5);
		    a = p2.getArg("gorilla");
		    assertEquals(a.getValue(), 5.5f);
        }
        
    @Test
        public void testShorthand() {
            a.setShortHand("s");
            String tempS = a.getShortHand();
            assertEquals(tempS, "s");
        }
        
    @Test
        public void testValue() {
            a.setValue("7");
            String temp = a.getValue();
            assertEquals(temp, "7");
        }
        
    @Test
        public void testDefault() {
            a.setDefault("7");
            String temp = a.getValue();
            assertEquals(temp, "7");
        }
        
    @Test
        public void testDescription() {
            a.setDescription("this is a description");
            String tempD = a.getDescription();
            assertEquals(tempD, "this is a description");
        }
        
    @Test
        public void testType() {
            a.setType(Argument.Type.STRING);
            Argument.Type tempT = a.getType();
            assertEquals(tempT, Argument.Type.STRING);
        }
        
    @Test
        public void testProgramName() {
            p.programInfo("Program", "This is a program.");
            String name = p.getProgramName();
            assertEquals(name, "Program");
        }
        
    @Test
        public void testDashDashException() {
            expectedEx.expect(IllegalArgumentException.class);
				expectedEx.expectMessage("the argument does not exist");
            String args[] = {"--Monkey"};
            p.parse(args);
        }
        
    @Test
        public void testDashException() {
            expectedEx.expect(IllegalArgumentException.class);
				expectedEx.expectMessage("the argument does not exist");
            String args[] = {"-M"};
             p.parse(args);
        }
}
