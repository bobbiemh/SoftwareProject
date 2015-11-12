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
    
    public void readXML(String file) throws IOException, SAXException, ParserConfigurationException{
        p = new ParseArgs();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        this.handler = new XMLParse();
        saxParser.parse(file, this.handler);
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
    public void endElement(String uri, String qName) throws SAXException {
        if(isPos){
            if(qName.equalsIgnoreCase("positional"))
                p.addPos(tempName, tempDescript, tempType);
            else if(qName.equalsIgnoreCase("name"))
                tempName = nodeTemp;
            else if(qName.equalsIgnoreCase("descript"))
                tempDescript = nodeTemp;
            else if(qName.equalsIgnoreCase("type"))
                tempType = Argument.Type.valueOf(nodeTemp);
        }
        else{
            if(qName.equalsIgnoreCase("optional"))
                p.addOpt(tempName, tempDefaultValue, tempType);
            else if(qName.equalsIgnoreCase("name"))
                tempName = nodeTemp;
            else if(qName.equalsIgnoreCase("default"))
                tempDefaultValue = nodeTemp;
            else if(qName.equalsIgnoreCase("type"))
                tempType = Argument.Type.valueOf(nodeTemp);
        }
        
        
        /*if(qName.equalsIgnoreCase("positional"))
            p.map.put(tempName, posTemp);
        else if(qName.equalsIgnoreCase("optional"))
            p.map.put(tempName, optTemp);
        else if(qName.equalsIgnoreCase("name")){
            tempName = nodeTemp;
            if(isPos)
                p.positionalKeys.add(tempName);
            else
                p.optionalKeys.add(tempName);
        }
        else if(qName.equalsIgnoreCase("descript"))
            posTemp.setDescription(nodeTemp);
        else if(qName.equalsIgnoreCase("default"))
            optTemp.setDefault(nodeTemp);
        else if(qName.equalsIgnoreCase("type")){
            if(isPos)
                posTemp.setType(Argument.Type.valueOf(nodeTemp));
            else
                optTemp.setType(Argument.Type.valueOf(nodeTemp));
        }*/
    }
}
