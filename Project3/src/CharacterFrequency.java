import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CharacterFrequency {

    public static void main(String[] args) {
        // 1. Αρχικοποίηση πίνακα 128x2
        int[][] charStats = new int[128][2];

        // Γεμίζουμε την πρώτη στήλη με χαρακτήρες
        for (int i = 0; i < 128; i++) {
            charStats[i][0] = i; // Αποθηκεύουμε τον χαρακτήρα
            charStats[i][1] = 0; // Αρχικοποιούμε τη συχνότητα σε 0
        }

        // 2. Διαβάζουμε το αρχείο χαρακτήρα προς χαρακτήρα
        String inputFile = "C:\\Users\\sfato\\OneDrive\\Υπολογιστής\\FileProject3.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            int ch;
            while ((ch = br.read()) != -1) { // Διαβάζουμε κάθε χαρακτήρα
                if (!Character.isWhitespace(ch)) { // Αγνοούμε τα whitespaces
                    charStats[ch][1]++; // Αυξάνουμε τη συχνότητα του χαρακτήρα
                }
            }
        } catch (IOException e) {
            System.out.println("Σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
            return;
        }


        // 3. Φτιάχνουμε λίστες για ταξινόμηση
        List<int[]> statsList = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            if (charStats[i][1] > 0) { // Προσθέτουμε μόνο τους χαρακτήρες που εμφανίστηκαν
                statsList.add(new int[]{charStats[i][0], charStats[i][1]});
            }
        }

        // 4. Εμφάνιση ταξινομημένων στατιστικών
        // (α) Ταξινόμηση κατά χαρακτήρα
        statsList.sort(Comparator.comparingInt(a -> a[0]));
        System.out.println("Στατιστικά ανά χαρακτήρα (αλφαβητική σειρά):");
        for (int[] stat : statsList) {
            System.out.printf("Χαρακτήρας: '%c', Συχνότητα: %d%n", stat[0], stat[1]);
        }

        // (β) Ταξινόμηση κατά συχνότητα
        statsList.sort((a, b) -> Integer.compare(b[1], a[1])); // Φθίνουσα σειρά
        System.out.println("\nΣτατιστικά ανά συχνότητα εμφάνισης (φθίνουσα σειρά):");
        for (int[] stat : statsList) {
            System.out.printf("Χαρακτήρας: '%c', Συχνότητα: %d%n", stat[0], stat[1]);
        }
    }
}