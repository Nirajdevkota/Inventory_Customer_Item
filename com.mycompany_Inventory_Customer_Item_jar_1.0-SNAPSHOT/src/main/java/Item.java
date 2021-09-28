/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author niraj
 */
public class Item {                         //declare item to access its attributes as needed in the inventory

   private int key;
   private String name;
   private int threshold;
   private int stock;
   private float price;

    public Item() {                        //empty constructor, its object returns default null value of the attributes, you gotta initialize all after u create object
    }

    public Item(int key, String name, int threshold, int stock, float price) {        //constructor to directly assign values to the attributes
        this.key = key;
        this.name = name;
        this.threshold = threshold;
        this.stock = stock;
        this.price = price;
    }

    
    public void setKey(int key) {                             //setter 
        this.key = key;
    }

    public void setName(String name) {                      //getter
        this.name = name;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getStock() {
        return stock;
    }

    public float getPrice() {
        return price;
    }

}
