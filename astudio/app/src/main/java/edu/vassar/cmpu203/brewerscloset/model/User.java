package edu.vassar.cmpu203.brewerscloset.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a single user account
 */
public class User {
    public String email;
    public String password;
    public ItemCatalog myItems;
    public ItemInterestCatalog myInterests;
    public UUID id;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.myItems = new ItemCatalog();
        this.myInterests = new ItemInterestCatalog(null);
        this.id = UUID.randomUUID();
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

    public void addInterest(Item item, String interest) {
        ItemInterestForm itemInterest = new ItemInterestForm(this, item,interest);
        item.interests.addInterest(itemInterest);
        this.myInterests.addInterest(itemInterest);
    }
    public void deleteInterest(ItemCatalog items, ItemInterestForm interest) {
        Item item = items.getItemFromID(interest.item);
        item.interests.removeInterest(interest);
        myInterests.removeInterest(interest);
    }
    public void deleteItem(ItemCatalog items, Item item) {
        items.removeItem(item);
        this.myItems.removeItem(item);
    }
    public void deleteUserStuff(ItemCatalog items) {
        for (int i = 0; i < myInterests.length; i++) {
            this.deleteInterest(items, myInterests.getItem(i));
            i--;
        }
        for (int i = 0; i < myItems.length; i++) {
            this.deleteItem(items, myItems.getItem(i));
            i--;
        }
    }

}