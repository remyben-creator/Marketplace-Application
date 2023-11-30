package edu.vassar.cmpu203.brewerscloset.controller;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;
import edu.vassar.cmpu203.brewerscloset.model.User;
import edu.vassar.cmpu203.brewerscloset.model.UserCatalog;
import edu.vassar.cmpu203.brewerscloset.view.AccountFragment;
import edu.vassar.cmpu203.brewerscloset.view.AddItemFragment;
import edu.vassar.cmpu203.brewerscloset.view.ConfirmDeleteFragment;
import edu.vassar.cmpu203.brewerscloset.view.HomeFeedFragment;
import edu.vassar.cmpu203.brewerscloset.view.IAccountView;
import edu.vassar.cmpu203.brewerscloset.view.IConfirmDeleteView;
import edu.vassar.cmpu203.brewerscloset.view.IMainView;
import edu.vassar.cmpu203.brewerscloset.view.LoggedInAccountFragment;
import edu.vassar.cmpu203.brewerscloset.view.MainView;
import edu.vassar.cmpu203.brewerscloset.view.IAddItemView;
import edu.vassar.cmpu203.brewerscloset.view.IHomeFeedView;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity
    implements IAddItemView.Listener, IHomeFeedView.Listener, IAccountView.Listener, IConfirmDeleteView.Listener {

    ItemCatalog items;
    User user;
    UserCatalog users;
    IMainView mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //start with empty item/user catalog
        this.items = new ItemCatalog();
        this.users = new UserCatalog();

        //start with no login info
        this.user = new User("Guest", null);

        //create main view object
        mainView = new MainView(this);
        setContentView(mainView.getRootView());


        mainView.displayFragment(new HomeFeedFragment(this, this.items), false, "home feed");
        // call this again to move around fragments
    }

    //Home feed listener methods
    public void uponAddItem() {
        this.mainView.displayFragment(new AddItemFragment(this, false, null),false,"add item screen");
    }
    public void uponSearch(String searchString) {
        ItemCatalog searchItems = this.items.searchResult(searchString);
        this.mainView.displayFragment(new HomeFeedFragment(this, searchItems),false,"search item feed");
    }
    public void uponMyItems() {
        // to be implemented with the addition of accounts
        ItemCatalog myItems = this.user.myItems;
        this.mainView.displayFragment(new HomeFeedFragment(this, myItems),false,"my item feed");
    }
    public void uponAccount() {
        if (this.user.email.equals("Guest")) {
            this.mainView.displayFragment(new AccountFragment(this), false, "account screen");
        }
        else {
            this.mainView.displayFragment(new LoggedInAccountFragment(this), false, "logged in account screen");
        }
    }
    public boolean loggedIn() {
        if (this.user.email.equals("Guest")) {
            return false;
        }
        return true;
    }
    //Home feed --> items adapter listener methods
    public boolean checkForMyItems(ItemCatalog items) {
        if (items.equals(this.user.myItems)) {return true;}
        return false;
    }
    public void uponEdit(Item item) {
        this.mainView.displayFragment(new AddItemFragment(this, true, item),false,"add item screen in edit");
    }
    public void uponViewInterest() {}
    public void uponInterest() {}
    public void uponDelete(Item item) {
        this.mainView.displayFragment(new ConfirmDeleteFragment(this, item), false, "confirm delete page");
    }

    //Add Item listener methods
    public void uponBackToHome() {
        this.mainView.displayFragment(new HomeFeedFragment(this, this.items),false,"home feed");
    }
    public void uponHome() {
        this.mainView.displayFragment(new HomeFeedFragment(this, this.items),false,"home feed");
    }
    public void uponPost(Item item, String itemTitle, Double itemPrice, String itemDesc, String itemPics, boolean edit) {
        if (edit) {
            this.user.editItem(item, itemTitle, itemPrice, itemDesc, itemPics);
        }
        else {
        Item newItem = this.user.createItem(itemTitle, itemPrice, itemDesc, itemPics, this.user);
        this.items.addItem(newItem);}

        this.mainView.displayFragment(new HomeFeedFragment(this, this.items),false,"home feed");
    }

    public void uponAddPics(IAddItemView aiv) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        this.aiv = aiv;
        imgARL.launch(intent);


    }

    IAddItemView aiv;
    ActivityResultLauncher<Intent> imgARL = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //there are no request codes
                        Intent data = result.getData();
                        Uri photo = data.getData();
                        aiv.updateImage(photo);
                    }
                }
            }
    );

    //Account listener methods
    public void uponLoginGoHome() {
        //just to switch to the HomeFeed
        this.mainView.displayFragment(new HomeFeedFragment(this, this.items),false,"home feed");
    }
    public boolean checkValidLogin(String userEmail, String userPassword) {
        //user params to check a user login
        //and puts the user as current user if correct
        if (this.users.loginUser(userEmail, userPassword) != null) {
            this.user = this.users.loginUser(userEmail, userPassword);
            return true;
        }
        return false;
    }
    public void uponLogout(){
        this.user = new User("Guest", null);
        this.mainView.displayFragment(new HomeFeedFragment(this, this.items),false,"home feed");

    }
    public void uponCreateAccountGoHome() {
        //to switch to home feed following an account creation
        this.mainView.displayFragment(new HomeFeedFragment(this, this.items),false,"home feed");
    }
    public boolean checkCreateAccount(String userEmail, String userPassword) {
        //user params to create a user
        //check to see if its already there
        user = this.users.checkForUser(userEmail, userPassword);
        if (user == null) {
            User user = new User(userEmail, userPassword);
            this.user = user;
            this.users.addUser(user);
            return true;
        }
        return false;
    }
    public String getUserEmail() {
        return this.user.email;
    }
    //confirm delete listener methods
    public void uponBackToMyItems() {
        ItemCatalog myItems = this.user.myItems;
        this.mainView.displayFragment(new HomeFeedFragment(this, myItems),false,"my item feed");
    }
    public void uponConfirmDelete(Item item) {
        this.user.deleteItem(item);
        this.items.removeItem(item);
        this.mainView.displayFragment(new HomeFeedFragment(this, this.user.myItems), false, "my item feed");
    }

}