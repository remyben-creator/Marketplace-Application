package edu.vassar.cmpu203.brewerscloset.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a single user account
 */
public class User {
    public String email;
    public String password;
    public ItemCatalog myItems;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.myItems = new ItemCatalog();
    }

    public Item createItem(String title, Double price, String description, String pictures, User seller) {
        // for a user to create an item to be listed on the service
        Item item = new Item(title, price, description, pictures, this);
        this.myItems.addItem(item);
        return item;
    }
    public Boolean userEquals(User user) {
        if (user.email.equals(this.email)) {return true;}
        return false;
    }

    public void editItem(Item item,String title, Double price, String description, String pictures) {
        // for a user to edit an item that has been listed
        item.setTitle(title);
        item.setPrice(price);
        item.setDescription(description);
    }
    public void deleteItem(Item item) {
        myItems.removeItem(item);
    }
}