package passwordEvaluationTestbed;

/*******
 * <p> Title: PasswordEvaluationTestingAutomation Class. </p>
 * 
 * <p> Description: Enhanced testing automation for PasswordEvaluator with updated FSM logic. </p>
 * 
 */
public class PasswordEvaluationTestingAutomation {

    static int numPassed = 0; // Counter for passed tests
    static int numFailed = 0; // Counter for failed tests

    public static void main(String[] args) {
        /************** Test cases semi-automation report header **************/
        System.out.println("______________________________________");
        System.out.println("\nTesting Automation\n");

        /************** Start of the test cases **************/

        // Valid password tests
        performTestCase(1, "Aa1@bcde", true);
        performTestCase(2, "Zz9#Yt56", true);

        // Invalid password tests
        performTestCase(3, "short1A@", false); // Less than 8 characters
        performTestCase(4, "alllowercase1@", false); // No uppercase letter
        performTestCase(5, "ALLUPPERCASE1@", false); // No lowercase letter
        performTestCase(6, "NoSpecialChar1", false); // Missing special character
        performTestCase(7, "NoDigits!@#$", false); // Missing numeric digit

        // Edge cases
        performTestCase(8, "", false); // Empty string
        performTestCase(9, "ValidPass#1234", true); // Long valid password
        performTestCase(10, "InvalidChar^", false); // Invalid character

        /************** End of the test cases **************/

        /************** Test cases semi-automation report footer **************/
        System.out.println("____________________________________________________________________________");
        System.out.println("\nNumber of tests passed: " + numPassed);
        System.out.println("Number of tests failed: " + numFailed);
    }

    /**
     * Executes a single test case and evaluates the results.
     * 
     * @param testCase     The test case number
     * @param inputText    The input password to test
     * @param expectedPass True if the password is expected to pass validation, false otherwise
     */
    private static void performTestCase(int testCase, String inputText, boolean expectedPass) {
        /************** Display an individual test case header **************/
        System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
        System.out.println("Input: \"" + inputText + "\"");
        System.out.println("______________\n");

        /************** Call the recognizer to process the input **************/
        String resultText = PasswordEvaluator.evaluatePassword(inputText);

        /************** Interpret the result and display that interpreted information **************/
        if (!resultText.isEmpty()) {
            if (expectedPass) {
                System.out.println("***Failure*** The password <" + inputText + "> is invalid.\nBut it was supposed to be valid, so this is a failure!\n");
                System.out.println("Error message: " + resultText);
                numFailed++;
            } else {
                System.out.println("***Success*** The password <" + inputText + "> is invalid.\nAnd it was supposed to be invalid, so this is a pass!\n");
                System.out.println("Error message: " + resultText);
                numPassed++;
            }
        } else {
            if (expectedPass) {
                System.out.println("***Success*** The password <" + inputText + "> is valid, so this is a pass!");
                numPassed++;
            } else {
                System.out.println("***Failure*** The password <" + inputText + "> was judged as valid,\nbut it was supposed to be invalid, so this is a failure!");
                numFailed++;
            }
        }

        displayEvaluation();
    }

    /**
     * Displays the evaluation flags for the most recent password test.
     */
    private static void displayEvaluation() {
        if (PasswordEvaluator.foundUpperCase)
            System.out.println("At least one uppercase letter - Satisfied");
        else
            System.out.println("At least one uppercase letter - Not Satisfied");

        if (PasswordEvaluator.foundLowerCase)
            System.out.println("At least one lowercase letter - Satisfied");
        else
            System.out.println("At least one lowercase letter - Not Satisfied");

        if (PasswordEvaluator.foundNumericDigit)
            System.out.println("At least one numeric digit - Satisfied");
        else
            System.out.println("At least one numeric digit - Not Satisfied");

        if (PasswordEvaluator.foundSpecialChar)
            System.out.println("At least one special character - Satisfied");
        else
            System.out.println("At least one special character - Not Satisfied");

        if (PasswordEvaluator.foundLongEnough)
            System.out.println("At least 8 characters - Satisfied");
        else
            System.out.println("At least 8 characters - Not Satisfied");
    }
}
