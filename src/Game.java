import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int chances;
    private static final String HIGH_SCORES_PATH = "highscores.txt";
    private static final String WORDS_PATH = "Words.txt";
    private String [][] cards = new String[4][4];
    private ArrayList<String> tempHighScores;

//    MAIN METHOD
    public Game(int difficultySelector) {

        if (difficultySelector == 1) chances = 10;
        if (difficultySelector == 2) chances = 15;

        DataIn dataIn = new DataIn();
        Logic logic = new Logic();
        DataOut dataOut = new DataOut();

        tempHighScores = dataIn.readFile(HIGH_SCORES_PATH);

        shuffleCards(dataIn.wordsPoolBuilder(WORDS_PATH, difficultySelector), difficultySelector);
        logic.setBoard(difficultySelector);
        dataOut.saveFile(logic.cardComparison(cards, difficultySelector, chances), tempHighScores, HIGH_SCORES_PATH);

        System.out.println("\nHigh scores list:\n");
        dataIn.filePrinter(HIGH_SCORES_PATH);
        System.out.println("\nPress 1 to play again or 4 to quit.");
    }

//    SHUFFLES CARDS BASED ON CHOSEN GAME DIFFICULTY. RESETS ARRAYLIST OF WORDS.
    private void shuffleCards(ArrayList<String> words, int level) {
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
}