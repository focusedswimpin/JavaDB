package automatedJUnit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import newFiles.Answers;
import newFiles.Answer;

public class AnswersTest {
    private Answers answers;

    @BeforeEach
    public void setup() {
        answers = new Answers();
    }

    @Test
    public void testAddAnswer() {
        answers.addAnswer(1, 1, "Java is a programming language.");
        assertEquals(1, answers.getAllAnswers().size(), "✅ Answer should be added.");
    }

    @Test
    public void testRetrieveAnswersForQuestion() {
        answers.addAnswer(1, 1, "Java is object-oriented.");
        answers.addAnswer(2, 1, "Java is platform-independent.");
        
        List<Answer> answerList = answers.getAnswersForQuestion(1);
        assertEquals(2, answerList.size(), "✅ There should be 2 answers for the question.");
    }

    @Test
    public void testDeleteAnswer() {
        answers.addAnswer(1, 1, "Java is a programming language.");
        answers.deleteAnswer(1);
        assertEquals(0, answers.getAllAnswers().size(), "✅ Answer should be deleted.");
    }

    @Test
    public void testEmptyAnswerInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            answers.addAnswer(2, 1, "");
        });
        assertEquals("Answer text cannot be empty.", exception.getMessage(), "✅ Should throw exception for empty answer.");
    }

    @Test
    public void testUpdateAnswer() {
        answers.addAnswer(1, 1, "Java is a programming language.");
        answers.updateAnswer(1, "Java is an OOP language.");
        Answer updatedAnswer = answers.getAnswersForQuestion(1).get(0);
        assertEquals("Java is an OOP language.", updatedAnswer.getText(), "✅ Answer should be updated.");
    }
}
