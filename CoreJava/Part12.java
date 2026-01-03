/**
 * Part 12: File Read & Write (Text Files)
 * Concepts Covered:
 * 1. FileWriter: Writing text to files.
 * 2. BufferedWriter: Performance optimization (writes big chunks).
 * 3. Modes: Overwriting (Default) vs Appending (boolean flag).
 * 4. FileReader & BufferedReader: Reading text efficiently.
 */

import java.io.*; // Import all IO classes

public class Part12 {

    public static void main(String[] args) {

        String fileName = "my_notes.txt";

        System.out.println("--- 1. Writing to File (Overwrite Mode) ---");
        // This will create the file. If it exists, it WIPES it clean.
        writeToFile(fileName, "Hello! This is line 1.\n");
        writeToFile(fileName, "This is line 2 (Previous content was wiped if I call this again).\n");

        System.out.println("--- 2. Appending to File (Add to end) ---");
        // This keeps existing content and adds new lines at the bottom.
        appendToFile(fileName, "This is line 3 (Appended).\n");
        appendToFile(fileName, "This is line 4 (Appended).\n");

        System.out.println("--- 3. Reading from File ---");
        readFromFile(fileName);
    }

    /**
     * WRITES data to a file.
     * WARNING: This mode (default) overwrites existing files!
     */
    public static void writeToFile(String fileName, String content) {
        // try-with-resources auto-closes the writer
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println(">> Overwrite Success.");
        } catch (IOException e) {
            System.out.println("Error writing: " + e.getMessage());
        }
    }

    /**
     * APPENDS data to a file.
     * key: new FileWriter(fileName, TRUE) -> The 'true' enables append mode.
     */
    public static void appendToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content);
            System.out.println(">> Append Success.");
        } catch (IOException e) {
            System.out.println("Error appending: " + e.getMessage());
        }
    }

    /**
     * READS data line by line.
     */
    public static void readFromFile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File does not exist!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n--- File Contents Start ---");

            // Loop runs as long as there is a line to read
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("--- File Contents End ---\n");
        } catch (IOException e) {
            System.out.println("Error reading: " + e.getMessage());
        }
    }
}