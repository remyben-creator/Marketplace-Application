package edu.vassar.cmpu203.brewerscloset.model;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

public class ItemInterestCatalog implements Catalog, java.io.Serializable{
    private static final String INTERESTS = "interests";
    private static final String LENGTH = "length";
    private static final String ITEM = "item";
    private static final String ID = "id";
    public int length;
    public List<ItemInterestForm> interests;
    public String item;
    public String id;

    public ItemInterestCatalog(Item item) {
        this.length = 0;
        this.interests = new LinkedList<>();
        if (item == null) {
            UUID uuid = new UUID(0,0);
            this.item = uuid.toString();}
        else {this.item = item.id;}
        this.id = UUID.randomUUID().toString();
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
    public ItemInterestForm getItemFromID(String id) {
        //get single item
        ListIterator<ItemInterestForm> iterator = this.interests.listIterator();

        while (iterator.hasNext()) {
            ItemInterestForm current = iterator.next();
            if (current.id.equals(id)) {
                return current;
            }
        }
        return null;
    }

    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> interestList = new LinkedList<>();
        for (ItemInterestForm interest : this.interests) interestList.add(interest.toMap());
        map.put(INTERESTS, interestList);
        map.put(LENGTH, this.length);
        map.put(ITEM, this.item.toString());
        map.put(ID, this.id.toString());

        return map;
    }

    @NonNull
    public static ItemInterestCatalog fromMap(@NonNull Map<String, Object> map) {
        ItemInterestCatalog interests = new ItemInterestCatalog(null);

        interests.length = (int) ((long) map.get(LENGTH));
        interests.id = (String) map.get(ID);
        interests.item = (String) map.get(ITEM);

        List<Map<String, Object>> interestMapList = (List<Map<String, Object>>) map.get(INTERESTS);
        for (Map<String, Object> interestMap : interestMapList)
            interests.interests.add(ItemInterestForm.fromMap(interestMap));

        return interests;
    }

}