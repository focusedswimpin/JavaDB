package newFiles;
import java.util.ArrayList;
import java.util.List;

public class Questions {
    private List<Question> questionList;

    public Questions() {
        this.questionList = new ArrayList<>();
    }

    public void addQuestion(int id, String text) {
        questionList.add(new Question(id, text));
    }

    public Question getQuestionById(int id) {
        return questionList.stream().filter(q -> q.getId() == id).findFirst().orElse(null);
    }

    public void updateQuestion(int id, String newText) {
        Question q = getQuestionById(id);
        if (q != null) q.setText(newText);
    }

    public void deleteQuestion(int id) {
        questionList.removeIf(q -> q.getId() == id);
    }

    public List<Question> getAllQuestions() {
        return questionList;
    }
}
