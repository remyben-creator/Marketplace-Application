package edu.vassar.cmpu203.brewerscloset;

import androidx.test.espresso.Espresso;
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

public class HomeFeedInstrumentedTest {
    // specify what activity to launch for test (it will be launched afresh everytime a test is run
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * This test tries to add some avocado to a sale and checks to see that it is actually
     * added by checking the contents of the line items text.
     */
    @Test
    public void testSearch() {

        // get a ViewInteraction for the line items text
        ViewInteraction viItemsText = Espresso.onView(
                ViewMatchers.withId(R.id.lineItemsText));
        // check the text matches the default one from strings.xml
        viItemsText.check(
                ViewAssertions.matches(
                        ViewMatchers.withText(R.string.line_items_text_def)));

        // find item name and type in avocado
        ViewInteraction viItemName = Espresso.onView(ViewMatchers.withId(R.id.itemNameText));
        viItemName.perform(ViewActions.typeText("avocado"));

        Espresso.closeSoftKeyboard();

        // find item quantity and type in 2
        Espresso.onView(ViewMatchers.withId(R.id.itemQtyText))
                .perform(ViewActions.typeText("2"));

        Espresso.closeSoftKeyboard();

        // find add button and click it
        Espresso.onView(ViewMatchers.withId(R.id.addItemButton))
                .perform(ViewActions.click());

        /// check that line items text contains 2 x avocado
        viItemsText.check(
                ViewAssertions.matches(
                        ViewMatchers.withSubstring("2 x avocado")));
    }

    /**
     * Tests that we're able to move on to the payment screen after adding an item to the sale.
     */
    @Test
    public void testMovingToPayment(){
        testAddItems(); // call other test as a helper method

        // find and click the done button
        Espresso.onView(ViewMatchers.withText(R.string.done_button_label))
                .perform(ViewActions.click());

        // confirm we're in the payment screen by checking that a pay button exists
        Espresso.onView(ViewMatchers.withId(R.id.payButton));
    }
}


}
