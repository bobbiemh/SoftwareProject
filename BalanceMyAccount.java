import softwareproject.*;
import java.io.*;

public class BalanceMyAccount {
    
        public static void main(String[] args) {
        
        ParseArgs p = new ParseArgs();
        
        p.programInfo("balanceMyAccount", "Balances your account.");
        p.addPos("startingCheckingAccount", "the name of the dog", Argument.Type.INT);
        
        p.addOpt("deposit", "the amount of deposit", Argument.Type.INT);
        p.setShortHand("deposit", "d");
        p.addOpt("withdrawal", "the amount of the withdrawal", Argument.Type.INT);
        p.setShortHand("withdrawal", "w");
        
        p.parse(args);
        
        int startingCheckingAccount = p.getValue("startingCheckingAccount");
        int deposit = p.getValue("deposit");
        int withdrawal = p.getValue("withdrawal");
        
        int endingCheckingAccount = startingCheckingAccount + deposit - withdrawal;
        
        if(endingCheckingAccount < startingCheckingAccount) {
            System.out.println("Wow. You should really get that spending habit checked out. Better luck next time.");
        }
        
    }
}