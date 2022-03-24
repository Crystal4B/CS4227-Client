package payment;


import requestsystem.commands.CommandInvoker;
import requestsystem.commands.UpdateReservationPaidCommand;

public class Payment {
    private double finalCost;
    private boolean isPaid = false;
    CommandInvoker invoker;
    
    public void setCost(double cost) {
        finalCost = cost;
    } 

    // On reservation made, notifies server that this reservation needs to be paid
    public boolean payByCash(int orderId) {
        invoker = new CommandInvoker();
        invoker.setCommand(new UpdateReservationPaidCommand(orderId, isPaid));
        invoker.execute();
            if(invoker.getResponse() == null) {
                return false;
            }
            else {
                return true;
            }
    }

    public boolean payCreditCard(int orderId, String cardNo, String cardName, int cardDate, int cardBack){
        invoker = new CommandInvoker();
        if (isValid("cardNo", cardNo) &&
        isValid("cardDate", cardDate) &&
        isValid("cardBack", cardBack) ) {
            finalCost = 0;
            isPaid = true;
            invoker.setCommand(new UpdateReservationPaidCommand(orderId, isPaid));
            invoker.execute();
            if(invoker.getResponse() == null) {
                return false;
            }
            else {
                return true;
            }
            
        }
        else{
            return false;
        }
    }

    // When guest pays at reception, accessible through staff menu
    public void payAtReception(double amount) {
        double change;
        if(amount>finalCost) {
            change = amount - finalCost ;
            finalCost = 0;
            isPaid = true;
        }
        else if (amount < finalCost){
            finalCost = finalCost - amount;
        }
        else {
            finalCost = 0;
            isPaid = true;
        }
    }

    public boolean isValid (String name, int toCheck) {
        switch (name) {
            case "cardDate":
                if(String.valueOf(toCheck).length() == 4 || String.valueOf(toCheck).length() == 3 ) {
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

    public boolean isPaid() {
        return isPaid();
    }

    //TODO: Get Order object, get Order.getfinalprice
}

