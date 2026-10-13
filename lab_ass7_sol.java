import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class lab_ass7_sol {

    public static void main(String[] args) {
        System.out.println("========== LAB ASSIGNMENT 7 ==========");

        question1();
        question2();
        question3();
        question4();
        question5();
    }

    // Q1. Generic method to find the sum of numeric arrays.
    public static <T extends Number> double sumArray(T[] numbers) {
        Objects.requireNonNull(numbers, "Array cannot be null.");

        double sum = 0.0;
        for (T number : numbers) {
            sum += Objects.requireNonNull(number, "Array values cannot be null.").doubleValue();
        }
        return sum;
    }

    private static void question1() {
        System.out.println("\nQ1. Sum of Numeric Arrays");

        Integer[] integers = {10, 20, 30, 40};
        Float[] floats = {1.5f, 2.5f, 3.0f};
        Double[] doubles = {4.5, 5.5, 10.0};

        System.out.println("Sum of integers: " + sumArray(integers));
        System.out.println("Sum of floats: " + sumArray(floats));
        System.out.println("Sum of doubles: " + sumArray(doubles));
    }

    // Q2. Use Optional to handle email addresses.
    private static void question2() {
        System.out.println("\nQ2. Optional Email Address");

        Optional<String> email = Optional.of("student@example.com");
        Optional<String> emptyEmail = Optional.empty();

        System.out.print("Email using ifPresent(): ");
        email.ifPresent(System.out::println);

        System.out.println("Default email: "
                + emptyEmail.orElse("default@example.com"));

        try {
            String requiredEmail = emptyEmail.orElseThrow(
                    () -> new IllegalStateException("Email address is missing."));
            System.out.println("Required email: " + requiredEmail);
        } catch (IllegalStateException e) {
            System.out.println("orElseThrow(): " + e.getMessage());
        }
    }

    // Q3. Generic class Result<T extends Number> stores a student's marks.
    private static void question3() {
        System.out.println("\nQ3. Student Result Using Generics");

        List<Integer> marks = Arrays.asList(85, 78, 92, 80);
        Result<Integer> result = new Result<>("Aman", marks, 40.0);

        System.out.println("Student: " + result.getStudentName());
        Optional<Double> average = result.getAverage();

        if (average.isPresent()) {
            System.out.printf("Average/Percentage: %.2f%%\n", average.get());
            System.out.println("Result: " + (result.hasPassed() ? "Pass" : "Fail"));
        } else {
            System.out.println("Marks are not available.");
        }
    }

    // Q4. Manage an event using LocalDate and LocalTime.
    private static void question4() {
        System.out.println("\nQ4. Event Management");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter event name: ");
            String eventName = scanner.nextLine().trim();
            if (eventName.isEmpty()) {
                eventName = "Unnamed Event";
            }

            LocalDate eventDate = readEventDate(scanner);
            LocalTime startTime = readStartTime(scanner);

            System.out.print("Enter venue (press Enter if not specified): ");
            String venueInput = scanner.nextLine().trim();
            Optional<String> venue = venueInput.isEmpty()
                    ? Optional.empty() : Optional.of(venueInput);

            LocalDate today = LocalDate.now();
            long daysRemaining = ChronoUnit.DAYS.between(today, eventDate);
            LocalTime endTime = startTime.plusHours(2);

            System.out.println("\nEvent Details");
            System.out.println("Event name: " + eventName);
            System.out.println("Event date: " + eventDate);
            System.out.println("Start time: " + startTime);
            System.out.println("End time (after 2 hours): " + endTime);
            System.out.println("Venue: " + venue.orElse("Venue not specified"));

            if (eventDate.isAfter(today)) {
                System.out.println("Date status: The event is in the future.");
            } else if (eventDate.isEqual(today)) {
                System.out.println("Date status: The event is today.");
            } else {
                System.out.println("Date status: The event date is in the past.");
            }
            System.out.println("Days until event: " + daysRemaining);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format. Please use yyyy-MM-dd for the date and HH:mm for the time.");
        }
    }

    private static LocalDate readEventDate(Scanner scanner) {
        while (true) {
            System.out.print("Enter event date (yyyy-MM-dd): ");
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Example: 2026-12-25");
            }
        }
    }

    private static LocalTime readStartTime(Scanner scanner) {
        while (true) {
            System.out.print("Enter start time (HH:mm, 24-hour format): ");
            String input = scanner.nextLine().trim();
            try {
                return LocalTime.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time. Example: 14:30");
            }
        }
    }

    // Q5. Generic utility methods.
    private static void question5() {
        System.out.println("\nQ5. Generic Utility Class");

        System.out.println("isEqual(\"Java\", \"Java\"): "
                + Utility.isEqual("Java", "Java"));
        System.out.println("getDefault(empty Optional, 100): "
                + Utility.getDefault(Optional.<Integer>empty(), 100));
        System.out.println("max(25, 40): " + Utility.max(25, 40));
        System.out.println("isNull(null): " + Utility.isNull(null));
        System.out.println("isEqual(\"Hello\", \"World\"): "
                + Utility.isEqual("Hello", "World"));
    }
}

class Result<T extends Number> {
    private final String studentName;
    private final List<T> marks;
    private final double passThreshold;

    public Result(String studentName, List<T> marks, double passThreshold) {
        this.studentName = Objects.requireNonNull(studentName, "Student name cannot be null.");
        Objects.requireNonNull(marks, "Marks list cannot be null.");

        this.marks = new ArrayList<>();
        for (T mark : marks) {
            this.marks.add(Objects.requireNonNull(mark, "A mark cannot be null."));
        }
        this.passThreshold = passThreshold;
    }

    public String getStudentName() {
        return studentName;
    }

    // Returns an empty Optional when no marks have been entered.
    public Optional<Double> getAverage() {
        if (marks.isEmpty()) {
            return Optional.empty();
        }

        double total = 0.0;
        for (T mark : marks) {
            total += mark.doubleValue();
        }
        return Optional.of(total / marks.size());
    }

    public boolean hasPassed() {
        Optional<Double> average = getAverage();
        return average.isPresent() && average.get() >= passThreshold;
    }
}

class Utility {
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        Objects.requireNonNull(value, "Optional value cannot be null.");
        return value.orElse(defaultValue);
    }

    public static <T extends Comparable<? super T>> T max(T a, T b) {
        Objects.requireNonNull(a, "First value cannot be null.");
        Objects.requireNonNull(b, "Second value cannot be null.");
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}
