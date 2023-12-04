package edu.vassar.cmpu203.brewerscloset.tests;

import static org.junit.Assert.*;
import org.junit.Test; // for the @Test annotation

import edu.vassar.cmpu203.brewerscloset.model.Item;

public class ItemTest {
    Item i = new Item("lights", 9.99, "blue", "none" );
    @Test
    public void testGetTitle() {
        assertEquals("lights", i.getTitle());
    }
    @Test
    public void testSetTitle() {
        assertEquals("lights", i.title);
    }
    @Test
    public void testGetDescription() {
        assertEquals("blue", i.getDescription());
    }
    @Test
    public void testSetDescription() {
        assertEquals("lights", i.title);
    }
    @Test
    public void testGetPriceString() {
        assertEquals("9.99", i.getPriceString());
    }
    //@Test
    //public void testGetSeller() {
    //    assertEquals("Default", i.getSeller());
    //}
    @Test
    public void testSetSeller() {
        assertEquals("Default", i.seller);
    }
}