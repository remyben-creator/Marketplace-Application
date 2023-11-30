package edu.vassar.cmpu203.brewerscloset.view;

import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemInterestCatalog;
import edu.vassar.cmpu203.brewerscloset.model.User;

public interface IConfirmDeleteView {

    interface Listener {
        public void uponBackToMyItems();
        public void uponConfirmDeleteItem(Item item);
        public void uponConfirmDeleteInterest(ItemInterestCatalog interests, int index);
        public void uponBackToInterests(ItemInterestCatalog interests);
        public void uponConfirmDeleteUser();
        public void uponAccount();
    }
}
