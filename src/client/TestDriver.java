
package client;

import java.util.Scanner; //import for getting user input
import entityClass.*; //import for using entityClass package java file
import adt.*; //import for using adt package java file
import java.text.ParseException; //import for parsing a String that is ought to have a special format which is Date data type
import java.text.SimpleDateFormat; //import for formatting the date
import java.util.Iterator; //import for using loop through collections, which is ArrayList and ArrayQueue

public class TestDriver {
    
    //declare object and variable
    static QueueInterface<Order> orderQueue = new ArrayQueue<>(); //used for arrayQueue adt to store the order value
    static ListInterface<Food> foodList = new ArrayList<>(); //used for arrayList adt to store the food value
    static Order newOrder = new Order(); //used for store the new order value
    static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy"); //used for formatting the date
    static Scanner input = new Scanner(System.in); //used for getting user input
    static String userInput; //used for temporary store each user input value
    static char confirmation = '\0'; //used for temporary confirmation value
    static char exit = '\0'; //used for temporary store exit value
    static int selection = 0; //used for temporary store selection value

    //throws ParseException to parse a String that is ought to have a special format  
    public static void main(String[] args) throws ParseException { //which is used for convert String to Date data type.
        
        //Food Menu List        
        foodList.add(new Food(1, "Fried Rice", 6.50));
        foodList.add(new Food(2, "Noodles", 6.00));
        foodList.add(new Food(3, "Burger", 5.50));
        foodList.add(new Food(4, "Pizza", 6.50));
        foodList.add(new Food(5, "Hot Dog", 5.00));
        foodList.add(new Food(6, "Sandwich", 4.50));
        foodList.add(new Food(7, "Cake", 7.00));
        foodList.add(new Food(8, "Salads", 4.00));
        foodList.add(new Food(9, "Ice Cream", 3.00));
        foodList.add(new Food(10, "Pasta", 8.50));

        //Order List
        orderQueue.enqueue(new Order(formatDate.parse("02/01/2022"), new Food(2, "Noodles", 6.00), 2));
        orderQueue.enqueue(new Order(formatDate.parse("04/03/2022"), new Food(4, "Pizza", 6.50), 1));
        orderQueue.enqueue(new Order(formatDate.parse("06/05/2022"), new Food(6, "Sandwich", 4.50), 3));
        orderQueue.enqueue(new Order(formatDate.parse("08/07/2022"), new Food(8, "Salads", 4.00), 4));
        orderQueue.enqueue(new Order(formatDate.parse("10/09/2022"), new Food(10, "Pasta", 8.50), 1));
        
        mainMenu();
    }
    
    //main menu
    public static void mainMenu(){
 
        do {
            //reset all the value
            userInput = ""; 
            selection = 0;
            confirmation = '\0';
            exit = '\0'; 
        
            System.out.println("\n------------------------------------------------------------------\n" +
                               "                      Order Management Menu                       \n" +
                               "------------------------------------------------------------------\n" +
                               ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                               ">>      1. Add new order                                          \n" +
                               ">>      2. Edit order                                             \n" +
                               ">>      3. Delete order                                           \n" +
                               ">>      4. View order                                             \n" +
                               ">>      0. Exit                                                   \n" +
                               ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                               ">>");
            //prompt to the user enter the option
            do {
                System.out.printf(">> Please enter your option: ");
                //validation check the user input value and convert it into int variable then assign into option variable
                userInput = validationCheck("ValidateNumberFormat", userInput); //validation check user input format
                selection = Integer.parseInt(validationCheck("ValidateMenuOption", userInput)); //validation check value
                
                //identify which function called by user
                switch (selection) {
                    case 1:
                        addOrder(); //add Order function
                        break;
                    case 2:
                        editOrder(); //edit Order function
                        break;
                    case 3:
                        deleteOrder(); //delete Order function
                        break;
                    case 4:
                        viewOrder("ViewOrder"); //view Order function
                        break;
                    case 0:
                        exit = promptMessage(" ", "ExitMsg"); //exit the program
                        break;
                    default:
                        break;
                }
             //while the user input the option value not in between 1-4 will loop 
            } while (selection < 0 && selection > 5); 
        //loop until user input 0 which is exit variable value change to true    
        }while(exit != 'E');
        System.exit(0); //terminate whole program
    }
    
