import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private final int quizId;
    private final String quizName;
    private final List<Question> questions;

    public Quiz(int quizId, String quizName) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.questions = new ArrayList<>();
    }

    public int getQuizId() {
        return quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}