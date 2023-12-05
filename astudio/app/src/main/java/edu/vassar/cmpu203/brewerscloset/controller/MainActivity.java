package edu.vassar.cmpu203.brewerscloset.controller;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;
import edu.vassar.cmpu203.brewerscloset.model.ItemInterestCatalog;
import edu.vassar.cmpu203.brewerscloset.model.ItemInterestForm;
import edu.vassar.cmpu203.brewerscloset.model.User;
import edu.vassar.cmpu203.brewerscloset.model.UserCatalog;
import edu.vassar.cmpu203.brewerscloset.persistence.IPersistenceFacade;
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
import edu.vassar.cmpu203.brewerscloset.persistence.FirestoreFacade;
import edu.vassar.cmpu203.brewerscloset.persistence.IPersistenceFacade;


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
    Fragment curFrag;
    IPersistenceFacade persFacade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize persistence facade
        this.persFacade = new FirestoreFacade();

        //empty item/user catalogs by default
        this.items = new ItemCatalog();
        this.users = new UserCatalog();

        //start with no login info
        this.user = new User("Guest", null);
        
        //load catalogs if they exist
        this.persFacade.retrieveCatalogs(new IPersistenceFacade.Listener() {
            @Override
            public void onItemCatalogReceived(@NonNull ItemCatalog itemCatalog) {
                MainActivity.this.items = itemCatalog;
            }

            @Override
            public void onUserCatalogReceived(@NonNull UserCatalog userCatalog) {
                MainActivity.this.users = userCatalog;
            }
        });

        this.syncItemsAndUsers();


        //create main view object
        mainView = new MainView(this);
        setContentView(mainView.getRootView());


        mainView.displayFragment(new HomeFeedFragment(this, this.items), false, "home feed");
        // call this again to move around fragments
    }

    private void syncItemsAndUsers() {
        for (Item item : this.items.items) item.getSellerFromID(users);
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
        this.mainView.displayFragment(new HomeFeedFragment(this, this.user.myItems),false,"my item feed");
    }
    public void uponAccount() {
        if (this.user.email.equals("Guest")) {
            this.mainView.displayFragment(new AccountFragment(this), false, "account screen");
        }
        else {
            this.mainView.displayFragment(new LoggedInAccountFragment(this), false, "logged in account screen");
        }
    }
    public void uponHome() {
        this.mainView.displayFragment(new HomeFeedFragment(this, this.items),false,"home feed");
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
    public void uponViewInterest(Item item) {
        this.mainView.displayFragment(new HomeFeedFragment(this, item.interests), false, "interests");
        }
    public void uponInterest(Item item) {
        ItemCatalog itemInterest = new ItemCatalog();
        itemInterest.addItem(item);
        itemInterest.forInterest = true;
        this.mainView.displayFragment(new HomeFeedFragment(this, itemInterest), false, "home feed");
    }
    public void uponDeleteItem(Item item) {
        this.mainView.displayFragment(new ConfirmDeleteFragment(this, item, null, -1, null), false, "confirm delete page");
    }
    public void uponDeleteInterest(ItemInterestCatalog interests, int index) {
        this.mainView.displayFragment(new ConfirmDeleteFragment(this, null, interests, index, null), false, "confirm delete page");
    }
    public void uponConfirm(Item item, String interest) {
        this.user.addInterest(item, interest);
        this.mainView.displayFragment(new HomeFeedFragment(this, this.items), false, "home feed");
    }

    //Add Item listener methods
    public void uponBackToHome(boolean edit) {
        if (edit) {
            this.mainView.displayFragment(new HomeFeedFragment(this, this.user.myItems),false,"my item feed");
        }
        else {
            this.mainView.displayFragment(new HomeFeedFragment(this, this.items), false, "home feed");
        }
    }
    public void uponPost(Item item, String itemTitle, Double itemPrice, String itemDesc, String itemPics, boolean edit) {
        if (edit) {
            this.user.editItem(item, itemTitle, itemPrice, itemDesc, itemPics);
            this.mainView.displayFragment(new HomeFeedFragment(this, this.user.myItems),false,"my item feed");
        }
        else {
        Item newItem = this.user.createItem(itemTitle, itemPrice, itemDesc, itemPics, this.user);
        this.items.addItem(newItem);
        this.persFacade.saveItem(newItem);
        this.mainView.displayFragment(new HomeFeedFragment(this, this.user.myItems),false,"home feed");}

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
        if (this.users.loginUser(userEmail, userPassword, this.items) != null) {
            this.user = this.users.loginUser(userEmail, userPassword, this.items);
            return true;
        }
        return false;
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
            User newUser = new User(userEmail, userPassword);
            this.user = newUser;
            this.users.addUser(newUser);
            this.persFacade.saveUser(newUser);
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
    public void uponConfirmDeleteItem(Item item) {
        this.user.deleteItem(this.items, item);
        this.mainView.displayFragment(new HomeFeedFragment(this, this.user.myItems), false, "my item feed");
    }
    public void uponConfirmDeleteInterest(ItemInterestCatalog interests, int index) {
        ItemInterestForm interest = interests.getInterest(index);
        interests.removeInterest(interest);
        this.mainView.displayFragment(new HomeFeedFragment(this, interests), false, "interests");
    }
    public void uponConfirmDeleteUser() {
        this.users.removeUser(this.user, this.items);
        this.user = new User("Guest", null);
        this.mainView.displayFragment(new AccountFragment(this), false, "account screen");
    }
    public void uponBackToInterests(ItemInterestCatalog interests) {
        this.mainView.displayFragment(new HomeFeedFragment(this, interests), false, "interests");
    }
    //logged in account methods
    public void uponLogout(){
        this.user = new User("Guest", null);
        this.mainView.displayFragment(new HomeFeedFragment(this, this.items),false,"home feed");
    }
    public void uponDelete() {
        this.mainView.displayFragment(new ConfirmDeleteFragment(this, null, null, -1, this.user), false, "confirm delete page");
    }


}