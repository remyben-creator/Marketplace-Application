package edu.vassar.cmpu203.brewerscloset.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;
import edu.vassar.cmpu203.brewerscloset.model.User;
import edu.vassar.cmpu203.brewerscloset.model.UserCatalog;
public class UserCatalogTest {

    @Test
    //adding a user
    public void testAddUser() {
        UserCatalog userCatalog = new UserCatalog();
        User user = new User("user@example.com", "password");

        userCatalog.addUser(user);

        assertEquals(1, userCatalog.getLength());
        assertTrue(userCatalog.getList().contains(user));
    }

    @Test
    //removing a user
    public void testRemoveUser() {
        UserCatalog userCatalog = new UserCatalog();
        User user = new User("user@example.com", "password");

        userCatalog.addUser(user);
        userCatalog.removeUser(user);

        assertEquals(0, userCatalog.getLength());
        assertFalse(userCatalog.getList().contains(user));
    }

    @Test
    //logging a user in
    public void testLoginUser() {
        UserCatalog userCatalog = new UserCatalog();
        ItemCatalog items = new ItemCatalog();
        User user = new User("user@example.com", "password");
        userCatalog.addUser(user);

        User loggedInUser = userCatalog.loginUser("user@example.com", "password", items);
        User loggedInUser2 = userCatalog.loginUser("invalid@example.com", "wrongpassword", items);

        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
        assertNotNull(loggedInUser2);
        assertEquals("Guest", loggedInUser2.email);
    }


    @Test
    //checking for a user
    public void testCheckForUser() {
        UserCatalog userCatalog = new UserCatalog();
        User user = new User("user@example.com", "password");
        userCatalog.addUser(user);

        User foundUser = userCatalog.checkForUser("user@example.com", "password");
        User notFoundUser = userCatalog.checkForUser("nonexistent@example.com", "password");

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
        assertNull(notFoundUser);

    }

    @Test
    //getting item from id
    public void testGetItemFromID() {
        UserCatalog userCatalog = new UserCatalog();
        User user = new User("user@example.com", "password");
        userCatalog.addUser(user);
        User foundUser = userCatalog.getItemFromID(user.id);
        User notFoundUser = userCatalog.getItemFromID("nonexistentUserID");
        assertNotNull(foundUser);
        assertEquals(user, foundUser);
        assertNull(notFoundUser);
    }
}