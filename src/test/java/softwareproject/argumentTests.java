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
			p.addArgs("length","");
			assertEquals(p.getNumberOfKeys(), 1);
			p.addArgs("width","");
			assertEquals(p.getNumberOfKeys(), 2);
			p.addArgs("height","");
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"0", "0", "0"};
			p.parse(args);
			assertEquals(p.getNumberOfArgs(args), 3);
        }
        @Test(expected = IllegalArgumentException.class)
		public void TooFewArgs(){
			p.addArgs("length","");
			p.addArgs("width","");
			p.addArgs("height","");
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"0","0"};
			p.parse(args);
		}
		
	@Test (expected = IllegalArgumentException.class)
		public void TooManyArgs(){
			p.addArgs("length","");
			p.addArgs("width","");
			p.addArgs("height","");
			assertEquals(p.getNumberOfKeys(), 3);
			String[] args = {"7", "5", "2", "3"};
			p.parse(args);
		}
		
	@Test
		public void returnArgumentValue(){
			p.addArgs("length","");
			p.addArgs("width","");
			p.addArgs("height","");
			
			String[] args = {"7", "5", "2"};
			String temp = "0";
			p.parse(args);			

			temp = p.getArgs("length","");
			assertEquals(temp, "7");
			temp = p.getArgs("width","");
			assertEquals(temp, "5");
			temp = p.getArgs("height","");
			assertEquals(temp, "2","");
		}
		
	/*@Test
	        public void testArgumentDataType(){
	                p.addArgs("height","int","");
	                assertEquals(p.getDataType("height"), "int");
	        }
		*/
	@Test
	        public void testArgumentDescription(){
	                p.addArgs("height","The Height of What You Want to Measure");
	                assertEquals(p.getDescription("height"), "The Height of What You Want to Measure");
	        }
	
	@Test
		public void helpMessageWorking(){
			String[] args = {"-h"};
			p.parse(args);
			assertEquals(p.doesHelpWork(), true);
		}
	@Test
	        public void helpMessageCorrect(){
	                p.programInfo("Volume Calculator","Calculate the volume of a box.");
	                p.addArgs("length","the length of the box");
	                p.addArgs("height","the height of the box");
	          `      String[] args = {"-h"};
	                p.parse(args);
	                assertEquals(p.helpMessage, "usage: java VolumeCalculator length width height\n"+
	                                            "Calculate the volume of a box.\n"+
	                                            "Positional arguments:\n"+
	                                            "length the length of the box\n"+
	                                            "height the height of the box");
	        }
        /*@Test(expected = IllegalArgumentException.class)
                public void incorrectArgType(){
                        p.addArgs("length");
                        p.addArgs("width");
                        p.addArgs("height");
                        
                        String[] args = {"0", "something", "0"};
                        p.parse(args);
                }*/
}
