import java.util.Scanner;

public class Menue {
    User u;
    QuestionBank q = new QuestionBank();
    Scanner s = new Scanner(System.in);

    public Menue() {
        // No user is passed in the constructor
    }

    public void launchScreen(){
        System.out.print("Enter your name: ");
        String userName = s.next();
        System.out.println("Select difficulty: 1. Easy 2. Hard");
        int difficultyChoice = s.nextInt();
        boolean isEasy = (difficultyChoice == 1);
        u = new User(userName, isEasy); // Create User object with name and mode selection

        Game game = new Game(q, u);
        System.out.println("1- Start the game.");
        System.out.println("2- View the rules of the game.");
        System.out.println("3- Exit.");
        int user_choice = 0;
        try{
            user_choice = s.nextInt();
        }catch (Exception error)
        {
            System.out.println(error);
        }
        switch(user_choice){
            case 1:
                game.play();
                break;
            case 2:
                viewRules();
                break;
            case 3:
                System.out.println("GoodBye!");
                return;
            default:
                System.out.println("Invalid selection!");
                launchScreen();
        }
    }

    public void viewRules() {
        System.out.println("If you select Easy difficulty option, you will be asked up to 9 questions on any topics at varying difficulties.");
        System.out.println("If you select Hard difficulty option, you will be asked up to a maximum of 15 questions on any topics.");
        System.out.println("You must choose the most correct answer choice of the four (4) possible answer choices provided.");
        System.out.println("Both difficulty options must be played in three distinct rounds.");
        System.out.println("In Round 1, the player will face up to three (3) questions of varying degrees of difficulty. Each question has a " +
                "respective dollar value. These dollar values shall include the following: Question 1 is $100, Question 2 is $500 and Question 3 is $1,000.");
        System.out.println("Once you incorrectly answer a question, you will lose the game and walk away with no money.");
        System.out.println("Tap 1 to go back");
        System.out.println("Tap 2 to exit");
        int rules_menue_choice = 0;
        try {
            rules_menue_choice = s.nextInt();
            if(rules_menue_choice == 1)
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
