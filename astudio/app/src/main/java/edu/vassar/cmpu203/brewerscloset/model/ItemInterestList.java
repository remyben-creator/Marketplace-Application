package edu.vassar.cmpu203.brewerscloset.model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ItemInterestList {
    public int length;
    public List<ItemInterestForm> interests;

    public ItemInterestList() {
        this.length = 0;
        this.interests = new LinkedList<>();
    }

    public void addInterest(ItemInterestForm interest) {
        interests.add(interest);
        this.length++;
    }

    public void removeInterest(ItemInterestForm interest) {
        interests.remove(interest);
        this.length--;
    }

    public List<ItemInterestForm> getInterests() {
        return this.interests;
    }
    public int getLength() {return this.length;}

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
