import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int chances;
    private String [][] cards = new String[4][4];

//    MAIN METHOD
    public Game(int difficultySelector) {

        int level = difficultySelector;

        if (level == 1) chances = 10;
        if (level == 2) chances = 15;

        DataIn data = new DataIn(level);
        Logic coMet = new Logic();

        shuffleCards(data.wordsPoolBuilder("Words.txt"), level);
        coMet.setBoard(level);
        coMet.cardComparison(cards, level, chances);
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