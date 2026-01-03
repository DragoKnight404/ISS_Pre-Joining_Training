package com.myServlets.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthServlet extends HttpServlet {

    // A temporary "Database" to store users (Reset when server restarts)
    // Key = Username, Value = Password
    private static final Map<String, String> userDatabase = new HashMap<>();

    // Initialize with one default user
    static {
        userDatabase.put("admin", "admin");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Figure out what the user wants to do (Login? Register? Logout?)
        String action = req.getParameter("action");

        if ("register".equals(action)) {
            handleRegister(req, resp);
        } else if ("login".equals(action)) {
            handleLogin(req, resp);
        } else if ("logout".equals(action)) {
            handleLogout(req, resp);
        } else {
            resp.sendRedirect("login.jsp"); // Fallback
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        // Simple "Save" logic
        if (userDatabase.containsKey(user)) {
            // User already exists? Go back to register page
            resp.sendRedirect("register.jsp?error=UserExists");
        } else {
            userDatabase.put(user, pass);
            // Success! Send them to login page
            resp.sendRedirect("login.jsp?msg=RegisteredSuccessfully");
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        // Check credentials against our "Database"
        if (userDatabase.containsKey(user) && userDatabase.get(user).equals(pass)) {

            // 1. Create a Session (The "ID Card" that proves they are logged in)
            HttpSession session = req.getSession();
            session.setAttribute("username", user);

            // 2. Send data to the JSP to display "Welcome, [User]"
            req.setAttribute("username", user);

            // 3. Forward to the welcome page
            req.getRequestDispatcher("welcome.jsp").forward(req, resp);

        } else {
            // Login Failed
            req.setAttribute("errorMessage", "Invalid Username or Password!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    private void handleLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Destroy the "ID Card" (Session)
        HttpSession session = req.getSession(false); // Get existing session if any
        if (session != null) {
            session.invalidate();
        }
        // Send back to login
        resp.sendRedirect("login.jsp?msg=logOutSuccessful");
    }
}