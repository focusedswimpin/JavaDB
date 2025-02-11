package automatedJUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import newFiles.Questions;
import newFiles.Question;

public class QuestionsTest {
    private Questions questions;
    private final String user1 = "UserA";
    private final String user2 = "UserB";

    @BeforeEach
    public void setup() {
        questions = new Questions();
    }

    @Test
    public void testAddQuestion() {
        questions.addQuestion(1, "What is Java?", user1);
        assertEquals(1, questions.getAllQuestionsByUser(user1).size(), " Question should be added for UserA.");
    }

    @Test
    public void testRetrieveQuestionById() {
        questions.addQuestion(1, "What is Java?", user1);
        Question q = questions.getQuestionById(1, user1);
        assertNotNull(q, " Question should exist.");
        assertEquals("What is Java?", q.getText(), " Question text should match.");
    }

    @Test
    public void testUpdateQuestion() {
        questions.addQuestion(1, "What is Java?", user1);
        questions.updateQuestion(1, "What is Python?", user1);
        Question q = questions.getQuestionById(1, user1);
        assertNotNull(q, " Question should exist.");
        assertEquals("What is Python?", q.getText(), " Question text should be updated.");
    }

    @Test
    public void testDeleteQuestion() {
        questions.addQuestion(1, "What is Java?", user1);
        questions.deleteQuestion(1, user1);
        assertNull(questions.getQuestionById(1, user1), " Question should be deleted.");
    }

    @Test
    public void testEmptyQuestionInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            questions.addQuestion(2, "", user1);
        });
        assertEquals("Question text cannot be empty.", exception.getMessage(), " Should throw exception for empty question.");
    }

    @Test
    public void testUserCannotAccessAnotherUsersQuestion() {
        questions.addQuestion(1, "What is Java?", user1);
        Question q = questions.getQuestionById(1, user2);
        assertNull(q, "❌ UserB should not be able to retrieve UserA's question.");
    }

    @Test
    public void testUserCannotEditAnotherUsersQuestion() {
        questions.addQuestion(1, "What is Java?", user1);
        questions.updateQuestion(1, "Modified by another user", user2);
        Question q = questions.getQuestionById(1, user1);
        assertNotEquals("Modified by another user", q.getText(), "❌ UserB should not be able to edit UserA's question.");
    }
}
