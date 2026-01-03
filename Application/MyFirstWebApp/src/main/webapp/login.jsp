<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body { font-family: sans-serif; padding: 50px; }
        .error { color: red; font-size: 0.9em; }
        form { border: 1px solid #ccc; padding: 20px; width: 300px; }
        input { width: 100%; margin-bottom: 10px; padding: 8px; }
    </style>
    <script>
        function validateLogin() {
            let user = document.forms["loginForm"]["username"].value;
            let pass = document.forms["loginForm"]["password"].value;

            if (user === "") {
                alert("Username cannot be empty");
                return false;
            }
            if (pass === "") {
                alert("Password cannot be empty");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h2>Login Page</h2>

    <% if (request.getAttribute("errorMessage") != null) { %>
        <p class="error"><%= request.getAttribute("errorMessage") %></p>
    <% } %>

    <!-- Here action="auth" means "http://localhost:8080/MyApp/auth" relative to current JSP location -->
    <form name="loginForm" action="auth" method="post" onsubmit="return validateLogin()">
        <input type="hidden" name="action" value="login">

        <label>Username:</label>
        <input type="text" name="username">

        <label>Password:</label>
        <input type="password" name="password">

        <input type="submit" value="Login">
    </form>

    <p>New user? <a href="register.jsp">Register here</a></p>
</body>
</html>