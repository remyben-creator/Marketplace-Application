package edu.vassar.cmpu203.brewerscloset.view;

public interface IAccountView {

    interface Listener {
        public void uponLoginGoHome();
        public void uponBackToHome();
        public void uponCreateAccountGoHome();
        public void uponLogout();
        public String getUserEmail();
        public boolean checkValidLogin(String userEmail, String userPassword);
        public boolean checkCreateAccount(String userEmail, String userPassword);
    }


}