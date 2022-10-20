<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make A Transfer</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="navbar">
		<table>
			<tr>
				<td style="padding-left: 150px;">National Bank Logo</td>
				<td style="padding-left: 150px;"><a href="RegisterAccount.jsp">Create
						Bank Account</a></td>
				<td style="padding-left: 150px;"><a href="Transfer.jsp">Make
						A Transfer</a>
				<td style="padding-left: 150px">
					<form action="LogOut">
						<input
							style="border: 0 float: left; display: block; color: white; text-align: center; padding-top: 55px; padding-bottom: 10px; font-size: 30px;"
							type="submit" value="Logout">
					</form>
				</td>
			</tr>
		</table>

	</div>
	<div style="color: #FF0000; font-size: 17px; padding-left: 800px;">${transferFailed}</div>

	<form method="post" action="TransferFunds">
		<table>
			<tr>
				<td><h2
						style="font-size: 25px; font-family: Arial; padding-left: 600px;">
						Transfer Funds
						</h1></td>
			</tr>
			<tr>
				<td>
					<div style="padding-left: 600px;">
						<label for="AccountNumber">Account Number To Transfer To:</label><br> <input
							type="text" name="AccountNumber" id="AccountNumber" size="25"
							maxlength=30 required>
					</div>
				</td>
				<td>
					<div style="padding-left: 150px;">
						<label for="Amount">Amount:</label><br> <input type="text"
							name="Amount" id="Amount" size="25" maxlength=30 required>
					</div>
				</td>


			</tr>
		</table>
		<br> <br> <br>
		<center>
			<input
				style="font-size: 15px; font-weight: bold; height: 30px; width: 75px; background-color: #b21e04"
				type="submit" value="submit">
		</center>

	</form>

</body>
</html>