package newFiles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answerList;

    public Answers() {
        this.answerList = new ArrayList<>();
    }

    public void addAnswer(int id, int questionId, String text, String userId) {
        if (text == null || text.trim().isEmpty()) {
            System.out.println("Error: Answer cannot be empty.");
            return;
        }
        answerList.add(new Answer(id, questionId, text, userId));
    }

    public List<Answer> getAllAnswersByUser(String userId) {
        return answerList.stream()
                .filter(a -> a.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Answer> getAnswersForQuestion(int questionId, String userId) {
        return answerList.stream()
                .filter(a -> a.getQuestionId() == questionId && a.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public void deleteAnswer(int id, String userId) {
        answerList.removeIf(a -> a.getId() == id && a.getUserId().equals(userId));
    }

    public void updateAnswer(int id, String newText, String userId) {
        for (Answer a : answerList) {
            if (a.getId() == id && a.getUserId().equals(userId)) {
                if (newText == null || newText.trim().isEmpty()) {
                    System.out.println("Error: Answer cannot be empty.");
                    return;
                }
                a.setText(newText);
                return;
            }
        }
    }
}
