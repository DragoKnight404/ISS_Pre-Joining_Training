# Core Java Pre-boarding Assignment

## Overview
This repository contains a comprehensive set of Java classes developed to demonstrate proficiency in Core Java concepts, Object-Oriented Programming, Data Structures, Concurrency, and Database Connectivity. 
## Environment Details
* **Java Version:** JDK 8+ (Compatible with JDK 11/17/21)
* **Database:** MySQL (Running via Docker)
* **Dependencies:** `mysql-connector-j-8.3.0.jar` (Required for Part 11)

---

## Code Index & Concept Mapping

| Assignment Part | File Name     | Topic | Key Concepts Demonstrated |
| :--- |:--------------| :--- | :--- |
| **1** | `Part1.java`  | **Basics** | Class structure, Object instantiation, State (Instance variables) vs Behavior (Methods). |
| **2** | `Part2.java`  | **Datatypes** | Primitives vs Wrappers (Autoboxing), `static`, `final`, Constructor Overloading. |
| **3** | `Part3.java`  | **Logic Flow** | Loops (`for`, `while`, `do-while`), Branching (`switch`, `if-else`), Jump statements (`break`, `continue`). |
| **4** | `Part4.java`  | **Strings/Arrays** | String Immutability, String Pool, 1D & 2D Arrays, Array copying. |
| **5** | `Part5.java`  | **OOP Pillars** | Inheritance, Polymorphism (Overriding vs Overloading), Encapsulation, Abstraction, Interfaces. |
| **6** | `Part6.java`  | **Exceptions** | Checked vs Unchecked exceptions, Custom Exceptions, `throw` vs `throws`, `try-catch-finally`. |
| **7** | `Part7.java`  | **Collections** | `List`, `Set` (Unique), `Map` (Key-Value), Sorting Custom Objects (`Comparable` & `Comparator`). |
| **8** | `Part8.java`  | **Multithreading** | Thread Safety, `synchronized` methods vs blocks, `volatile`, `ExecutorService` (Thread Pools). |
| **9** | `Part9.java`  | **JSON** | Manual JSON serialization/formatting, Try-with-resources for resource management. |
| **10** | `Part10.java` | **XML** | XML DOM Parsing, Node traversal, Reading Attributes vs Elements. |
| **11** | `Part11.java` | **JDBC** | Database connectivity, `PreparedStatement` (Security), Transaction Management (ACID), CRUD operations. |
| **12** | `Part12.java` | **File I/O** | Text file processing, `BufferedWriter` vs `FileWriter`, Append vs Overwrite modes. |

---

## Execution Instructions

### 1. Standard Files (Parts 1-10, 12)
Most files rely only on the standard Java library. Compile and run them using:

```bash
javac FileName.java
java FileName
```
### 2. Database & JDBC (Part 11)
Prerequisites:

1. Ensure Docker is running.
2. Start the MySQL container:
```bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=testdb -p 3306:3306 -d mysql:latest
```
3. Ensure mysql-connector-j-8.3.0.jar is present in the project root.

Running the Code: You must include the driver in the classpath.

```bash
javac Part11.java
java -cp ".;mysql-connector-j-8.3.0.jar" Part11
```

* Best Practices: All files utilize strictly typed variables and naming conventions (camelCase for methods, PascalCase for classes).

* Resource Management: try-with-resources is used extensively for File I/O and JDBC connections to prevent memory leaks.

* Security: JDBC implementation uses PreparedStatement to prevent SQL Injection.