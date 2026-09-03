import java.util.Scanner;

// Custom Exception
class InvalidAgeException extends Exception {

    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Assignment4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== JAVA EXCEPTION HANDLING ASSIGNMENT =====");

        // 1. Arithmetic Exception
        try {
            System.out.print("\nEnter first number: ");
            int num1 = sc.nextInt();

            System.out.print("Enter second number: ");
            int num2 = sc.nextInt();

            int result = num1 / num2;

            System.out.println("Division Result = " + result);

        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
        }

        // 2. Array Index Out Of Bounds Exception
        try {
            int[] numbers = {10, 20, 30};

            System.out.println("\nTrying to access array element...");
            System.out.println(numbers[5]);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index is out of range!");
        }

        // 3. Custom Exception
        try {
            System.out.print("\nEnter your age: ");
            int age = sc.nextInt();

            if (age < 18) {
                throw new InvalidAgeException(
                        "Age is less than 18. You are not eligible!"
                );
            }

            System.out.println("You are eligible!");

        } catch (InvalidAgeException e) {
            System.out.println("Custom Exception: " + e.getMessage());
        }

        // 4. Number Format Exception
        try {
            String text = "ABC";

            int number = Integer.parseInt(text);

            System.out.println(number);

        } catch (NumberFormatException e) {
            System.out.println("\nError: Cannot convert ABC into a number!");
        }

        // Finally block
        finally {
            System.out.println("\nProgram executed successfully.");
        }

        sc.close();
    }
}
