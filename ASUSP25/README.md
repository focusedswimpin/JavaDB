# ASUHelloWorldJavaFX Application

## Overview
This repository contains the **ASUHelloWorldJavaFX Application**, a JavaFX-based project demonstrating the basics of JavaFX development. It includes username and password validation features implemented using Finite State Machines (FSMs) to handle input validation, providing user-friendly error messages for invalid inputs.

## Features
- **Username Validation:**
  - Usernames must start with an alphabetic character.
  - Allows alphanumeric characters, underscores (`_`), hyphens (`-`), and periods (`.`).
  - Special characters cannot appear consecutively or at the end.
  - Provides detailed error messages for invalid usernames.

- **Password Validation:**
  - Must be at least 8 characters long.
  - Requires at least one uppercase letter, one lowercase letter, one numeric digit, and one special character.
  - Rejects invalid characters with appropriate error feedback.

- **JavaFX GUI:**
  - User-friendly interface for account setup.
  - Includes fields for entering a username, password, and invitation code.

## Prerequisites
Ensure the following are installed on your system:
- **JDK 17 or later**
- **JavaFX SDK 23.0.2**
- **Eclipse IDE 2024-12** (or any IDE that supports JavaFX)

## Installation
### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/ASUHelloWorldJavaFX.git
cd ASUHelloWorldJavaFX
```

### 2. Set Up JavaFX SDK
- Download the JavaFX SDK from [GluonHQ](https://gluonhq.com/products/javafx/).
- Extract the SDK and note the path to the `lib` directory (e.g., `C:\Users\tejaa\javafx-sdk-23.0.2\lib`).

### 3. Configure Environment Variables
- Add the JavaFX `lib` path to your system's `Path` variable.
- Set the `JAVA_HOME` variable to point to your JDK installation.

### 4. Import the Project into Eclipse
1. Open Eclipse.
2. Go to **File > Import > Existing Projects into Workspace**.
3. Browse to the cloned repository directory and click **Finish**.

### 5. Update Run Configurations
1. Right-click the project and select **Run As > Run Configurations**.
2. In the **Arguments** tab, add the following to the **VM Arguments** field:
   ```plaintext
   --module-path "C:\Users\tejaa\javafx-sdk-23.0.2\lib" --add-modules javafx.controls,javafx.fxml
   ```
3. Click **Apply** and **Run**.

## Usage
### Running the Application
1. Launch the application from Eclipse.
2. The GUI includes fields for entering:
   - **Username**: Validates based on FSM logic.
   - **Password**: Validates based on FSM logic.
   - **Invitation Code**: Ensures the code is valid (dummy validation in this version).
3. Follow the error messages and instructions provided by the application for invalid inputs.

### Testing the Application
- **Test Username Validation:**
  - Enter invalid usernames to trigger FSM transitions and error messages (e.g., `123user`, `user..name`, `user_`).
  - Enter valid usernames to test success scenarios (e.g., `userName123`, `valid_user-name`).

- **Test Password Validation:**
  - Enter invalid passwords to trigger error messages (e.g., `password`, `Pass123`, `P@ss`).
  - Enter valid passwords to test success scenarios (e.g., `P@ssword123`).

## Project Structure
```plaintext
ASUHelloWorldJavaFX
├── src
│   ├── application
│   │   ├── ASUHelloWorldJavaFX.java       # Main class
│   │   ├── UserNameRecognizer.java       # FSM-based username validation
│   │   ├── PasswordEvaluator.java        # FSM-based password validation
│   │   ├── SetupAccountPage.java         # Account setup GUI
│   │   ├── DatabaseHelper.java           # Database operations (dummy implementation)
│   │   ├── WelcomeLoginPage.java         # Login welcome page
│   │   └── ... (other supporting files)
├── lib
│   └── javafx-sdk-23.0.2                 # JavaFX SDK (not included in repo)
├── README.md
└── ...
```

## FSM Details
### Username FSM
- **State 0**: Start state, expects an alphabetic character.
- **State 1**: Accepts alphanumeric characters.
- **State 2**: Accepts special characters (`_`, `-`, `.`), followed by alphanumeric characters.
- **Reject State**: Handles invalid inputs.

### Password FSM
- Validates passwords based on length, case sensitivity, numeric, and special characters.
- Rejects passwords not meeting the criteria.

## Known Issues
- **Invitation Code Validation:** Currently a placeholder; implement actual validation logic as needed.
- **Database Integration:** Dummy implementation provided for demonstration purposes.

## Contributing
Feel free to contribute to this project by submitting pull requests or reporting issues.

## License
This project is licensed under the MIT License. See `LICENSE` for details.

## Acknowledgments
- JavaFX Documentation: [https://openjfx.io/](https://openjfx.io/)
- ASU CSE360 Course Materials
