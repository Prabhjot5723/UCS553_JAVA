import java.util.Scanner;

// ================= CUSTOM EXCEPTIONS =================
class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) { super(message); }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) { super(message); }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) { super(message); }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) { super(message); }
}

class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) { super(message); }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) { super(message); }
}

class AccountLockedException extends Exception {
    public AccountLockedException(String message) { super(message); }
}

// ================= MAIN PROGRAM =================
public class Assignment5 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("===== UCS553 ASSIGNMENT 5 =====");
        System.out.println("Exception Handling and Debugging");

        // 1. Common Exception Hierarchy
        commonExceptions();

        // 2. Nested Try-Catch
        nestedTryCatch();

        // 3. Calculator
        calculator();

        // 4. Student Result Processing
        studentResult();

        // 5. Banking Application
        banking();

        // 6. Login System
        loginSystem();

        sc.close();
        System.out.println("\n===== PROGRAM FINISHED =====");
    }

    // ==================================================
    // EXPERIMENT 1: COMMON EXCEPTIONS
    // ==================================================
    static void commonExceptions() {
        System.out.println("\n--- 1. COMMON EXCEPTIONS ---");

        try {
            int x = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: Cannot divide by zero.");
        }

        try {
            String name = null;
            System.out.println(name.length());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: Object is null.");
        }

        try {
            int[] arr = {10, 20, 30};
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: Invalid index.");
        }

        try {
            int number = Integer.parseInt("ABC");
            System.out.println(number);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: Invalid number format.");
        }
    }

    // ==================================================
    // EXPERIMENT 2: NESTED TRY-CATCH
    // ==================================================
    static void nestedTryCatch() {
        System.out.println("\n--- 2. NESTED TRY-CATCH ---");

        try {
            try {
                int value = 10 / 0;
                System.out.println(value);
            } catch (ArithmeticException e) {
                System.out.println("Inner Catch: Division by zero handled.");
            }

            int[] arr = {1, 2};
            System.out.println(arr[5]);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Outer Catch: Array index error handled.");
        }
    }

    // ==================================================
    // EXPERIMENT 3: CALCULATOR
    // ==================================================
    static void calculator() {
        System.out.println("\n--- 3. CALCULATOR ---");

        try {
            System.out.print("Enter first number: ");
            double a = Double.parseDouble(sc.next());

            System.out.print("Enter operator (+ - * /): ");
            String op = sc.next();

            System.out.print("Enter second number: ");
            double b = Double.parseDouble(sc.next());

            double result;

            switch (op) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0)
                        throw new ArithmeticException("Division by zero is not allowed.");
                    result = a / b;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator.");
            }

            System.out.println("Result = " + result);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input.");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid operator.");
        }
    }

    // ==================================================
    // EXPERIMENT 4: STUDENT RESULT
    // ==================================================
    static void studentResult() {
        System.out.println("\n--- 4. STUDENT RESULT PROCESSING ---");

        try {
            System.out.print("Enter marks in Subject 1: ");
            int m1 = sc.nextInt();

            System.out.print("Enter marks in Subject 2: ");
            int m2 = sc.nextInt();

            System.out.print("Enter marks in Subject 3: ");
            int m3 = sc.nextInt();

            checkMarks(m1);
            checkMarks(m2);
            checkMarks(m3);

            int total = m1 + m2 + m3;
            double percentage = total / 3.0;

            String grade;

            if (percentage >= 90)
                grade = "A";
            else if (percentage >= 75)
                grade = "B";
            else if (percentage >= 60)
                grade = "C";
            else if (percentage >= 40)
                grade = "D";
            else
                grade = "F";

            System.out.println("Total = " + total);
            System.out.println("Percentage = " + percentage + "%");
            System.out.println("Grade = " + grade);

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Please enter valid integer marks.");
        }
    }

    static void checkMarks(int marks) throws InvalidMarksException {
        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException(
                    "Marks must be between 0 and 100."
            );
        }
    }

    // ==================================================
    // EXPERIMENT 5: BANKING APPLICATION
    // ==================================================
    static void banking() {
        System.out.println("\n--- 5. BANKING APPLICATION ---");

        double balance = 1000;

        try {
            System.out.print("Enter account number: ");
            int accountNumber = sc.nextInt();

            validateAccount(accountNumber);

            System.out.println("Current Balance = " + balance);

            System.out.print("Enter deposit amount: ");
            double deposit = sc.nextDouble();

            balance = depositMoney(balance, deposit);
            System.out.println("Balance after deposit = " + balance);

            System.out.print("Enter withdrawal amount: ");
            double withdrawal = sc.nextDouble();

            balance = withdrawMoney(balance, withdrawal);
            System.out.println("Balance after withdrawal = " + balance);

        } catch (AccountNotFoundException |
                 InvalidAmountException |
                 InsufficientBalanceException e) {

            System.out.println("Banking Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        }
    }

    static void validateAccount(int accountNumber)
            throws AccountNotFoundException {

        if (accountNumber != 12345) {
            throw new AccountNotFoundException(
                    "Account number not found."
            );
        }
    }

    static double depositMoney(double balance, double amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero."
            );
        }

        return balance + amount;
    }

    static double withdrawMoney(double balance, double amount)
            throws InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance."
            );
        }

        return balance - amount;
    }

    // ==================================================
    // EXPERIMENT 6: LOGIN SYSTEM
    // ==================================================
    static void loginSystem() {
        System.out.println("\n--- 6. LOGIN SYSTEM ---");

        String correctUsername = "admin";
        String correctPassword = "1234";
        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {
            try {
                System.out.print("Enter username: ");
                String username = sc.next();

                if (!username.equals(correctUsername)) {
                    throw new InvalidUsernameException(
                            "Invalid username."
                    );
                }

                System.out.print("Enter password: ");
                String password = sc.next();

                if (!password.equals(correctPassword)) {
                    attempts++;

                    if (attempts >= maxAttempts) {
                        throw new AccountLockedException(
                                "Account locked after 3 failed attempts."
                        );
                    }

                    throw new InvalidPasswordException(
                            "Invalid password. Attempts left: "
                                    + (maxAttempts - attempts)
                    );
                }

                System.out.println("Login successful!");
                break;

            } catch (InvalidUsernameException e) {
                attempts++;
                System.out.println(e.getMessage());

            } catch (InvalidPasswordException e) {
                System.out.println(e.getMessage());

            } catch (AccountLockedException e) {
                System.out.println(e.getMessage());
                break;

            } finally {
                System.out.println("Login attempt completed.");
            }
        }
    }
}
