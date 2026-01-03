import java.util.Arrays;

/**
Part 4: Strings and Arrays
Concepts Covered:
1. Strings: Immutability, String Pool vs Heap, Common Methods
2. StringBuilder: Mutable strings for performance
3. Arrays: 1D declaration, 2D Matrix, and Helper methods
*/

public class Part4 {

    public static void main(String[] args) {

        System.out.println("=== 1. STRING IMMUTABILITY & MEMORY POOL ===");

        // Scenario A: String Literals (Stored in String Constant Pool)
        String s1 = "Java";
        String s2 = "Java"; // s2 points to the SAME object in memory as s1 (Optimization)

        // Scenario B: 'new' Keyword (Forces a new object in Heap)
        String s3 = new String("Java");

        // PROOF: '==' compares memory references, not values
        System.out.println("s1 == s2 (Pool vs Pool): " + (s1 == s2)); // true
        System.out.println("s1 == s3 (Pool vs Heap): " + (s1 == s3)); // false

        // Correct way to compare CONTENT
        System.out.println("s1.equals(s3) (Content check): " + s1.equals(s3)); // true

        // PROOF OF IMMUTABILITY
        s1.concat(" Rules");
        System.out.println("s1 after concat (Unchanged): " + s1); // Prints "Java"

        // We must re-assign the variable to point to the NEW string object
        s1 = s1.concat(" Rules");
        System.out.println("s1 after re-assignment: " + s1); // Prints "Java Rules"


        System.out.println("\n=== 2. USEFUL STRING METHODS ===");

        String rawData = "  ID:101,Name:Alice,Role:Dev  ";

        // Chaining methods: Trim spaces -> uppercase -> substring
        String cleanData = rawData.trim();
        System.out.println("Cleaned: " + cleanData);

        // Splitting string into an Array
        String[] parts = cleanData.split(",");
        System.out.println("Extracted Role: " + parts[2].substring(5)); // Skips "Role:"


        System.out.println("\n=== 3. ARRAYS (1D & 2D) ===");

        // 1D ARRAY: Fixed size container
        int[] numbers = {10, 20, 30, 40, 50};

        // Copying arrays (Be careful: Arrays are objects, '=' just copies reference)
        int[] deepCopy = Arrays.copyOf(numbers, numbers.length);
        int[] shallowCopy = numbers;

        numbers[0] = 999; // Changing original

        System.out.println("Original[0]: " + numbers[0]); // 999
        System.out.println("Deep Copy[0]: " + deepCopy[0]);    // 10 (Safe because we made a deep copy)
        System.out.println("Shallow Copy[0]: " + shallowCopy[0]); // 999

        // 2D ARRAY: Array of Arrays (Matrix)
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("2D Matrix Traversal:");
        for (int i = 0; i < matrix.length; i++) {         // Rows
            for (int j = 0; j < matrix[i].length; j++) {  // Columns
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // New line after every row
        }
    }
}