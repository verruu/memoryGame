import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static int level;
    private static int chances;
    public static ArrayList<String> words = new ArrayList<String>();
    public static String [][] board = new String[4][4];
    public static String [][] cards = new String[4][4];
    public static Scanner scanner = new Scanner(System.in);

////    SOME BASIC TESTING FUNCTIONS - FOR REMOVAL AT FINAL COMMIT
//    public static void printCards() {
//        for (int i = 0; i < level*2; i++) {
//            System.out.print("|");
//            for (int j = 0; j < 4; j++) {
//                System.out.print(cards[i][j]);
//                System.out.print("|");
//            }
//            System.out.println();
//        }
//    }
//
//    public static void simplePrinter(List<String> list) {
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//    }




//    MAIN METHOD
    public static void main(String[] args) {

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

                    wordsPoolBuilder();
                    shuffleCards();
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
    public static int intCheck(int maxVal) {
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

//    BUILDS A BASE OF WORDS BASED ON CHOSEN GAME DIFFICULTY. USES ARRAYLIST CREATED BY READFILE METHOD
//    RETURNS ARRAYLIST OF TYPE STRING (SINGLE ITERATION OF RANDOMIZED WORDS)
    public static ArrayList<String> wordsPoolBuilder() {
        Random rand = new Random();
        ArrayList<String> tempList = readFile("Words.txt");
        int index;
        for (int i = 0; i < level*4; i++) {
            index = rand.nextInt(tempList.size());
            words.add(tempList.get(index));
            tempList.remove(index);
        }
        return words;
    }

//    READS TEXT FILE LINE BY LINE AND RETURNS CONTENT IN AN ARRAYLIST OF TYPE STRING (WORDS)
    public static ArrayList<String> readFile(String fileName) {
        ArrayList<String> tempList = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null) {
                tempList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }

//    SHUFFLES CARDS BASED ON CHOSEN GAME DIFFICULTY. RESETS ARRAYLIST OF WORDS.
    public static void shuffleCards() {
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
    public static void setBoard() {
        System.out.println();
        for (int i = 0; i < level*2; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = " _ ";
            }
        }
    }

//    PRINTS CURRENT BOARD STATE
    public static void printBoard() {
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
                System.out.println("\nRow: ");
                row1 = intCheck(level*2);
                scanner.nextLine();
                System.out.println("Column: ");
                col1 = intCheck(4);
                scanner.nextLine();
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
                System.out.println("\nRow: ");
                row2 = intCheck(level*2);
                scanner.nextLine();
                System.out.println("Column: ");
                col2 = intCheck(4);
                scanner.nextLine();
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
