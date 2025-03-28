import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    boolean isEasy;
    QuestionBank questionBank;
    Lifeline lifeline = new Lifeline();
    boolean lifelinesUsed[];
    Scanner scanner = new Scanner(System.in);
    int prizeMoney;
    User user;

    public Game( QuestionBank questionBank, User user) {
        this.questionBank = questionBank;
        this.lifelinesUsed = new boolean[]{false, false, false};
        this.user = user;
    }

    public void play() {
        if(user.gertUserModeSelection()) isEasy = true;
        else isEasy = false;
        System.out.println("\nWelcome, " + user.getName() + "! Let's begin the game.");
        System.out.println(isEasy ? "You selected Easy Mode." : "You selected Hard Mode.");

        int maxRounds = isEasy ? 9 : 15;
        for (int i = 1; i <= maxRounds; i++) {
            if (!askQuestion(i)) {
                System.out.println("Game Over! You lost.");
                return;
            }
            if(i == 9) break;
            if (i == 3 || i == 6 ) {
                System.out.println("You've reached $" + prizeMoney + ". Do you want to continue or walk away?");
                System.out.println("1. Continue\n2. Walk Away");
                int choice = scanner.nextInt();
                if (choice == 2) {
                    System.out.println("You walked away with $" + prizeMoney);
                    return;
                }
            }
        }

        System.out.println("Congratulations! You won $1,000,000!");
    }


    public boolean askQuestion(int questionNumber) {
        Questions question = questionBank.getRandomQuestion();
        if (question == null) return false;

        System.out.println("\nQuestion " + questionNumber + ":");
        question.displayQuestion();

        char userChoice;
        boolean usedLifeline = false;

        do {
            System.out.println("\nChoose an answer (or enter 0 for lifelines): ");
            userChoice = scanner.next().toLowerCase().charAt(0);

            if (userChoice == '0' ) {
                useLifeline(question);
                usedLifeline = true;
            }

        } while (userChoice == '0');

        int confirm;
        do {
            System.out.println("Final answer? (1: Yes, 2: No)");
            confirm = scanner.nextInt();

            if (confirm == 2) {
                System.out.println("Re-enter your answer: ");
                userChoice = scanner.next().toLowerCase().charAt(0);
            }
        } while (confirm == 2);
//        System.out.println("Final answer? (1: Yes, 2: No)");
//        int confirm = scanner.nextInt();
//        if (confirm == 2) return askQuestion(questionNumber);

        if (userChoice == question.correctAnswer) {
            prizeMoney = getPrizeMoney(questionNumber);
            System.out.println("Correct! You now have $" + prizeMoney);
            return true;
        } else {
            System.out.println("Incorrect! The correct answer was: " + question.getCorrectAnswer());
            return false;
        }
    }



    public int getPrizeMoney(int questionNumber) {
        int[] easyPrizes = {100, 500, 1000, 8000, 16000, 32000, 125000, 500000, 1000000};
        int[] hardPrizes = {100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 250000, 500000, 1000000};
        return isEasy ? easyPrizes[questionNumber - 1] : hardPrizes[questionNumber - 1];
    }
    public List<Questions> generateQuestions(boolean difficultyLevel)
    { int maxQuestions = 0;
        List<Questions> questions = new ArrayList<>();
        if(difficultyLevel)
            maxQuestions = 9;
        else
            maxQuestions = 15;

        for (int i = 0; i < maxQuestions; i++) {
            Questions question = questionBank.getRandomQuestion();
            questions.add(question);
        }
        return questions;
    }

    public void useLifeline(Questions question)
    {
        System.out.println("Available Lifelines:");
        if (!lifelinesUsed[0]) System.out.println("1. 50/50");
        if (!lifelinesUsed[1]) System.out.println("2. Ask the Audience");
        if (!lifelinesUsed[2]) System.out.println("3. Phone a Friend");

        int lifelineChoice = scanner.nextInt();
        switch (lifelineChoice) {
            case 1:
                lifelinesUsed[0] = true;
                System.out.println("50/50 Eliminates two wrong answers.");
                for(String s: lifeline.eliminateAnswers(question))
                System.out.println(s.toString());
                break;
            case 2:
                lifelinesUsed[1] = true;
               System.out.println( lifeline.audiencHelp(question));;
                break;
            case 3:
                lifelinesUsed[2] = true;
                System.out.println(lifeline.friendHelp(question));;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

}
