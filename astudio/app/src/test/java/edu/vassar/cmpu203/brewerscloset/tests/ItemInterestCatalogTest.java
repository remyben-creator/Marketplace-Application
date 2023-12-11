package edu.vassar.cmpu203.brewerscloset.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.UUID;

import edu.vassar.cmpu203.brewerscloset.model.*;


public class ItemInterestCatalogTest {

    @Test
    //adding interest to an item
    public void testAddInterest() {
        ItemInterestCatalog interestCatalog = new ItemInterestCatalog("item");
        User user = new User("bmarkind@vassar.edu", "Password");
        Item item = new Item("Test Item", 10.99, "Description", null, null);
        ItemInterestForm interest = new ItemInterestForm(user, item, "Message");

        interestCatalog.addInterest(interest);

        assertEquals(1, interestCatalog.getLength());
        assertTrue(interestCatalog.getList().contains(interest));
    }

    @Test
    //removing interest from an item
    public void testRemoveInterest() {
        ItemInterestCatalog interestCatalog = new ItemInterestCatalog("item");
        User user = new User("bmarkind@vassar.edu", "Password");
        Item item = new Item("Test Item", 10.99, "Description", null, null);
        ItemInterestForm interest = new ItemInterestForm(user, item, "Message");

        interestCatalog.addInterest(interest);
        interestCatalog.removeInterest(interest);

        assertEquals(0, interestCatalog.getLength());
        assertFalse(interestCatalog.getList().contains(interest));
    }

    @Test
    //removing all interests froma given user
    public void testRemoveByUser() {
        ItemInterestCatalog interestCatalog = new ItemInterestCatalog("item");
        User user1 = new User("bmarkind@vassar.edu", "Password");
        User user2 = new User("bremy@vassr.edu", "123");
        Item item = new Item("Test Item", 10.99, "Description", null, null);
        ItemInterestForm interest1 = new ItemInterestForm(user1, item, "Message1");
        ItemInterestForm interest2 = new ItemInterestForm(user2, item, "Message2");

        interestCatalog.addInterest(interest1);
        interestCatalog.addInterest(interest2);

        interestCatalog.removeByUser(user1.id);

        assertEquals(1, interestCatalog.getLength());
        assertTrue(interestCatalog.getList().contains(interest2));
        assertFalse(interestCatalog.getList().contains(interest1));
    }

    @Test
    //getting an item from an interest form
    public void testGetItem() {
        ItemInterestCatalog interestCatalog = new ItemInterestCatalog("item");
        User user = new User("bmarkind@vassar.edu", "Password");
        Item item1 = new Item("Test Item 1", 10.99, "Description 1", null, null);
        Item item2 = new Item("Test Item 2", 20.99, "Description 2", null, null);
        ItemInterestForm interest1 = new ItemInterestForm(user, item1, "Message1");
        ItemInterestForm interest2 = new ItemInterestForm(user, item2, "Message2");

        interestCatalog.addInterest(interest1);
        interestCatalog.addInterest(interest2);

        assertEquals(interest1, interestCatalog.getItem(0));
        assertEquals(interest2, interestCatalog.getItem(1));
    }

    @Test
    //getting interest from a given item
    public void testGetInterest() {
        ItemInterestCatalog interestCatalog = new ItemInterestCatalog("item");
        User user = new User("bmarkind@vassar.edu", "Password");
        Item item1 = new Item("Test Item 1", 10.99, "Description 1", null, null);
        Item item2 = new Item("Test Item 2", 20.99, "Description 2", null, null);
        ItemInterestForm interest1 = new ItemInterestForm(user, item1, "Message1");
        ItemInterestForm interest2 = new ItemInterestForm(user, item2, "Message2");

        interestCatalog.addInterest(interest1);
        interestCatalog.addInterest(interest2);

        assertEquals(interest1, interestCatalog.getInterest(0));
        assertEquals(interest2, interestCatalog.getInterest(1));
    }

    @Test
    //getting an item from the interest form using it's id
    public void testGetItemFromID() {
        ItemInterestCatalog interestCatalog = new ItemInterestCatalog("item");
        User user = new User("bmarkind@vassar.edu", "Password");
        Item item1 = new Item("Test Item 1", 10.99, "Description 1", null, null);
        Item item2 = new Item("Test Item 2", 20.99, "Description 2", null, null);
        ItemInterestForm interest1 = new ItemInterestForm(user, item1, "Message1");
        ItemInterestForm interest2 = new ItemInterestForm(user, item2, "Message2");

        interestCatalog.addInterest(interest1);
        interestCatalog.addInterest(interest2);

        assertEquals(interest1, interestCatalog.getItemFromID(interest1.id));
        assertEquals(interest2, interestCatalog.getItemFromID(interest2.id));
        assertNull(interestCatalog.getItemFromID(UUID.randomUUID().toString()));
    }
}