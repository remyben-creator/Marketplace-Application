package edu.vassar.cmpu203.brewerscloset.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import edu.vassar.cmpu203.brewerscloset.model.*;

public class UserTest {

    @Test
    //creating the my items feed
    public void testGenMyItems() {
        User user = new User("user@example.com", "password");
        ItemCatalog items = new ItemCatalog();
        Item item1 = new Item("Item 1", 10.99, "Description 1", null, null);
        Item item2 = new Item("Item 2", 20.99, "Description 2", null, null);
        items.addItem(item1);
        items.addItem(item2);

        user.myItemsIds = item1.id + " " + item2.id;
        user.genMyItems(items);

        assertEquals(2, user.myItems.getLength());
        assertTrue(user.myItems.getList().contains(item1));
        assertTrue(user.myItems.getList().contains(item2));
    }

    @Test
    //creating an item
    public void testCreateItem() {
        User user = new User("user@example.com", "password");
        Item item = user.createItem("Test Item", 15.99, "Item Description", null, user);

        assertEquals(1, user.myItems.getLength());
        assertEquals(item, user.myItems.getItem(0));
    }

    @Test
    //matching two users
    public void testUserEquals() {
        User user1 = new User("user@example.com", "password");
        User user2 = new User("user@example.com", "differentpassword");
        User user3 = new User("user2@example.com", "password");

        assertFalse(user1.userEquals(user3));
        assertTrue(user1.userEquals(user2));
    }


    @Test
    //adding interest on the user side
    public void testAddInterest() {
        User user = new User("user@example.com", "password");
        Item item = user.createItem("Test Item", 15.99, "Item Description", null, user);

        user.addInterest(item, "Interest Message");

        assertEquals(1, user.myInterests.getLength());
        assertEquals(user.myInterests.getList().get(0).item, item.id);
    }

    @Test
    //deleting interest on the user side
    public void testDeleteInterest() {
        User user = new User("user@example.com", "password");
        ItemCatalog items = new ItemCatalog();
        Item item = user.createItem("Test Item", 15.99, "Item Description", null, user);
        ItemInterestForm interest = new ItemInterestForm(user, item, "Interest Message");
        items.addItem(item);
        user.myInterests.addInterest(interest);
        user.deleteInterest(items, interest);

        assertEquals(0, user.myInterests.getLength());
        assertFalse(item.interests.getList().contains(interest));
    }

    @Test
    //deleting an item
    public void testDeleteItem() {
        User user = new User("user@example.com", "password");
        ItemCatalog items = new ItemCatalog();
        Item item = user.createItem("Test Item", 15.99, "Item Description", null, user);

        user.deleteItem(items, item);

        assertEquals(0, user.myItems.getLength());
        assertFalse(items.getList().contains(item));
    }
}