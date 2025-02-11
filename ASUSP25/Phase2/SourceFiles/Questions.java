package newFiles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Questions {
    private List<Question> questionList;

    public Questions() {
        this.questionList = new ArrayList<>();
    }

    public void addQuestion(int id, String text, String userId) {
        questionList.add(new Question(id, text, userId));
    }

    public Question getQuestionById(int id, String userId) {
        return questionList.stream()
                .filter(q -> q.getId() == id && q.getUserId().equals(userId))
                .findFirst().orElse(null);
    }

    public void updateQuestion(int id, String newText, String userId) {
        Question q = getQuestionById(id, userId);
        if (q != null) q.setText(newText);
    }

    public void deleteQuestion(int id, String userId) {
        questionList.removeIf(q -> q.getId() == id && q.getUserId().equals(userId));
    }

    public List<Question> getAllQuestionsByUser(String userId) {
        return questionList.stream()
                .filter(q -> q.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
