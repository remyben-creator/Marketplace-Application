package edu.vassar.cmpu203.brewerscloset;

import static androidx.core.content.MimeTypeFilter.matches;

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

public class AccountInstrumentedTest {
    // specify what activity to launch for test (it will be launched afresh everytime a test is run
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     *
     */
    @Test
    public void testAccountCreationAndDeletion(){
        //time to check account button by check for login button
        Espresso.onView(ViewMatchers.withId(R.id.accountButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.loginButton));

        //create account portion
        ViewInteraction viEmailBar = Espresso.onView(ViewMatchers.withId(R.id.emailBar));
        viEmailBar.perform(ViewActions.typeText("aperson@vassar.edu"));
        Espresso.closeSoftKeyboard();
        ViewInteraction viPassBar = Espresso.onView(ViewMatchers.withId(R.id.passwordBar));
        viPassBar.perform(ViewActions.typeText(("123")));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.createAccountButton))
                .perform(ViewActions.click());
        //check that we are back in the homefeed(succesful creation)
        Espresso.onView(ViewMatchers.withId(R.id.homeButton));

        //time to check account button by check for logout button
        Espresso.onView(ViewMatchers.withId(R.id.accountButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.logoutButton));

        //test deletion
        Espresso.onView(ViewMatchers.withId(R.id.deleteButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.confirmDeleteView));

        Espresso.onView(ViewMatchers.withId(R.id.deleteButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.loginButton));

        //check that we cant login
        viEmailBar.perform(ViewActions.typeText("aperson@vassar.edu"));
        Espresso.closeSoftKeyboard();
        viPassBar.perform(ViewActions.typeText(("123")));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.loginButton))
                .perform(ViewActions.click());
        //check that we are back in the homefeed(unsuccesful login)
        Espresso.onView(ViewMatchers.withId(R.id.loginButton));
    }
    @Test
    public void testLoginAndLogout(){
        //time to check account button by check for login button
        Espresso.onView(ViewMatchers.withId(R.id.accountButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.loginButton));

        //create account portion
        ViewInteraction viEmailBar = Espresso.onView(ViewMatchers.withId(R.id.emailBar));
        viEmailBar.perform(ViewActions.typeText("test@vassar.edu"));
        Espresso.closeSoftKeyboard();
        ViewInteraction viPassBar = Espresso.onView(ViewMatchers.withId(R.id.passwordBar));
        viPassBar.perform(ViewActions.typeText(("123")));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.loginButton))
                .perform(ViewActions.click());
        //check that we are back in the homefeed(succesful creation)
        Espresso.onView(ViewMatchers.withId(R.id.homeButton));

        //time to check account button by check for logout button
        Espresso.onView(ViewMatchers.withId(R.id.accountButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.logoutButton));

        //test logout by looking for login button
        Espresso.onView(ViewMatchers.withId(R.id.logoutButton))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.loginButton));
    }


}
