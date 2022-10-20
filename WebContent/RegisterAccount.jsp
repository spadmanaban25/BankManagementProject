<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Bank Account</title>
<link rel="stylesheet" href="styles.css">
<script type="text/javascript" src="script.js"></script>
<%@page import="java.sql.*"%>
</head>

<body onload='hideRadioForm(<%=session.getAttribute("regStatus")%>)'>


	<div class="navbar">
		<table>
			<tr>
				<td style="padding-left: 150px;"><a href="">National Bank
						Logo</a></td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		if (session.getAttribute("username") == null && session.getAttribute("password") == null) {
			response.sendRedirect("NotLogin.jsp");
		}
	%>

	<form name="TypeofAccount" onclick="showAccountForm()"
		id="TypeofAccount" style="display: block;">
		<center>
			<h4>Select What Type of Account to Create</h4>
		</center>
		<div style="padding-left: 850px;">
			<input type="radio" id="savings" name="AccountType" value="savings">
			<label for="savings">Savings</label><br>
		</div>
		<div style="padding-left: 850px;">
			<input type="radio" id="checking" name="AccountType" value="checking">
			<label for="checking">Checking</label><br>
		</div>


	</form>

	<form method="post" action="UpdateInitialAmount" id="InitialAmount"
		style="display: none;">
		<center>
			<h4>When opening a Checking Account, Initial Deposit of $25 and
				$100 must be made.</h4>
			<br>
			<h4>Deposit can be made either through transfer or National Bank
				will transfer you a $75 deposit.</h4>
		</center>
		<table>
			<tr>
				<td>
					<div style="padding-left: 600px;">
						<a href="InitialAmountTransfer.jsp"> <input
							style="font-size: 15px; font-weight: bold; height: 30px; width: 350px; background-color: #b21e04"
							type="button" value="Transfer from an existing Checking Account">

						</a>

					</div>
				</td>

				<td>
					<div style="padding-left: 150px;">
						<input
							style="font-size: 15px; font-weight: bold; height: 30px; width: 300px; background-color: #b21e04"
							type="submit" value="Receive Deposit from National Bank">
					</div>

				</td>
			</tr>

		</table>

	</form>

	<form method="post" action="UpdateInitialAmountSavings"
		id="InitialAmountSavings" style="display: none;">
		<center>
			<h4>When opening a Savings Account, Initial Deposit of $25 and
				$100 must be made.</h4>
			<br>
			<h4>Deposit can be made either through transfer or National Bank
				will transfer you a $75 deposit.</h4>
		</center>
		<table>
			<tr>
				<td>
					<div style="padding-left: 600px;">
						<a href="InitialAmountTransfer.jsp"> <input
							style="font-size: 15px; font-weight: bold; height: 30px; width: 350px; background-color: #b21e04"
							type="button" value="Transfer from an existing Savings Account">

						</a>
					</div>
				</td>

				<td>
					<div style="padding-left: 150px;">
						<input
							style="font-size: 15px; font-weight: bold; height: 30px; width: 300px; background-color: #b21e04"
							type="submit" name="Bank Deposit"
							value="Receive Deposit from National Bank">
					</div>

				</td>
			</tr>

		</table>

	</form>

	<form method="post" action="CreateChecking" id="checkingForm"
		style="display: none;">
		<br> <br> <br>
		<table>
			<tr>
				<td><div style="padding-left: 600px">
						<label for="FirstName">First Name</label><br> <input
							type="text" value=<%=session.getAttribute("firstName")%>
							name="FirstName" id="FirstName" size="25" maxlength=30 required>
					</div></td>
				<td><div style="padding-left: 150px;">
						<label for="LastName">Last Name</label><br> <input
							type="text" value=<%=session.getAttribute("lastName")%>
							name="LastName" id="LastName" size="25" maxlength=30 required>
					</div></td>
				<td><div style="padding-left: 150px;">
						<label for="birthDate">Date</label><br> <input type="date"
							value=<%=session.getAttribute("date")%> name="birthDate"
							id="birthDate" required>
					</div></td>
			</tr>

			<tr>
				<td><div style="padding-left: 600px">
						<label for="Address">Address</label><br> <input type="text"
							name="Address" id="Address" size="25" maxlength=30 required>
					</div></td>
				<td><div style="padding-left: 150px">
						<label for="City">City</label><br> <input type="text"
							name="City" id="City" size="25" maxlength=30 required>
					</div></td>
				<td><div style="padding-left: 150px">
						<label for="State">State</label><br> <input type="text"
							name="State" id="State" size="25" maxlength=30 required>
					</div></td>
			</tr>

		</table>

		<center>
			<input
				style="font-size: 15px; font-weight: bold; height: 30px; width: 75px; background-color: #b21e04"
				type="submit" value="submit">
		</center>
	</form>


	<form method="post" action="CreateSavings" id="savingsForm"
		style="display: none;">
		<br> <br> <br>
		<table>
			<tr>
				<td><div style="padding-left: 600px">
						<label for="FirstName">First Name</label><br> <input
							type="text" value=<%=session.getAttribute("firstName")%>
							name="FirstName" id="FirstName" size="25" maxlength=30 required>
					</div></td>
				<td><div style="padding-left: 150px;">
						<label for="LastName">Last Name</label><br> <input
							type="text" value=<%=session.getAttribute("lastName")%>
							name="LastName" id="LastName" size="25" maxlength=30 required>
					</div></td>
				<td><div style="padding-left: 150px;">
						<label for="birthDate">Date</label><br> <input type="date"
							value=<%=session.getAttribute("date")%> name="birthDate"
							id="birthDate" required>
					</div>
			</tr>

			<tr>
				<td><div style="padding-left: 600px">
						<label for="Address">Address</label><br> <input type="text"
							name="Address" id="Address" size="25" maxlength=30 required>
					</div></td>
				<td><div style="padding-left: 150px">
						<label for="City">City</label><br> <input type="text"
							name="City" id="City" size="25" maxlength=30 required>
					</div></td>
				<td><div style="padding-left: 150px">
						<label for="State">State</label><br> <input type="text"
							name="State" id="State" size="25" maxlength=30 required>
					</div></td>
			</tr>

		</table>

		<center>
			<input
				style="font-size: 15px; font-weight: bold; height: 30px; width: 75px; background-color: #b21e04"
				type="submit" value="submit">
		</center>
	</form>



</body>
</html>