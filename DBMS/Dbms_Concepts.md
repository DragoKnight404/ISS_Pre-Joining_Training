# DBMS Theoretical Concepts

## 1. Need for a Database (vs. File System)
Before databases, systems used flat files (like text or Excel). Databases were invented to solve critical flaws in file systems:

* **Data Redundancy:** In files, the same address might be stored in 10 different places. If it changes, you have to update all 10. Databases normalize this (store it once).
* **Data Inconsistency:** If a system crashes while writing to a file, the data becomes corrupt. Databases use **ACID properties** to guarantee validity.
* **Security:** File systems grant access to the whole file. Databases can grant access to specific *columns* or *rows* (e.g., hiding salaries from junior employees).
* **Concurrent Access:** Multiple users cannot safely edit a text file at the same time. Databases use **Locking** to allow thousands of simultaneous transactions.

### The ACID Properties (The Holy Grail of DBMS)
Every reliable transaction MUST follow these rules:
1.  **Atomicity:** All or Nothing. If a bank transfer fails halfway, the money is returned.
2.  **Consistency:** The database moves from one valid state to another (constraints are never broken).
3.  **Isolation:** One transaction doesn't interfere with another running at the same time.
4.  **Durability:** Once saved (Committed), data survives even a power loss.

---

## 2. Normalization
Normalization is the process of organizing data to **reduce redundancy** and **improve integrity**.

* **1NF (First Normal Form):** Data must be atomic.
    * *Bad:* Storing "Red, Blue, Green" in a single `Colors` cell.
    * *Good:* Splitting this into three rows.
* **2NF (Second Normal Form):** Must be in 1NF + No Partial Dependency.
    * *Rule:* All non-key columns must depend on the *entire* Primary Key.
* **3NF (Third Normal Form):** Must be in 2NF + No Transitive Dependency.
    * *Rule:* Non-key columns should not depend on other non-key columns. (e.g., `City` usually depends on `ZipCode`, not the `UserId` directly. Move Zip/City to a separate table).

---

## 3. SQL Concepts & Commands

### DDL (Data Definition Language) - *Defining Structure*
* `CREATE`: Builds new tables/databases.
* `ALTER`: Modifies table structure (e.g., adding a column).
* `DROP`: Deletes the table and structure entirely.
* `TRUNCATE`: Wipes all data but keeps the table structure.

### DML (Data Manipulation Language) - *Managing Data*
* `INSERT`, `UPDATE`, `DELETE`.

### DQL (Data Query Language) - *Asking Questions*
* `SELECT`: The primary tool for fetching data.
* **Clauses:**
    * `WHERE`: Filters rows *before* grouping.
    * `HAVING`: Filters groups *after* grouping (e.g., "Show departments where the *average* salary > 50k").
    * `ORDER BY`: Sorts results (ASC/DESC).
    * `DISTINCT`: Removes duplicate rows from the result.

---

## 4. Advanced Objects

### Views ("Virtual Tables")
A View is a saved query that looks like a table.
* **Use Case:** Security. Create a view `Public_Employee_Info` that selects names but excludes salaries. Give the HR intern access to the View, not the Table.
* **Storage:** It does not store data physically; it fetches fresh data every time you query it.

### Stored Procedures
A block of SQL code stored on the database server.
* **Pros:** Performance. Logic runs on the server (no network lag). Security (prevent SQL injection).
* **Cons:** Hard to debug compared to Java code.

### Indexes
A data structure (usually a B-Tree) that improves the speed of data retrieval operations.
* **Analogy:** The index at the back of a book.
* **Trade-off:** Speeds up `SELECT` but slows down `INSERT`/`UPDATE` (because the index must be updated with every write).

---

## 5. JDBC Concepts (Java <-> SQL)

### Statement vs. PreparedStatement
* **Statement:** Compiles SQL every time. Vulnerable to SQL Injection. (e.g., `Select * from users where name = '` + var + `'`)
* **PreparedStatement:** Pre-compiles the query structure. Input is treated strictly as data, not code. Safe and Faster. (e.g., `Select * from users where name = ?`)

### CallableStatement
* Used specifically to execute **Stored Procedures** from Java code.

### ResultSet
* Acts as a cursor (pointer) to the data returned by a query. It does not load the entire database into RAM; it fetches rows as you iterate (`rs.next()`).