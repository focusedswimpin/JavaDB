package passwordEvaluationTestbed;

/**
 * <p> Title: FSM-translated Password Evaluator </p>
 *
 * <p> Description: A demonstration of the mechanical translation of a Directed Graph
 * diagram into an executable Java program using the Password Evaluator FSM design.
 * The program ensures robust password validation based on several security criteria,
 * such as uppercase, lowercase, numeric, special characters, and minimum length. </p>
 *
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 *
 * @author Lynn Robert Carter
 *
 * @version 1.00 - January 24, 2025
 */

public class PasswordEvaluator {

    /**
     * Attributes for result tracking.
     */
    public static String passwordErrorMessage = "";  // The error message for invalid input
    public static boolean foundUpperCase = false;    // True if at least one uppercase letter is found
    public static boolean foundLowerCase = false;    // True if at least one lowercase letter is found
    public static boolean foundNumericDigit = false; // True if at least one numeric digit is found
    public static boolean foundSpecialChar = false;  // True if at least one special character is found
    public static boolean foundLongEnough = false;   // True if the password is at least 8 characters long

    /**
     * This method validates a password based on the FSM rules.
     *
     * FSM Details:
     * - Accepts passwords that meet the following criteria:
     *   - Contains at least one uppercase letter.
     *   - Contains at least one lowercase letter.
     *   - Contains at least one numeric digit.
     *   - Contains at least one special character from the specified set.
     *   - Is at least 8 characters long.
     * - Rejects passwords with invalid characters.
     * - Returns meaningful error messages for each failed condition.
     *
     * @param input The password to validate
     * @return An error message if invalid, or an empty string if valid
     */
    public static String evaluatePassword(String input) {
        // Reset validation flags and error message
        passwordErrorMessage = "";
        foundUpperCase = false;
        foundLowerCase = false;
        foundNumericDigit = false;
        foundSpecialChar = false;
        foundLongEnough = input.length() >= 8; // Check minimum length upfront

        // Handle empty input
        if (input.isEmpty()) {
            return "*** ERROR *** The password is empty.";
        }

        // Process each character in the input
        for (char currentChar : input.toCharArray()) {
            // Check for uppercase letters
            if (Character.isUpperCase(currentChar)) {
                foundUpperCase = true;
            }
            // Check for lowercase letters
            else if (Character.isLowerCase(currentChar)) {
                foundLowerCase = true;
            }
            // Check for numeric digits
            else if (Character.isDigit(currentChar)) {
                foundNumericDigit = true;
            }
            // Check for special characters
            else if ("~`!@#$%^&*()_-+={}[]|\\:;\"'<>,.?/".indexOf(currentChar) >= 0) {
                foundSpecialChar = true;
            }
            // Reject invalid characters
            else {
                return "*** ERROR *** Invalid character '" + currentChar + "' found in password.";
            }
        }

        // Construct error message based on missing conditions
        if (!foundUpperCase) {
            passwordErrorMessage += "Password must contain at least one uppercase letter. ";
        }
        if (!foundLowerCase) {
            passwordErrorMessage += "Password must contain at least one lowercase letter. ";
        }
        if (!foundNumericDigit) {
            passwordErrorMessage += "Password must contain at least one numeric digit. ";
        }
        if (!foundSpecialChar) {
            passwordErrorMessage += "Password must contain at least one special character. ";
        }
        if (!foundLongEnough) {
            passwordErrorMessage += "Password must be at least 8 characters long. ";
        }

        // Return the final error message or indicate success
        return passwordErrorMessage.isEmpty() ? "" : passwordErrorMessage;
    }

    /**
     * This helper method prints the FSM state transitions and current character processing.
     * It is useful for debugging and understanding how the FSM processes the input.
     *
     * @param input The password being validated
     */
    public static void debugFSM(String input) {
        System.out.println("Debugging FSM for password: " + input);
        System.out.println("Initial password length: " + input.length());
        passwordErrorMessage = evaluatePassword(input);
        System.out.println("Final validation result: " + (passwordErrorMessage.isEmpty() ? "Valid password" : passwordErrorMessage));
        System.out.println("Flags: Uppercase=" + foundUpperCase + ", Lowercase=" + foundLowerCase + ", Digit=" + foundNumericDigit + ", SpecialChar=" + foundSpecialChar + ", LongEnough=" + foundLongEnough);
    }

    /**
     * Main method to test the PasswordEvaluator functionality.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Example test cases
        String[] testPasswords = {
            "",                  // Empty password
            "short",             // Too short
            "NoSpecial1",        // Missing special character
            "noupper1!",         // Missing uppercase letter
            "NOLOWER1!",         // Missing lowercase letter
            "Valid1!",           // Valid password
            "Invalid@Char#\u2603" // Invalid character (Unicode snowman)
        };

        for (String password : testPasswords) {
            System.out.println("Testing password: " + password);
            debugFSM(password);
            System.out.println("========================================");
        }
    }
}
