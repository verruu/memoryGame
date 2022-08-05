import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Tools {
    private static final Scanner scanner = new Scanner(System.in);

//    USER INPUT CHECKER, USED FOR INTEGER INPUT VALIDATION.
    public int checkUserInput(int maxVal) {
        boolean incorrectInput = true;
        int userChoice;
        while (incorrectInput) {
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                if (userChoice > 0 && userChoice <= maxVal) {
                    return userChoice;
                } else {
                    System.out.println("Error: Invalid input, please try again.");
                }
            } else {
                scanner.next();
                System.out.println("Error: Invalid input, please try again.");
            }
        }
        return -1;
    }

//    CREATE A FILE (IN THIS CASE THE BASE HIGSCORES.TXT FILE TO PREVENT READ/WRITE ERRORS)
    public void createFile(String pathName) {
        try {
            File New_File = new File(pathName);
            if (New_File.createNewFile()){
//                System.out.println("The file is created successfully!");
            } else {
//                System.out.println("The file already exists.");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
