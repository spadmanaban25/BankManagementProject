<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<%@page import="java.sql.*, javax.servlet.*"%>
<link rel="stylesheet" href="styles.css">

</head>
<body>
	<script>
		let status = '<%=session.getAttribute("transferstatus")%>';
		if (status === true) {
			alert("Transfer successful");
		}	
	</script>

	<div class="navbar">
		<table>
			<tr>
				<td style="padding-left: 150px;">National Bank Logo</td>
				<td style="padding-left: 150px;"><a href="RegisterAccount.jsp">Create
						Bank Account</a></td>
				<td style="padding-left: 150px;"><a href="Transfer.jsp">Make A Transfer</a>
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
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		if (session.getAttribute("username") == null && session.getAttribute("password") == null) {
			response.sendRedirect("NotLogin.jsp");
		}
	%>
	<br>
	<br>



	<center>
		<div style="color: #3470d1; font-size: 17px;">Welcome
			${fullName}</div>
	</center>

	<center>
		<h2>View Accounts</h2>
	</center>
	<div style="padding-left: 850px;">

		<table border="1">
			<tr>
				<th>Checking Account</th>
				<th>Amount</th>
			</tr>


			<%
				String firstName = (String) session.getAttribute("firstName");
				String lastName = (String) session.getAttribute("lastName");
				ResultSet rs = null;
				if (firstName != null && lastName != null) {
					String connectionUrl = "jdbc:sqlserver://DESKTOP-LH9UA50\\SQLEXPRESS.database.windows.net:1433;"
							+ "database=Projects;" + "loginTimeout=30;" + "integratedSecurity=true";
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						Connection connection = DriverManager.getConnection(connectionUrl);
						Statement statement = connection.createStatement();
						String sql = "Select * From BankCheckingAccount WHERE FirstName = '" + firstName
								+ "' AND LastName = '" + lastName + "'";

						rs = statement.executeQuery(sql);
						if (!rs.isBeforeFirst()) {
			%>
			<tr>
				<td>No Checking Account</td>
			
			</tr>
			<%	
						}
						while (rs.next()) {
							session.setAttribute("AccountNumber", rs.getString("AccountNumber"));
							
			%>
			<tr>
				<td><a href="Transfer.jsp"><%=rs.getString("AccountNumber")%></a></td>
				<td>$<%=rs.getInt("Amount")%></td>
			</tr>
			<%
			
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			%>

		</table>
	</div>
	<br>
	<br>
	<div style="padding-left: 850px;">
		<table border="1">
			<tr>
				<th>Savings Account</th>
				<th>Amount</th>
			</tr>


			<%
				ResultSet rsSavings = null;
				if (firstName != null && lastName != null) {
					String connectionUrl = "jdbc:sqlserver://DESKTOP-LH9UA50\\SQLEXPRESS.database.windows.net:1433;"
							+ "database=Projects;" + "loginTimeout=30;" + "integratedSecurity=true";
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						Connection connection = DriverManager.getConnection(connectionUrl);
						Statement statement = connection.createStatement();
						String sql = "Select * From BankSavingsAccount WHERE FirstName = '" + firstName
								+ "' AND LastName = '" + lastName + "'";

						rsSavings = statement.executeQuery(sql);
						if (!rsSavings.isBeforeFirst()) {
			%>
			<tr>
				<td>No Savings Account</td>
							
			</tr>
			<%	
						}
						while (rsSavings.next()) {
			%>
			<tr>
				<td><a href="Transfer.jsp"><%=rsSavings.getString("AccountNumber")%></a></td>
				<td>$<%=rsSavings.getInt("Amount")%>
			</tr>
			<%
				}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			%>

		</table>
	</div>



</body>
</html>