package edu.vassar.cmpu203.brewerscloset.model;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

/**
 * Represents the main model class
 * Model class that aggregates posts
 */

public class ItemCatalog implements Catalog{
    public int length;
    public LinkedList<Item> items;
    public boolean forInterest;

    public ItemCatalog() {
        this.length = 0;
        this.items = new LinkedList<>();
        this.forInterest = false;
    }

    public int getLength() {return this.length;}

    public void addItem(Item item) {
        this.items.add(item);
        this.length++;
    }

    public void removeItem(Item toRemove) {
        Iterator<Item> iterator = items.iterator();

        while (iterator.hasNext()) {
            Item element = iterator.next();
            if (element.equals(toRemove)) {
                iterator.remove();
            }
        }
        this.length--;
    }

    public List<Item> getList() {return this.items;}
    @Override

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
    public Item getItemFromID(String id) {
        //get single item
        ListIterator<Item> iterator = this.items.listIterator();

        while (iterator.hasNext()) {
            Item current = iterator.next();
            if (current.id.equals(id)) {
                return current;
            }
        }
        return null;
    }

    public ItemCatalog getRecent10() {
        ItemCatalog recent10 = new ItemCatalog();
        Iterator<Item> iterator = this.items.descendingIterator();

        int i = 0;
        while (iterator.hasNext() && i < 10) {
            recent10.addItem(iterator.next());
            i++;
        }

        return recent10;
    }


}