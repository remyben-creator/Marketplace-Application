package edu.vassar.cmpu203.vassarmarketplace.model;

import java.util.ArrayList;

/**
 * Represents a single user account
 */
public class User {
    public String name;
    private String email;
    private ArrayList<Item> listedItems;
    private ArrayList<Item> viewedItems;

    public Post createItem() {
        // for a user to create an item to be listed on the service
        return null;
    }
    public void editItem() {
        // for a user to edit an item that has been listed
    }
}