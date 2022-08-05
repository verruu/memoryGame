import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Tools tools = new Tools();
        PrintMainMenu menu = new PrintMainMenu();
        DataIn dataIn = new DataIn();

        tools.createFile("highscores.txt");

        boolean quit = false;

        System.out.println("\nWords Memory Game.");
        menu.printMenu();

        while (!quit) {
            switch (tools.checkUserInput(4)) {
                case 1: // NEW GAME

                    System.out.println("\nEnter player name: ");
                    String playerName = scanner.nextLine();

                    System.out.println("\nSelect difficulty (1 for easy, 2 for hard): ");
                    new Game(tools.checkUserInput(2), playerName);

                    break;
                case 2: // PRINT HIGH SCORES TABLE
                    System.out.println("\nHigh scores list:\n");
                    dataIn.filePrinter("highscores.txt");
                    System.out.println("\nEnter your choice: ");
                    break;
                case 3: // PRINT MENU
                    menu.printMenu();
                    break;
                case 4: // QUIT
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }
}