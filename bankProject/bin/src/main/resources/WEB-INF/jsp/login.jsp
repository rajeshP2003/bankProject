<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h1>Login</h1>
    <form id="loginForm">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit" onclick="submitLoginForm()">Login</button>
    </form>
    <script>
        function submitLoginForm(event) {
            event.preventDefault(); // Prevent default form submission

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            const data = {
                username,
                password
            };

            const jsonData = JSON.stringify(data);

            const xhr = new XMLHttpRequest();
            xhr.open("POST", "/login");
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.onload = function() {
                if (xhr.status === 200) {
                    // Handle successful login (e.g., redirect to dashboard)
                    console.log("Login successful:", xhr.responseText);
                } else {
                    // Handle login error
                    console.error("Login error:", xhr.statusText);
                    alert("Login failed!");
                }
            };

            xhr.onerror = function() {
                console.error("Network error:", xhr.statusText);
                alert("Login failed!");
            };

            xhr.send(jsonData);
        }
    </script>
</body>
</html>