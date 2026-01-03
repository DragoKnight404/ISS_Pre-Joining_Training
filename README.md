# Pre-Boarding Engineering Assignment

## 📖 Overview
This repository contains a comprehensive suite of technical implementations completed as part of the pre-joining training. The project spans the full stack of software development, moving from low-level logic to high-level system architecture.

The goal of this repository is to demonstrate first-principles understanding of **Core Java**, **Relational Databases**, **Python Scripting**, **Client-Side Interactivity**, and **Server-Side Architecture**.

---

## 📂 Repository Structure

```text
📦 Root
 ┣ 📂 CoreJava        # Strong typing, OOP, and Memory Management
 ┣ 📂 DBMS            # Data persistence, Normalization, and SQL Logic
 ┣ 📂 Python          # Dynamic typing, Scripting, and Advanced Logic
 ┣ 📂 Frontend        # Native Browser Technologies (Tic-Tac-Toe Game)
 ┗ 📂 Application     # The Integration Layer (Tomcat + Servlets + JSP)
```

---

## 🛠 Module Breakdown

### 1. Core Java (`/CoreJava`)
**Focus:** Strict Object-Oriented Programming and JVM internals.

**Concepts Covered:**
- **OOP Principles:** Implementation of Inheritance, Polymorphism, Encapsulation, and Abstraction without frameworks.
- **Data Structures:** Internal workings of the Collections Framework (List vs Set vs Map).
- **Robustness:** Exception Handling (try-catch-finally), Multi-threading, and File I/O operations.

### 2. Database Management (`/DBMS`)
**Focus:** Data integrity and relational theory.

**Contents:**
- **SQL Implementation:** Raw scripts demonstrating DDL (Schema Design), DML (Data Manipulation), and Complex Joins.
- **Theoretical Foundation:** Documentation covering Normalization forms (1NF, 2NF, 3NF), ACID properties, and Indexing strategies.

### 3. Python Scripting (`/Python`)
**Focus:** Modern, concise logic and data manipulation.

**Files:**
- **Basics & Collections:** Mutable vs Immutable structures, List Comprehensions, and F-Strings.
- **Advanced Logic:** Control flow patterns (including loop-else) and Error Handling.
- **OOP & Internals:** Magic Methods (`__init__`, `__str__`), Generators, and Memory Reference management.

### 4. Frontend Engineering (`/Frontend`)
**Focus:** DOM Manipulation and Event-Driven Programming.

**Project:** Tic-Tac-Toe Game.

**Tech Stack:** Vanilla HTML5, CSS3, JavaScript (ES6+).

**Key Mechanics:**
- **Game Loop:** Handling user clicks and updating the UI state dynamically.
- **Logic:** Algorithms to detect win conditions or draw states.
- **Styling:** Responsive grid layout using CSS Flexbox/Grid.

### 5. Web Application (`/Application`)
**Focus:** Server-Side Architecture (MVC Pattern). This module serves as the integration point, combining logic (Java), view (HTML/JSP), and data handling.

**Architecture:** Model-View-Controller (MVC).

**Tech Stack:** Apache Tomcat 10, Jakarta Servlet API, JSP.

**Components:**
- **Controller (Servlet):** Java classes that intercept HTTP requests, enforce business logic (Authentication), and manage Sessions.
- **View (JSP):** Server-side rendering of HTML content.
- **Configuration:** Deployment descriptors (`web.xml`) for URL routing and dependency management via Maven.