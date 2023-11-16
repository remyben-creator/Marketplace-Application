package edu.vassar.cmpu203.vassarmarketplace.controller;

import androidx.appcompat.app.AppCompatActivity;

import edu.vassar.cmpu203.vassarmarketplace.model.Item;
import edu.vassar.cmpu203.vassarmarketplace.model.ItemCatalog;
import edu.vassar.cmpu203.vassarmarketplace.view.AccountFragment;
import edu.vassar.cmpu203.vassarmarketplace.view.AddItemFragment;
import edu.vassar.cmpu203.vassarmarketplace.view.HomeFeedFragment;
import edu.vassar.cmpu203.vassarmarketplace.view.IAccountView;
import edu.vassar.cmpu203.vassarmarketplace.view.IMainView;
import edu.vassar.cmpu203.vassarmarketplace.view.MainView;
import edu.vassar.cmpu203.vassarmarketplace.view.IAddItemView;
import edu.vassar.cmpu203.vassarmarketplace.view.IHomeFeedView;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity
    implements IAddItemView.Listener, IHomeFeedView.Listener, IAccountView.Listener {

    ItemCatalog items;
    IMainView mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //start with empty item catalog
        this.items = new ItemCatalog();

        //create main view object
        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        mainView.displayFragment(new HomeFeedFragment(this), false, "home feed");
        // call this again to move around fragments
    }

    //Home feed listener methods
    public void uponAddItem() {
        this.mainView.displayFragment(new AddItemFragment(this),false,"add item screen");
    }
    public void uponSearch() {}
    public void uponMyItems() {}
    public void uponAccount() {
        this.mainView.displayFragment(new AccountFragment(this),false,"account screen");
    }

    //Add Item listener methods
    public void uponBack() {
        this.mainView.displayFragment(new HomeFeedFragment(this),false,"home feed");
    }
    public void uponPost(String itemTitle, Double itemPrice, String itemDesc, String itemPics) {
        this.items.addItem(new Item(itemTitle, itemPrice, itemDesc, itemPics));
        this.mainView.displayFragment(new HomeFeedFragment(this),false,"home feed");
    }

    //Account listener methods
    public void uponLogin(String userEmail, String userPassword) {
        //user params to create a user
        this.mainView.displayFragment(new HomeFeedFragment(this),false,"home feed");
    }

}