
package softwareproject;

import java.util.*;
import java.lang.*;


public class ParseArgs{
    private Map<String, Argument> map;
    private List<String> positionalKeys;
    private List<String> optionalKeys;
    private List<String> allArgs;//make not global
    
    private boolean messageTrue;
    private boolean illegalArgs;
    private boolean argRequired;
    
    private String programName;
    private String programDescription;
    private String helpMessage;
    private String dashType;
    private String digit;    
    
    
    public ParseArgs() {
        map = new HashMap<String, Argument>();
        positionalKeys = new ArrayList<String>();
        optionalKeys = new ArrayList<String>();
            
        messageTrue = false;
        illegalArgs = false;
        argRequired = true;
        
        dashType = "box";
        digit = "4";
        
        helpMessage = "usage: java ";
        
        allArgs = new ArrayList<String>();
    }
    
    public void addPos(String name, String description, Argument.Type type)
    {
        Positional temp = new Positional();
        positionalKeys.add(name);
        temp.setDescription(description);
        temp.setType(type);
        map.put(name, temp);
    }
                           //key        shorthand -t        datatype           required?
    public void addOpt(String name, String shortHand, Argument.Type type, boolean defaultOption){
        Optional temp = new Optional();
        optionalKeys.add(name);
        temp.setShortHand(shortHand);
        temp.setType(type);
        temp.setRequired(defaultOption);
        map.put(name, temp);
    }
    
    public void parse(String[] args)
    {
        for(int i = 0; i < args.length; i++) {
            allArgs.add(args[i]);
        }
        checkDashes();
        if(!messageTrue)
        {
            String exceptionMessage = "Error: the following Argument are required: ";
            if(allArgs.size() < positionalKeys.size() || allArgs.size() > positionalKeys.size())
                illegalArgs = true;
            if(allArgs.size() == 0 && illegalArgs){
                for(int i = 0; i < positionalKeys.size(); i++)
                {
                    exceptionMessage = exceptionMessage + " " + getPositionalKey(i);
                }
                throw new IllegalArgumentException(exceptionMessage);
            }
            else if(allArgs.size() < positionalKeys.size() && illegalArgs)
            {                    
                for(int i = 0; i <= allArgs.size(); i++)
                {
                    exceptionMessage = exceptionMessage + " " + getPositionalKey(i);
                }
                throw new IllegalArgumentException(exceptionMessage);        
            }
            else if (allArgs.size() > positionalKeys.size())
            {
                int a = allArgs.size() - 1;
                String temp = args[a];
                exceptionMessage = "usage: java " + programName;
                for(int i = 0; i < positionalKeys.size(); i++)
                {
                    exceptionMessage = exceptionMessage + " " + getPositionalKey(i);
                }
                exceptionMessage = exceptionMessage + programName + ".java: error: unrecognized Argument: " + temp;
                throw new IllegalArgumentException(exceptionMessage);
            }
            putToMap();
        }
    }
    
    private void checkDashes(){
        if(allArgs.contains("--help")) {
            messageTrue = true;
            throw new IllegalArgumentException(helpMessage);
        }
        if(optionalKeys.contains("type"))
        {
            if(allArgs.contains("--type")) {
                if(argRequired){
                    int i = allArgs.indexOf("--type");
                    Argument temp = getArg("type");
                    temp.setValue("box");
                    map.put("type", temp);
                    allArgs.remove(i+1);                    
                    allArgs.remove(i);
                }
                else if(!argRequired){
                    int i = allArgs.indexOf("--type");
                    allArgs.remove(i+1);
                    allArgs.remove("--type");
                }
            }
            else{
                if(argRequired)
                {
                    Argument temp = getArg("type");
                    temp.setValue("box");
                    map.put("type", temp);
                }
            }
        }
        if(optionalKeys.contains("digit"))
        {
            if(allArgs.contains("--digit")) {
                if(argRequired){
                    int i = allArgs.indexOf("--digit");
                    Argument temp = getArg("digit");
                    int num = Integer.parseInt(allArgs.get(i+1));
                    temp.setValue(num);
                    map.put("digit", temp);
                    allArgs.remove(i+1);
                    allArgs.remove(i);
                }
                else if(!argRequired){
                    int i = allArgs.indexOf("--digit");
                    allArgs.remove(i+1);
                    allArgs.remove(i);
                }
            }
            else
                if(argRequired){
                    Argument temp = getArg("digit");
                    int num = Integer.parseInt("4");
                    temp.setValue(num);
                    map.put("digit", temp);
                }
        }
        allArgs.removeAll(Collections.singleton(null));
    }
    
