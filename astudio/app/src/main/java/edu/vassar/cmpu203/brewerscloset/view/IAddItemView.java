package edu.vassar.cmpu203.brewerscloset.view;

import android.net.Uri;

import edu.vassar.cmpu203.brewerscloset.model.Item;

public interface IAddItemView {

    interface Listener {
        public void uponPost(Item item, String itemTitle, Double itemPrice, String itemDesc, String itemPics, boolean edit);
        public void uponBackToHome(boolean edit);
        public void uponAddPics(IAddItemView aiv);

    }
    public void updateImage(Uri data);


}
