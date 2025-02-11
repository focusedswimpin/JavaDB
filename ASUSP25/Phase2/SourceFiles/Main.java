package newFiles;
import java.util.Scanner;

public class Main {
    private static Questions questions = new Questions();
    private static Answers answers = new Answers();
    private static Scanner scanner = new Scanner(System.in);
    private static int questionCounter = 1;
    private static int answerCounter = 1;
    private static String currentUser; // Track the logged-in user

    public static void main(String[] args) {
        loginUser(); // Prompt user login at the start

        while (true) {
            System.out.println("\n=== HW2 Console Application (" + currentUser + ") ===");
            System.out.println("1. Add Question");
            System.out.println("2. View Your Questions");
            System.out.println("3. Update a Question");
            System.out.println("4. Delete a Question");
            System.out.println("5. Add Answer to Your Question");
            System.out.println("6. View Answers to Your Questions");
            System.out.println("7. Delete an Answer");
            System.out.println("8. Update an Answer");
            System.out.println("9. Switch User");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addQuestion();
                    break;
                case 2:
                    viewAllQuestions();
                    break;
                case 3:
                    updateQuestion();
                    break;
                case 4:
                    deleteQuestion();
                    break;
                case 5:
                    addAnswer();
                    break;
                case 6:
                    viewAnswersForQuestion();
                    break;
                case 7:
                    deleteAnswer();
                    break;
                case 8:
                    updateAnswer();
                    break;
                case 9:
                    switchUser();
                    break;
                case 10:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void loginUser() {
        System.out.print("\nEnter your username: ");
        currentUser = scanner.nextLine().trim();
        if (currentUser.isEmpty()) {
            System.out.println("Username cannot be empty. Please try again.");
            loginUser(); // Recursive call if the username is empty
        }
    }

    private static void switchUser() {
        System.out.print("\nSwitching user... Enter new username: ");
        currentUser = scanner.nextLine().trim();
        if (currentUser.isEmpty()) {
            System.out.println("Username cannot be empty. Please try again.");
            switchUser(); // Recursive call if the username is empty
        } else {
            System.out.println("✅ User switched successfully to " + currentUser);
        }
    }

    private static void addQuestion() {
        System.out.print("Enter question text: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Error: Question cannot be empty.");
            return;
        }
        questions.addQuestion(questionCounter++, text, currentUser);
        System.out.println("✅ Question added successfully.");
    }

    private static void viewAllQuestions() {
        System.out.println("\n--- Your Questions ---");
        for (Question q : questions.getAllQuestionsByUser(currentUser)) {
            System.out.println(q);
        }
    }

    private static void updateQuestion() {
        System.out.print("Enter question ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new question text: ");
        String newText = scanner.nextLine().trim();

        if (newText.isEmpty()) {
            System.out.println("Error: Question cannot be empty.");
            return;
        }

        questions.updateQuestion(id, newText, currentUser);
        System.out.println("✅ Question updated successfully.");
    }

    private static void deleteQuestion() {
        System.out.print("Enter question ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        questions.deleteQuestion(id, currentUser);
        System.out.println("✅ Question deleted successfully.");
    }

    private static void addAnswer() {
        System.out.print("Enter question ID to answer: ");
        int questionId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter answer text: ");
        String text = scanner.nextLine().trim();

        if (text.isEmpty()) {
            System.out.println("Error: Answer cannot be empty.");
            return;
        }

        answers.addAnswer(answerCounter++, questionId, text, currentUser);
        System.out.println("✅ Answer added successfully.");
    }

    private static void viewAnswersForQuestion() {
        System.out.print("Enter question ID to view answers: ");
        int questionId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("\n--- Answers for Question " + questionId + " ---");
        for (Answer a : answers.getAnswersForQuestion(questionId, currentUser)) {
            System.out.println(a);
        }
    }

    private static void deleteAnswer() {
        System.out.print("Enter answer ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        answers.deleteAnswer(id, currentUser);
        System.out.println("✅ Answer deleted successfully.");
    }

    private static void updateAnswer() {
        System.out.print("Enter answer ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new answer text: ");
        String newText = scanner.nextLine().trim();

        if (newText.isEmpty()) {
            System.out.println("Error: Answer cannot be empty.");
            return;
        }

        answers.updateAnswer(id, newText, currentUser);
        System.out.println("✅ Answer updated successfully.");
    }
}
