package edu.vassar.cmpu203.vassarmarketplace.view;

import android.view.View;

public interface IAddItemView {

    interface Listener {
        public void uponPost(String itemTitle, Double itemPrice, String itemDesc, String itemPics);
        public void uponBack();
    }


}
