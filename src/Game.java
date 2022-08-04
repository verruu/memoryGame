import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int chances;
    private String [][] cards = new String[4][4];

//    MAIN METHOD
    public Game(int difficultySelector, String playerName) {

        int level = difficultySelector;

        if (level == 1) chances = 10;
        if (level == 2) chances = 15;

        DataIn dataIn = new DataIn();
        Logic coMet = new Logic();
        DataOut dataOut = new DataOut();

        shuffleCards(dataIn.wordsPoolBuilder("Words.txt", level), level);
        coMet.setBoard(level);
        dataOut.saveFile(playerName, coMet.cardComparison(cards, level, chances));
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