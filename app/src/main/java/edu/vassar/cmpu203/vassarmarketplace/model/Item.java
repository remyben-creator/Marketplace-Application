package edu.vassar.cmpu203.vassarmarketplace.model;

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
    public String seller;

    public Item(String title, Double price, String description, String pictures) {
        this.serialNumber = 0;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pictures = pictures;
        this.seller = "Default";
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

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }
}

