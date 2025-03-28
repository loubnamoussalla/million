import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionBank {

    Random random = new Random();
    List<Questions> questions = new ArrayList<>();


    public QuestionBank() {
        this.questions = questions;
        load_questions();
    }
    public void load_questions()
    {
        BufferedReader reader;
        try {

            reader = new BufferedReader(new FileReader("./src/questions.txt"));
            String line ;

            while ((line = reader.readLine()) != null) {
                String questionTxt = line;
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = reader.readLine();
                }
                char correctAnswer = reader.readLine().charAt(0);

                questions.add(new Questions(questionTxt,options,correctAnswer));

            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Questions> getQuestions() {
        return questions;
    }
    public Questions getRandomQuestion() {
        if (questions.isEmpty()) return null;
        return questions.remove(random.nextInt(questions.size()));
    }

}
