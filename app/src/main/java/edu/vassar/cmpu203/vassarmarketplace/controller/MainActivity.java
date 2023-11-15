package edu.vassar.cmpu203.vassarmarketplace.controller;

import androidx.appcompat.app.AppCompatActivity;

import edu.vassar.cmpu203.vassarmarketplace.model.ItemCatalog;
import edu.vassar.cmpu203.vassarmarketplace.view.AddItemFragment;
import edu.vassar.cmpu203.vassarmarketplace.view.HomeFeedFragment;
import edu.vassar.cmpu203.vassarmarketplace.view.HomeFeedView;
import edu.vassar.cmpu203.vassarmarketplace.view.IHomeFeedView;
import edu.vassar.cmpu203.vassarmarketplace.view.IMainView;
import edu.vassar.cmpu203.vassarmarketplace.view.MainView;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //start with empty item catalog
        ItemCatalog items;
        //create main view object
        IMainView mainView;

        //create main view object
        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        mainView.displayFragment(new HomeFeedFragment(), false, "home feed");
        // call this again to move around fragments
    }
}