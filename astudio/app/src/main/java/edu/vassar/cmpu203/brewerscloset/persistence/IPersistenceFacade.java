package edu.vassar.cmpu203.brewerscloset.persistence;

import androidx.annotation.NonNull;

import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;
import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.User;
import edu.vassar.cmpu203.brewerscloset.model.UserCatalog;

/** interface that specifies a contract that all persistance soluctions must fulfill
 *
 */
public interface IPersistenceFacade {
    /**
     * Interface that classes interested ion being notified of data-genrating events
     * from the persistence layer should implement
     */
    interface Listener {
        void onItemCatalogReceived(@NonNull ItemCatalog itemCatalog);
        void onUserCatalogReceived(@NonNull UserCatalog userCatalog);
    }

    /**
     * Saves the Item passed in as input to the underlying persistence solution.
     * @param sale the Item to be saved
     */
    void saveItem(@NonNull Item item);
    void saveUser(@NonNull User user);
    void setItem(@NonNull Item item);
    void setUser(@NonNull User user);

    /**
     * Issues an ItemCatalog retrieval operation
     * @param listener the observer to be notified of query result
     */
    void retrieveCatalogs(@NonNull Listener listener);
}

