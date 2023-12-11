package edu.vassar.cmpu203.brewerscloset.tests;

import static org.junit.Assert.*;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.UUID;

import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;

public class ItemCatalogTest {
    @Test
    //adding an item to the database
    public void testAddItem() {
        ItemCatalog catalog = new ItemCatalog();
        Item item = new Item("Test Item", 10.99, "Description", null, null);

        catalog.addItem(item);

        assertEquals(1, catalog.getLength());
        assertTrue(catalog.getList().contains(item));
    }

    @Test
    //removing an item from the database
    public void testRemoveItem() {
        ItemCatalog catalog = new ItemCatalog();
        Item item = new Item("Test Item", 10.99, "Description", null, null);

        catalog.addItem(item);
        catalog.removeItem(item);

        assertEquals(0, catalog.getLength());
        assertFalse(catalog.getList().contains(item));
    }

    @Test
    //getting a specific item from the database using it's index
    public void testGetItem() {
        ItemCatalog catalog = new ItemCatalog();
        Item item1 = new Item("Test Item 1", 10.99, "Description 1", null, null);
        Item item2 = new Item("Test Item 2", 20.99, "Description 2", null, null);

        catalog.addItem(item1);
        catalog.addItem(item2);

        assertEquals(item1, catalog.getItem(0));
        assertEquals(item2, catalog.getItem(1));
    }

    @Test
    //searching for items
    public void testSearchResult() {
        ItemCatalog catalog = new ItemCatalog();
        Item item1 = new Item("Test Item 1", 10.99, "Description 1", null, null);
        Item item2 = new Item("Test Item 2", 20.99, "Description 2", null, null);
        Item item3 = new Item("Another Item", 15.99, "Description 3", null, null);

        catalog.addItem(item1);
        catalog.addItem(item2);
        catalog.addItem(item3);

        ItemCatalog searchResult = catalog.searchResult("Test");

        assertEquals(2, searchResult.getLength());
        assertTrue(searchResult.getList().contains(item1));
        assertTrue(searchResult.getList().contains(item2));
        assertFalse(searchResult.getList().contains(item3));
    }

    @Test
    //getting an item from the database using it's ID
    public void testGetItemFromID() {
        ItemCatalog catalog = new ItemCatalog();
        Item item1 = new Item("Test Item 1", 10.99, "Description 1", null, null);
        Item item2 = new Item("Test Item 2", 20.99, "Description 2", null, null);

        catalog.addItem(item1);
        catalog.addItem(item2);

        assertEquals(item1, catalog.getItemFromID(item1.id));
        assertEquals(item2, catalog.getItemFromID(item2.id));
        assertNull(catalog.getItemFromID(UUID.randomUUID().toString()));
    }

    @Test
    //getting the most recent 10 posted items to show when loading the app
    public void testGetRecent10() {
        ItemCatalog catalog = new ItemCatalog();
        for (int i = 0; i < 15; i++) {
            Item item = new Item("Test Item " + i, 10.99 * (i + 1), "Description " + i, null, null);
            catalog.addItem(item);
        }

        ItemCatalog recent10 = catalog.getRecent10();

        assertEquals(10, recent10.getLength());
        assertEquals("Test Item 14", recent10.getItem(0).getTitle()); // Most recent item
        assertEquals("Test Item 5", recent10.getItem(9).getTitle());  // 6th most recent item
    }
}