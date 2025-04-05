public class HardMode extends Game {
    public HardMode(QuestionBank questionBank, User user) {
        super(questionBank, user);
    }

    @Override
    public void play() {

        System.out.println(color.GREEN_TEXT + "\nWelcome, " + user.getName() + "! Let's begin the game." + color.RESET);
        System.out.println(color.GREEN_TEXT + "You selected Hard Mode." + color.RESET);
        int maxRounds = 15;

        for (int i = 1; i <= maxRounds; i++) {
            if (!askQuestion(i)) {
                System.out.println(color.RED_TEXT + "Game Over! You lost." + color.RESET);
                return;
            }
            if (i == 15) break; // for last question
            if (i == 5 || i == 10) { // when the user completes a round
                System.out.println(color.GREEN_TEXT + "You've reached $" + prizeMoney + ". Do you want to continue or walk away?" + color.RESET);
                System.out.println("1. Continue\n2. Walk Away");

                while (true) {
                    System.out.print("Enter your choice: ");
                    if (scanner.hasNextInt()) {
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice == 2) {
                            System.out.println(color.GREEN_TEXT + "You walked away with $" + prizeMoney + color.RESET);
                            return;
                        } else if (choice == 1) {
                            break;
                        } else {
                            System.out.println(color.RED_TEXT + "Invalid option, please enter 1 or 2." + color.RESET);
                        }
                    } else {
                        System.out.println(color.RED_TEXT + "Invalid input! Please enter 1 or 2." + color.RESET);
                        scanner.next();
                    }
                }
            }

        }

        System.out.println(color.WHITE_BG + "Congratulations! You won $1,000,000!" + color.RESET);
    }

    @Override
    public int getPrizeMoney(int questionNumber) {
        int[] hardPrizes = {100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 250000, 500000, 1000000};

        return hardPrizes[questionNumber - 1];
    }

}
