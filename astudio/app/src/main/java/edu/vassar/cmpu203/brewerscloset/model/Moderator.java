package edu.vassar.cmpu203.brewerscloset.model;

import java.util.Arrays;
import java.util.List;

/**
 * Represents where the item validation functions reside
 */
public class Moderator {

    public static boolean isValidEmail(String email) {
        if (email.endsWith("@vassar.edu")) {return true;}
        return false;
    }
    public static boolean isBannedItem(String title, String description) {
        List<String> bannedWords = Arrays.asList("Fuck", "Shit", "Damn");
        for (String word : bannedWords) {
            if (title.toLowerCase().contains(word.toLowerCase()) ||
                    description.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}