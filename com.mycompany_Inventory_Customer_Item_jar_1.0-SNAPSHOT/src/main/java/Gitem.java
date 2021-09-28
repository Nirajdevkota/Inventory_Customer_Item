
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author niraj
 */
public class Gitem {                        //define Gitem, its item with amount that costumer takes, so get the key to match it with item's key from inventory, get amount to difine how many you took
    private int key;
    private int amount;

    public Gitem() {
    }

    public Gitem(int key, int amount) {
        this.key = key;
        this.amount = amount;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getKey() {
        return key;
    }

    public int getAmount() {
        return amount;
    }
    
}
