import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {

            List<User> users = loadUsers();
            Map<Integer, Quiz> quizzes = loadQuizzes();

            if (users.isEmpty()) {
                System.out.println("No users found.");
                return;
            }

            if (quizzes.isEmpty()) {
                System.out.println("No quizzes found.");
                return;
            }

            // ================= USER SELECTION =================
            System.out.println("Select User:");
            for (User u : users) {
                System.out.println(u.getUserId() + ". "
                        + u.getName() + " ("
                        + u.getDepartment() + ")");
            }

            int selectedUserId = getIntInput("Enter User ID: ");

            boolean userExists = users.stream()
                    .anyMatch(u -> u.getUserId() == selectedUserId);

            if (!userExists) {
                System.out.println("Invalid User ID.");
                return;
            }

            // ================= QUIZ SELECTION =================
            System.out.println("\nAvailable Quizzes:");
            for (Quiz q : quizzes.values()) {
                System.out.println(q.getQuizId() + ". "
                        + q.getQuizName());
            }

            int selectedQuizId = getIntInput("Enter Quiz ID: ");
            Quiz selectedQuiz = quizzes.get(selectedQuizId);

            if (selectedQuiz == null) {
                System.out.println("Invalid Quiz ID.");
                return;
            }

            // ================= START QUIZ =================
            int score = 0;

            for (Question ques : selectedQuiz.getQuestions()) {

                System.out.println("\n" + ques.getQuestionText());

                String[] options = ques.getOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }

                int answer = getIntInput("Your Answer: ");

                if (answer == ques.getCorrectOption()) {
                    score++;
                }
            }

            System.out.println("\nFinal Score: "
                    + score + "/"
                    + selectedQuiz.getQuestions().size());

            // ================= SAVE RESULT =================
            saveResult(selectedQuizId, selectedUserId, score);

        } catch (Exception e) {
            System.out.println("Something went wrong: "
                    + e.getMessage());
        }
    }

    // ================= SAFE INTEGER INPUT =================
    static int getIntInput(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Try again.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // ================= LOAD USERS =================
    static List<User> loadUsers() throws Exception {

        List<User> users = new ArrayList<>();

        File file = new File("data/users.txt");
        if (!file.exists()) {
            return users;
        }

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length < 3) continue;

                users.add(new User(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim()
                ));
            }
        }

        return users;
    }

    // ================= LOAD QUIZZES =================
    static Map<Integer, Quiz> loadQuizzes() throws Exception {

        Map<Integer, Quiz> quizMap = new HashMap<>();

        File file = new File("data/quizzes.txt");
        if (!file.exists()) {
            return quizMap;
        }

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length < 6) continue;

                int quizId = Integer.parseInt(parts[0].trim());
                String quizName = parts[1].trim();
                int questionId = Integer.parseInt(parts[2].trim());
                String questionText = parts[3].trim();
                String[] options = parts[4].split("\\|");
                int correctOption =
                        Integer.parseInt(parts[5].trim());

                Quiz quiz = quizMap.getOrDefault(
                        quizId, new Quiz(quizId, quizName)
                );

                quiz.addQuestion(new Question(
                        questionId,
                        quizId,
                        questionText,
                        options,
                        correctOption
                ));

                quizMap.put(quizId, quiz);
            }
        }

        return quizMap;
    }

    // ================= SAVE RESULT =================
    static void saveResult(int quizId,
                           int userId,
                           int score) throws Exception {

        File file = new File("data/results.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        int lastId = 0;

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length > 0) {
                    lastId = Integer.parseInt(parts[0].trim());
                }
            }
        }

        int newId = lastId + 1;

        try (FileWriter fw =
                     new FileWriter(file, true)) {

            fw.write(newId + ","
                    + quizId + ","
                    + userId + ","
                    + score + ","
                    + LocalDateTime.now()
                    + "\n");
        }

        System.out.println("Result saved successfully!");
    }
}