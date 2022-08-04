import java.util.Scanner;

public class Tools {

    private static final Scanner scanner = new Scanner(System.in);

    public Tools() {
    }

//    SHUFFLES CARDS BASED ON CHOSEN GAME DIFFICULTY. RESETS ARRAYLIST OF WORDS.
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

//    MENU OPTIONS
    public void printMenu() {
        System.out.println("MainMenu Menu\n\n" +
                "Press: \n" +
                "1: to start a new game,\n" +
                "2: to show high scores table,\n" +
                "3: to print menu,\n" +
                "4: to quit.\n\n" +
                "Enter your choice: ");
    }
}
