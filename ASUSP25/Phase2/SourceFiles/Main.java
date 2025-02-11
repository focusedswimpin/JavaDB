package newFiles;
import java.util.Scanner;

public class Main {
    private static Questions questions = new Questions();
    private static Answers answers = new Answers();
    private static Scanner scanner = new Scanner(System.in);
    private static int questionCounter = 1;
    private static int answerCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== HW2 Console Application ===");
            System.out.println("1. Add Question");
            System.out.println("2. View All Questions");
            System.out.println("3. Update a Question");
            System.out.println("4. Delete a Question");
            System.out.println("5. Add Answer to a Question");
            System.out.println("6. View Answers for a Question");
            System.out.println("7. Delete an Answer");
            System.out.println("8. Update an Answer");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {  // Prevent invalid input (non-integer)
                System.out.println("Invalid choice. Please enter a number.");
                scanner.next();  // Consume invalid input
                continue;
            }

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
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void addQuestion() {
        System.out.print("Enter question text: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Error: Question cannot be empty.");
            return;
        }
        questions.addQuestion(questionCounter++, text);
        System.out.println("Question added successfully.");
    }

    private static void addAnswer() {
        System.out.print("Enter question ID to answer: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid question ID. Please enter a valid number.");
            scanner.next();  // Consume invalid input
            return;
        }
        int qId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter answer text: ");
        String text = scanner.nextLine().trim();

        // Validate if the answer is empty
        if (text.isEmpty()) {
            System.out.println("Error: Answer cannot be empty. Please enter a valid answer.");
            return;
        }

        answers.addAnswer(answerCounter++, qId, text);
        System.out.println("Answer added successfully.");
    }

    private static void viewAllQuestions() {
        if (questions.getAllQuestions().isEmpty()) {
            System.out.println("No questions available.");
            return;
        }
        System.out.println("\n--- List of Questions ---");
        for (Question q : questions.getAllQuestions()) {
            System.out.println(q);
        }
    }

    private static void viewAnswersForQuestion() {
        System.out.print("Enter question ID to view answers: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid question ID. Please enter a valid number.");
            scanner.next();
            return;
        }
        int qId = scanner.nextInt();
        scanner.nextLine();

        if (answers.getAnswersForQuestion(qId).isEmpty()) {
            System.out.println("No answers found for this question.");
            return;
        }
        for (Answer a : answers.getAnswersForQuestion(qId)) {
            System.out.println(a);
        }
    }

    private static void deleteAnswer() {
        System.out.print("Enter answer ID to delete: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid answer ID. Please enter a valid number.");
            scanner.next();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        answers.deleteAnswer(id);
        System.out.println("Answer deleted successfully.");
    }

    private static void updateAnswer() {
        System.out.print("Enter answer ID to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid answer ID. Please enter a valid number.");
            scanner.next();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new answer text: ");
        String text = scanner.nextLine().trim();

        // Validate if the answer is empty
        if (text.isEmpty()) {
            System.out.println("Error: Answer cannot be empty. Please enter a valid answer.");
            return;
        }

        answers.updateAnswer(id, text);
        System.out.println("Answer updated successfully.");
    }

    private static void updateQuestion() {
        System.out.print("Enter question ID to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid question ID. Please enter a valid number.");
            scanner.next();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter new question text: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Error: Question cannot be empty.");
            return;
        }
        questions.updateQuestion(id, text);
        System.out.println("Question updated successfully.");
    }

    private static void deleteQuestion() {
        System.out.print("Enter question ID to delete: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid question ID. Please enter a valid number.");
            scanner.next();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        questions.deleteQuestion(id);
        System.out.println("Question deleted successfully.");
    }
}