    public String getUsage() {
        String message = "usage: java " + programName;
        for(int a = 0; a < positionalKeys.size(); a++) {
            message = message + " " + getPositionalKey(a);
        }
        for(int a = 0; a < optionalKeys.size(); a++) {
            message = message + " " + getOptionalKey(a);
        }
        return message;
    }
    
    private void putToMap(){
        int i = 0;
        for(i = 0; i < positionalKeys.size(); i++){
            
            String key = positionalKeys.get(i);           
            Argument temp = new Argument();
            temp = getArg(key);
            Argument.Type type = temp.getType();
            
            String exceptionMessage = "usage: java " + programName;
            for(int a = 0; a < positionalKeys.size(); a++) {
                exceptionMessage = exceptionMessage + " " + getPositionalKey(a);
            }
            exceptionMessage = exceptionMessage + "\n" + programName + ".java: error: argument " + getPositionalKey(i) + ": invalid ";
            
            
            if(type == Argument.Type.INT){
                temp.setValue(convertToInt(allArgs.get(i), getPositionalKey(i)));
            }
            else if(type == Argument.Type.BOOLEAN){
                temp.setValue(convertToBoolean(allArgs.get(i), getPositionalKey(i)));
            }
            else if(type == Argument.Type.FLOAT){
                temp.setValue(convertToFloat(allArgs.get(i), getPositionalKey(i)));
            }
            else{
                temp.setValue(convertToString(allArgs.get(i)));
            }
            map.put(key, temp);
        }
        
        while(i <= allArgs.size() && i > positionalKeys.size())
        {
            String str = allArgs.get(allArgs.size() - 1);
        }
    }
    
    private String convertToString(String arg){
        return arg;
    }
    
    private int convertToInt(String arg, String key){
        try{
            int num = Integer.parseInt(arg);
            return num;
        } 
        catch(NumberFormatException e){
            String exceptionMessage = getUsage() + "\n" + programName + ".java: error: argument " + key + ": invalid int value: " + arg;
            throw new NumberFormatException(exceptionMessage);
        }
    }
    
    private boolean convertToBoolean(String arg, String key){
        try{
            boolean b = Boolean.parseBoolean(arg);
            return b;
        }
        catch(NumberFormatException e){
            String exceptionMessage = getUsage() + "\n" + programName + ".java: error: argument " + key + ": invalid boolean value: " + arg;
            throw new NumberFormatException(exceptionMessage);
        }
    }
    
    private float convertToFloat(String arg, String key){
        try{
            float f = Float.valueOf(arg);
            return f;
        }
        catch(NumberFormatException e){
            String exceptionMessage = getUsage() + "\n" + programName + ".java: error: argument " + key + ": invalid float value: " + arg;
            throw new NumberFormatException(exceptionMessage);
        }
    }
    
    public int numberOfPositionalKeys(){
        return positionalKeys.size();
    }
    
    public int numberOfOptionalKeys(){
        return optionalKeys.size();
    }
    
    public int numberOfArgs(){
        return allArgs.size();
    }
    
    public int numberOfTotalKeys(){
        return optionalKeys.size() + positionalKeys.size();
    }
    
    public String getHelpMessage() {
        return helpMessage;
    }
    
    public boolean doesHelpWork(){
        return messageTrue;
    }
    
    public boolean getIllegalArgs()
    {
        return illegalArgs;
    }
    
    public Argument getArg(String key)
    {
        Argument temp = new Argument();
        temp = map.get(key);
        return temp;
    }
    
    public String getPositionalKey(int where)
    {
        String s = positionalKeys.get(where);
        return s;
    }
    
    public String getOptionalKey(int where)
    {
        String s = optionalKeys.get(where);
        return s;
    }
    
    public void programInfo(String name, String description){
        /*String key = "";
        Argument temp = new Positional();
        String[] keyDescription = new String[getNumberOfKeys()];
        for(int i = 0; i < getNumberOfKeys(); i++)
        {
            key = key + " " + getKey(i);
            temp = getArg(getKey(i));
            try{
                keyDescription[i] = temp.getDescription();
            } 
            catch (UnsupportedOperationException ex) {}
        }
        this.programName = name;
        this.programDescription = description;          
        helpMessage = helpMessage + name + key + "\n" + description;
        
        helpMessage = helpMessage + "\nPositional arguments:";
        
        for(int i = 0; i < getNumberOfKeys(); i++)
        {
            helpMessage = helpMessage + "\n" + getKey(i) + " " + keyDescription[i];
        }*/
    }
}
