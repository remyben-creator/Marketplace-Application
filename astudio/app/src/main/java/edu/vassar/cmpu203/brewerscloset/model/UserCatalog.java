package edu.vassar.cmpu203.brewerscloset.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class UserCatalog implements Catalog{
    public int length;
    public List<User> users;

    public UserCatalog() {
        this.length = 0;
        this.users = new LinkedList<>();
    }

    public int getLength() {return this.length;}
    public List<User> getList() {return this.users;}
    public User getItem(int index) {
        //get single item
        ListIterator<User> iterator = this.users.listIterator();
        int counter = 0;

        while (iterator.hasNext()) {
            User current = iterator.next();
            if (counter == index) {
                return current;
            }
            counter++;
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
        this.length++;
    }

    public void removeUser(User toRemove, ItemCatalog items) {
        toRemove.deleteUserStuff(items);
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User element = iterator.next();
            if (element.equals(toRemove)) {
                iterator.remove();
            }
        }
        this.length--;
    }

    public User loginUser(String userEmail, String userPassword) {
        //get single item
        ListIterator<User> iterator = this.users.listIterator();

        while (iterator.hasNext()) {
            User current = iterator.next();
            if (current.email.equals(userEmail) && current.password.equals(userPassword)) {
                return current;
            }
        }
        return null;
    }
    public User checkForUser(String userEmail, String userPassword) {
        //get single item
        ListIterator<User> iterator = this.users.listIterator();

        while (iterator.hasNext()) {
            User current = iterator.next();
            if (current.email.equals(userEmail)) {
                return current;
            }
        }
        return null;
    }
}
