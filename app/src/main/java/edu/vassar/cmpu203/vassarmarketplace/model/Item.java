package edu.vassar.cmpu203.vassarmarketplace.model;

/**
 * Represents a single item
 */
public class Item {
    public int serialNumber;
    public String title;
    public String description;
    public int price;
    //public JPEG pictures;
    public User seller;

    public Item(String title, String description, int price, User seller) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.seller = seller;
    }

    // Define methods for posts
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }
    public void setSeller(User seller) {
        this.seller = seller;
    }
}

