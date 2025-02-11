package automatedJUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import newFiles.Answers;
import newFiles.Answer;

public class AnswersTest {
    private Answers answers;
    private final String user1 = "UserA";
    private final String user2 = "UserB";

    @BeforeEach
    public void setup() {
        answers = new Answers();
    }

    @Test
    public void testAddAnswer() {
        answers.addAnswer(1, 1, "Java is a programming language.", user1);
        assertEquals(1, answers.getAllAnswersByUser(user1).size(), " Answer should be added for UserA.");
    }

    @Test
    public void testRetrieveAnswersForQuestion() {
        answers.addAnswer(1, 1, "Java is object-oriented.", user1);
        answers.addAnswer(2, 1, "Java is platform-independent.", user1);

        List<Answer> answerList = answers.getAnswersForQuestion(1, user1);
        assertEquals(2, answerList.size(), " There should be 2 answers for the question by UserA.");
    }

    @Test
    public void testDeleteAnswer() {
        answers.addAnswer(1, 1, "Java is a programming language.", user1);
        answers.deleteAnswer(1, user1);
        assertEquals(0, answers.getAllAnswersByUser(user1).size(), " Answer should be deleted.");
    }

    @Test
    public void testEmptyAnswerInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            answers.addAnswer(2, 1, "", user1);
        });
        assertEquals("Answer text cannot be empty.", exception.getMessage(), " Should throw exception for empty answer.");
    }

    @Test
    public void testUpdateAnswer() {
        answers.addAnswer(1, 1, "Java is a programming language.", user1);
        answers.updateAnswer(1, "Java is an OOP language.", user1);
        Answer updatedAnswer = answers.getAnswersForQuestion(1, user1).get(0);
        assertEquals("Java is an OOP language.", updatedAnswer.getText(), " Answer should be updated.");
    }

    @Test
    public void testUserCannotEditAnotherUsersAnswer() {
        answers.addAnswer(1, 1, "Java is a programming language.", user1);
        answers.updateAnswer(1, "Modified by another user", user2);
        Answer answer = answers.getAnswersForQuestion(1, user1).get(0);
        assertNotEquals("Modified by another user", answer.getText(), "❌ UserB should not be able to modify UserA's answer.");
    }
}
