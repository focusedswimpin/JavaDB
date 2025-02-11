package automatedJUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import newFiles.Questions;
import newFiles.Question;

public class QuestionsTest {
    private Questions questions;

    @BeforeEach
    public void setup() {
        questions = new Questions();
    }

    @Test
    public void testAddQuestion() {
        questions.addQuestion(1, "What is Java?");
        assertEquals(1, questions.getAllQuestions().size(), "✅ Question should be added.");
    }

    @Test
    public void testRetrieveQuestionById() {
        questions.addQuestion(1, "What is Java?");
        Question q = questions.getQuestionById(1);
        assertNotNull(q, "✅ Question should exist.");
        assertEquals("What is Java?", q.getText(), "✅ Question text should match.");
    }

    @Test
    public void testUpdateQuestion() {
        questions.addQuestion(1, "What is Java?");
        questions.updateQuestion(1, "What is Python?");
        Question q = questions.getQuestionById(1);
        assertNotNull(q, "✅ Question should exist.");
        assertEquals("What is Python?", q.getText(), "✅ Question text should be updated.");
    }

    @Test
    public void testDeleteQuestion() {
        questions.addQuestion(1, "What is Java?");
        questions.deleteQuestion(1);
        assertNull(questions.getQuestionById(1), "✅ Question should be deleted.");
    }

    @Test
    public void testEmptyQuestionInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            questions.addQuestion(2, "");
        });
        assertEquals("Question text cannot be empty.", exception.getMessage(), "✅ Should throw exception for empty question.");
    }
}
