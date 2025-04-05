import java.util.Scanner;


public class Menue {
    User u;
    QuestionBank q = new QuestionBank();
    Scanner s = new Scanner(System.in);
    Game game;
    Colours color = new Colours();
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";

    public Menue() {
    }


    public void launchScreen() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1- Start the game.");
            System.out.println("2- View the rules of the game.");
            System.out.println("3- Exit.");

            try {
                int user_choice = s.nextInt();
                s.nextLine();

                switch (user_choice) {
                    case 1:
                        startGame();
                        break;
                    case 2:
                        viewRules();
                        break;
                    case 3:
                        System.out.println("GoodBye!");
                        return;
                    default:
                        System.out.println("Invalid selection!");

                }
            } catch (Exception e) {
                System.out.println(color.RED_TEXT + "Please enter a valid answer!" + color.RESET);
                s.nextLine();
            }
        }
    }

    public void startGame() {

        System.out.println(color.GREEN_TEXT + "Welcome to who wins the million Game!" + color.RESET);
        System.out.println("Please enter your name here: ");
        String name = s.nextLine();
        while (true) {
            try {

                System.out.println("Choose 1 for Easy mode or 2 for Hard mode ");
                int mode = s.nextInt();
                s.nextLine();
                //getting the difficulty level
                if (mode == 1) {
                    u = new User(name, true);
                    game = new EasyMode(q, u);
                } else if (mode == 2) {
                    u = new User(name, false);
                    game = new HardMode(q, u);
                } else {
                    System.out.println(color.RED_TEXT + "Please enter a valid answer!" + color.RESET);
                    continue;
                }
                game.play();
                break;
            } catch (Exception e) {
                System.out.println(color.RED_TEXT + "Please enter a valid answer!" + color.RESET);
                s.nextLine();
            }
        }
    }

    public void viewRules() {

        System.out.println("If you select Easy difficulty option, you will be asked up to 9 questions on any topics at varying difficulties.");
        System.out.println("If you select Hard difficulty option, you will be asked up to a maximum of 15 questions on any topics.");
        System.out.println("You must choose the most correct answer choice of the four (4) possible answer choices provided.");
        System.out.println("Both difficulty options must be played in three distinct rounds.");

        System.out.println(color.YELLOW_TEXT + "Easy Mode:" + color.RESET);
        System.out.println("*In Round 1*, the player will face up to three (3) questions of varying degrees of difficulty. Each question has a " +
                "respective dollar value. These dollar values shall include the following: Question 1 is $100, Question 2 is $500 and Question 3 is $1,000..");
        System.out.println("Upon correctly answering all questions in this round, a player can choose to walk away. If a player walks away at this round" +
                " they will “walk away” with $1,000.00.");
        System.out.println("If the player successfully completes Round 1 and they choose not to walk away they will move on to Round 2.\n" +
                "*In round 2* the player will face up to three (3) questions of varying degrees of difficulty. Each question has a respective dollar value. \n" +
                " These dollar values shall include the following amounts: Question 4 is $8,000, Question 5 is $16,000 and Question 6 is $32,000.\n");
        System.out.println("Once the player correctly answers question 6, they can choose to “walk away” or move on to Round 3. If the player “walks away”" +
                " they will walk away with $32,000.\n");
        System.out.println("*In Round 3*, the player will face up to three (3) questions of varying degrees of difficulty.\n" +
                "Each question has a respective dollar value. These dollar values shall include the following amounts:  Question 7 is $125,000, Question 8 is $500,000 and Question is 9 is $1,000,000.\n");
        System.out.println("The player cannot walk away at this round however, once they complete this round they will win the game and walk away with 1 million dollars.\n");
        System.out.println("Once the you incorrectly answers a question they will lose the game and walk away with no money.");
        System.out.println(color.YELLOW_TEXT + "Hard Mode:" + color.RESET);
        System.out.println("*In Round 1*, the player will face up to five (5) questions of varying degrees of difficulty. Each question has a respective dollar value. " +
                "These dollar values shall include the following: Question 1 is $100, Question 2 is $200, Question 3 is $300, Question 4 is 500 and Question 5 is $1,000");
        System.out.println("*In round 2* the player will face up to five (5) questions of varying degrees of difficulty. Each question has a respective dollar value. " +
                "These dollar values shall include the following amounts: Question 6 is $2,000, Question 7 is $4,000, Question 8 is $8,000, Question 9 is $16,000 and Question 10 is $32,000.\n");
        System.out.println("Once the player correctly answers question 10, they can choose to “walk away” or move on to Round 3. If the player “walks away” after correctly answering question 10 then the will walk away with $32,000.\n");
        System.out.println(" In Round 3, the player will face up to five (5) questions of varying degrees of difficulty.\n" +
                "Each question has a respective dollar value. These dollar values shall include the following amounts:  Question 11 is $64,000, Question 12 is $125,000, Question 13 is $250,000, Question 14 is $500,000 and Question is 15 is $1,000,000.\n" +
                "\nThe player cannot walk away at this round however, once they complete this round they will win the game and walk away with 1 million dollars.\n");
        System.out.println("The lifelines are only available in round 2 and round 3 for players who chose the hard option, whereas all lifelines are available from round 1 to players who chose the easy option.");
        System.out.println("Tap 1 to go back");
        System.out.println("Tap 2 to exit");
        int rules_menue_choice = 0;
        try {
            rules_menue_choice = s.nextInt();
            s.nextLine();
            if (rules_menue_choice == 1)
                launchScreen();
            else if (rules_menue_choice == 2) {
                System.out.println("GoodBye!");
                return;
            } else {
                System.out.println("Invalid choice!");
                viewRules();
            }
        } catch (Exception error) {
            System.out.println(error);
        }

    }
}
