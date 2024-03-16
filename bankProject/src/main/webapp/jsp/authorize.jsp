<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Credit Card Authorization</title>
</head>
<body>
    <h1>Authorize Credit Card Transaction</h1>
    <form id="creditCardForm">
        <label for="name">Name on Card:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="cardNumber">Card Number:</label>
        <input type="number" id="cardNo" name="cardNo" required><br><br>
        <label for="cvv">CVV2/CVC2:</label>
        <input type="number" id="cvv" name="cvv" required maxlength="3"><br><br>
        <label for="amount">Amount</label>
        <input type="number" id="amt" name="amt" required><br><br>
        <button type="button" onclick="authorizeTransaction()">Authorize</button>
    </form>

    <script>
        function authorizeTransaction() {
            var formData = {
                name: document.getElementById("name").value,
                cardNo: document.getElementById("cardNo").value,
                cvv: document.getElementById("cvv").value,
                amt: document.getElementById("amt").value
            };

            // Convert form data to JSON
            var jsonData = JSON.stringify(formData);

            // AJAX request to send JSON data to server
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/authorize", true);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        // Success: show alert
                        alert("Transaction Authorized!");
                        window.location.href = '/home'; // Redirect to home page after successful login
                    } else {
                        // Error: show alert and reject transaction
                        alert("Transaction Failed. Please try again later.");
                    }
                }
            };
            xhr.send(jsonData);
        }
    </script>
</body>
</html>