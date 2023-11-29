package edu.vassar.cmpu203.brewerscloset.view;

import android.net.Uri;
import android.view.View;

public interface IAddItemView {

    interface Listener {
        public void uponPost(String itemTitle, Double itemPrice, String itemDesc, String itemPics);
        public void uponBackToHome();
        public void uponAddPics(IAddItemView aiv);

    }
    public void updateImage(Uri data);


}
