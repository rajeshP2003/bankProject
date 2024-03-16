<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Statement</title>
</head>
<body>
<h2>Display Statement</h2>
<form id="dateForm">
    <label for="fromDate">From Date:</label>
    <input type="datetime-local" id="fromDate" name="fromDate" required><br><br>
    <label for="toDate">To Date:</label>
    <input type="datetime-local" id="toDate" name="toDate" required><br><br>
    <input type="submit" value="Submit">
</form>

<div id="transactionTable">
    <!-- Transaction table will be displayed here -->
</div>

<script>
    document.getElementById('dateForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent form submission

        var fromDate = document.getElementById('fromDate').value;
        var toDate = document.getElementById('toDate').value;

        var data = {
            "fromDate": fromDate,
            "toDate": toDate
        };
        console.log(`fromDate : (fromDate)`);
        console.log(`toDate : (toDate)`);
        console.log(`accountIdFromPath : ${accountIdFromPath}`);

        var xhr = new XMLHttpRequest();
        xhr.open('POST', ${accountIdFromPath} + '/displayStatement', true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var transactions = JSON.parse(xhr.responseText);

                // Build transaction table
                var table = "<table border='1'><tr><th>Sr. ID</th><th>Transaction ID</th><th>Transaction DateTime</th><th>Description</th><th>Cheque No</th><th>Withdraw Amount</th><th>Deposit Amount</th><th>Available Balance</th></tr>";
                for (var i = 0; i < transactions.length; i++) {
                    table += "<tr>";
                    table += "<td>" + (i+1) +"</td>";
                    table += "<td>" + transactions[i].transId + "</td>";
                    table += "<td>" + transactions[i].transDateTime + "</td>";
                    table += "<td>" + transactions[i].description + "</td>";
                    table += "<td>" + transactions[i].chequeNo + "</td>";
                    table += "<td>" + transactions[i].withdrawAmt + "</td>";
                    table += "<td>" + transactions[i].depositAmt + "</td>";
                    table += "<td>" + transactions[i].availBal + "</td>";
                    table += "</tr>";
                }
                table += "</table>";

                // Display table
                document.getElementById('transactionTable').innerHTML = table;
            }
        };
        xhr.send(JSON.stringify(data));
    });
</script>
</body>
</html>