public class Questions {
    String questiontxt;
    String[] qOptions;
    char correctAnswer;

    public Questions(String questiontxt, String[] qOptions, char correctAnswer) {
        this.questiontxt = questiontxt;
        this.qOptions = qOptions;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestiontxt() {
        return questiontxt;
    }

    public void setQuestiontxt(String questiontxt) {
        this.questiontxt = questiontxt;
    }

    public String[] getqOptions() {

        return qOptions;
    }
    public void displayQuestion() {
        System.out.println("\n" + questiontxt);
        for (int i = 0; i < qOptions.length; i++) {
            System.out.println((i + 1) + ". " + qOptions[i]);
        }
    }

    public void setqOptions(String[] qOptions) {
        this.qOptions = qOptions;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(char correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
