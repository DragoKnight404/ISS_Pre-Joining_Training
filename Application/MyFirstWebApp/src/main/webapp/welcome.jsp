<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Welcome</title></head>
<body>
    <h1>Welcome, ${username}!</h1>

    <p>You have successfully logged in.</p>

    <form action="auth" method="post">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="Logout">
    </form>
</body>
</html>