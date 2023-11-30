package edu.vassar.cmpu203.brewerscloset.view;

import android.view.View;

import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;

public interface IHomeFeedView {
    interface Listener {
        public void uponSearch(String searchString);
        public void uponAddItem();
        public void uponMyItems();
        public void uponAccount();
        public void uponHome();
        public boolean checkForMyItems(ItemCatalog items);
        public boolean loggedIn();

        //items adapter called listener methods
        public void uponEdit(Item item);
        public void uponViewInterest();
        public void uponInterest(Item item);
        public void uponDelete(Item item);
        public void uponConfirm(Item item, String interest);
    }

}
