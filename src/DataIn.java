import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DataIn {
    private final ArrayList<String> words;

    public DataIn() {
        this.words = new ArrayList<>();
    }

//    BUILDS A BASE OF WORDS BASED ON CHOSEN GAME DIFFICULTY. USES ARRAYLIST CREATED BY READFILE METHOD
//    RETURNS ARRAYLIST OF TYPE STRING (SINGLE ITERATION OF RANDOMIZED WORDS)
    public ArrayList<String> wordsPoolBuilder(String fileName, int level) {
        Random rand = new Random();
        ArrayList<String> tempList = readFile(fileName);
        int index;
        for (int i = 0; i < level*4; i++) {
            index = rand.nextInt(tempList.size());
            words.add(tempList.get(index));
            tempList.remove(index);
        }
        return words;
    }

//    READS TEXT FILE LINE BY LINE AND RETURNS CONTENT IN AN ARRAYLIST OF TYPE STRING (WORDS)
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> tempList = new ArrayList<>();
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
