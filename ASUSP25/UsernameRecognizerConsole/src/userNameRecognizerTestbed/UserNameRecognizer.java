package userNameRecognizerTestbed;

/**
 * <p> Title: FSM-translated UserNameRecognizer. </p>
 *
 * <p> Description: A demonstration of the mechanical translation of Finite State Machine
 * diagram into an executable Java program using the UserName Recognizer. Updated FSM
 * includes validation for alphabetic start, extended character set, and no trailing special characters. </p>
 *
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 *
 * <p> Author: Lynn Robert Carter </p>
 *
 * <p> Version: 1.00 - January 24, 2025 </p>
 */

public class UserNameRecognizer {

    /**
     * The error message text, which stores feedback for invalid usernames.
     */
    public static String userNameRecognizerErrorMessage = "";

    /**
     * The input being processed by the FSM.
     */
    public static String userNameRecognizerInput = "";

    /**
     * The index of the error location in the input string.
     */
    public static int userNameRecognizerIndexofError = -1;

    /**
     * The current state value in the FSM.
     */
    private static int state = 0;

    /**
     * A flag to determine if the FSM is still running.
     */
    private static boolean running;

    /**
     * The input line being processed character by character.
     */
    private static String inputLine = "";

    /**
     * The current character being processed.
     */
    private static char currentChar;

    /**
     * The index of the current character in the input line.
     */
    private static int currentCharNdx;

    /**
     * Private method to move to the next character in the input string.
     * This is invoked at every valid transition to advance the FSM processing.
     * If the end of the input is reached, the FSM stops running.
     */
    private static void moveToNextCharacter() {
        currentCharNdx++;
        if (currentCharNdx < inputLine.length()) {
            currentChar = inputLine.charAt(currentCharNdx);
        } else {
            currentChar = ' '; // Assign space to signify end of input
            running = false;   // Stop the FSM
        }
    }

    /**
     * This method validates the username based on the updated FSM logic.
     *
     * FSM Details:
     * - Start State (state 0): The first character of the username must be alphabetic (A-Z or a-z).
     * - Valid Continuation (state 1): Accepts alphanumeric characters (A-Z, a-z, 0-9).
     * - Special Character State (state 2): Ensures that a special character is followed by an alphanumeric character.
     * - Reject State: Any invalid input, such as invalid characters or trailing special characters, terminates processing.
     *
     * @param input The input username to validate
     * @return Error message if invalid, or an empty string if valid
     */
    public static String checkForValidUserName(String input) {
        // Handle empty input case
        if (input.isEmpty()) {
            userNameRecognizerIndexofError = 0; // Error occurs at the beginning
            System.out.println("Error: The input is empty. Please provide a valid username.");
            return "\n*** ERROR *** The input is empty.";
        }

        // Initialize FSM state and input parameters
        state = 0; // Start state
        inputLine = input;
        currentCharNdx = 0;
        currentChar = input.charAt(0);
        running = true;

        // FSM Execution Loop
        while (running) {
            switch (state) {
                case 0: // Start State
                    // The first character must be alphabetic (A-Z or a-z)
                    if (Character.isAlphabetic(currentChar)) {
                        state = 1; // Transition to Valid Continuation State
                    } else {
                        running = false; // Invalid input, terminate FSM
                        userNameRecognizerErrorMessage = "Usernames must start with an alphabetic character.";
                        System.out.println("Error: " + userNameRecognizerErrorMessage);
                    }
                    break;

                case 1: // Valid Continuation State
                    // Accept alphanumeric characters
                    if (Character.isAlphabetic(currentChar) || Character.isDigit(currentChar)) {
                        state = 1; // Stay in Valid Continuation State
                    } 
                    // Transition to Special Character State
                    else if (currentChar == '-' || currentChar == '_' || currentChar == '.') {
                        state = 2; 
                    } else {
                        running = false; // Invalid input, terminate FSM
                        userNameRecognizerErrorMessage = "Invalid character found: '" + currentChar + "'.";
                        System.out.println("Error: " + userNameRecognizerErrorMessage);
                    }
                    break;

                case 2: // Special Character State
                    // After a special character, expect an alphanumeric character
                    if (Character.isAlphabetic(currentChar) || Character.isDigit(currentChar)) {
                        state = 1; // Return to Valid Continuation State
                    } else {
                        running = false; // Invalid input, terminate FSM
                        userNameRecognizerErrorMessage = "Special characters must be followed by an alphanumeric character.";
                        System.out.println("Error: " + userNameRecognizerErrorMessage);
                    }
                    break;

                default: // Catch-all for unexpected states
                    running = false;
                    userNameRecognizerErrorMessage = "Unknown error occurred.";
                    System.out.println("Error: " + userNameRecognizerErrorMessage);
                    break;
            }

            // Advance to the next character if FSM is still running
            if (running) moveToNextCharacter();
        }

        // Final Validation after FSM stops running
        if (state == 1 && currentCharNdx == input.length()) {
            // Ensure the last character is not a special character ('-', '_', '.')
            char lastChar = input.charAt(input.length() - 1);
            if (lastChar == '_' || lastChar == '-' || lastChar == '.') {
                userNameRecognizerErrorMessage = "Usernames cannot end with special characters.";
                System.out.println("Error: " + userNameRecognizerErrorMessage);
            } else {
                userNameRecognizerErrorMessage = ""; // Valid username
                System.out.println("Success: The username is valid.");
            }
        } else if (userNameRecognizerErrorMessage.isEmpty()) {
            // Handle cases where the input does not complete a valid state
            userNameRecognizerErrorMessage = "Incomplete or invalid username.";
            System.out.println("Error: " + userNameRecognizerErrorMessage);
        }

        return userNameRecognizerErrorMessage; // Return the final validation result
    }

    /**
     * Additional helper method for debugging FSM behavior.
     * Prints the state transitions and character processing step-by-step.
     * Useful for understanding how the FSM processes the input.
     */
    public static void debugFSM(String input) {
        System.out.println("Debugging FSM for input: " + input);
        checkForValidUserName(input);
        System.out.println("Final State: " + state);
        System.out.println("Error Message: " + userNameRecognizerErrorMessage);
        System.out.println("Index of Error: " + userNameRecognizerIndexofError);
    }
}
