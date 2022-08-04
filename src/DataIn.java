import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DataIn {

    private int level;
    private ArrayList<String> words;

    public DataIn(int level) {
        this.level = level;
        this.words = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    //    BUILDS A BASE OF WORDS BASED ON CHOSEN GAME DIFFICULTY. USES ARRAYLIST CREATED BY READFILE METHOD
//    RETURNS ARRAYLIST OF TYPE STRING (SINGLE ITERATION OF RANDOMIZED WORDS)
    public  ArrayList<String> wordsPoolBuilder() {
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
    public  ArrayList<String> readFile(String fileName) {
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
}
