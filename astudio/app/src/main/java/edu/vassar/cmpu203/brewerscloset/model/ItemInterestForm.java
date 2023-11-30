package edu.vassar.cmpu203.brewerscloset.model;

public class ItemInterestForm {
    public User user;
    public Item item;
    public String interest;

    public ItemInterestForm(User user, Item item, String interest){
        this.user = user;
        this.item = item;
        this.interest = interest;
    }

}
