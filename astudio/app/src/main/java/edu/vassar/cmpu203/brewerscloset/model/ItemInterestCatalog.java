package edu.vassar.cmpu203.brewerscloset.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ItemInterestCatalog implements Catalog{
    public int length;
    public List<ItemInterestForm> interests;
    public Item item;

    public ItemInterestCatalog(Item item) {
        this.length = 0;
        this.interests = new LinkedList<>();
        this.item = item;
    }

    public void addInterest(ItemInterestForm interest) {
        interests.add(interest);
        this.length++;
    }

    public void removeInterest(ItemInterestForm toRemove) {
        Iterator<ItemInterestForm> iterator = interests.iterator();

        while (iterator.hasNext()) {
            ItemInterestForm element = iterator.next();
            if (element.equals(toRemove)) {
                iterator.remove();
            }
        }
        this.length--;
    }
    public int getLength() {return this.length;}
    public List<ItemInterestForm> getList() {
        return this.interests;
    }
    public ItemInterestForm getItem(int index) {
        //get single item
        ListIterator<ItemInterestForm> iterator = this.interests.listIterator();
        int counter = 0;

        while (iterator.hasNext()) {
            ItemInterestForm current = iterator.next();
            if (counter == index) {
                return current;
            }
            counter++;
        }
        return null;
    }

    public ItemInterestForm getInterest(int index) {
        //get single item
        ListIterator<ItemInterestForm> iterator = this.interests.listIterator();
        int counter = 0;

        while (iterator.hasNext()) {
            ItemInterestForm current = iterator.next();
            if (counter == index) {
                return current;
            }
            counter++;
        }
        return null;
    }
}