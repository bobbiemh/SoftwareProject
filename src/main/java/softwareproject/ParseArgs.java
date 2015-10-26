
package softwareproject;

import java.util.*;
import java.lang.*;


public class ParseArgs{
    private Map<String, Argument> map;
    private List<String> keys;
    private ArrayList<String> args;
    private boolean messageTrue;
    private boolean illegalArgs;
    private String programName;
    private String programDescription;
    private String helpMessage;
    
    
    
    public ParseArgs() {
            map = new HashMap<String, Argument>();
            keys = new ArrayList<String>();
            
            messageTrue = false;
            illegalArgs = false;
            helpMessage = "usage: java ";
            
            args = new ArrayList<String>();
    }
    
    public void addArgs(String userInput, String Description, Argument.Datatype datatype)
    {
        Argument temp = new Argument();
        keys.add(userInput);
        temp.setDescription(Description);
        temp.setDataType(datatype);
        map.put(userInput, temp);
    }
    
    public void parse(String[] args)
    {
        for(int i = 0; i < args.length; i++) {
            this.args.add(args[i]);
        }
        if(args[0].equals("--help"))
        {
            messageTrue = true;
            throw new IllegalArgumentException(helpMessage);
        }
        else
        {
                String exceptionMessage = "Error: the following Argument are required: ";
            if(args.length < getNumberOfKeys() || args.length > getNumberOfKeys())
                illegalArgs = true;
            if(args.length == 0 && illegalArgs){
                    for(int i = 0; i < getNumberOfKeys(); i++)
                    {
                        exceptionMessage = exceptionMessage +" "+ getKey(i);
                    }
                    throw new IllegalArgumentException(exceptionMessage);
                }
            else if(args.length < getNumberOfKeys() && illegalArgs)
            {
                    
                            for(int i = args.length-1; i < getNumberOfKeys(); i++)
                            {
                                exceptionMessage = exceptionMessage +" "+ getKey(i);
                            }
                                throw new IllegalArgumentException(exceptionMessage);
                    
                }
            else if (args.length > getNumberOfKeys())
            {
                int a = args.length - 1;
                String temp = args[a];
                exceptionMessage = "usage: java "+programName;
                for(int i = 0; i < getNumberOfKeys(); i++)
                    {
                        exceptionMessage = exceptionMessage +" "+ getKey(i);
                    }
                    exceptionMessage = exceptionMessage + programName + ".java: error: unrecognized Argument: "+temp;
                throw new IllegalArgumentException(exceptionMessage);
            }
            putToMap();
        }
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
        for(i = 0; i < getNumberOfKeys(); i++){
            
            String key = keys.get(i);           
            Argument temp = getArg(key);
            Argument.Datatype datatype = temp.getDataType();
            
            String exceptionMessage = "usage: java "+programName;
            for(int a = 0; a < getNumberOfKeys(); a++) {
                exceptionMessage = exceptionMessage +" "+ getKey(a);
            }
            exceptionMessage = exceptionMessage + "\n"+programName+".java: error: argument "+getKey(i)+": invalid ";
            
            
            if(datatype == Argument.Datatype.INT){
                temp.setValue(convertToInt(args.get(i), getKey(i)));
            }
            else if(datatype == Argument.Datatype.BOOLEAN){
                temp.setValue(convertToBoolean(args.get(i), getKey(i)));
            }
            else if(datatype == Argument.Datatype.FLOAT){
                temp.setValue(convertToFloat(args.get(i), getKey(i)));
            }
            else{
                temp.setValue(convertToString(args.get(i)));
            }
            map.put(key, temp);
        }
        
        while(i <= this.args.size() && i > getNumberOfKeys())
        {
            String str = this.args.get(args.size() - 1);
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
            Argument temp = new Argument();
            String[] keyDescription = new String[getNumberOfKeys()];
            for(int i = 0; i < getNumberOfKeys(); i++)
            {
                key = key + " " + getKey(i);
                temp = getArg(getKey(i));
                keyDescription[i] = temp.getDescription();
            }
            this.programName = name;
            this.programDescription = description;          
            helpMessage = helpMessage + name + key + "\n"+description;
            
            helpMessage = helpMessage + "\nPositional arguments:";
            
            for(int i = 0; i < getNumberOfKeys(); i++)
            {
                helpMessage = helpMessage + "\n" + getKey(i) + " " + keyDescription[i];
            }
    }
}
