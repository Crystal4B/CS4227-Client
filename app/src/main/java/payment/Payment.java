package payment;

import hotelsystem.room.*;
import order.*;
import java.util.ArrayList;
import billingsystem.*;
import java.util.Random;

public class Payment {
    private double finalCost;
   
    
    public void setCost(double cost) {
        finalCost = cost;
    } 

    public void payByCash() {


    }

    public boolean payCreditCard(String cardNo, String cardName, int cardDate, int cardBack){
        if (isValid("cardNo", cardNo) &&
        isValid("cardDate", cardDate) &&
        isValid("cardBack", cardBack) ) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean payVoucher(int vouchNumber){
        Random random = new Random();
        return random.nextBoolean();
    }

    public double payVoucherDiscount(int vouchNumber){
        Random random = new Random();
        double num =  random.nextDouble();
        if(num > .5) {
            num = 0.5;
        }
        return num;
    }

    public boolean isValid (String name, int toCheck) {
        switch (name) {
            case "cardDate":
                if(String.valueOf(toCheck).length() == 4 ) {
                    return true;
                    
                }
                else{
                    break;
                }
            case "cardBack":
                if(String.valueOf(toCheck).length() == 3 ) {
                    return true;
                }
                else{
                    break;
                }
        }
		return false;
    }

    public boolean isValid (String name, String toCheck) {
        switch (name) {
            case "cardNo":    
                    if(toCheck.length() == 16)  {
                        return true;
                    }
                    else{
                        break;
                    }
        }
		return false;
    }

    //TODO
    /*
    Order object
    Order.getfinalprice
    */
}

