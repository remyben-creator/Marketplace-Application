package edu.vassar.cmpu203.brewerscloset.view;

import android.view.View;

import edu.vassar.cmpu203.vassarmarketplace.model.ItemCatalog;

public interface IHomeFeedView {
    interface Listener {
        public void uponSearch(String searchString);
        public void uponAddItem();
        public void uponMyItems();
        public void uponAccount();
        public void uponHome();
        public boolean checkForMyItems(ItemCatalog items);
        public boolean loggedIn();
    }

}
