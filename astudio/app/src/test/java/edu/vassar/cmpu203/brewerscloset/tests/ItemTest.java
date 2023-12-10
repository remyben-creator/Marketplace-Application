package edu.vassar.cmpu203.brewerscloset.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.User;


public class ItemTest {
    @Test
    // tests setting the item title on the database
    public void testSetTitle() {
        Item item = new Item("Test Item", 10.99, "Description", null, null);
        item.setTitle("New Title");
        assertEquals("New Title", item.getTitle());
        assertFalse(item.getTitle() == "HI");
    }

    @Test
    //tests the conversion of price from a double to a string
    public void testGetPriceString() {
        Item item = new Item("Test Item", 10.99, "Description", null, null);
        assertEquals("10.99", item.getPriceString());
    }

    @Test
    //tests getting a given Item's seller
    public void testGetSeller() {
        Item item = new Item("Test Item", 10.99, "Description", null, null);
        assertEquals(null, item.getSeller());
        assertFalse(item.getSeller() ==new User("bmarkind@vassar.edu", "hi"));
    }
}