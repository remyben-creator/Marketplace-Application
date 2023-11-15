package edu.vassar.cmpu203.vassarmarketplace.model;

import java.util.LinkedList;
import java.util.Iterator;
/**
 * Represents the main model class
 * Model class that aggregates posts
 */

public class ItemCatalog {
    public int length;
    private LinkedList<Item> items;

    public void addItem(Item item) {
        items.add(item);
    }

    public LinkedList<Item> getItems() {
        return items;
    }


    //to be implemented later
    public LinkedList<Item> similarItems() {
        // returns a list of similar items
        return null;
    }
    public LinkedList<Item> searchResult() {
        // returns a list of items that match a user search
        return null;
    }
}