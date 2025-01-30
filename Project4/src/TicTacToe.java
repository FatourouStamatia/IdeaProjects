import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        while (true) {
            printBoard();
            playerMove();
            if (isWinner()) {
                printBoard();
                System.out.println("Ο παίχτης " + currentPlayer + " κέρδισε!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("Το παιχνίδι είναι ισοπαλία");
                break;
            }
            TicTacToe();
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.println("Παίχτη " + currentPlayer + ", κάνε κίνηση (σειρά και στήλη): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row >= 0 && row <3 && col >= 0 && col <3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("Αυτή η κίνηση δεν είναι ορθή.");
            }
        }
    }

    private static boolean isWinner() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void TicTacToe() {
        currentPlayer = (currentPlayer == 'X') ? 'O' :'X';
    }
}