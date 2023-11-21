package edu.vassar.cmpu203.vassarmarketplace.view;

public interface IAccountView {

    interface Listener {
        public void uponLogin(String userEmail, String userPassword);
        public void uponBack();
    }


}