/**
 Part 6: Exceptions & Handling
 Concepts Covered:
 1. Exception Hierarchy (Checked vs Unchecked)
 2. Handling: try, catch, finally
 3. Keywords: throw (Action) vs throws (Declaration)
 */

// 1. CUSTOM EXCEPTION (Demonstrates Hierarchy)
// By extending 'Exception', this becomes a "Checked Exception".
// The compiler will FORCE code to handle this.
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message); // Pass message up to parent Exception class
    }
}

class UserRegistration {

    // 2. THROWS (The Warning Sign)
    // Declares that this method *might* explode.
    // It passes the responsibility to the caller.
    public void registerUser(String name, int age) throws InvalidAgeException {

        if (age < 18) {
            // 3. THROW (The Explosion)
            // Explicitly generates the exception instance.
            throw new InvalidAgeException("User " + name + " is underage (" + age + "). Registration Denied.");
        }

        System.out.println(">> Success: User " + name + " registered.");
    }

    // Unchecked Exception Demo
    // No 'throws' needed for RuntimeExceptions (like ArithmeticException)
    // These usually indicate bugs in logic (division by zero, null pointers).
    public void calculateRiskScore(int dataPoints) {
        // If dataPoints is 0, Java automatically throws ArithmeticException
        int score = 100 / dataPoints;
        System.out.println("Risk Score: " + score);
    }
}

public class Part6 {
    public static void main(String[] args) {

        UserRegistration reg = new UserRegistration();

        System.out.println("--- Scenario 1: Handling Checked Exceptions (try-catch-finally) ---");

        // TRY: The block of code that might fail
        try {
            System.out.println("Attempting to register Alice...");
            reg.registerUser("Alice", 16); // This will fail

            // CATCH: What to do if it fails
        } catch (InvalidAgeException e) {
            System.out.println("CAUGHT EXCEPTION: " + e.getMessage());
            // Real world usage: Log this error to a file

            // FINALLY: Always executes, success or failure
        } finally {
            System.out.println("FINALLY BLOCK: Closing database connections... (Always runs)");
        }


        System.out.println("\n--- Scenario 2: Unchecked (Runtime) Exceptions ---");

        try {
            reg.calculateRiskScore(0); // Division by zero
        } catch (ArithmeticException e) {
            System.out.println("CAUGHT RUNTIME ERROR: Cannot divide by zero.");
            e.printStackTrace(); // Prints the red error text (useful for debugging)
        }

        System.out.println("\n--- Summary of 'throw' vs 'throws' ---");
        System.out.println("1. 'throws': Used in method signature. Tells compiler 'This method is dangerous'.");
        System.out.println("2. 'throw': Used inside method. Actually creates the error.");
    }
}