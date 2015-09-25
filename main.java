package software-project;

import java.awt.*;
import java.awt.event.*;

public class VolumeCalculator{

          public VolumeCalculator()
          {
               string name = "VolumeCalculator";
               int length = 0, width = 0, height = 0;
          }
          
          public void setLength(int length)
          {
               this.length = length;
          }
          public int getLength()
          {
               return length;
          }
          public void setHeight(int height)
          {
               this.height = height;
          }
          public int getHeight()
          {
               return height;
          }
          public void setWidth(int width)
          {
               this.width = width;
          }
          public int getWidth()
          {
               return width;
          }
          
          public string getname()
          {
               return name;
          }
          public void setName(string name)
          {
               this.name = name;
          }
          
          public int calculateVolume(int length, int height, int width)
          {
               int Volume = length * height * width;
               return Volume;
          }
}
public static void main (String[] args)
{

}
