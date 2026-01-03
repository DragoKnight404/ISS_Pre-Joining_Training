# Java Web Application: Process & Architecture Guide

## 1. Project Concept
This guide documents the creation of a standard **MVC (Model-View-Controller)** web application using raw Java technologies (Servlets & JSP) without external frameworks.

**The Goal:** Build an Authentication System (Login/Register/Logout) to understand the request-response lifecycle of a Java web server.

---

## 2. The Architecture (The "Big Picture")
Before building, it is crucial to understand the roles of the components we used:

1.  **The Server (Apache Tomcat):** Acts as the container. It listens for web requests (like a waiter) and wakes up our Java code to handle them.
2.  **The Build Tool (Maven):** managing the project structure and automatically downloading the required libraries (Servlet API) so we don't have to manually manage JAR files.
3.  **The Controller (Servlet):** The "Brain." It receives user input, processes logic (e.g., checks passwords), and decides which page to show next.
4.  **The View (JSP):** The "Face." It generates the HTML that the user actually sees in the browser.
5.  **The Router (web.xml):** The configuration map that tells the server which URL triggers which Servlet.

---

## 3. Step-by-Step Implementation Process

### Phase 1: Environment & Scaffolding
* **Objective:** Create a blank project that Tomcat recognizes as a valid Web Application.
* **Action:** We initialized a **Maven** project and set the packaging to **WAR** (Web Application Archive).
* **Directory Setup:** We manually created the standard web folder structure:
    * `src/main/java`: For backend Java code.
    * `src/main/webapp`: For frontend HTML/JSP files.
    * `WEB-INF`: A protected folder for configuration.

### Phase 2: Configuration (The "Shopping List")
* **Dependency Management (`pom.xml`):**
    * We instructed Maven to download two specific libraries: **Jakarta Servlet API** and **Jakarta JSP API**.
    * We marked them as `provided`, meaning "use these for coding, but let the Tomcat server provide the actual real versions at runtime."
* **Routing Configuration (`web.xml`):**
    * We created a "Deployment Descriptor."
    * We registered our Servlet (gave it a name) and mapped it to a specific URL pattern (`/auth`). This ensures that any form submitting to "auth" hits our Java code.

### Phase 3: The Frontend (The Views)
* **Objective:** Create the interface for the user.
* **Login Page:** Created a form that collects username/password.
    * *Key Logic:* Validated input using JavaScript (Client-side) before sending to the server.
    * *Key Logic:* Configured the form to send data via `POST` to the mapped URL (`auth`).
* **Registration Page:** Similar to login, but includes a "Confirm Password" check.
* **Welcome Page:** A secure page that displays dynamic data (the username) passed from the server.

### Phase 4: The Backend (The Logic)
* **Objective:** Handle the incoming data and make decisions.
* **The Servlet Class:** We created a Java class extending `HttpServlet`.
* **Action Handling:** Inside the `doPost` method, we implemented a switch logic to check if the user wanted to "login", "register", or "logout".
* **State Management (Session):**
    * **Login:** If credentials matched, we created a **Session** (a server-side ID card) to remember the user.
    * **Logout:** We invalidated (destroyed) the Session to log the user out.
* **Data Persistence:** We used a simple In-Memory Map (HashMap) to store registered users temporarily while the server is running.

---

## 4. The Execution Flow (What happens when you click "Login")

1.  **User Action:** User types credentials on `login.jsp` and clicks Submit.
2.  **Request:** Browser sends a `POST` request to the URL `/auth`.
3.  **Mapping:** Tomcat looks at `web.xml`, sees that `/auth` belongs to `AuthServlet`, and passes the request there.
4.  **Processing:**
    * `AuthServlet` reads the input parameters.
    * It checks the HashMap to see if the user exists and the password matches.
5.  **Decision:**
    * *If Valid:* The Servlet creates a Session, attaches the username to the request, and **forwards** the user to `welcome.jsp`.
    * *If Invalid:* The Servlet attaches an error message to the request and **forwards** the user back to `login.jsp`.
6.  **Response:** The JSP reads the message (Success or Error) and generates the final HTML for the browser.

---

## 5. Troubleshooting & Pitfalls

* **Port Conflicts:** If the server fails to start with "Address already in use," it usually means another service (like Docker) is hogging port 8080. We fixed this by changing the Tomcat config to port 8081.
* **Sync Issues:** If the Java code turns red (can't find symbols), it means Maven hasn't downloaded the libraries yet. A "Reload Project" in the Maven tab fixes this.
* **Context Path:** If you get a 404 error, check the URL. It must include the context path configured in the server settings (e.g., `localhost:8081/app/login.jsp`).

---

## 6. Key Terminology for Revision

* **Servlet Container:** The software (Tomcat) that runs the Servlet.
* **Context Path:** The root URL of your specific application (e.g., `/app`).
* **Request Scope:** Data that survives for only one request-response cycle.
* **Session Scope:** Data that survives across multiple requests for the same user (until logout).
* **Forward vs. Redirect:**
    * *Forward:* Happens internally on the server (URL doesn't change).
    * *Redirect:* Tells the browser to make a new request to a new URL.