import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static int level;
    private static int chances;
    private static String [][] board = new String[4][4];
    private static String [][] cards = new String[4][4];
    private static Scanner scanner = new Scanner(System.in);

//    MAIN METHOD
    public static void main(String[] args) {

        new Game();
//        boolean quit = false;
//        int choice;
//        System.out.println("Words Memory Game.\n");
//        printMenu();
//
//        while (!quit) {
//            choice = intCheck(4);
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    System.out.println("\nSelect difficulty (1 for easy, 2 for hard): ");
//                    level = intCheck(2);
//                    scanner.nextLine();
//                    if (level == 1) chances = 10;
//                    if (level == 2) chances = 15;
//
//                    wordsPoolBuilder();
//                    shuffleCards();
//                    setBoard();
//                    printBoard();
//                    cardComparison(cards);
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    printMenu();
//                    break;
//                case 4:
//                    quit = true;
//                    break;
//                default:
//                    System.out.println("Invalid input, please try again.");
//                    break;
//
//            }
//        }
    }

    public Game() {
        boolean quit = false;
        int choice;
        System.out.println("Words Memory Game.\n");
        printMenu();

        while (!quit) {
            choice = intCheck(4);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nSelect difficulty (1 for easy, 2 for hard): ");
                    level = intCheck(2);
                    scanner.nextLine();
                    if (level == 1) chances = 10;
                    if (level == 2) chances = 15;

                    DataIn data = new DataIn(level);

                    shuffleCards(data.wordsPoolBuilder());
                    setBoard();
                    printBoard();
                    cardComparison(cards);
                    break;
                case 2:
                    break;
                case 3:
                    printMenu();
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;

            }
        }
    }
//    INTEGER INPUT VALIDITY CHECKER
    private static int intCheck(int maxVal) {
        boolean incorrectInput = true;
        int userChoice;
        while (incorrectInput) {
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                if (userChoice > 0 && userChoice <= maxVal) {
                    return userChoice;
                } else {
                    System.out.println("Error: Invalid input, please try again.");
                }
            } else {
                scanner.next();
                System.out.println("Error: Invalid input, please try again.");
            }
        }
        return -1;
    }

//    MENU OPTIONS
    public static void printMenu() {
        System.out.println("Main Menu\n\n" +
                "Press: \n" +
                "1: to start a new game,\n" +
                "2: to show high scores table,\n" +
                "3: to print menu,\n" +
                "4: to quit.\n\n" +
                "Enter your choice: ");
    }

//    SHUFFLES CARDS BASED ON CHOSEN GAME DIFFICULTY. RESETS ARRAYLIST OF WORDS.
    private static void shuffleCards(ArrayList<String> words) {
        Random rand = new Random();
        int index;
        words.addAll(words);
        for (int i = 0; i < level*2; i++) {
            for (int j = 0; j < 4; j++) {
                index = rand.nextInt(words.size());
                cards[i][j] = words.get(index);
                words.remove(index);
            }
        }
    }

//    PREPARES THE BASE LOOK OF THE BOARD
    private static void setBoard() {
        System.out.println();
        for (int i = 0; i < level*2; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = " _ ";
            }
        }
    }

//    PRINTS CURRENT BOARD STATE
    private static void printBoard() {
        for (int i = 0; i < level*2; i++) {
            System.out.print("|");
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

//    COMPARES CHOSEN CARDS AND PRINTS RESULTS, NEEDS REFACTORING..
    public static void cardComparison(String [][] cards) {

        while (true) {
            int row1, row2, col1, col2;
            if (!gameOver()) {
                System.out.println("\nEnter coordinates of the first word.");
                row1 = row();
                col1 = col();
                System.out.println();
                if (!board[row1-1][col1-1].equals(" _ ")) {
                    System.out.println("Already entered.\n");
                    printBoard();
                    continue;
                } else {
                    board[row1-1][col1-1] = " " + cards[row1-1][col1-1] + " ";
                    printBoard();
                }

                System.out.println("\nEnter coordinates of the second word.");
                row2 = row();
                col2 = col();
                System.out.println();
                if (!board[row2-1][col2-1].equals(" _ ")) {
                    System.out.println("Already entered.");
                    board[row1 - 1][col1 - 1] = " _ ";
                    chances-=2;
                    System.out.println(chances + " chances left.\n");
                    printBoard();
                    continue;
                } else {
                    board[row2 - 1][col2 - 1] = " " + cards[row2 - 1][col2 - 1] + " ";
                    printBoard();
                }

                if (board[row1-1][col1-1].equals(board[row2-1][col2-1])) {
                    System.out.println("\nCorrect");
                    System.out.println(chances + " chances left.");
                } else {
                    System.out.println("\nFalse");
                    chances--;
                    board[row1-1][col1-1] = " _ ";
                    board[row2-1][col2-1] = " _ ";
                    System.out.println(chances + " chances left.");
                }
            } else {
                if (chances > 0) System.out.println("\nCongratulations, you have won!");
                else System.out.println("\nYou have lost, better luck next time!");
                System.out.println("\nPress 1 to play again or 4 to quit.");
                break;
            }
        }
    }

//    ROW
    private static int row() {
        System.out.println("\nRow: ");
        int row = intCheck(level*2);
        scanner.nextLine();
        return row;
    }

//    COLUMN
    private static int col() {
        System.out.println("Column: ");
        int col = intCheck(4);
        scanner.nextLine();
        return col;
    }

////    PREVIOUSLY CHOSEN CARD CHECKER - THIS NEEDS TO BE REDONE
//    private static void checker() {
//        boolean incorrect = true;
//        while (incorrect) {
//            int row1 = row();
//            int col1 = col();
//            System.out.println();
//            if (!board[row1-1][col1-1].equals(" _ ")) {
//                System.out.println("Already entered.\n");
//                printBoard();
//                continue;
//            } else {
//                board[row1-1][col1-1] = " " + cards[row1-1][col1-1] + " ";
//                printBoard();
//                incorrect = false;
//            }
//        }
//    }

//    GAMEOVER CONDITIONS CHECKER
    public static boolean gameOver() {
        if (chances == 0) return true;
        for (int i = 0; i < level*2; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(" _ ")) return false;
            }
        }
        return true;
    }
}
