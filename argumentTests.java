package softwareproject;

import org.junit.*;
import static org.junit.Assert.*;

public class argumentTests {
        @Before
        ParseArgs p = new ParseArgs();
        
        @Test(expected = IllegalArgumentException.class)
        public void exceptionForNotEnoughArguments() {
                assertEquals(map.length, 0);
                p.addArgs("length");
                assertEquals(map.length, 1);
                p.addArgs("width");
                assertEquals(map.length, 2);
                p.addArgs("height");
                assertEquals(map.length, 3);
                p.argsGiven.add("0");
                p.isArgsNumberCorrect();
        }
      //  @Test(expected = IllegalArgumentException.class)
      //  public void exceptionForTooManyArguments
      }
