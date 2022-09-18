
package entityClass;

import java.text.SimpleDateFormat; //import for formatting the dates
import java.util.Date; //import for using date and time purpose

public class Order{
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy"); //used for formatting the date
    Date orderDate = new Date(); //used for store the order date
    private int orderID = 1000; //used for store the order ID and default as 1000
    private int quantity; //used for store the quantity of food in each order
    private static int increment = 0; //used for increase the order ID number
    private Food food; //used for access the food entity class

    //no arg constructor
    public Order(){};
    
    //arg constructor for setting the order details
    public Order(Date orderDate, Food food, int quantity){
        this.orderID += increment++;
        this.orderDate = orderDate;
        this.food = food;
        this.quantity = quantity;
    }
    
    //arg constructor for adding a new order
    public Order(Food food, int quantity) {
        this.orderID += increment++;
        this.food = food;
        this.quantity = quantity;
    }
    
    public String getOrderDate(){
        return formatDate.format(orderDate);
    }

    public int getOrderID() {
        return orderID;
    }
   
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format(">> %6s   %7d    %s %10d"
                ,getOrderDate() ,getOrderID() , food, getQuantity());
    }
    
}
