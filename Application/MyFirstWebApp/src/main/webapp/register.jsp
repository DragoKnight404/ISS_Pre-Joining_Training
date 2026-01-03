<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <style>
        body { font-family: sans-serif; padding: 50px; }
        form { border: 1px solid #ccc; padding: 20px; width: 300px; }
        input { width: 100%; margin-bottom: 10px; padding: 8px; }
    </style>
    <script>
        function validateRegister() {
            let pass = document.forms["regForm"]["password"].value;
            let confirm = document.forms["regForm"]["confirmPassword"].value;

            if (pass.length < 4) {
                alert("Password must be at least 4 characters");
                return false;
            }
            if (pass !== confirm) {
                alert("Passwords do not match!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h2>Registration</h2>

    <form name="regForm" action="auth" method="post" onsubmit="return validateRegister()">
        <input type="hidden" name="action" value="register">

        <label>Username:</label>
        <input type="text" name="username" required>

        <label>Email:</label>
        <input type="email" name="email" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <label>Confirm Password:</label>
        <input type="password" name="confirmPassword" required>

        <input type="submit" value="Register">
    </form>

    <p>Already have an account? <a href="login.jsp">Login here</a></p>
</body>
</html>