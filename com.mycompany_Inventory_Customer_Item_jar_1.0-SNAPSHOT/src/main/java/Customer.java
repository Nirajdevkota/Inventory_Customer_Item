/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author niraj
 */
public class Customer {
    private String name;
    private float cash;
    private List<Gitem> groceryList;
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public List<Gitem> getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(List<Gitem> groceryList) {
        this.groceryList = groceryList;
    }

  
    

    public Customer() {
    }

    public Customer(String name, float amount, List<Gitem> groceryList) {
        this.name = name;
        this.cash = amount;
        this.groceryList = groceryList;
    }
    
    public Queue<Customer> getCustomerList(File file) throws FileNotFoundException, IOException{     //this method gets the queue of customers
        BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            
            
            Queue<Customer> customers =  new LinkedList<Customer>();                        //create queue object to return at the end after reading all customers
             while((line = br.readLine()) != null){                                   //read each line of the text file until null line 
                List<Gitem> gitemList = new ArrayList();                               //create list of gitems array 
                StringTokenizer tokenizer = new StringTokenizer(line,"{ ,[]\"}");           //tokenizer to break each string in regards to the provided signs \" takes of "" sign
                int size = tokenizer.countTokens();                                  //get total size of the array of strings
                
                
                Customer cux = new Customer();                             //new customer to add to the queue after fill ups
                if(size % 2 != 0){                                   //check if the name of the person consists of last name, customer with last name will have odd length
                    cux.name = tokenizer.nextToken() + " "+ tokenizer.nextToken();         
                    cux.cash = Float.parseFloat(tokenizer.nextToken());                      //get the cash value that comes after name of a person
                  
                }else{
                    cux.name = tokenizer.nextToken();     
                    cux.cash = Float.parseFloat(tokenizer.nextToken());
                    
                }
                while( tokenizer.hasMoreTokens()){                         //after cash, all the left overs are gitems
                      
                    Gitem gitem_obj = new Gitem();                                    //create new gitem object to fill and pass to the item list of the customer
                    gitem_obj.setKey(Integer.parseInt(tokenizer.nextToken()));
                    gitem_obj.setAmount(Integer.parseInt(tokenizer.nextToken()));
                    gitemList.add(gitem_obj);                                         //add items to the gitem list
                    
                }
                cux.setGroceryList(gitemList);                               //set he grocery items list to the customers grocery list
                customers.add(cux);                                 //since customer has everything needed, add the customer to the queue of customers
             }
        return customers;     
    }
   
}
