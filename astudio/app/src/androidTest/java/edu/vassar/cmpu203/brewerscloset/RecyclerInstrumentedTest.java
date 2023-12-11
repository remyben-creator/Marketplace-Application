package edu.vassar.cmpu203.brewerscloset;

import static androidx.core.content.MimeTypeFilter.matches;
import static android.support.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.contrib.RecyclerViewActions;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;


import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.vassar.cmpu203.brewerscloset.controller.MainActivity;
@RunWith(AndroidJUnit4.class)
public class RecyclerInstrumentedTest {
    // specify what activity to launch for test (it will be launched afresh everytime a test is run
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /*
    Recyclerview comes into view
     */
//    @Test
//    public void recyclerTest() {
//        //scroll to position and click on it
//        Espresso.onView(ViewMatchers.withId(R.id.item_catalog_widget))
//                .perform((ViewAction) RecyclerViewActions.actionOnItemAtPosition(
//                        1, click()
//                ));
//        //match the text in the item
//        Espresso.onView(ViewMatchers.withText("Test Title"));
//    }

    /*
    I was not able to implement the tests for the recycler view no matter how much I tried

    I feel as though the acknowledgement of the things that need formal espresso tests would be appreciated
    (This functionality has been tested from the user side countless times during the process of creation)

    Here they are
    - The recycler view has four different .xml item views for different scenarios of viewing items
        - Item view: after clicking home or a search operation
            - would be tested by looking for the interestButton
        - My Item view: after clicking myItemsButton
            - would be tested by looking for the editButton
        - Item Interest view: after clicking to show interest in the item
            - would be tested by looking for the interestBar or confirmButton
        - Interest view: this is the list of interests
            - would be tested by going into myItems and clicking the view Interests button
            - once there looking for the UserInterestView textView
    - To edit an item, one must click within the recycler view
        - I would test that the items parameters are filled in the titleBar, priceBar, and descriptionBar
    - To show an interest, one must go to the item interest view as stated above and input and confirm through there
    - Deletion of item or interest(though the deletion confirm page has been espresso tested through the user)
     */


}
