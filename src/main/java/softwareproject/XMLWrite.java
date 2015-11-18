package softwareproject;

import java.io.File;
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLWrite{
    ParseArgs p;
    
    public XMLWrite(){
        p = new ParseArgs();
    }
    
    public void saveToXML(String filename){ 
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuild = docFactory.newDocumentBuilder();
            
            Document doc = docBuild.newDocument();
                    
            Element root = doc.createElement("Argument");
            int posCount = 0, optCount = 0;
            for(int i = 0; i < p.numberOfTotalKeys(); i++){
                if(posCount < p.numberOfPositionalKeys()){
                    Element pos = doc.createElement("Positional");
                    
                    String key = p.getPositionalKey(posCount);
                    Argument temp = p.getArg(key);
                    
                    Element child1 = doc.createElement("name");
                    child1.appendChild(doc.createTextNode(key));
                    
                    Argument.Type type = temp.getType();
                    Element child2 = doc.createElement("type");
                    child2.appendChild(doc.createTextNode(type.name()));
                    
                    String descript = temp.getDescription();
                    Element child3 = doc.createElement("description");
                    child3.appendChild(doc.createTextNode(descript));
                    
                    pos.appendChild(child1);
                    pos.appendChild(child2);
                    pos.appendChild(child3);
                    root.appendChild(pos);
                    
                    posCount ++;
                }
                else if(optCount < p.numberOfOptionalKeys()){
                    Element opt = doc.createElement("Optional");
                    
                    String key = p.getOptionalKey(optCount);
                    Argument temp = p.getArg(key);
                    
                    Element child1 = doc.createElement("name");
                    child1.appendChild(doc.createTextNode(key));
                    
                    Element child2 = doc.createElement("shorthand");
                    String shorthand = temp.getShortHand().substring(1);
                    child2.appendChild(doc.createTextNode(shorthand));
                    
                    Element child3 = doc.createElement("type");
                    Argument.Type type = temp.getType();
                    child3.appendChild(doc.createTextNode(type.name()));
                    
                    Element child4 = doc.createElement("default");
                    Object def = temp.getDefault();
                    child4.appendChild(doc.createTextNode((String)def));
                    
                    opt.appendChild(child1);
                    opt.appendChild(child2);
                    opt.appendChild(child3);
                    opt.appendChild(child4);
                    root.appendChild(opt);
                    
                    optCount ++;
                }
                
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filename));
                
                transformer.transform(source, result);
            }
            
        }
        catch(ParserConfigurationException | TransformerException e) {
            System.err.print("You done goof");
        }
    }
}