import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataOut {

//    WRITES HIGHSCORES TEXT FILE BASED ON PREVIOUS FILE CONTENT AND THE LAST SCORE ACHIEVED.
    public void saveFile(ArrayList<String> list, ArrayList<String> pastHighScores, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            if (Integer.parseInt(list.get(1)) > 0 && list.get(0) != null) {
                writer.write(list.get(0) + " | " + LocalDate.now() + " | " +
                        list.get(2) + " min " + list.get(3) + " sec" + " | " + list.get(1) + "\n");
                if (pastHighScores.size() < 9)
                    for (int i = 0; i < pastHighScores.size(); i++) writer.write(pastHighScores.get(i) + "\n");
                if (pastHighScores.size() >= 9)
                    for (int i = 0; i < 9; i++) writer.write(pastHighScores.get(i) + "\n");
            } else {
                if (pastHighScores.size() < 10)
                    for (int i = 0; i < pastHighScores.size(); i++) writer.write(pastHighScores.get(i) + "\n");
                if (pastHighScores.size() >= 10)
                    for (int i = 0; i < 10; i++) writer.write(pastHighScores.get(i) + "\n");
            }
            writer.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
