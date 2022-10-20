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
 * Servlet implementation class UpdateInitialAmountSavings
 */
@WebServlet("/UpdateInitialAmountSavings")
public class UpdateInitialAmountSavings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInitialAmountSavings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String firstName = (String) session.getAttribute("firstName");
		String lastName = (String) session.getAttribute("lastName");

		boolean status = false;
		String connectionUrl = "jdbc:sqlserver://\\SQLEXPRESS.database.windows.net:1433;"
				+ "database=Projects;" + "loginTimeout=30;" + "integratedSecurity=true";
		
		String AccountNumber = "202";
		for (int i = 0; i <= 7; i++) {
			Integer randNum = (int)(Math.random() * ((9 - 0) + 1)) + 0;
			AccountNumber += randNum.toString();
		}
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM BankSavingsAccount");
			int id = 0;
			
			while (rs.next()) {
				id = rs.getInt("id");
			}
			
			String sql = "UPDATE BankSavingsAccount SET AccountNumber = '" + AccountNumber + "', Amount = '75' WHERE FirstName = '" + firstName + "' AND LastName = '" 
			+ lastName + "' AND id = " + id;

			statement.execute(sql);
			status = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (status) {
			session.setAttribute("AccountNumber", AccountNumber);
			request.getRequestDispatcher("/AccountConfirmation.jsp").forward(request, response);
		}
	}

}
