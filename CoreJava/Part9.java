/**
 * Part 9 & 12: JSON Data & File I/O
 * Concepts Covered:
 * 1. File Handling: Creating and checking files.
 * 2. Writing: FileWriter & BufferedWriter.
 * 3. Reading: FileReader & BufferedReader.
 * 4. Resource Management: 'Try-with-resources' (Auto-closing streams).
 * 5. JSON: Understanding the syntax (Key-Value pairs, standard format).
 */

import java.io.*; // Import for File, FileWriter, FileReader, etc.

class AppConfig {
    int port;
    String appName;
    boolean isDebugMode;

    public AppConfig(int port, String name, boolean debug) {
        this.port = port;
        this.appName = name;
        this.isDebugMode = debug;
    }

    // 1. MANUAL JSON SERIALIZATION (Object -> String)
    // In production, use libraries like Jackson or Gson.
    // Here, we demonstrate understanding of the FORMAT.
    public String toJSON() {
        return "{\n" +
                "  \"appName\": \"" + appName + "\",\n" +
                "  \"port\": " + port + ",\n" +
                "  \"isDebugMode\": " + isDebugMode + "\n" +
                "}";
    }
}

public class Part9 {

    public static void main(String[] args) {

        String fileName = "config_data.json";
        AppConfig config = new AppConfig(8080, "PaymentService", true);

        System.out.println("=== STEP 1: Writing Data to File ===");
        writeJsonToFile(fileName, config);

        System.out.println("\n=== STEP 2: Reading Data from File ===");
        readJsonFromFile(fileName);
    }

    /**
     * Writes the object data to a file in JSON format.
     * Demonstrates: BufferedWriter and Try-with-resources.
     */
    public static void writeJsonToFile(String fileName, AppConfig config) {
        // TRY-WITH-RESOURCES:
        // Automatically closes the file even if an error occurs.
        // No need for 'finally { writer.close(); }'
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            String jsonContent = config.toJSON();
            writer.write(jsonContent);

            System.out.println(">> Success: Data written to " + fileName);
            System.out.println(">> Content Preview:\n" + jsonContent);

        } catch (IOException e) {
            System.out.println(">> Error writing file: " + e.getMessage());
        }
    }

    /**
     * Reads the file line by line and prints it.
     * Demonstrates: BufferedReader.
     */
    public static void readJsonFromFile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Error: File not found!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            System.out.println(">> Reading file contents:");

            // Loop through the file line by line until null (End of File)
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // In a real app, we would parse this string back into an Object here
            }

        } catch (IOException e) {
            System.out.println(">> Error reading file: " + e.getMessage());
        }
    }
}