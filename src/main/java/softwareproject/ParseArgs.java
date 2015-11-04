
package softwareproject;

import java.util.*;
import java.lang.*;


public class ParseArgs{
    private Map<String, Argument> map;
    private List<String> keys;
    private List<String> args;
    private List<String> copy;//keys that need args
    
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
        keys = new ArrayList<String>();
            
        messageTrue = false;
        illegalArgs = false;
        argRequired = true;
        
        dashType = "box";
        digit = "4";
        
        helpMessage = "usage: java ";
        
        args = new ArrayList<String>();
        copy = new ArrayList<String>();
    }
    
    public void addPos(String name, String description, Argument.Type type)
    {
        Argument temp = new Positional();
        keys.add(name);
        temp.setDescription(description);
        temp.setType(type);
        map.put(name, temp);
    }
    
    public void addOpt(String name, String shortHand, String defaultOption, Argument.Type type){
        Argument temp = new Optional();
        keys.add(name);
        temp.setShort(shortHand);
        temp.setType(type);
        map.put(name, temp);
    }
    
    public void parse(String[] args)
    {
        for(int i = 0; i < args.length; i++) {
            this.args.add(args[i]);
        }
        checkDashes();
        if(!messageTrue)
        {
            String exceptionMessage = "Error: the following Argument are required: ";
            if(getNumberOfArgs() < needToSet() || getNumberOfArgs() > needToSet())
                illegalArgs = true;
            if(getNumberOfArgs() == 0 && illegalArgs){
                for(int i = 0; i < needToSet(); i++)
                {
                    exceptionMessage = exceptionMessage + " " + getCopy(i);
                }
                throw new IllegalArgumentException(exceptionMessage);
            }
            else if(getNumberOfArgs() < needToSet() && illegalArgs)
            {                    
                for(int i = 0; i <= getNumberOfArgs(); i++)
                {
                    exceptionMessage = exceptionMessage + " " + getCopy(i);
                }
                throw new IllegalArgumentException(exceptionMessage);        
            }
            else if (getNumberOfArgs() > needToSet())
            {
                int a = this.args.size() - 1;
                String temp = args[a];
                exceptionMessage = "usage: java " + programName;
                for(int i = 0; i < needToSet(); i++)
                {
                    exceptionMessage = exceptionMessage + " " + getCopy(i);
                }
                exceptionMessage = exceptionMessage + programName + ".java: error: unrecognized Argument: " + temp;
                throw new IllegalArgumentException(exceptionMessage);
            }
            putToMap();
        }
    }
    
    private void checkDashes(){
        for(int i = 0; i < getNumberOfKeys(); i++){
            copy.add(keys.get(i));
        }
        if(args.contains("--help")) {
            messageTrue = true;
            throw new IllegalArgumentException(helpMessage);
        }
        if(keys.contains("type"))
        {
            if(args.contains("--type")) {
                if(argRequired){
                    int i = args.indexOf("--type");
                    setType(args.get(i+1));
                    Argument temp = getArg("type");
                    temp.setValue(getType());
                    map.put("type", temp);
                    args.remove(i+1);                    
                    args.remove(i);
                    copy.remove("type");
                }
                else if(!argRequired){
                    int i = args.indexOf("--type");
                    args.remove(i+1);
                    args.remove("--type");
                    keys.remove("type");
                    copy.remove("type");
                }
            }
            else{
                if(argRequired)
                {
                    Argument temp = getArg("type");
                    temp.setValue(getType());
                    map.put("type", temp);
                    copy.remove("type");
                }
            }
        }
        if(keys.contains("digit"))
        {
            if(args.contains("--digit")) {
                if(argRequired){
                    int i = args.indexOf("--digit");
                    setDigit(args.get(i+1));
                    Argument temp = getArg("digit");
                    int num = Integer.parseInt(args.get(i+1));
                    temp.setValue(num);
                    map.put("digit", temp);
                    args.remove(i+1);
                    args.remove(i);
                    copy.remove("digit");
                }
                else if(!argRequired){
                    int i = args.indexOf("--digit");
                    args.remove(i+1);
                    args.remove(i);
                    keys.remove("digit");
                    copy.remove("digit");
                }
            }
            else
                if(argRequired){
                    Argument temp = getArg("digit");
                    int num = Integer.parseInt(getDigit());
                    temp.setValue(num);
                    map.put("digit", temp);
                    copy.remove("digit");
                }
        }
        args.removeAll(Collections.singleton(null));
    }
    
    public String getUsage() {
        String message = "usage: java " + programName;
        for(int a = 0; a < getNumberOfKeys(); a++) {
            message = message + " " + getKey(a);
        }
        return message;
    }
    
    private void putToMap(){
        int i = 0;
        for(i = 0; i < needToSet(); i++){
            
            String key = copy.get(i);           
            Argument temp = new Argument();
            temp = getArg(key);
            Argument.Type type = temp.getType();
            
            String exceptionMessage = "usage: java " + programName;
            for(int a = 0; a < needToSet(); a++) {
                exceptionMessage = exceptionMessage + " " + getCopy(a);
            }
            exceptionMessage = exceptionMessage + "\n" + programName + ".java: error: argument " + getCopy(i) + ": invalid ";
            
            
            if(type == Argument.Type.INT){
                temp.setValue(convertToInt(args.get(i), getCopy(i)));
            }
            else if(type == Argument.Type.BOOLEAN){
                temp.setValue(convertToBoolean(args.get(i), getCopy(i)));
            }
            else if(type == Argument.Type.FLOAT){
                temp.setValue(convertToFloat(args.get(i), getCopy(i)));
            }
            else{
                temp.setValue(convertToString(args.get(i)));
            }
            map.put(key, temp);
        }
        
        while(i <= this.args.size() && i > needToSet())
        {
            String str = this.args.get(args.size() - 1);
        }
    }
    
    public void setType(String dashType){
        this.dashType = dashType;
    }
    
    public String getType(){
        return dashType;
    }
    
    public void setDigit(String digit){
        this.digit = digit;
    }
    
    public String getDigit(){
        return (digit);
    }
    
    private int needToSet(){
        return copy.size();
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
    
    public String getCopy(int where)
    {
        String s = copy.get(where);
        return s;
    }
    
    public String getKey(int where)
    {
        String s = keys.get(where);
        return s;
    }
    
    public int getNumberOfArgs(){
        return args.size();
    }
    
    public int getNumberOfKeys(){
        return keys.size();
    }
    public void programInfo(String name, String description){
        String key = "";
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
        }
    }
}
