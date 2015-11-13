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

public class XMLParse extends DefaultHandler {
    private Positional posTemp;
    private Optional optTemp;
    private String nodeTemp;
    private String tempName;
    private Argument.Type tempType;
    private String tempDescript;
    private Object tempDefaultValue;
    private boolean isPos;
    private ParseArgs p;
    private XMLParse handler;
    
    private List<String> positional;
    private List<String> optional;
    private Map<String, Argument> xmlmap;
    
    public XMLParse() {        
        p = new ParseArgs();
        xmlmap = new HashMap();
        positional = new ArrayList<String>();
        optional = new ArrayList<String>();
    }
    
    public void readXML(String file) throws IOException, SAXException, ParserConfigurationException{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        this.handler = new XMLParse();
        try{
            saxParser.parse(file, this.handler);
        }
        catch(IOException | SAXException e){
            throw new IOException("XML file does not exist");
        }
    }
    public void characters(char[] buffer, int start, int length){
        nodeTemp = new String(buffer, start, length);
    }
    
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
        }
    }
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(isPos){
            if(qName.equalsIgnoreCase("positional")){
                Argument temp = new Argument();
                temp.setType(tempType);
                temp.setDescription(tempDescript);
                positional.add(tempName);
                xmlmap.put(tempName, temp);
            }
            else if(qName.equalsIgnoreCase("name"))
                tempName = nodeTemp;
            else if(qName.equalsIgnoreCase("descript"))
                tempDescript = nodeTemp;
            else if(qName.equalsIgnoreCase("type"))
                tempType = Argument.Type.valueOf(nodeTemp);
        }
        else if(!isPos){
            if(qName.equalsIgnoreCase("optional")){
                Argument temp = new Argument();
                temp.setType(tempType);
                temp.setDescription(tempDescript);
                optional.add(tempName);
                xmlmap.put(tempName, temp);
            }
            else if(qName.equalsIgnoreCase("name"))
                tempName = nodeTemp;
            else if(qName.equalsIgnoreCase("default"))
                tempDefaultValue = nodeTemp;
            else if(qName.equalsIgnoreCase("type")){
                tempType = Argument.Type.valueOf(nodeTemp);               
            }
        }
   }
   public void endDocument() throws SAXException{
        p.setMap(xmlmap);
        p.setKeys(positional, optional);
   }
}
