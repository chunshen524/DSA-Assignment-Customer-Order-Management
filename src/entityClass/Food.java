
package entityClass;

public class Food {
    
    private int foodID; //used for store the food ID 
    private String foodName; //used for store food name
    private double foodPrice; //used for store each price of food

    //no arg constructor
    public Food(){}
    
    //arg constructor for setting the food details
    public Food(int foodID, String foodName, double foodPrice) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public int getFoodID() {
        return this.foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return this.foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public String toString() {
        return String.format(" %7d              %-10s           RM %-5.2f "
                , getFoodID(), getFoodName(), getFoodPrice());
        
    }
    
}
