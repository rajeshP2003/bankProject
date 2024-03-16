<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form id="loginForm">
        <label for="userName">Username:</label>
        <input type="text" id="userName" name="userName" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="button" value="Login" onclick="login()">
    </form>

    <script>
        function login() {
            var userName = document.getElementById('userName').value;
            var password = document.getElementById('password').value;

            var user = {
                userName: userName,
                password: password
            };

            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/login', true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        alert('Login successful!');
                        window.location.href = '/home'; // Redirect to home page after successful login
                    } else if (xhr.status === 401) {
                        alert('Incorrect password!');
                    } else if (xhr.status === 404) {
                        alert('User not found!');
                    }
                }
            };
            xhr.send(JSON.stringify(user));
        }
    </script>
</body>
</html>
