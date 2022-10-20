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
 * Servlet implementation class TransferFunds
 */
@WebServlet("/TransferFunds")
public class TransferFunds extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferFunds() {
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
		String ownerAccountNumber = (String) session.getAttribute("AccountNumber");
		String AccountNumber = request.getParameter("AccountNumber");
		String amountToTransfer = request.getParameter("Amount");
		BankUser user = new BankUser();
		BankUser transferToUser = new BankUser();

		boolean transferstatus = false;

		String connectionUrl = "jdbc:sqlserver://\\SQLEXPRESS.database.windows.net:1433;"
				+ "database=Projects;" + "loginTimeout=30;" + "integratedSecurity=true";

		if (AccountNumber.substring(0, 3).equals("101")) {
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
				rs = statement.executeQuery(
						"SELECT * FROM BankCheckingAccount WHERE AccountNumber = '" + AccountNumber + "'");

				while (rs.next()) {
					int destAmount = rs.getInt("Amount");
					transferToUser.setChecking(new BankAccount(AccountNumber, Integer.valueOf(destAmount)));
				}

				int result = user.transferChecking(transferToUser, Integer.valueOf(amountToTransfer));

				if (result == 1) {
					transferstatus = false;
				} else {
					statement.execute(
							"UPDATE BankCheckingAccount SET Amount = '" + transferToUser.getChecking().getAmount()
									+ "' WHERE AccountNumber = '" + AccountNumber + "'");
					statement.execute("UPDATE BankCheckingAccount SET Amount = '" + user.getChecking().getAmount()
							+ "' WHERE AccountNumber = '" + ownerAccountNumber + "'");
					transferstatus = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (AccountNumber.equals("202")) {
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
				rs = statement.executeQuery(
						"SELECT * FROM BankSavingsAccount WHERE AccountNumber = '" + AccountNumber + "'");

				while (rs.next()) {
					int destAmount = rs.getInt("Amount");
					transferToUser.setSavings(new BankAccount(AccountNumber, Integer.valueOf(destAmount)));
				}

				int result = user.transferSaving(transferToUser, Integer.valueOf(amountToTransfer));

				if (result == 1) {
					transferstatus = false;
				} else {
					statement.execute(
							"UPDATE BankSavingsAccount SET Amount = '" + transferToUser.getSavings().getAmount()
									+ "' WHERE AccountNumber = '" + AccountNumber + "'");
					statement.execute("UPDATE BankSavingsAccount SET Amount = '" + user.getSavings().getAmount()
							+ "' WHERE AccountNumber = '" + ownerAccountNumber + "'");
					transferstatus = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (transferstatus) {
			session.setAttribute("transferstatus", transferstatus);
			request.getRequestDispatcher("/User.jsp").forward(request, response);
		} else {
			request.setAttribute("transferFailed", "Transfer has failed. Please try Again");
			request.getRequestDispatcher("/Transfer.jsp").forward(request, response);
			
		}

	}

}
