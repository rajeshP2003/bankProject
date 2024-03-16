<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
</head>
<body>
    <h2>Create Account</h2>
    <form id="createAccountForm" onsubmit="return createAccount()">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="address">Address:</label>
        <textarea id="address" name="address" rows="5" cols="50" required></textarea><br><br>
        <label for="dob">Date of Birth:</label>
        <input type="datetime-local" id="dob" name="dob" required><br><br>
        <label for="balance">Balance:</label>
        <input type="number" id="balance" name="balance" required><br><br>
        <input type="submit" value="Create Account">
    </form>

    <script>
        function createAccount() {
            var name = document.getElementById('name').value;
            var email = document.getElementById('email').value;
            var address = document.getElementById('address').value;
            var dob = new Date(document.getElementById('dob').value);
            var balance = document.getElementById('balance').value;

            // Validate name
            if (name.trim() === '') {
                alert('Please enter a name.');
                return false;
            }

            // Validate email
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email)) {
                alert('Please enter a valid email address.');
                return false;
            }

            // Validate address
            if (address.trim() === '') {
                alert('Please enter an address.');
                return false;
            }

            // Validate DOB (age > 18 years)
            var today = new Date();
            var minDob = new Date(today.getFullYear() - 18, today.getMonth(), today.getDate());
            if (dob > minDob) {
                alert('You must be at least 18 years old.');
                return false;
            }

            // Validate balance
            if (isNaN(balance) || balance <= 0) {
                alert('Please enter a valid positive number for balance.');
                return false;
            }

            // All validations passed, create account
            var accountData = {
                "name": name,
                "email": email,
                "address": address,
                "dob": dob,
                "balance": balance
            };

            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/accounts/create-account', true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 201) {
                        alert('Account created successfully!');
                        window.location.href = '/home';
                    } else {
                        alert('Error creating account. Please try again.');
                    }
                }
            };
            xhr.send(JSON.stringify(accountData));

            return false; // Prevent form submission
        }
    </script>
</body>
</html>
