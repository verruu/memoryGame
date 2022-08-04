import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataOut {
    Stopwatch stopwatch = new Stopwatch();

    public DataOut() {
    }

    //    public void saveFile() {
//        try {
//            ArrayList<String> tempList = new ArrayList<>();
//            BufferedWriter writer = new BufferedWriter(new FileWriter("highscores.txt"));
//            writer.write("Writing to a file.");
//            writer.write("\nHere is another line.");
//
//            for (String name : names) {
//                writer.write("\n" + name);
//            }
//            writer.close();
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void saveFile(String name, ArrayList<String> list) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("highscores.txt"));
            writer.write(name + " | " + LocalDate.now() + " | " +
                    list.get(1) + " min " + list.get(2) + " sec" + " | " + list.get(0));
            writer.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
