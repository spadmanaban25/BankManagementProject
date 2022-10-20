import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateSavings
 */
@WebServlet("/CreateSavings")
public class CreateSavings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSavings() {
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
		BankUser user = new BankUser();
		user.setFirstName(request.getParameter("FirstName"));
		user.setLastName(request.getParameter("LastName"));
		user.setDOB(request.getParameter("birthDate"));
		user.setChecking(new BankAccount("", 0));
		
		
		boolean status = false;
		
		String connectionUrl = "jdbc:sqlserver://\\SQLEXPRESS.database.windows.net:1433;"
				+ "database=Projects;" + "loginTimeout=30;" + "integratedSecurity=true";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO BankSavingsAccount (FirstName, LastName, BirthDate, Address, City, State)"
					+ "Values('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getDOB()
					+ "', '" + request.getParameter("Address") + "', '" + request.getParameter("City") + "', '" + request.getParameter("State") + "')";

			statement.execute(sql);
			status = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (status) {
			session.setAttribute("regStatus", status);
			request.getRequestDispatcher("/RegisterAccount.jsp").forward(request, response);
			session.setAttribute("regStatus", false);
		} else {
			System.out.println("registration failed");
		}
		
	}

}
