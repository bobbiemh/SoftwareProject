package softwareproject;

import java.util.*;
import java.lang.*;
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

    /**
     * This class extends the DefaultHandler class. It reads in an XML document to be
     * used by an application.
     *
     * @author CS 310 XXY Team
     * @version Fall 2015
     */
public class XMLParse extends DefaultHandler {
    private Positional posTemp;
    private Optional optTemp;
    private String nodeTemp;
    private String tempName;
    private Argument.Type tempType;
    private String tempDescript;
    private String tempShort;
    private Object tempDefaultValue;
    private boolean isPos;
    private ParseArgs p;
    private XMLParse handler;
    
    /**
     * Creates a new XMLParse XML parser. 
     *
     */
    public XMLParse() {        
        p = new ParseArgs();
    }
    
    /**
     * Reads in the XML file and parses it into the application. If the XML file
     * does not exist, it throws an IOException, SAXException, or ParserConfigurationException.
     *
     * @param  file the file to be read
     */
    public void readXML(String file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try{
            SAXParser saxParser = factory.newSAXParser();
            this.handler = new XMLParse();
            saxParser.parse(file, this.handler);
        }
        catch(IOException | SAXException | ParserConfigurationException e){
            System.err.println("XML file does not exist");
        }
    }
    
    /**
     * The parser calls this method for each character set.
     *
     * @param  buffer the characters from the XML document
     * @param  start  the start position in the array
     * @param  length  the number of characters to read from the array
     */
    public void characters(char[] buffer, int start, int length){
        nodeTemp = new String(buffer, start, length);
    }
    
    /**
     * Reads in the beginning tag of the XML document and sets up the data accordingly.
     * The uri, localName, and qName are variables used by the SAX Parser. This method
     * throws a SAXException.
     *
     * @param  uri the name of the resource
     * @param  localName the element type name
     * @param  qName the positional argument tag name
     * @param  attributes the attributes attached to the element, if any
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeTemp = "";
        if(qName.equalsIgnoreCase("positional")){
            posTemp = new Positional();
            isPos = true;
            tempName = "";
            tempType = Argument.Type.STRING;
            tempDescript = "";
        }
        if(qName.equalsIgnoreCase("optional")){
            optTemp = new Optional();
            isPos = false;
            tempName = "";
            tempType = Argument.Type.STRING;
            tempDefaultValue = "";
            tempShort = "";
        }
    }
    
    /**
     * Reads in the ending tag of the XML document and sets up the data accordingly.
     * The uri, localName, and qName are variables used by the SAX Parser. This method
     * throws a SAXException.
     *
     * @param  uri the name of the resource
     * @param  localName the element type name
     * @param  qName the positional argument tag name
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(isPos){
            if(qName.equalsIgnoreCase("positional"))
                p.addPos(tempName, tempDescript, tempType);
            else if(qName.equalsIgnoreCase("name"))
                tempName = nodeTemp;
            else if(qName.equalsIgnoreCase("description"))
                tempDescript = nodeTemp;
            else if(qName.equalsIgnoreCase("type"))
                tempType = Argument.Type.valueOf(nodeTemp);
        }
        else{
            if(qName.equalsIgnoreCase("optional")){
                p.addOpt(tempName, tempDefaultValue, tempType);
                p.setShortHand(tempName, tempShort);
            }
            else if(qName.equalsIgnoreCase("name"))
                tempName = nodeTemp;
            else if(qName.equalsIgnoreCase("default"))
                tempDefaultValue = nodeTemp;
            else if(qName.equalsIgnoreCase("type"))
                tempType = Argument.Type.valueOf(nodeTemp);
            else if(qName.equalsIgnoreCase("shorthand"));
                tempShort = nodeTemp;
       }
   }
}
