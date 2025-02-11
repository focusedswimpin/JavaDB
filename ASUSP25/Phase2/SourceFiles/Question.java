package newFiles;

public class Question {
    private int id;
    private String text;
    private String userId; // Associate with a user

    public Question(int id, String text, String userId) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty.");
        }
        this.id = id;
        this.text = text;
        this.userId = userId;
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public String getUserId() { return userId; }

    public void setText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty.");
        }
        this.text = text;
    }

    @Override
    public String toString() {
        return "[User: " + userId + " | ID: " + id + "] " + text;
    }
}
