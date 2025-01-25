package userNameRecognizerTestbed;

import java.util.Scanner;

/*******
 * <p> Title: UserNameRecognizerTestbed. </p>
 * 
 * <p> Description: A console terminal demonstration application for testing
 * the updated UserNameRecognizer FSM. </p>
 * 
 * @author Assistant
 * 
 */

public class UserNameRecognizerTestbed {

    public static void main(String[] args) {
        System.out.println("Welcome to the UserName Recognizer Testbed\n");
        System.out.println("Please enter a username or an empty line to stop.");

        Scanner keyboard = new Scanner(System.in);

        while (true) {
            System.out.print("Enter username: ");
            String inputLine = keyboard.nextLine();

            if (inputLine.isEmpty()) {
                System.out.println("\n*** Empty input line detected. Exiting the application. ***");
                break;
            }

            String errMessage = UserNameRecognizer.checkForValidUserName(inputLine);

            if (!errMessage.isEmpty()) {
                System.out.println("\n*** ERROR *** " + errMessage);
                System.out.println("Invalid input: " + inputLine);
                System.out.println("Error at position: " + UserNameRecognizer.userNameRecognizerIndexofError);
            } else {
                System.out.println("\nSuccess! The username is valid.");
            }

            System.out.println("\n--- Test another username or press Enter to exit ---\n");
        }

        keyboard.close();
    }
}