    //display Food menu list
    public static void displayFoodMenu() {
        System.out.println(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                           ">>   Food ID             Food Name           Food Price          \n" +
                           ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //used iterator to display food list
        Iterator<Food> displayList = foodList.getIterator();
        while(displayList.hasNext()){
            System.out.printf(">> ");
            System.out.println(displayList.next());
        }
        System.out.println(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                           ">>");
    }
    
    //add Order function
    public static void addOrder() {
        
        do{
            //reset all the value
            userInput = ""; 
            selection = 0;
            confirmation = '\0';
            exit = '\0'; 

            //generate a new Order ID
            int newOrderID = newOrder.getOrderID() + orderQueue.getNumberOfEntries() + 1; 

            System.out.println("\n------------------------------------------------------------------\n" +
                               "                         Add Order Menu                           \n" +
                               "------------------------------------------------------------------");
            displayFoodMenu();//display Food menu
            System.out.println(">> Your OrderID (*Order ID will generated by system automatcally*): " + newOrderID);
            
            //prompt to the user to input the Food ID
            System.out.print(">> Select the \"Food ID\" that you want to order (Type {0} to exit) : " );
            userInput = validationCheck("ValidateNumberFormat", userInput); //validation check user input format
            selection = Integer.parseInt(validationCheck("ValidateFoodID", userInput)); //validation check value

            //if the user input is 0
            if(selection == 0){
                //prompt exit msg in add menu page and receive char value assign to exit variable
                exit = promptMessage("AddMenu","ExitMsg"); 
                
                //if exit equal to 'E' will exit to main menu page
                if(exit == 'E'){
                    mainMenu();
                }
                else { //else continue the add menu page
                    continue; 
                }
            }
            
            //prompt to the user to input the quantity of Food
            System.out.print(">> Enter the \"Quantity\" of food that you want to order: ");  
            //validate to check user input format then assign the value into quantity variable 
            userInput = validationCheck("ValidateNumberFormat", userInput); 
            int quantity = Integer.parseInt(validationCheck("ValidateQuantity", userInput));
            
            //prompt to the user make double confirmation when adding the order
            confirmation = promptMessage("AddMenu", "ConfirmationMsg");

            //initialize the variables
            String foodName = " ";
            double foodPrice = 0.00;

            //if confirmation equal to 'Y' will add the new order inside array queue
            if(confirmation == 'Y'){
                //search the food id from food arrayList to identify food name and price //adt food arrayList
                for (int i = 1; i <= foodList.getNumberOfEntries(); i++) {
                    if(selection == foodList.getEntry(i).getFoodID()){
                        foodName = foodList.getEntry(i).getFoodName();
                        foodPrice = foodList.getEntry(i).getFoodPrice();
                    }
                }
                orderQueue.enqueue(new Order(new Food(selection, foodName, foodPrice) , quantity));
                System.out.println(">> Add New Order Successfully~");
            }
            else { //else the new Order will no add in array queue
                System.out.println(">> No New Order Will be Added.");
            }
            
            //prompt to the user continue or exit the add order menu
            exit = promptMessage("AddMenu", "ExitMsg");

        }while(exit != 'E'); //loop until exit equal to E
        
        mainMenu(); //exit to main menu page
    }
    
    //edit Order function
    public static void editOrder(){
        
        do{
            //reset all the value
            userInput = ""; 
            selection = 0;
            confirmation = '\0';
            exit = '\0'; 

            System.out.printf("\n----------------------------------------------------------------------------------------------\n" +
                              "                                        Edit Order Menu                                       \n" +
                              "----------------------------------------------------------------------------------------------\n");
            viewOrder("EditOrder");//display Order list which in edit order menu
            System.out.print(">> Select the \"Order ID\" that you want to delete (Type {0} to exit): ");
            userInput = validationCheck("ValidateNumberFormat", userInput); //validation check user input format
            selection = Integer.parseInt(validationCheck("ValidateOrderID", userInput)); //validation check value
           
            //if the user input is 0
            if(selection == 0){
                //prompt exit msg in add menu page and receive char value assign to exit variable
                exit = promptMessage("EditMenu","ExitMsg");
                
                //if exit equal to 'E' will exit to main menu page
                if(exit == 'E'){
                    mainMenu();
                }
                else { //else continue the edit order menu
                    continue; 
                }
            }
            
            //prompt the user input the new quantity
            System.out.print(">> Enter the New Quantity: ");
            //validate to check user input format then assign the value into newQuantity variable  
            userInput = validationCheck("ValidateNumberFormat", userInput); 
            int newQuantity = Integer.parseInt(validationCheck("ValidateQuantity", userInput));

            //prompt to the user to make double confirmation when editing the order 
            confirmation = promptMessage("EditMenu", "ConfirmationMsg");
            
            //if confirmation equal to 'Y' will update the new quantity of the order
            if(confirmation == 'Y'){
                //search the order id from order arrayQueue to change the quantity of the order //adt order arrayQueue
                for (int i = 0; i <= orderQueue.getNumberOfEntries(); i++) {
                    Order order = orderQueue.dequeue();
                    if (order.getOrderID() == selection) {
                        order.setQuantity(newQuantity);
                    }
                    orderQueue.enqueue(order);
                }
                System.out.println(">> Update New Quantity Successfully~");
            }
            else { //else the new Order will no add in queue
                System.out.println(">> No Changes Will Be Made.");
            }
            
            //prompt to the user continue or exit 
            exit = promptMessage("EditMenu", "ExitMsg");

        }while(exit != 'E'); //loop until exit equal to E
        
        mainMenu(); //exit to main menu page
    }
    
    //delete Order function
    public static void deleteOrder(){
        do {
            //reset all the value
            userInput = ""; 
            selection = 0;
            confirmation = '\0';
            exit = '\0'; 
            
            System.out.printf("\n----------------------------------------------------------------------------------------------\n" +
                              "                                       Delete Order Menu                                      \n" +
                              "----------------------------------------------------------------------------------------------\n");
                             
            viewOrder("DeleteOrder"); //display Order list which in delete order menu
            System.out.print(">> Select the \"Order ID\" that you want to delete (exit press {0}, delete all press {1}): ");
            userInput = validationCheck("ValidateNumberFormat", userInput); //validation check user input format
            selection = Integer.parseInt(validationCheck("ValidateOrderID", userInput)); //validation check value

            //if the user input is 0
            if(selection == 0){
                //prompt exit msg in delete menu page and receive char value assign to exit variable
                exit = promptMessage("DeleteMenu","ExitMsg");
                
                //if exit equal to 'E' will exit to main menu page
                if(exit == 'E'){
                    mainMenu();
                }
            }

            //prompt to the user to make double confirmation when deleting the order 
            confirmation = promptMessage("DeleteMenu", "ConfirmationMsg");

            //if confirmation equal to 'Y' will delete the order from queue //adt order arrayQueue
            if(confirmation == 'Y'){
                //if the user input is 1 will delete all the order
                if(selection == 1){
                    orderQueue.clear();
                }
                else {
                    Iterator<Order> orderList = orderQueue.getIterator();
                    int index = 0;
                    while(orderList.hasNext()){
                        if (orderList.next().getOrderID() == selection) {
                            orderQueue.remove(index);
                            System.out.println(">> Delete the Order Successfully~");
                            break;
                        }  
                        index++;
                    }
                }
            }
            else { //else the order will no be delete from queue
                System.out.println(">> No Changes Will Be Made.");
            }
            //prompt to the user continue or exit 
            exit = promptMessage("DeleteMenu", "ExitMsg");
        }while(exit != 'E'); //loop until exit equal to E
        
        mainMenu(); //exit to main menu page
    }
    
    //view Order list function
    public static void viewOrder(String functionType){
        
        do{
            //reset all the value
            userInput = ""; 
            selection = 0;
            confirmation = '\0';
            exit = '\0';
            
            if(functionType.equals("ViewOrder")){ //used for display the title when the user select the view order menu only
                System.out.printf("\n----------------------------------------------------------------------------------------------\n" +
                              "                                        View Order Menu                                       \n" +
                              "----------------------------------------------------------------------------------------------\n");
            }
            
            System.out.printf(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                              ">>    Date        OrderID       Food ID         Food Name           Food Price       Quantity \n" +
                              ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            //display Order list
            Iterator<Order> displayOrder = orderQueue.getIterator();
            while(displayOrder.hasNext()){
                System.out.println(displayOrder.next());
            }
            System.out.println(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                               ">> ");
            
            if(functionType.equals("ViewOrder")){ //used for prompt the msg when the user select the view order menu only
                do{
                    System.out.print(">> Enter {0} to exit else continue to view the order list: ");
                    selection = Integer.parseInt(validationCheck("ValidateNumberFormat", userInput)); //validation check user input format //validation check format

                    //if the user input is 0
                    if(selection == 0){
                        //prompt exit msg in delete menu page and receive char value assign to exit variable
                        exit = promptMessage("ViewMenu","ExitMsg");

                        //if exit equal to 'E' will exit to main menu page
                        if(exit == 'E'){
                            mainMenu();
                        }
                    }
                }while(selection != 0); //loop until selection equal to 0  
            }            
        }while(exit == 'S'); //loop until exit equal to S
    }
    
    //prompt message function
    public static char promptMessage(String functionType, String messageType){
        
        //declare variable
        String functionName; //used for store each function name of system
        
        //identify function type of system
        switch(functionType){
            case "AddMenu":
                functionName = "Add New Order";
                break;
            case "EditMenu":
                functionName = "Edit Order";
                break;
            case "DeleteMenu":
                functionName = "Delete Order";
                break;
            case "ViewMenu":
                functionName = "View Order";
                break;
            default:
                functionName = "Main";
                break;
        }
        
        //identify message type to prompt to the user
        switch(messageType){
            case "ConfirmationMsg":
                System.out.printf("\n>> Are your confirm \"" + functionName + "\"? (Yes{Y}, No{N}) : ");
                return validationCheck("ValidateConfirmValue", userInput).charAt(0); 

            case "ExitMsg":
                System.out.printf("\n>> Are your sure to exit \"" + functionName + " Menu\"? (Stay{S}, Exit{E}) : ");
                return validationCheck("ValidateExitValue", userInput).charAt(0); 
        
            default: 
                return exit;
        }
    }
    
    //do validation check for each input
    public static String validationCheck(String validateType, String userInput) {

        //identify which type of validation
        switch(validateType){ 
            
            case "ValidateNumberFormat": 
                while (true) {
                    try {
                        userInput = input.next();
                        Integer.parseInt(userInput); //convert it into int value
                    } catch (NumberFormatException ex) { //validate number format
                        System.out.printf("\n>> !!INVALID option!! The selection must in \"numbering\" only!\n");
                        System.out.printf(">> Please \"RE-ENTER\" the selection again: ");
                        continue;
                    }
                    break;
                }
                return userInput;
            
            case "ValidateMenuOption": 
                do{
                    //if user input less than 0 or greater than 4 will prompt out error msg
                    if(Integer.parseInt(userInput) < 0 || Integer.parseInt(userInput) > 4){
                       System.out.printf("\n>> !!INVALID option!! The selection must in \" 0 to 4\" only!\n");
                       System.out.printf(">> Please \"RE-ENTER\" the selection again: ");
                       //user input will be validate check again the number format
                       userInput = validationCheck("ValidateNumberFormat", userInput);
                    }
                    //loop until user input between 0 and 4 value
                }while(Integer.parseInt(userInput) < 0 || Integer.parseInt(userInput) > 4); 
                return userInput;
                
            case "ValidateConfirmValue": 
                do{
                    userInput = input.next();
                    //if the user input not equal Y or N will prompt the error msg
                    if(!userInput.toUpperCase().equals("Y") && !userInput.toUpperCase().equals("N")){
                       System.out.printf("\n>> !!INVALID selection!! The selection must in alphabet \"Y\" and \"N\" only!\n");
                       System.out.printf(">> Please \"RE-ENTER\" the selection again (Yes{Y}, No{N}): ");
                    }
                    //loop until user input Y or N
                }while(!userInput.toUpperCase().equals("Y") && !userInput.toUpperCase().equals("N")); 
                return userInput.toUpperCase(); //convert the user input to upper case when return
                   
            case "ValidateExitValue": 
                do{
                    userInput = input.next();
                    //if the user input not equal S or E will prompt the error msg
                    if(!userInput.toUpperCase().equals("S") && !userInput.toUpperCase().equals("E")){
                       System.out.printf("\n>> !!INVALID option!! The selection must in alphabet \"S\" and \"E\" only!\n");
                       System.out.printf(">> Please \"RE-ENTER\" the selection again (Stay{S}, Exit{E}): ");
                    }
                    //loop until user input S or E
                }while(!userInput.toUpperCase().equals("S") && !userInput.toUpperCase().equals("E")); 
                return userInput.toUpperCase(); //convert the user input to upper case when return
   
            case "ValidateFoodID": 
                //declare frist food ID and last food ID variable 
                int first_FoodID = foodList.getEntry(1).getFoodID();
                int last_FoodID = foodList.getNumberOfEntries();
                do{
                    //if user input 0 means exit the menu
                    if(Integer.parseInt(userInput) == 0){ 
                        return userInput; //it will return the 0 value to main menu to prompt the exit msg
                    }
                    //if the user input the value less than first food ID or geater than the last foodID will prompt out error msg
                    if(Integer.parseInt(userInput) < first_FoodID || Integer.parseInt(userInput) > last_FoodID){
                       System.out.printf("\n>> !!INVALID input!! The Food ID \"" + first_FoodID + " until " + last_FoodID + "\" only!\n");
                       System.out.printf(">> Please \"RE-ENTER\" the Food ID again: ");
                       //user input will be validate check again the number format
                       userInput = validationCheck("ValidateNumberFormat", userInput);
                   }
                   //loop until user input between first food ID and last food ID value
                }while(Integer.parseInt(userInput) < first_FoodID || Integer.parseInt(userInput) > last_FoodID); 
                return userInput;
  
            case "ValidateOrderID": 
                //declare first order and ID last order ID variable  
                if(orderQueue.isEmpty()){
                    System.out.println(">> You doesn't have order any food please go to add order menu add order.");
                    return (userInput = "0");
                }
                int first_OrderID = orderQueue.getFront().getOrderID();
                int last_OrderID = newOrder.getOrderID() + orderQueue.getNumberOfEntries();
                do{
                    //if user input 0 means exit the menu or 1 means delete all the order
                    if(Integer.parseInt(userInput) == 0 || Integer.parseInt(userInput) == 1){ 
                        return userInput;
                    }
                    //else if the user input the value less than first orderID or geater than the last orderID will prompt out error msg
                    else if(Integer.parseInt(userInput) < first_OrderID || Integer.parseInt(userInput) > last_OrderID){
                       System.out.printf("\n>> !!INVALID input!! The order ID \"" + first_OrderID + " until " + 
                               last_OrderID + "\" only!\n");
                       System.out.printf(">> Please \"RE-ENTER\" the order ID again: ");
                       //user input will be validate check again the number format
                       userInput = validationCheck("ValidateNumberFormat", userInput);                       
                    }
                    //loop until user input between first order ID and last order ID value
                }while(Integer.parseInt(userInput) < first_OrderID || Integer.parseInt(userInput) > last_OrderID);   
                return userInput;
            
            case "ValidateQuantity":
                do{
                    if(Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > 50){
                        System.out.printf("\n>> !!INVALID input!! The order quantity min is 1 unit and max is 50 unit!\n");
                       System.out.printf(">> Please \"RE-ENTER\" the quantity again: ");
                       //user input will be validate check again the number format
                       userInput = validationCheck("ValidateNumberFormat", userInput);
                    }
                }while(Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > 50);

            default:
                return userInput;
        }
    }   
}
