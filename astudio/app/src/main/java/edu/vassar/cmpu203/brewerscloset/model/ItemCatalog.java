package edu.vassar.cmpu203.brewerscloset.model;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents the main model class
 * Model class that aggregates posts
 */

public class ItemCatalog {
    public int length;
    public List<Item> items;

    public ItemCatalog() {
        this.length = 0;
        this.items = new LinkedList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        this.length++;
    }

    public void removeItem(Item item) {
        items.remove(item);
        this.length--;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public Item getItem(int index) {
        //get single item
        ListIterator<Item> iterator = this.items.listIterator();
        int counter = 0;

        while (iterator.hasNext()) {
            Item current = iterator.next();
            if (counter == index) {
                return current;
            }
            counter++;
        }
        return null;
    }
    /** /to be implemented later
    public ItemCatalog similarItems() {
        //returns items similar to
        ItemCatalog similarItems = new ItemCatalog();
        ListIterator<Item> iterator = this.items.listIterator();

        while (iterator.hasNext()) {
            //to be implemented later
        }
        return similarItems;
    }
     */
    public ItemCatalog searchResult(String searchString) {
        // returns a list of items that match a user search
        ItemCatalog searchItems = new ItemCatalog();
        ListIterator<Item> iterator = this.items.listIterator();

        searchString = searchString.toUpperCase();
        //does not currently check for price range similarity
        while (iterator.hasNext()) {
            Item current = iterator.next();
            if (current.title.toUpperCase().contains(searchString) ||
                    current.description.toUpperCase().contains(searchString)) {
                searchItems.addItem(current);
            }
        }
        return searchItems;
    }


}