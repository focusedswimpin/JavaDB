package newFiles;

public class Answer {
    private int id;
    private int questionId;
    private String text;
    private String userId; // Associate with a user

    public Answer(int id, int questionId, String text, String userId) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be empty.");
        }
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.userId = userId;
    }

    public int getId() { return id; }
    public int getQuestionId() { return questionId; }
    public String getText() { return text; }
    public String getUserId() { return userId; }

    public void setText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be empty.");
        }
        this.text = text;
    }

    @Override
    public String toString() {
        return "[User: " + userId + " | Answer ID: " + id + " | Question ID: " + questionId + "] " + text;
    }
}
