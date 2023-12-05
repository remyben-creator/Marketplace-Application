package edu.vassar.cmpu203.brewerscloset.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a single user account
 */
public class User {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String MYITEMS = "myItems";
    private static final String MYINTERESTS = "myInterests";
    private static final String ID = "id";
    public String email;
    public String password;
    public List<UUID> myItemsIds;
    public ItemCatalog myItems;
    public ItemInterestCatalog myInterests;
    public UUID id;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.myItemsIds = new LinkedList<UUID>();
        this.myItems = new ItemCatalog();
        this.myInterests = new ItemInterestCatalog(null);
        this.id = UUID.randomUUID();
    }
    public void genMyItems(ItemCatalog items) {
        for (UUID id : myItemsIds) this.myItems.addItem(items.getItemFromID(id));
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

    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put(EMAIL, this.email);
        map.put(PASSWORD, this.password);
        map.put(ID, this.id);
        map.put(MYINTERESTS, this.myInterests.toMap());

        //use the ids of items and interests in myitems and myinterests
        List<UUID> myItemsIds = new LinkedList<>();
        for (Item item : this.myItems.items)  myItemsIds.add(item.id);
        map.put(MYITEMS, myItemsIds);

        return map;
    }

    @NonNull
    public static User fromMap(@NonNull Map<String, Object> map) {
        User user = new User(null, null);

        user.email = (String) map.get(EMAIL);
        user.password = (String) map.get(PASSWORD);
        user.id = (UUID) map.get(ID);
        user.myInterests = (ItemInterestCatalog.fromMap(map));

        List<UUID> myItemsIds = (List<UUID>) map.get(MYITEMS);
        for (UUID itemId : myItemsIds) user.myItemsIds.add(itemId);

        return user;
    }
}