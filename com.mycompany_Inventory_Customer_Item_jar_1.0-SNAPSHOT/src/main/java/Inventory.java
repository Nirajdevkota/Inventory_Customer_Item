
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author niraj
 */
public class Inventory {
 //   List<Item> item = new ArrayList<>();
    Hashtable<Integer,Item> inventoryItems = new Hashtable<>();      //we could have just created list of items like linkedlist or arraylist but question asked for hashtable
                                                                    //hashtable has list of unique keys and associated items not just the (key or item like lists), if same key for two or more items, not good idea as it might return any
    public Inventory() {                              //empty constructor, we assign values after creating object
    }
    
    public Inventory(Hashtable<Integer,Item> inventoryItems){                    //constructor with pre defined values
        this.inventoryItems = inventoryItems;
    }
        
     

    /*
    public void setTotalItems(Scanner scr, File file ) throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(file));
    }
    */
    
    public Hashtable<Integer,Item> setInventoryItems(File file) throws FileNotFoundException, IOException{    //set inventory items i.e hashtable from the file So pass required file through parameter
        
       Hashtable<Integer,Item> record = new Hashtable<Integer,Item>();               //create a hashtable to return at the end
        BufferedReader br = null;                                                  //BufferredReader can read the given file but now set null, get its object
        
        try{
            br = new BufferedReader(new FileReader(file));                           //use bufferredReader to read file, file read by file reader passed into bufferedReader
            String line = null;
            
            while((line = br.readLine()) != null){                //Benefit of buffered reader object reads a line not just one char or string, if line is not null we keep going
                StringTokenizer tokenizer = new StringTokenizer(line,"{ ,}\"");       // \" takes off string("") quotations, StringTokenizers takes of all unlike complex string split method 
                int size = tokenizer.countTokens();                              //get how many tokens broken to 
                
                int key, threshold, stock;
                String name;
                float price;
               
                    key = (Integer.parseInt(tokenizer.nextToken()));                    //first is item key
                    if(size % 2 == 0 )                                                 //Get item name if size is 2, we have two words name in file for this project given file by professor so add two consecutive tokenizers
                    name = tokenizer.nextToken()+" "+ tokenizer.nextToken();
                    else
                     name = (tokenizer.nextToken());
                    
                    threshold = (Integer.parseInt(tokenizer.nextToken()));           //get the rest splitted tokenizers accordingly
                    stock = (Integer.parseInt(tokenizer.nextToken()));
                    price = (Float.parseFloat(tokenizer.nextToken()));
                    
                    addItem(key,name,threshold,stock,price);                   //adds read item to the inventory by calling addItem function from below
               
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
     return this.inventoryItems;                            //return this inventory item as addItem works on this inventory item so its already filled with our given data
    }
    
    
    
    public void restockInventoryItems(Scanner scr, Hashtable<Integer,Item> items){          //function to check if restock needed and stock them, scr was passed if needed input but not used so can take it off but change in main program while passing this function
       
        Enumeration<Integer> e = items.keys();                 //enumerate through each keys
        while(e.hasMoreElements()){                         //if enumeration has more elements in it
            int key = e.nextElement();                     //get their key first
            Item item = items.get(key);                      // then get items from the key
            if(item.getThreshold() < item.getStock()){          //check for threshold
            item.setStock(item.getThreshold());                   // if needed add items or set the threshold value at least, I set it to threshold value, u can add more too
        }
        }
        this.inventoryItems = items;                          //update to the inventoryItems i.e hashtable of inventory items
    }
    
    public Hashtable<Integer, Item> getInventoryItems() {
        return this.inventoryItems;
    }
    
    
    public void delete(int key){
        if(this.inventoryItems.contains(key)){               //delete function, check if it contains the given key
           this.inventoryItems.get(key).setStock(0);             // if yes set the stock to 0 i.e delete . I never used this function as items.contains is not efficient rather items.containsKey() is, check deleteItem function below
           
        }
    }
    
    
    public void addItem(int itemKey, String itemName, int threshold, int stock, float price){             //pass everything to add item
        if(!this.inventoryItems.containsKey(itemKey)){                                                    //if key contained, you cannot add so make sure its not contained there
           Item item_object = new Item(itemKey,itemName,threshold, stock, price);
           this.inventoryItems.put(itemKey, item_object);
            System.out.println("Item added successfully\n");
        } else
            System.out.println("The Key "+itemKey+" is already taken. You cannot add it in the system\n");
    }
    
    public void deleteItem(int key){                    //delete function , check the key with containsKey() method of hashtable and remove if it is contained in the hashtable i.e list
        if(this.inventoryItems.containsKey(key)){
            System.out.println("Let me delete this");
            this.inventoryItems.remove(key);
            System.out.println("The item has been deleted successfully\n");
        }else
            System.out.println("The item is not in the hashtable to delete\n");
    }
    
    public void restock(int key, int numItems){                                            // get the item number with its key and add if the key i.e corresponding item, is within our inventory
        if(this.inventoryItems.containsKey(key)){
            this.inventoryItems.get(key).setStock(numItems+this.inventoryItems.get(key).getStock());
            System.out.println("Item successfully stocked\n");
        }else
            System.out.println("The item cannot be found\n");
    }

    public void restockAll(){                                  //restock, first check if invenotry is empty. if not, check for each item and restock them. Enumerate through each item
           if(!this.inventoryItems.isEmpty()){
               Enumeration<Integer> e = this.inventoryItems.keys();
               
               while(e.hasMoreElements()){
                   int key = e.nextElement();
                   Item item = this.inventoryItems.get(key);
                   if(item.getStock()<item.getThreshold()){
                       item.setStock(item.getThreshold());
                   }
               }
           }else
               System.out.println("does not have any item registered. Please register items so that we can register them. Thank you");
    }
    
    public void showAllItems(Inventory inventory){                                   //show all of whats in the inventory if inventory is not empty
        if(inventory.inventoryItems.isEmpty()){
            System.out.println("There is no item in the inventory");
        }else{
            System.out.println("The Items are as follows in the inventory: ");
            Enumeration<Integer> e = inventory.inventoryItems.keys();
            while(e.hasMoreElements()){
                int key = e.nextElement();
                
                Item item = inventory.inventoryItems.get(key);
                System.out.println("ItemKey\t\tItemName\tItemThreshold\t\tItemStock\tItemPrice");
                System.out.println(item.getKey()+"\t\t"+item.getName()+"\t\t"+item.getThreshold()+"\t\t\t"+item.getStock()+"\t\t"+item.getPrice());     //print these all in console
            }
        }
    }
    
}
