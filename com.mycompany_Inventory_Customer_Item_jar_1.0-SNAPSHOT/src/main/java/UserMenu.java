
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Queue;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author niraj
 */
public class UserMenu {
    
    public static void main(String [] args) throws IOException{
        Scanner scr = new Scanner(System.in);
        
        Inventory inventory = new Inventory();                                     //get object of each item for we will pass only these so that everything happens to same objects parallelly
        Customer customer = new Customer();
        Gitem gitem = new Gitem();
        Item item = new Item();
        RegisterOperations registerOperations = new RegisterOperations();
        
        File inventoryFile = new File("C:\\Users\\niraj\\Desktop\\inventory.txt");                 //input file for inventory taken from my desktop
        File customerFile = new File("C:\\Users\\niraj\\Desktop\\test.txt");                    // file for the costumer info with their list taken from my desktop
        FileWriter writer = new FileWriter("C:\\Users\\niraj\\Desktop\\CustomerReceipt.txt");     //new or existing file on my desktop to write the required results as needed
        inventory.setInventoryItems(inventoryFile);                                 //first of all fill the inventory from the given file, directly read from file to save some lines of code for now
        
        
        writer.write("\t\tWelcome Niraj Devkota's first hands Program!!\n");
        
        int choice = 0;
        System.out.println("Enter your selection in numbers:: \n1. Check-out Queue of Customers"
                + "\n2.Manage Inventory\n3.Close Program\nCHOICE = ");
         choice = scr.nextInt();
         
         while(choice != 3){

             if(choice == 1){
                Queue<Customer> cuxs = customer.getCustomerList(customerFile);       //get the customers from customer file that contains required attributes of costumers
                registerOperations.checkOut(cuxs, inventory, writer);                    //pass the queue of costumers from above line here, pass the inventory and writer to wrtie to a file
                registerOperations.checkAllItems(inventory);                         //pass checkAllItems to notify the warnings about the items
             }
             
             
             else if(choice == 2){                                                        //as per option it all work, check their resp method calls if needed in resp Java Class
                 System.out.println("Choose options for the inventory by integers"
                         + "\n1. Add items"
                         + "\n2. Delete items"
                         + "\n3. Restock items"
                         + "\n4. Restock all items under threshold"
                         + "\n5. Show All Items On The Inventory"
                         + "\n CHOICE: ");
                 int inventoryChoice = scr.nextInt();
                 
                 switch(inventoryChoice){
                      case 1:
                          System.out.println("Enter information to add items: ");
                          System.out.println("Item Key: ");
                          int itemKey = scr.nextInt();
                          scr.nextLine();
                          System.out.println("Item Name: ");
                          String itemName = scr.nextLine();
                          System.out.println("Item Threshold: ");
                          int itemThreshold = scr.nextInt();
                          System.out.println("Item Stock: ");
                          int itemStock = scr.nextInt();
                          System.out.println("Item Price: ");
                          float itemPrice = scr.nextFloat();
                          
                          inventory.addItem(itemKey, itemName,itemThreshold , itemStock, itemPrice);
                          break;
                      case 2:
                          System.out.println("Enter the key of item to delete: ");
                          int itemkey = scr.nextInt();
                          inventory.deleteItem(itemkey);
                          break;
                      case 3: 
                          System.out.println("Enter the key of item to restock: ");
                          int Key = scr.nextInt();
                          System.out.println("Enter the amount of item to restock: ");
                          int itemAmount = scr.nextInt();
                          inventory.restock(Key, itemAmount);
                          break;
                      case 4:
                          inventory.restockAll();
                          break;
                      case 5: 
                          inventory.showAllItems(inventory);
                          break;                                  //dont have to do this break since its the last statement in the condition though
                          
                 }     
             }
             
              
         System.out.println("Enter your selection in numbers:: \n1. Check-out Queue of Customers"
                + "\n2.Manage Inventory\n3.Close Program\nCHOICE = ");
         choice = scr.nextInt(); 
         }
         writer.write("\t\t!Thank you for your time!");
        writer.close();
        scr.close();
    }

}
