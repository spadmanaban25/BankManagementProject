import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TransferInitialAmount
 */
@WebServlet("/TransferInitialAmount")
public class TransferInitialAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferInitialAmount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String firstName = (String) session.getAttribute("firstName");
		String lastName = (String) session.getAttribute("lastName");

		String ownerAccountNumber = request.getParameter("AccountNumber");
		String Amount = request.getParameter("Amount");
		BankUser user = new BankUser();
		BankUser transferToUser = new BankUser();

		String transferAccountNumber = "101";

		String connectionUrl = "jdbc:sqlserver://\\SQLEXPRESS.database.windows.net:1433;"
				+ "database=Projects;" + "loginTimeout=30;" + "integratedSecurity=true";

		boolean transferstatus = false;

		if (ownerAccountNumber.substring(0, 3).equals("101")) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection connection = DriverManager.getConnection(connectionUrl);
				Statement statement = connection.createStatement();

				ResultSet rs = statement.executeQuery(
						"SELECT * FROM BankCheckingAccount WHERE AccountNumber = '" + ownerAccountNumber + "'");

				while (rs.next()) {
					int ownerAmount = rs.getInt("Amount");
					user.setChecking(new BankAccount(ownerAccountNumber, ownerAmount));
				}

				for (int i = 0; i <= 7; i++) {
					Integer randNum = (int) (Math.random() * ((9 - 0) + 1)) + 0;
					transferAccountNumber += randNum.toString();
				}

				transferToUser.setChecking(new BankAccount(transferAccountNumber, 0));

				rs = statement.executeQuery("SELECT * FROM BankCheckingAccount");

				int id = 0;

				while (rs.next()) {
					id = rs.getInt("id");
				}

				int result = user.transferChecking(transferToUser, Integer.valueOf(Amount));

				if (result == 1) {
					transferstatus = false;
				} else {
					statement.execute("UPDATE BankCheckingAccount SET AccountNumber = '" + transferAccountNumber
							+ "', Amount = '" + transferToUser.getChecking().getAmount() + "' WHERE FirstName = '"
							+ firstName + "' AND LastName = '" + lastName + "' AND id = '" + id + "'");

					statement.execute("UPDATE BankCheckingAccount SET Amount = '" + user.getChecking().getAmount()
							+ "' WHERE AccountNumber = '" + ownerAccountNumber + "'");
					transferstatus = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (ownerAccountNumber.substring(0, 3).equals("202")) {
			transferAccountNumber = "202";
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection connection = DriverManager.getConnection(connectionUrl);
				Statement statement = connection.createStatement();

				ResultSet rs = statement.executeQuery(
						"SELECT * FROM BankSavingsAccount WHERE AccountNumber = '" + ownerAccountNumber + "'");

				while (rs.next()) {
					int ownerAmount = rs.getInt("Amount");
					user.setSavings(new BankAccount(ownerAccountNumber, ownerAmount));
				}

				for (int i = 0; i <= 7; i++) {
					Integer randNum = (int) (Math.random() * ((9 - 0) + 1)) + 0;
					transferAccountNumber += randNum.toString();
				}

				transferToUser.setSavings(new BankAccount(transferAccountNumber, 0));

				rs = statement.executeQuery("SELECT * FROM BankSavingsAccount");

				int id = 0;

				while (rs.next()) {
					id = rs.getInt("id");
				}

				int result = user.transferSaving(transferToUser, Integer.valueOf(Amount));

				if (result == 1) {
					transferstatus = false;
				} else {
					statement.execute("UPDATE BankSavingsAccount SET AccountNumber = '" + transferAccountNumber
							+ "', Amount = '" + transferToUser.getSavings().getAmount() + "' WHERE FirstName = '"
							+ firstName + "' AND LastName = '" + lastName + "' AND id = '" + id + "'");

					statement.execute("UPDATE BankSavingsAccount SET Amount = '" + user.getSavings().getAmount()
							+ "' WHERE AccountNumber = '" + ownerAccountNumber + "'");
					transferstatus = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (transferstatus) {
			session.setAttribute("AccountNumber", transferAccountNumber);
			request.getRequestDispatcher("/AccountConfirmation.jsp").forward(request, response);
		} else {
			request.setAttribute("transferFailed", "Transfer has failed. Please try Again");
			request.getRequestDispatcher("/Transfer.jsp").forward(request, response);
		}
	}

}
