package edu.vassar.cmpu203.brewerscloset.tests;
import org.junit.Assert;
import org.junit.Test; // for the @Test annotation

import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;

public class ItemCatalogTest {
    ItemCatalog c = new ItemCatalog();
    Item i1  = new Item("lights", 9.99, "blue", "none" );
    Item i2  = new Item("calculator", 99.99, "TI-84", "none" );
    @Test
    public void testAddItem() {
        c.addItem(i1);
        Assert.assertEquals(1, c.length);
        Assert.assertTrue(c.getItems().contains(i1));
    }
    @Test
    public void testRemoveItem() {
        c.addItem(i1);
        c.removeItem(i1);
        Assert.assertEquals(0, c.length);
        Assert.assertFalse(c.getItems().contains(i1));
    }
    @Test
    public void testGetItems() {
        c.addItem(i1);
        Assert.assertEquals(1, c.getItems().size());
        Assert.assertTrue(c.getItems().contains(i1));
        Assert.assertFalse(c.getItems().contains(i2));
    }
    @Test 
    public void testGetItem() {
        c.addItem(i1);
        c.addItem(i2);
        Assert.assertEquals(i1, c.getItem(0));
        Assert.assertEquals(i2, c.getItem(1));
        Assert.assertNull(c.getItem(2));
    }
    @Test 
    public void testSearchResult() {

        c.addItem(i1);
        c.addItem(i2);
        ItemCatalog searchResult = c.searchResult("calculator");
        Assert.assertEquals(1, searchResult.length);
        Assert.assertFalse(searchResult.getItems().contains(i1));
        Assert.assertTrue(searchResult.getItems().contains(i2));
    }
    @Test 
    public void testMyItems() {
        c.addItem(i1);
        c.addItem(i2);
        ItemCatalog myItems = c.myItems("Default");
        Assert.assertEquals(2, myItems.length);
        Assert.assertTrue(myItems.getItems().contains(i1));
        Assert.assertTrue(myItems.getItems().contains(i2));
    }
}