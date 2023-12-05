package edu.vassar.cmpu203.brewerscloset.persistence;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;
import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.User;
import edu.vassar.cmpu203.brewerscloset.model.UserCatalog;

/**
 * Class that implements the persistance facade by writing data to the local device's file storage
 */
public class LocalStorageFacade implements IPersistenceFacade {
    public static final String ITEMCATALOG_FILENAME = "itemCatalog.brewersCloset";
    public static final String USERCATALOG_FILENAME = "userCatalog.brewersCloset";

    private final File itemsFile; //the file to write to/read from
    private final File usersFile;
    private ItemCatalog itemCatalog;
    private UserCatalog userCatalog;

    public LocalStorageFacade(@NonNull File storageDir) {
        this.itemsFile = new File(storageDir, ITEMCATALOG_FILENAME);
        this.usersFile = new File(storageDir, USERCATALOG_FILENAME);

        this.itemCatalog = new ItemCatalog(); //empty ItemCatalog to begin with
        this.userCatalog = new UserCatalog();
    }

    @Override
    public void saveItem(@NonNull Item item) {
        this.itemCatalog.addItem(item);

        try {
            FileOutputStream fos = new FileOutputStream(this.itemsFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this.itemCatalog);
        } catch (IOException e) {
            final String emsg = String.format("I/O error writing to %s", this.itemsFile);
            Log.e("BrewersCloset", emsg);
            e.printStackTrace();
        }
    }
    @Override
    public void saveUser(@NonNull User user) {
        this.userCatalog.addUser(user);

        try {
            FileOutputStream fos = new FileOutputStream(this.usersFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this.userCatalog);
        } catch (IOException e) {
            final String emsg = String.format("I/O error writing to %s", this.usersFile);
            Log.e("BrewersCloset", emsg);
            e.printStackTrace();
        }
    }
    /**
     * Issues a Ledger retreival operation
     * @param listener the listener to be notified when the Ledger becomes available
     */
    @Override
    public void retrieveCatalogs(@NonNull Listener listener) {
        if (this.itemsFile.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(this.itemsFile);
                ObjectInputStream ois = new ObjectInputStream(fis);

                this.itemCatalog = (ItemCatalog) ois.readObject(); //extract ledger from file
                listener.onItemCatalogReceived(this.itemCatalog); // tell the listener abou it
            } catch (IOException e) {
                final String emsg = String.format("I/O error writing to %s", this.itemsFile);
                Log.e("BrewersCloset", emsg);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                final String emsg = String.format("Can't find class of object from %s", this.itemsFile);
                Log.e("BrewersCloset", emsg);
                e.printStackTrace();
            }
        }
        if (this.usersFile.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(this.usersFile);
                ObjectInputStream ois = new ObjectInputStream(fis);

                this.userCatalog = (UserCatalog) ois.readObject(); //extract catalog from file
                listener.onUserCatalogReceived(this.userCatalog); // tell the listener about it
            } catch (IOException e) {
                final String emsg = String.format("I/O error writing to %s", this.usersFile);
                Log.e("BrewersCloset", emsg);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                final String emsg = String.format("Can't find class of object from %s", this.usersFile);
                Log.e("BrewersCloset", emsg);
                e.printStackTrace();
            }
        }
    }

}

