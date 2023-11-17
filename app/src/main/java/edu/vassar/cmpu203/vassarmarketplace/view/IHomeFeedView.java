package edu.vassar.cmpu203.vassarmarketplace.view;

import android.view.View;
public interface IHomeFeedView {
    interface Listener {
        public void uponSearch(String searchString);
        public void uponAddItem();
        public void uponMyItems();
        public void uponAccount();
        public void uponHome();
    }

}
