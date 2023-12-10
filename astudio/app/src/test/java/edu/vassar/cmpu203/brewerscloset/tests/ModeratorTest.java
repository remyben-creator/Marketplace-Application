package edu.vassar.cmpu203.brewerscloset.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import edu.vassar.cmpu203.brewerscloset.model.*;

public class ModeratorTest {

    @Test
    //does the email end with @vassar.edu
    public void testIsValidEmail() {
        assertTrue(Moderator.isValidEmail("user@vassar.edu"));
        assertFalse(Moderator.isValidEmail("user@gmail.com"));

    }

    @Test
    //does the item contain banned strings
    public void testIsBannedItem() {
        assertTrue(Moderator.isBannedItem("Title with Damn", "Description with some banned words"));
        assertTrue(Moderator.isBannedItem("Title with dAmN", "Description with some banned words"));
        assertFalse(Moderator.isBannedItem("Valid Title", "Valid description without banned words"));
    }
}