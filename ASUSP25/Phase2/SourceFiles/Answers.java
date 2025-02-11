package newFiles;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answerList;

    public Answers() {
        this.answerList = new ArrayList<>();
    }

    public void addAnswer(int id, int questionId, String text) {
        if (text == null || text.trim().isEmpty()) {
            System.out.println("Error: Answer cannot be empty.");
            return;
        }
        answerList.add(new Answer(id, questionId, text));
    }

    public List<Answer> getAllAnswers() {
        return answerList;
    }

    public List<Answer> getAnswersForQuestion(int questionId) {
        List<Answer> results = new ArrayList<>();
        for (Answer a : answerList) {
            if (a.getQuestionId() == questionId) results.add(a);
        }
        return results;
    }

    public void deleteAnswer(int id) {
        answerList.removeIf(a -> a.getId() == id);
    }

    public void updateAnswer(int id, String newText) {
        for (Answer a : answerList) {
            if (a.getId() == id) {
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
