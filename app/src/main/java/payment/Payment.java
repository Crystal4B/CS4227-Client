package payment;


import requestsystem.commands.CommandInvoker;
import requestsystem.commands.reservations.UpdateReservationPaidCommand;

/**
 * @author Eoin McDonough
 * Payment class to handle payment states
 */

public class Payment {
    private double finalCost;
    private boolean isPaid = false;
    CommandInvoker invoker;

    /**
     * Sets the cost of payment
     * @param cost Cost of payment
     */
    
    public void setCost(double cost) {
        finalCost = cost;
    } 

    /**
     * Notifies server that this reservation needs to be paid
     * 
     * @param orderId Id of the order to be paid
     * @return Response of server updating
     */
    
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

    /**
     * Notifies server that this reservation has been paid by card
     * 
     * @param orderId Id of the order to be paid
     * @param cardNo Card details
     * @param cardName Card details
     * @param cardDate Card details
     * @param cardBack Card details
     * @return Response of server updating
     */

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

    /**
     * When guest pays at reception, accessible through staff menu
     * 
     * @param orderId Id of the order to be paid
     * @param amount Amount to be paid
     * @return Sets paid state to true or false
     */

    public void payAtReception(int orderId, double amount) {
        if (amount < finalCost){
            finalCost = finalCost - amount;
        }
        else {
            finalCost = 0;
            isPaid = true;
            invoker.setCommand(new UpdateReservationPaidCommand(orderId, isPaid));
            invoker.execute();
        }
    }

    /**
     * Validated CreditCard details
     * 
     * @param name Variable of credit card e.g "CardName", "CardBack"
     * @param toCheck Value of name parameter
     * @return Returns validation state
     */

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
            default:
                return false;
        }
		return false;
    }

    /**
     * Validated CreditCard number details 
     * 
     * @param name Variable of credit card e.g "cardNo"
     * @param toCheck Value of name parameter
     * @return Returns validation state
     */

    public boolean isValid (String name, String toCheck) {
        if(name.equals("cardNo")) {   
                if(toCheck.length() == 16)  {
                    return true;
                }
                else{
                    return false;
                }
        }
        else{
		    return false;
        }
    }

    /**
     * Sets isPaid variable
     * 
     * @return isPaid state
     */

    public boolean isPaid() {
        return isPaid;
    }
}

