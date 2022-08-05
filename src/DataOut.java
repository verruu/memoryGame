import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataOut {

//    WRITES HIGHSCORES TEXT FILE BASED ON PREVIOUS FILE CONTENT AND THE LAST SCORE ACHIEVED.
    public void saveFile(String name, ArrayList<String> list, ArrayList<String> pastHighScores) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("highscores.txt"));
            writer.write(name + " | " + LocalDate.now() + " | " +
                    list.get(1) + " min " + list.get(2) + " sec" + " | " + list.get(0));
            if (pastHighScores.size() < 9)
                for (int i = 0; i < pastHighScores.size(); i++) writer.write("\n" + pastHighScores.get(i));
            if (pastHighScores.size() >= 9)
                for (int i = 0; i < 9; i++) writer.write("\n" + pastHighScores.get(i));
            writer.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
