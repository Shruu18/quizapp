import java.time.LocalDateTime;

public class Result {

    private final int resultId;
    private final int quizId;
    private final int userId;
    private final int score;
    private final LocalDateTime dateTime;

    public Result(int resultId, int quizId, int userId, int score, LocalDateTime dateTime) {
        this.resultId = resultId;
        this.quizId = quizId;
        this.userId = userId;
        this.score = score;
        this.dateTime = dateTime;
    }

    public int getResultId() {
        return resultId;
    }

    public int getQuizId() {
        return quizId;
    }

    public int getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}