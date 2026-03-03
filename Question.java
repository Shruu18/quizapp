public class Question {

    private final int questionId;
    private final int quizId;
    private final String questionText;
    private final String[] options;
    private final int correctOption;

    public Question(int questionId, int quizId, String questionText, String[] options, int correctOption) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}