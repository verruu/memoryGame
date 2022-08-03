import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static ArrayList<String> words = new ArrayList<String>();
    public static String [][] board = new String[4][4];
    public static String [][] cards = new String[4][4];
    public static Scanner scanner = new Scanner(System.in);
    private static int level;

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int lvl) {
        level = lvl;
    }

    public static void printBoard() {
        for (int i = 0; i < 4; i++) {
            System.out.print("|");
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void printCards() {
        for (int i = 0; i < 4; i++) {
            System.out.print("|");
            for (int j = 0; j < 4; j++) {
                System.out.print(cards[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }
//    SHUFFLE CARDS BASED ON CHOSEN GAME DIFFICULTY. RESETTING ARRAYLISTS.
    public static void shuffleCards() { //row? col?
        Random rand = new Random();
        int index;
        words.addAll(words);
        if (level == 1) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    index = rand.nextInt(words.size());
                    cards[i][j] = words.get(index);
                    words.remove(index);
                }
            }
        } else if (level == 2) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    index = rand.nextInt(words.size());
                    cards[i][j] = words.get(index);
                    words.remove(index);
                }
            }
        }
    }
//    BUILD A BASE OF WORDS BASED ON CHOSEN GAME DIFFICULTY. USES ARRAYLIST CREATED BY FILEREADER METHOD
//    RETURNS ARRAYLIST OF TYPE STRING (SINGLE ITERATION OF RANDOMIZED WORD)
    public static ArrayList<String> wordsPoolBuilder() {
        Random rand = new Random();
        ArrayList<String> tempList = fileReader();
        int index;
        if (level == 1) { // EASY: NEED 4 WORDS
            for (int i = 0; i < 4; i++) {
                index = rand.nextInt(tempList.size());
                words.add(tempList.get(index));
                tempList.remove(index);
            }
        } else if (level == 2) { // HARD: NEED 8 WORDS
            for (int i = 0; i < 8; i++) {
                index = rand.nextInt(tempList.size());
                words.add(tempList.get(index));
                tempList.remove(index);
            }
        }
        return words;
    }
//    READ TEXT FILE LINE BY LINE AND RETURN CONTENT IN AN ARRAYLIST OF TYPE STRING (WORDS)
    public static ArrayList<String> fileReader() {
        ArrayList<String> tempList = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Words.txt"));
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

    public static void simplePrinter(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void checkInput(String [][] cards) {
        while (true) {
            if (!gameOver()) {
                System.out.println("Row: (1-4)");
                int row1 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Collumn: (1-4)");
                int col1 = scanner.nextInt();
                scanner.nextLine();

                if (!board[row1-1][col1-1].equals(" _ ")) {
                    System.out.println("Already entered.\n");
                    printBoard();
                    continue;
                } else {
                    board[row1-1][col1-1] = " " + cards[row1-1][col1-1] + " ";
                    printBoard();
                }
                System.out.println("Row: (1-4)");
                int row2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Collumn: (1-4)");
                int col2 = scanner.nextInt();
                scanner.nextLine();

                if (!board[row2-1][col2-1].equals(" _ ")) {
                    System.out.println("Already entered.\n");
                    board[row1 - 1][col1 - 1] = " _ ";
                    printBoard();
                    continue;
                } else {
                    board[row2-1][col2-1] = " " + cards[row2-1][col2-1] + " ";
                    if (board[row1-1][col1-1].equals(board[row2-1][col2-1])) {
                        printBoard();
                        System.out.println("Correct");
                    } else {
                        printBoard();
                        System.out.println("False");
                        board[row1-1][col1-1] = " _ ";
                        board[row2-1][col2-1] = " _ ";
                        printBoard();
                    }
                }
            } else {
                System.out.println("Game over");
                break;
            }
        }
    }

    public static boolean gameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(" _ ")) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

//        simplePrinter(fileReader());
        while (true) {
            setLevel(2);
            simplePrinter(wordsPoolBuilder());
            shuffleCards();
            printBoard();
            printCards();
            break;
        }


//        while (true) {
//            System.out.println("Press n for a new game or q to quit.");
//            String choice = scanner.nextLine();
//            if (choice.equals("q")) {
//                System.out.println("Exitting the game.");
//                break;
//            } else if (choice.equals("n")) {
//                shuffleCards();
//                for (int i = 0; i < 4; i++) {
//                    for (int j = 0; j < 4; j++) {
//                        board[i][j] = " _ ";
//                    }
//                }
//                printBoard();
//                checkInput(cards);
//                break;
//            } else {
//                System.out.println("Invalid input.");
//                continue;
//            }
//        }
    }
}
