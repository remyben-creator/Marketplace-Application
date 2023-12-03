package edu.vassar.cmpu203.brewerscloset.persistance;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;
import edu.vassar.cmpu203.brewerscloset.model.User;
import edu.vassar.cmpu203.brewerscloset.model.UserCatalog;

public class FirestoreFacade implements IPersistanceFacade{
    private static final String ITEMS_COLLECTION = "items";
    private static final String USERS_COLLECTION = "users";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * Saves the Item passed in as input to the underlying persistence solution.
     * @param item the item to be saved
     */
    @Override
    public void saveItem(@NonNull Item item) {
        CollectionReference cref = this.db.collection(ITEMS_COLLECTION);
        cref.add(item.toMap());
    }
    @Override
    public void saveUser(@NonNull User user) {
        CollectionReference cref = this.db.collection(USERS_COLLECTION);
        cref.add(user.toMap());
    }

    /**
     * Issues a itemCatalog retrieval operation.
     * @param listener the observer to be notified of query result.
     */
    @Override
    public void retrieveItemCatalog(@NonNull Listener listener) {

        ItemCatalog itemCatalog = new ItemCatalog();

        Task<QuerySnapshot> task = this.db.collection(ITEMS_COLLECTION)
                .get();

        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot qsnap) {

                for (DocumentSnapshot dsnap : qsnap){
                    Map<String, Object> mapData = dsnap.getData();
                    Item item = Item.fromMap(mapData);
                    itemCatalog.addItem(item);
                }
                listener.onItemCatalogReceived(itemCatalog);
            }
        });
    }
    @Override
    public void retrieveUserCatalog(@NonNull Listener listener) {
        UserCatalog userCatalog = new UserCatalog();

        Task<QuerySnapshot> task = this.db.collection(USERS_COLLECTION)
                .get();

        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot qsnap) {

                for (DocumentSnapshot dsnap : qsnap){
                    Map<String, Object> mapData = dsnap.getData();
                    User user = User.fromMap(mapData);
                    userCatalog.addUser(user);
                }
                listener.onUserCatalogReceived(userCatalog);
            }
        });
    }


}


