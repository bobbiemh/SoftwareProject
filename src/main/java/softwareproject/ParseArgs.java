
package softwareproject;

import java.util.*;
import java.lang.*;


public class ParseArgs{
    private Map<String, Argument> map;
    private Map<String, String> shmap;
    
    private List<String> positionalKeys;
    private List<String> optionalKeys;
    
    private boolean messageTrue;
    
    private String programName;
    private String programDescription;
    private String helpMessage;
    
    private Map<Argument.Type, Object> defaultArgs;
    
    public ParseArgs() {
        map = new HashMap<String, Argument>();
        shmap = new HashMap<String, String>();
        
        positionalKeys = new ArrayList<String>();
        optionalKeys = new ArrayList<String>();
            
        messageTrue = false;
        
        helpMessage = "usage: java ";        
    }
    
    public void addPos(String name, String description, Argument.Type type)
    {
        Positional temp = new Positional();
        positionalKeys.add(name);
        temp.setDescription(description);
        temp.setType(type);
        map.put(name, temp);
    }
    
    public void addOpt(String name, Object defaultValue, Argument.Type type, boolean required){
        Optional temp = new Optional();
        optionalKeys.add(name);
        temp.setType(type);
        temp.setRequired(required);
        temp.setDefault(defaultValue);
        
        String shorthand = "";
        if(("-" + name.charAt(0)) != "-h") 
            shorthand = "-" + name.charAt(0);
        else
            shorthand = "-";
        temp.setShortHand(shorthand);
        map.put(name, temp);
        shmap.put(shorthand, name);
    }
    
    public void setShortHand(String key, String shorthand){
        if(shorthand == "-h")
            throw new IllegalArgumentException("-h is used for only help");
        Argument temp = getArg(key);
        temp.setShortHand(shorthand);
        map.put(key,temp);
        shmap.put(shorthand, key);
    }
    
    public void parse(String[] args)
    {
        
        List<String> allArgs = new ArrayList<String>();
        for(int i = 0; i < args.length; i++) {
            allArgs.add(args[i]);
        }
        checkHelp(allArgs);
        putToMap(allArgs);
    }
    
    private void checkHelp(List<String> allArgs){
        if(allArgs.contains("--help") || allArgs.contains("-h")) {
            messageTrue = true;
            throw new IllegalArgumentException(helpMessage);
        }
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
    
    private void putToMap(List<String> allArgs){
        
        
        int posCount = 0;
        for(int i = 0; i < allArgs.size(); i++){
            String key = "";
            String arg = allArgs.get(i);
            if(arg.startsWith("--")){
                if(map.containsKey(arg.substring(2))){
                    key = arg.substring(2);
                    i++;
                    arg = allArgs.get(i);
                }
            }
            else if(arg.startsWith("-")){
                if(shmap.containsKey(arg)){
                    key = shmap.get(arg);
                    Argument temp = getArg(key);
                    Argument.Type type = temp.getType();
                    if(type != Argument.Type.BOOLEAN)
                    {
                        i++;
                        arg = allArgs.get(i);
                    } 
                }
                else
                    throw new IllegalArgumentException("the argument does not exist");
            }
            else{
                if(posCount >= positionalKeys.size())
                {
                    throw new IllegalArgumentException("oh no");
                }
                else{
                    key = positionalKeys.get(posCount);
                    posCount++;
                }
            }

            Argument temp = getArg(key);
            Argument.Type type = temp.getType();
            if(type == Argument.Type.INT){
                temp.setValue(convertToInt(arg, key));
            }
            else if(type == Argument.Type.FLOAT){
                temp.setValue(convertToFloat(arg, key));
            }
            else if(type == Argument.Type.BOOLEAN){
                //do later
                
                temp.setValue(true);
            }
            else
                temp.setValue(arg);
            map.put(key, temp);
        }
        if(!messageTrue)
        {
            if(posCount < positionalKeys.size()){
                throw new IllegalArgumentException("oh no");
            }            
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
            if (arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("t"))
            {
                return true;
            }
            else if (arg.equalsIgnoreCase("false") || arg.equalsIgnoreCase("f"))
            {
                return false;
            }
            else
            {                
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
    
    public int numberOfArgs(List<String> allArgs){
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
    
    public Argument getArg(String key)
    {
        Argument temp = new Argument();
        temp = map.get(key);
        return temp;
    }
    
    public Object getValue(String key)
    {
        Argument temp = getArg(key);
        return temp.getValue();
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
        String key = "";
        Argument temp = new Positional();
        this.programName = name;
        this.programDescription = description;
        for(String k : positionalKeys)
        {
            key = key + " " + k;
        }          
        helpMessage = helpMessage + name + key + "\n" + description;
        
        helpMessage = helpMessage + "\nPositional arguments:";
        
        for(String k : positionalKeys)
        {
            temp = getArg(k);
            helpMessage = helpMessage + "\n" + k + " " + temp.getDescription();
        }
    }
}
