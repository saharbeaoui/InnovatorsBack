package tn.esprit.pidev4sae2back.entities;

import java.util.Arrays;
import java.util.List;

public class BadWordFilter {
    public static boolean checkForBadWords(String text) {
        List<String> badWords = Arrays.asList("fuck", "fuck you", "shit", "fml");

        String[] words = text.split("\\s+"); // Séparer les mots
        for (String word : words) {
            if (badWords.contains(word)) {
                return true; // mot interdit trouvé
            }
        }
        return false; // pas de mot interdit trouvé
    }
}
