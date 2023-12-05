package edu.vassar.cmpu203.brewerscloset.model;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemInterestForm {
    private static final String USER = "user";
    private static final String ITEM = "item";
    private static final String INTEREST = "interest";
    private static final String ID = "id";
    public String userId;
    public User user;
    public String item;
    public String interest;
    public String id;

    public ItemInterestForm(User user, Item item, String interest){
        this.userId = user.id;
        this.user = user;
        this.item = item.id;
        this.interest = interest;
        this.id = UUID.randomUUID().toString();
    }
    public void getUserFromID(UserCatalog users) {
        this.user = users.getItemFromID(this.userId);
    }
    public User getUser(){
        return user;
    }
    public String getInterest() {
        return this.interest;
    }

    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(USER, this.userId);
        map.put(ITEM, this.item);
        map.put(INTEREST, this.interest);
        map.put(ID, this.id);

        return map;
    }

    @NonNull
    public static ItemInterestForm fromMap(@NonNull Map<String, Object> map) {
        ItemInterestForm interest = new ItemInterestForm(null, null, null);

        interest.userId = (String) map.get(USER);
        interest.id = (String) map.get(ID);
        interest.interest = (String) map.get(INTEREST);
        interest.item = (String) map.get(ITEM);

        return interest;
    }


}
