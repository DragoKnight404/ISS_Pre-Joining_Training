/**
 * Part 11: JDBC (Java Database Connectivity)
 * Concepts Covered:
 * 1. Connection Management: DriverManager & URLs
 * 2. PreparedStatement: Protecting against SQL Injection
 * 3. Transactions: Commit & Rollback
 * 4. CRUD Operations: Create, Read, Update, Delete
 * 5. ResultSet: Extracting data
 */

// Must run this docker container of mysql
// docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=testdb -p 3306:3306 -d mysql:latest

//Command to run this code:
// java -cp ".;mysql-connector-j-8.3.0.jar" Part11

import java.sql.*;

public class Part11 {

    // DATABASE CONFIGURATION
    // Note: 'localhost' works because we mapped the Docker port
    private static final String URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "root"; // Matches the Docker command

    public static void main(String[] args) {

        System.out.println("--- Connecting to Database... ---");

        // TRY-WITH-RESOURCES (Auto-closes the connection)
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.println(">> Connection Successful!");

            try {
                dropTableIfExists(conn, "users");
            } catch (IllegalArgumentException e) {
                System.out.println(">> Skipping dropTable: " + e.getMessage());
            }

            // 0. INITIALIZATION: Create the table if it doesn't exist
            initializeTable(conn);

            // 1. TRANSACTION MANAGEMENT
            // By default, JDBC commits every query immediately.
            // We turn this OFF to group operations together (ACID properties).
            conn.setAutoCommit(false);

            try {
                System.out.println("\n--- 1. INSERT (Create) ---");
                insertUser(conn, "Alice", "alice@example.com", "Dev");
                insertUser(conn, "Bob", "bob@example.com", "QA");

                // Commit the transaction: Save changes permanently
                conn.commit();
                System.out.println(">> Transaction Committed.");

            } catch (SQLException e) {
                // Rollback: Undo ALL changes if any error occurred
                conn.rollback();
                System.out.println(">> Error! Rolled back transaction.");
            }

            // 2. READ (Select)
            System.out.println("\n--- 2. SELECT (Read) ---");
            readUsers(conn);

            // 3. UPDATE
            System.out.println("\n--- 3. UPDATE ---");
            updateUserRole(conn, "Alice", "Senior Dev");

            // 4. DELETE
            System.out.println("\n--- 4. DELETE ---");
            deleteUser(conn, "Bob");

            // Final Read to verify
            System.out.println("\n--- Final Table State ---");
            readUsers(conn);

        } catch (SQLException e) {
            System.out.println(">> DATABASE ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ==========================================
    // HELPER METHODS (The "DAO" Layer)
    // ==========================================

    private static void initializeTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "email VARCHAR(50), " +
                "role VARCHAR(20))";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * Demonstrates PreparedStatement.
     * CRITICAL: Never use string concatenation (e.g., "INSERT..." + name)
     * because it allows SQL Injection attacks. Always use '?'.
     */
    private static void insertUser(Connection conn, String name, String email, String role) throws SQLException {
        String sql = "INSERT INTO users (name, email, role) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);  // 1st '?'
            pstmt.setString(2, email); // 2nd '?'
            pstmt.setString(3, role);  // 3rd '?'
            pstmt.executeUpdate();     // Executes the INSERT
            System.out.println("Inserted: " + name);
        }
    }

    private static void readUsers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM users";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println(String.format("%-5s %-10s %-20s %-10s", "ID", "NAME", "EMAIL", "ROLE"));
            System.out.println("-----------------------------------------------------");

            // Loop through the result rows
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String role = rs.getString("role");

                System.out.println(String.format("%-5d %-10s %-20s %-10s", id, name, email, role));
            }
        }
    }

    private static void updateUserRole(Connection conn, String name, String newRole) throws SQLException {
        String sql = "UPDATE users SET role = ? WHERE name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newRole);
            pstmt.setString(2, name);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Updated " + rowsAffected + " row(s). Changed " + name + " to " + newRole);
        }
    }

    private static void deleteUser(Connection conn, String name) throws SQLException {
        String sql = "DELETE FROM users WHERE name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Deleted " + rowsAffected + " row(s) (User: " + name + ")");
        }
    }

    private static void dropTableIfExists(Connection conn, String tableName) throws SQLException, IllegalArgumentException {

        // VERY IMPORTANT: validate input to avoid SQL injection
        if (!tableName.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            throw new IllegalArgumentException("Invalid table name");
        }

        String sql = "DROP TABLE IF EXISTS " + tableName;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);   // execute(), not executeQuery()
            System.out.println("Table dropped if existed: " + tableName);
        }
    }

}