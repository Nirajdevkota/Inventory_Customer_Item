
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author niraj
 */
public class RegisterOperations {
   // Queue<Customer> customers =  new LinkedList<Customer>();
    Inventory inventory;
   
                                                        //check out, pass required queue of customer, inventory for items and FileWriter to write to a text file through parameter 
    public void checkOut(Queue<Customer> customers,Inventory inventory, FileWriter writer) throws IOException{            
       Customer cux = new Customer();                                //create costumer object
       
      while(!customers.isEmpty()){                              //if queue is not empty
          cux = customers.remove();                            //custumer.remove removes costumer from list but removed one is assigned to the cux for investigation
          boolean qualify = true;                           //set boolean to check qualification for check out
           System.out.println("Customer - "+cux.getName());     //print to console
           writer.write("Customer - "+cux.getName()+"\n");         //print to text file (text file remains blank until the program is fully ended, so wait until program end to check what you have typed in it)

          List<Gitem> groceryList = new LinkedList();             //get list of gitems, initialized for each cux so no overlapping
          float runningTotal = 0;
        groceryList = cux.getGroceryList();                 //get the list of groceries from cux
        for(Gitem items: groceryList){                       //go through each of them
            int key = items.getKey();                             //get the key
            
            Item item;
            if(!inventory.getInventoryItems().containsKey(key)){                        //extract item based on the key, if it is not in the inventory, throw comment below
                System.out.println("We do not have those items in the inventory.I do not know where you got that from. Sorry \n");
                writer.write("We do not have those items in the inventory.I do not know where you got that from. Sorry \n");
                qualify = false;                                                  //not qualified to check out
                break;                                                //break this persons lists to go for other person, break out of this grocery list loop and go for another cux
            }else{
                item = inventory.getInventoryItems().get(key);             //if not broken the loop, we have item in the inventory, get it
            
            runningTotal = runningTotal + item.getPrice()*items.getAmount();             //get the running total of items, before it was 0 as initialized
            }
            if(item.getStock()>= items.getAmount()&&cux.getCash() < runningTotal){                        // if there is item but amount is insufficient, comment as belows
                System.out.println("Sorry, "+cux.getName()+" You do not have enough money to buy these items\n"
                        + "========================================================================\n");
                writer.write("Sorry, "+cux.getName()+" You do not have enough money to buy these items\n"
                        + "========================================================================\n");
                
                qualify = false;                       //unqualified , break out
                break; 
            }
            else if(item.getStock() < items.getAmount()){                                   //if stock is out, given comment and break out
                System.out.println("Sorry, "+cux.getName()+" We are out of order\n"
                        + "=======================================================================\n");
            writer.write("Sorry, "+cux.getName()+" We are out of order\n"
                        + "=======================================================================\n");
            
                qualify = false;
                break;
            }
        }                                       //list loop ended with determining who qualifies and who does not
        
        if(runningTotal<=cux.getCash() && qualify == true){                        //if enough money and qualify to buy stuff go through their list again and make changes in the inventory after selling them
               
                for(Gitem boughts:groceryList){
                    int ke = boughts.getKey();
                    Item ite = inventory.getInventoryItems().get(ke);
                    inventory.getInventoryItems().get(ke).setStock((inventory.getInventoryItems().get(ke).getStock()-boughts.getAmount()));
                   
                    
                    System.out.println(ite.getName()+" x "+boughts.getAmount()+" @$"+ite.getPrice());
                    writer.write(ite.getName()+" x "+boughts.getAmount()+" @$"+ite.getPrice()+"\n");

                }
                System.out.println(cux.getName()+" has $"+cux.getCash());
                System.out.println("Total: $"+runningTotal);
                System.out.println("Thank you, Come Back Soon!");
                System.out.println("=======================================================================\n");
            
                writer.write(cux.getName()+" has $"+cux.getCash()+"\n");
                writer.write("Total: $"+runningTotal+"\n");
                writer.write("Thank you, Come Back Soon!"+"\n");
                writer.write("=======================================================================\n");
            }
      }                                             //loop for each cux is made and over now
          
    }
    
    public void checkAllItems(Inventory inventory){                  //check all items from the inventory so pass inventory as parameter so you can check what it has
        
        boolean restockReqd = false;                              //check if restock required so initially set it as false
        ArrayList<Item> itemArray = new ArrayList();                    //get arraylist object of items to add items that need to be restocked
        
        Enumeration<Integer> e = inventory.getInventoryItems().keys();           //get the key of items and enumerate through every one of them
        while(e.hasMoreElements()){
            
            int key = e.nextElement();
            Item item = inventory.getInventoryItems().get(key);               //get the item and check threshold mark, if less than threshold in stock, the add to array for warning
            if(item.getStock()<item.getThreshold()){
                itemArray.add(item);
                restockReqd = true;                 //if at least one needs restocking restock required is true
               // System.out.println(item.getKey()+" ("+item.getName()+"):"+item.getPrice()+" remain in stock, replenishment threshold is "+item.getThreshold());
            }
        }
        if(restockReqd){                              //if restock required for any item is true, loop through each item of the array and give out warning with the facts and numbers of items
            System.out.println("\tWarning! The following Item(s) may need to be restocked::\n");
            
            for(Item item: itemArray){
             System.out.println("\t\t"+item.getKey()+" ("+item.getName()+"):"+item.getStock()+" remain in stock, replenishment threshold is "+item.getThreshold());
            }
            System.out.println("========================================================");
        }
    }
}
