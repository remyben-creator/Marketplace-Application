package edu.vassar.cmpu203.brewerscloset.model;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a single item
 */
public class Item implements java.io.Serializable{
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String PICTURES = "pictures";
    private static final String SELLER = "seller";
    private static final String INTERESTS = "interests";
    public String title;
    public String description;
    public Double price;
    //both types below will need to be changed soon
    public String pictures;
    public UUID seller;
    public ItemInterestCatalog interests;
    public UUID id;

    public Item(String title, Double price, String description, String pictures, User seller) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.pictures = pictures;
        this.seller = seller.id;
        this.interests = new ItemInterestCatalog(this);
        this.id = UUID.randomUUID();
    }

    // Define methods for posts
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }
    public String getPriceString() {
        DecimalFormat decimalFormat = new DecimalFormat("##.##"); // Two decimal places
        String priceString = decimalFormat.format(this.price);

        return priceString;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public User getSeller(UserCatalog users) {
        return users.getItemFromID(this.seller);
    }
    public void setSeller(User seller) {
        this.seller = seller.id;
    }

    //data persistence mapping functions
    /**
     * return a map with the sales contents
     */
    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        //write String title, Double price, String description, String pictures, User seller
        map.put(TITLE, this.title);
        map.put(DESCRIPTION, this.description);
        map.put(PRICE, this.price);
        map.put(PICTURES, this.pictures);
        map.put(SELLER, this.seller.toMap());
        map.put(INTERESTS, this.interests.toMap());

        return map;
    }
    /**
     * Creates and returns a Sales from a previously created string to object map
     */
    @NonNull
    public static Item fromMap(@NonNull Map<String, Object> map) {
        Item item = new Item(null, null, null, null, null);

        //add String title, Double price, String description, String pictures, User seller
        item.title = (String) map.get(TITLE);
        item.description = (String) map.get(DESCRIPTION);
        item.price = (Double) map.get(PRICE);
        item.pictures = (String) map.get(PICTURES);
        item.seller = User.fromMap((Map<String, Object>)map.get(SELLER));
        item.interests = ItemInterestCatalog.fromMap((Map<String, Object>)map.get(INTERESTS));

        return item;
    }

}


