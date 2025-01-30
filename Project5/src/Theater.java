import java.util.Scanner;
import java.util.InputMismatchException;

public class Theater {

    private static final int ROWS = 30; // Αριθμός σειρών
    private static final int COLUMNS = 12; // Αριθμός στηλών
    private static boolean[][] seats; // Δυσδιάστατος πίνακας για τις θέσεις

    public static void main(String[] args) {
        seats = new boolean[ROWS][COLUMNS]; // Αρχικοποίηση θέσεων
        Scanner in = new Scanner(System.in);
        int choice;

        do {
            menu();
            try {
                System.out.print("Επιλογή: ");
                choice = in.nextInt();
                in.nextLine(); // Καθαρισμός buffer

                switch (choice) {
                    case 1:
                        System.out.print("Παρακαλούμε επιλέξτε στήλη (A-L): ");
                        char column = in.next().charAt(0);
                        System.out.print("Παρακαλούμε επιλέξτε σειρά (1-30): ");
                        int row = in.nextInt();
                        book(column, row);
                        break;
                    case 2:
                        System.out.print("Παρακαλούμε επιλέξτε στήλη (A-L): ");
                        column = in.next().charAt(0);
                        System.out.print("Παρακαλούμε επιλέξτε σειρά (1-30): ");
                        row = in.nextInt();
                        cancel(column, row);
                        break;
                    case 3:
                        System.out.println("Έξοδος από το πρόγραμμα.");
                        break;
                    default:
                        System.out.println("Μη έγκυρη επιλογή. Παρακαλώ προσπαθήστε ξανά.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Σφάλμα: Δώσατε μη έγκυρη είσοδο. Προσπαθήστε ξανά.");
                in.nextLine(); // Καθαρισμός buffer για να μη κολλήσει το loop
                choice = 0; // Επαναφορά επιλογής για να συνεχίσει το loop
            }
        } while (choice != 3);

        in.close(); // Κλείσιμο scanner
    }

    public static void menu() {
        System.out.println("\nΠαρακαλούμε επιλέξτε ένα από τα παρακάτω:");
        System.out.println("1. Κράτηση θέσης.");
        System.out.println("2. Ακύρωση θέσης.");
        System.out.println("3. Έξοδος.");
    }

    public static void book(char column, int row) {
        int colIndex = column - 'A'; // Μετατροπή από γράμμα σε αριθμό (A->0, B->1, ..., L->11)
        int rowIndex = row - 1; // Μετατροπή από 1-based σε 0-based (1->0, ..., 30->29)

        if (!isValidSeat(column, row)) {
            System.out.println("Ο αριθμός της θέσης δεν υπάρχει.");
            return;
        }
        if (seats[rowIndex][colIndex]) {
            System.out.println("Η θέση " + column + row + " δεν είναι διαθέσιμη.");
        } else {
            seats[rowIndex][colIndex] = true;
            System.out.println("Η θέση " + column + row + " κρατήθηκε με επιτυχία.");
        }
    }

    public static void cancel(char column, int row) {
        int colIndex = column - 'A';
        int rowIndex = row - 1;

        if (!isValidSeat(column, row)) {
            System.out.println("Ο αριθμός της θέσης δεν υπάρχει.");
            return;
        }
        if (!seats[rowIndex][colIndex]) {
            System.out.println("Η θέση " + column + row + " δεν είναι κρατημένη.");
        } else {
            seats[rowIndex][colIndex] = false;
            System.out.println("Η θέση " + column + row + " ακυρώθηκε με επιτυχία.");
        }
    }

    private static boolean isValidSeat(char column, int row) {
        return column >= 'A' && column <= 'L' && row >= 1 && row <= 30;
    }
}