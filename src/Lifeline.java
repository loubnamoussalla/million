import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lifeline {
    String questionText;
    Ai ai = new Ai();
    String answer;
    private Random random = new Random();

    public String friendHelp(Questions question) {
        questionText = question.getQuestiontxt();
        for (int i = 0; i < question.getqOptions().length ; i++) {
            questionText +=  question.getqOptions()[i]+ " ";
        }


        answer = ai.aiHelp("Yow will act like the friend a person calls during who wins the million game, provide one friend-like sentence including the answer " +
                "select the correct answer: " + questionText);
        return  "Your firend says: "+answer;

    }

    public String audiencHelp(Questions question) {
        questionText = question.getQuestiontxt();
        for (int i = 0; i < question.getqOptions().length ; i++) {
            questionText +=  question.getqOptions()[i]+ " ";
        }

        answer = ai.aiHelp("Yow will act like the audience lifeline for a person during who want to be a millionare game, (do not provide long answers) dont provide any introductions" +
                "provide percentages for each option : " + questionText);
        return "The audience voted: \n"+ answer;
    }
    public String[] eliminateAnswers(Questions question)
    {
        char correctAnswer = question.getCorrectAnswer();
        String[] options = question.getqOptions();
        List<Integer> wrongAnswers = new ArrayList<>();
        for (int i = 0; i < options.length; i++) {
            if (getOptionLetter(i) != correctAnswer) {
                wrongAnswers.add(i);
            }
        }
        for (int i = 0; i < 2 && !wrongAnswers.isEmpty(); i++) {
            int indexToRemove = wrongAnswers.remove(random.nextInt(wrongAnswers.size()));
            options[indexToRemove] = "❌";
        }
        return options;


    }
    public char getOptionLetter(int index) {
        return (char) ('a' + index);
    }
}

