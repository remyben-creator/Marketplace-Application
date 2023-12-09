package edu.vassar.cmpu203.brewerscloset.view;

import android.view.View;

import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;
import edu.vassar.cmpu203.brewerscloset.model.ItemInterestCatalog;
import edu.vassar.cmpu203.brewerscloset.model.ItemInterestForm;
import edu.vassar.cmpu203.brewerscloset.model.User;

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
        public void uponViewInterest(Item item);
        public void uponInterest(Item item);
        public void uponDeleteItem(Item item);
        public void uponDeleteInterest(ItemInterestCatalog interest, int index);
        public void uponConfirm(Item item, String interest);
        public User getSomeUser(String userId);
    }

}
