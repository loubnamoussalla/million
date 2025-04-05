import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Game {

    QuestionBank questionBank;
    Lifeline lifeline = new Lifeline();
    boolean lifelinesUsed[];
    Scanner scanner = new Scanner(System.in);
    int prizeMoney;
    User user;
    Colours color = new Colours();

    public Game(QuestionBank questionBank, User user) {

        this.questionBank = questionBank;
        //initializing lifelines
        this.lifelinesUsed = new boolean[]{false, false, false};
        this.user = user;
    }

    public abstract void play();


    public boolean askQuestion(int questionNumber) {
        Questions question = questionBank.getRandomQuestion(); //to get random questions from questionBank
        if (question == null) return false;

        System.out.println("\nQuestion " + questionNumber + ":");
        question.displayQuestion();// to display the question in a propeer format

        char userChoice = getUserAnswer(question, questionNumber);
        userChoice = confirmAnswer(userChoice, questionNumber, question);

        return checkAnswer(userChoice, question, questionNumber);
    }

    private char getUserAnswer(Questions question, int questionNumber) {
        char[] validAnswers = {'a', 'b', 'c', 'd'};// the valid answers the system can take
//        char userChoice;
        // first, we check if  the user can get lifelines, this is to provide the proper output
        if (!isEligibleForLifeline(questionNumber)) {
            System.out.println("\nChoose an answer: ");
        } else {
            System.out.println("\nChoose an answer or enter 0 for lifelines: ");
        }

        while (true) {
            String input = scanner.next().trim().toLowerCase();

            if (input.equals("0") && isEligibleForLifeline(questionNumber)) {
                useLifeline(question);//go to lifelines
            } else if (input.length() == 1 && validAnswers(input.charAt(0), validAnswers)) {
                return input.charAt(0);
            } else {
                System.out.println(color.RED_TEXT + "Invalid choice! Please enter a, b, c, or d." + color.RESET);
            }
        }
    }

    //confir thisis the final user's answer
    private char confirmAnswer(char userChoice, int questionNumber, Questions question) {
        int confirm;
        do {
            System.out.println(color.YELLOW_TEXT + "Final answer? (1: Yes, 2: No)" + color.RESET);

            while (!scanner.hasNextInt()) {
                System.out.println(color.RED_TEXT + "Invalid input! Enter 1 for Yes or 2 for No." + color.RESET);
                scanner.next();
            }

            confirm = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (confirm == 2) {
                System.out.println("Re-enter your answer (a, b, c, d" + (isEligibleForLifeline(questionNumber) ? ", or 0 for lifelines" : "") + "):");
                userChoice = getReenteredAnswer(question, questionNumber);
            } else if (confirm != 1) {
                System.out.println(color.RED_TEXT + "Invalid choice! Enter 1 for Yes or 2 for No." + color.RESET);
            }
        } while (confirm != 1);

        return userChoice;
    }


    private char getReenteredAnswer(Questions question, int questionNumber) {
        char[] validAnswers = {'a', 'b', 'c', 'd'};

        while (true) {
            String input = scanner.next().trim().toLowerCase();
            //check the eligibility for lifelines during the reentering the answer
            if (input.equals("0") && isEligibleForLifeline(questionNumber)) {
                useLifeline(question);
            } else if (input.length() == 1 && validAnswers(input.charAt(0), validAnswers)) {
                return input.charAt(0);
            } else {
                System.out.println(color.RED_TEXT + "Invalid choice! Please enter a, b, c, or d" + (isEligibleForLifeline(questionNumber) ? ", or 0 for lifelines" : "") + "." + color.RESET);
            }
        }
    }


    private boolean checkAnswer(char userChoice, Questions question, int questionNumber) {
        if (userChoice == question.correctAnswer) {
            prizeMoney = getPrizeMoney(questionNumber);
            System.out.println(color.GREEN_TEXT + "Correct! You now have $" + prizeMoney + color.RESET);
            return true;
        } else {
            System.out.println(color.RED_TEXT + "Incorrect! The correct answer was: " + question.getCorrectAnswer() + color.RESET);
            return false;
        }
    }

    private boolean isEligibleForLifeline(int questionNumber) {
        return !(this instanceof HardMode && questionNumber <= 5);
    }


    public abstract int getPrizeMoney(int questionNumber);


    public void useLifeline(Questions question) {
        if (lifelinesUsed[0] && lifelinesUsed[1] && lifelinesUsed[2]) //to check if the user consumed all the lifelines
            System.out.println(color.RED_TEXT + "No available lifelines" + color.RESET);
        else {
            System.out.println("Available Lifelines:");
            if (!lifelinesUsed[0]) System.out.println("1. 50/50");
            if (!lifelinesUsed[1]) System.out.println("2. Ask the Audience");
            if (!lifelinesUsed[2]) System.out.println("3. Phone a Friend");

            int lifelineChoice = scanner.nextInt();
            switch (lifelineChoice) {
                case 1:
                    lifelinesUsed[0] = true;
                    System.out.println("50/50 Eliminates two wrong answers.");
                    for (String s : lifeline.eliminateAnswers(question))
                        System.out.println(s.toString());
                    break;
                case 2:
                    lifelinesUsed[1] = true;
                    System.out.println(color.YELLOW_TEXT + lifeline.audiencHelp(question) + color.RESET);
                    ;
                    break;
                case 3:
                    lifelinesUsed[2] = true;
                    System.out.println(color.YELLOW_TEXT + lifeline.friendHelp(question) + color.RESET);
                    ;
                    break;
                default:
                    System.out.println(color.RED_TEXT + "Invalid choice." + color.RESET);
            }
        }
    }

    //if the user's input is one of the options, then its valid
    public boolean validAnswers(char choice, char[] validChoices) {
        for (char valid : validChoices) {
            if (choice == valid) return true;
        }
        return false;
    }

}
