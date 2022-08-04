public class MainMenu {

    public static void main(String[] args) {

        Tools tools = new Tools();
        boolean quit = false;
        System.out.println("Words Memory Game.\n");
        tools.printMenu();

        while (!quit) {
            switch (tools.checkUserInput(4)) {
                case 1: // NEW GAME
                    System.out.println("\nSelect difficulty (1 for easy, 2 for hard): ");

                    new Game(tools.checkUserInput(2));

                    break;
                case 2: // PRINT HIGH SCORES TABLE
                    break;
                case 3: // PRINT MENU
                    tools.printMenu();
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