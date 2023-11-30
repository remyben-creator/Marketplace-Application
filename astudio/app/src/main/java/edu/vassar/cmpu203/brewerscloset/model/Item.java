package edu.vassar.cmpu203.brewerscloset.model;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a single item
 */
public class Item {
    public int serialNumber;
    public String title;
    public String description;
    public Double price;
    //both types below will need to be changed soon
    public String pictures;
    public User seller;
    public ItemInterestCatalog interests;

    public Item(String title, Double price, String description, String pictures, User seller) {
        this.serialNumber = 0;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pictures = pictures;
        this.seller = seller;
        this.interests = new ItemInterestCatalog(this);
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

    public User getSeller() {
        return this.seller;
    }
    public void setSeller(User seller) {
        this.seller = seller;
    }

}


