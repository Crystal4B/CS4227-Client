package order;

import java.util.Date;

public class Order {

    private final Date startDate;
    private final Date endDate;
    private final int finalCost;
    private final int numberOfOccupants;
    
    public Order(Date startDate, Date endDate, int finalCost, int numberOfOccupants){
        this.startDate = startDate;
        this.endDate = endDate;
        this.finalCost = finalCost;
        this.numberOfOccupants = numberOfOccupants;
    }   

    public Date getStartDate(){
        return startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public int getFinalCost(){
        return finalCost;
    }

    public int getNumberOfOccupants(){
        return numberOfOccupants;
    }

    @Override
    public String toString() {
        return "Number of Occupants: " + getNumberOfOccupants() + "\tStart Date: " + getStartDate() + "\tEnd Date: " + getEndDate() + "\tCost: EURO " + getFinalCost() ;
    }

}
