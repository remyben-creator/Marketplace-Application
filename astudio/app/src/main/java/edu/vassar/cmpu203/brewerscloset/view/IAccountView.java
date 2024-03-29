package edu.vassar.cmpu203.brewerscloset.view;

public interface IAccountView {

    interface Listener {
        public void uponLoginGoHome();
        public void uponBackToHome(boolean edit);
        public void uponCreateAccountGoHome();
        public String getUserEmail();
        public boolean checkValidLogin(String userEmail, String userPassword);
        public boolean checkCreateAccount(String userEmail, String userPassword);
        //logged in methods
        public void uponLogout();
        public void uponDelete();
    }


}