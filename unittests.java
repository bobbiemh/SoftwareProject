package software.-project;

import org.junit.*;
import org.junit.Assert.*

public class VolumeCalculator{
          public VolumeCalculator calculator;
          
          @Before
          public void setup()
          {
               calculator = new VolumeCalculator();
          }

          @Test
          public void testName()
          {
               assertEquals("VolumeCalculator", calculator.getname());
          }

          @Test
          public void Calculate()
          {
               calculator.setLength(7);
               calculator.setHeight(5);
               calculator.setWidth(2);
               
               assertEquals(70, calculateVolume(calculator.getLength(), calculator.getHeight(), calculator.getWidth());
          }
}
