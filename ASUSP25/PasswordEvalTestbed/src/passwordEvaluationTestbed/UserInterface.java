package passwordEvaluationTestbed;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface testbed to develop and test UI ideas. Updated to
 * reflect the new FSM requirements for password validation.</p>
 */
public class UserInterface {

    /**********************************************************************************************

    Attributes

    **********************************************************************************************/

    private Label label_ApplicationTitle = new Label("Password Evaluation Testbed");
    private Label label_Password = new Label("Enter the password here");
    private TextField text_Password = new TextField();
    private Label feedbackLabel = new Label();

    private Label label_Requirements = new Label("A valid password must satisfy the following requirements:");
    private Label label_UpperCase = new Label("At least one upper case letter - Not yet satisfied");
    private Label label_LowerCase = new Label("At least one lower case letter - Not yet satisfied");
    private Label label_NumericDigit = new Label("At least one numeric digit - Not yet satisfied");
    private Label label_SpecialChar = new Label("At least one special character - Not yet satisfied");
    private Label label_LongEnough = new Label("At least eight characters - Not yet satisfied");

    /**********************************************************************************************

    Constructors

    **********************************************************************************************/

    /**
     * Initializes the graphical user interface components and layout.
     * 
     * @param theRoot The root pane where UI components will be placed
     */
    public UserInterface(Pane theRoot) {
        setupLabelUI(label_ApplicationTitle, "Arial", 24, PasswordEvaluationGUITestbed.WINDOW_WIDTH,
                Pos.CENTER, 0, 10);

        setupLabelUI(label_Password, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10,
                Pos.BASELINE_LEFT, 10, 50);

        setupTextUI(text_Password, "Arial", 18, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 20,
                Pos.BASELINE_LEFT, 10, 70, true);

        text_Password.textProperty().addListener((observable, oldValue, newValue) -> performEvaluation());

        setupLabelUI(feedbackLabel, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10,
                Pos.BASELINE_LEFT, 10, 110);
        feedbackLabel.setTextFill(Color.RED);

        setupLabelUI(label_Requirements, "Arial", 16, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10,
                Pos.BASELINE_LEFT, 10, 200);
        setupLabelUI(label_UpperCase, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10,
                Pos.BASELINE_LEFT, 30, 230);
        setupLabelUI(label_LowerCase, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10,
                Pos.BASELINE_LEFT, 30, 260);
        setupLabelUI(label_NumericDigit, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10,
                Pos.BASELINE_LEFT, 30, 290);
        setupLabelUI(label_SpecialChar, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10,
                Pos.BASELINE_LEFT, 30, 320);
        setupLabelUI(label_LongEnough, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10,
                Pos.BASELINE_LEFT, 30, 350);

        resetAssessments();

        theRoot.getChildren().addAll(label_ApplicationTitle, label_Password, text_Password, feedbackLabel,
                label_Requirements, label_UpperCase, label_LowerCase, label_NumericDigit,
                label_SpecialChar, label_LongEnough);
    }

    /**********************************************************************************************

    Private Methods

    **********************************************************************************************/

    private void performEvaluation() {
        String password = text_Password.getText();
        String evaluationResult = PasswordEvaluator.evaluatePassword(password);

        if (evaluationResult.isEmpty()) {
            feedbackLabel.setTextFill(Color.GREEN);
            feedbackLabel.setText("Success: Password is valid.");
        } else {
            feedbackLabel.setTextFill(Color.RED);
            feedbackLabel.setText("Error: " + evaluationResult);
        }

        updateFlags();
    }

    private void updateFlags() {
        if (PasswordEvaluator.foundUpperCase) {
            label_UpperCase.setText("At least one upper case letter - Satisfied");
            label_UpperCase.setTextFill(Color.GREEN);
        } else {
            label_UpperCase.setText("At least one upper case letter - Not yet satisfied");
            label_UpperCase.setTextFill(Color.RED);
        }

        if (PasswordEvaluator.foundLowerCase) {
            label_LowerCase.setText("At least one lower case letter - Satisfied");
            label_LowerCase.setTextFill(Color.GREEN);
        } else {
            label_LowerCase.setText("At least one lower case letter - Not yet satisfied");
            label_LowerCase.setTextFill(Color.RED);
        }

        if (PasswordEvaluator.foundNumericDigit) {
            label_NumericDigit.setText("At least one numeric digit - Satisfied");
            label_NumericDigit.setTextFill(Color.GREEN);
        } else {
            label_NumericDigit.setText("At least one numeric digit - Not yet satisfied");
            label_NumericDigit.setTextFill(Color.RED);
        }

        if (PasswordEvaluator.foundSpecialChar) {
            label_SpecialChar.setText("At least one special character - Satisfied");
            label_SpecialChar.setTextFill(Color.GREEN);
        } else {
            label_SpecialChar.setText("At least one special character - Not yet satisfied");
            label_SpecialChar.setTextFill(Color.RED);
        }

        if (PasswordEvaluator.foundLongEnough) {
            label_LongEnough.setText("At least eight characters - Satisfied");
            label_LongEnough.setTextFill(Color.GREEN);
        } else {
            label_LongEnough.setText("At least eight characters - Not yet satisfied");
            label_LongEnough.setTextFill(Color.RED);
        }
    }

    private void resetAssessments() {
        label_UpperCase.setText("At least one upper case letter - Not yet satisfied");
        label_UpperCase.setTextFill(Color.RED);

        label_LowerCase.setText("At least one lower case letter - Not yet satisfied");
        label_LowerCase.setTextFill(Color.RED);

        label_NumericDigit.setText("At least one numeric digit - Not yet satisfied");
        label_NumericDigit.setTextFill(Color.RED);

        label_SpecialChar.setText("At least one special character - Not yet satisfied");
        label_SpecialChar.setTextFill(Color.RED);

        label_LongEnough.setText("At least eight characters - Not yet satisfied");
        label_LongEnough.setTextFill(Color.RED);
    }

    private void setupLabelUI(Label l, String font, double fontSize, double width, Pos alignment, double x, double y) {
        l.setFont(Font.font(font, fontSize));
        l.setMinWidth(width);
        l.setAlignment(alignment);
        l.setLayoutX(x);
        l.setLayoutY(y);
    }

    private void setupTextUI(TextField t, String font, double fontSize, double width, Pos alignment, double x, double y, boolean editable) {
        t.setFont(Font.font(font, fontSize));
        t.setMinWidth(width);
        t.setAlignment(alignment);
        t.setLayoutX(x);
        t.setLayoutY(y);
        t.setEditable(editable);
    }
}
