package edu.vassar.cmpu203.brewerscloset.view;

import edu.vassar.cmpu203.brewerscloset.model.Item;

public interface IConfirmDeleteView {

    interface Listener {
        public void uponBackToMyItems();
        public void uponConfirmDelete(Item item);

    }
}
