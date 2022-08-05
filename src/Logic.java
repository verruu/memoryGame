import java.util.ArrayList;

public class Logic {
    private String [][] board = new String[4][4];

    Tools tools = new Tools();
    Stopwatch stopwatch = new Stopwatch();

//    COMPARES CHOSEN CARDS AND PRINTS RESULTS, NEEDS REFACTORING..
    public ArrayList<String> cardComparison(String [][] cards, int level, int chances) {
        stopwatch.start();
        printBoard(level);
        while (true) {
            int row1, row2, col1, col2;
            if (!gameOver(level, chances)) {
                System.out.println("\nEnter coordinates of the first word.");
                row1 = row(level);
                col1 = col();
                System.out.println();
                if (!board[row1-1][col1-1].equals(" _ ")) {
                    System.out.println("Already entered.\n");
                    printBoard(level);
                    continue;
                } else {
                    board[row1-1][col1-1] = " " + cards[row1-1][col1-1] + " ";
                    printBoard(level);
                }

                System.out.println("\nEnter coordinates of the second word.");
                row2 = row(level);
                col2 = col();
                System.out.println();
                if (!board[row2-1][col2-1].equals(" _ ")) {
                    System.out.println("Already entered.");
                    board[row1 - 1][col1 - 1] = " _ ";
                    chances-=2;
                    System.out.println(chances + " chances left.\n");
                    printBoard(level);
                    continue;
                } else {
                    board[row2 - 1][col2 - 1] = " " + cards[row2 - 1][col2 - 1] + " ";
                    printBoard(level);
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
                stopwatch.stop();
                if (chances > 0) System.out.println("\nCongratulations, you have won!");
                else System.out.println("\nYou have lost, better luck next time!");
                System.out.println("Your attempt took you " + stopwatch.getMinutes() +
                        " minute(s) and " + stopwatch.seconds() + " second(s). " + chances + " chances remained.");
                break;
            }
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(String.valueOf(chances));
        list.add(String.valueOf(stopwatch.getMinutes()));
        list.add(String.valueOf(stopwatch.seconds()));
        return list;
    }

//    ROW INPUT
    private int row(int level) {
        System.out.println("\nRow: ");
        return tools.checkUserInput(level*2);
    }

//    COLUMN INPUT
    private int col() {
        System.out.println("Column: ");
        return tools.checkUserInput(4);
    }

//    PREPARES THE BASE LOOK OF THE BOARD
    public void setBoard(int level) {
        System.out.println();
        for (int i = 0; i < level*2; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = " _ ";
            }
        }
    }

//    PRINTS CURRENT BOARD STATE
    private void printBoard(int level) {
        for (int i = 0; i < level*2; i++) {
            System.out.print("|");
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

//    GAMEOVER CONDITIONS CHECKER
    private boolean gameOver(int level, int chances) {
        if (chances == 0) return true;
        for (int i = 0; i < level*2; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(" _ ")) return false;
            }
        }
        return true;
    }
}
