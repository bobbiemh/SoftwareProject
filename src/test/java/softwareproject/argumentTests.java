package softwareproject;

import org.junit.*;
import static org.junit.Assert.*;

public class argumentTests {
        private ParseArgs p;
        @Before
        public final void setup() {
                p = new ParseArgs();
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void exceptionForNotEnoughArguments() {
                assertEquals(p.hmap.size(), 0);
                p.addArgs("length");
                assertEquals(p.hmap.size(), 1);
                p.addArgs("width");
                assertEquals(p.hmap.size(), 2);
                p.addArgs("height");
                assertEquals(p.hmap.size(), 3);
                String[] args = {"0"};
                p.parse(args);
                p.isArgsNumberCorrect();
        }
      //  @Test(expected = IllegalArgumentException.class)
      //  public void exceptionForTooManyArguments
}

