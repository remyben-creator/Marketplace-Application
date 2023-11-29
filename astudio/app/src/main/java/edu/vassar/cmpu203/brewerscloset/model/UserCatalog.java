package edu.vassar.cmpu203.brewerscloset.model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class UserCatalog {
    public int length;
    public List<User> users;

    public UserCatalog() {
        this.length = 0;
        this.users = new LinkedList<>();
    }

    public void addUser(User user) {
        users.add(user);
        this.length++;
    }

    public void removeUser(User user) {
        users.remove(user);
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
