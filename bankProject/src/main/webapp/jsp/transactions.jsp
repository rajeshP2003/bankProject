<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Debit From / Credit To Account</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Debit From / Credit To Account</h2>
    <form id="transactionForm">
        <label for="accountId">Account ID:</label>
        <input type="number" id="accountId" name="accountId" required><br><br>
        <label for="amount">Amount:</label>
        <input
        type="number" id="amount" name="amount" required><br><br>
        <input type="submit" value="Debit From Account" data-action="debit">
        <input type="submit" value="Credit To Account" data-action="credit">
    </form>

    <script>
        document.getElementById('transactionForm').addEventListener('submit', function(event) {
            event.preventDefault();

            var accountId = document.getElementById('accountId').value;
            var amount = document.getElementById('amount').value;
            var actionButton = event.submitter;

            var pathArray = window.location.pathname.split('/');
            var accountIdFromPath = pathArray[1];

            console.log(`accountIdFromPath : ${accountIdFromPath}`)

            var action;
            if (actionButton.getAttribute('data-action') === 'debit') {
                action = 'debit';
            } else {
                action = 'credit';
            }

            var data = {
                "accountid": accountId,
                "amount": amount
            };

            var endpoint;
            if (action === 'debit') {
                endpoint = ${accountIdFromPath} + '/debitFrom';
            } else {
                endpoint = ${accountIdFromPath} + '/creditTo';
            }

            var xhr = new XMLHttpRequest();
            xhr.open('POST', endpoint, true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 201) {
                        alert('Amount ' + action + 'ed successfully!');
                        window.location.href = '/home';
                    } else {
                        alert('Error ' + action + 'ing amount. Please try again.');
                    }
                }
            };
            xhr.send(JSON.stringify(data));
        });
    </script>
</body>
</html>
