<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign in</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>

	<div class="navbar">
		<table>
			<tr>
				<td style="padding-left: 150px;">National Bank Logo</td>
				<td style="padding-left: 150px;"><a href="RegisterUser.jsp">Sign
						Up</a></td>
				<td style="padding-left: 150px;"><a href="Login.jsp">Sign
						In</a></td>
		</table>
	</div>
	<br>
	<br>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		if (session.getAttribute("username") != null && session.getAttribute("password") != null) {
			response.sendRedirect("User.jsp");
		}
	%>
	<div style="color: #FF0000; font-size: 17px; padding-left: 800px;">${errorMessage}</div>

	<form method="post" action="LoginUser">
		<table>
			<tr>
				<td>
					<div style="padding-left: 850px;">
						<label for="Username">Username or Email:</label><br> <input
							type="text" name="Username" id="Username" size="25" maxlength=30
							required>
					</div>

				</td>

			</tr>

			<tr>
				<td>
					<div style="padding-left: 850px;">
						<label for="Password">Password:</label><br> <input
							type="Password" name="Password" id="Password" size="25"
							maxlength=30 required>
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