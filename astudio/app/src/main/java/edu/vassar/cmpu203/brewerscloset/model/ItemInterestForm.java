package edu.vassar.cmpu203.brewerscloset.model;

import java.util.UUID;

public class ItemInterestForm {
    public User user;
    public UUID item;
    public String interest;
    public UUID id;

    public ItemInterestForm(User user, Item item, String interest){
        this.user = user;
        this.item = item.id;
        this.interest = interest;
        this.id = UUID.randomUUID();
    }

    public User getUser(){
        return this.user;
    }
    public String getInterest() {
        return this.interest;
    }

}
