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

    @Test
    public void checkForAllButtons() {
        Espresso.onView(ViewMatchers.withId(R.id.searchButton));
        Espresso.onView(ViewMatchers.withId(R.id.accountButton));
        Espresso.onView(ViewMatchers.withId(R.id.homeButton));
        Espresso.onView(ViewMatchers.withId(R.id.myItemsAddButton));
    }
    @Test
    public void checkMyItemsAddButton() {
        Espresso.onView(ViewMatchers.withId(R.id.myItemsAddButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withText("Add Item"));
    }
}
