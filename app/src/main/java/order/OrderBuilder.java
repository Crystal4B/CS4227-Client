package order;

import java.util.Date;

public class OrderBuilder implements Builder{

    private Date startDate;
    private Date endDate;
    private int finalCost;
    private int numberOfOccupants;

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;  
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setFinalCost(int finalCost) {
        this.finalCost = finalCost;
    }

    @Override
    public void setNumberOfOccupants(int numberOfOccupants) {
        this.numberOfOccupants = numberOfOccupants;
    }

    public Order getOrder(){
        return new Order(startDate, endDate, finalCost, numberOfOccupants);
    }
    
}
