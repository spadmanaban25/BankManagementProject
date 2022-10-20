import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginUser
 */
@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUser() {
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
		// TODO Auto-generated method stub
		String connectionUrl = "jdbc:sqlserver://\\SQLEXPRESS.database.windows.net:1433;"
				+ "database=Projects;" + "loginTimeout=30;" + "integratedSecurity=true";

		
		BankUser user = new BankUser();
		boolean status = false;
		
		user.setUserName(request.getParameter("Username"));
		user.setEmail(request.getParameter("Username"));
		user.setPassword(request.getParameter("Password"));
		
		PreparedStatement ps = null;
		ResultSet resultset = null;
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			
			String sql = "Select * from BankUserTable where (Username=? or Email=?) and Password=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			resultset = ps.executeQuery();		
			status = resultset.next();		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (status) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUserName());
			session.setAttribute("password", user.getPassword());
			
			try {
				resultset = ps.executeQuery();
				while (resultset.next()) {
					String firstName = resultset.getString("FirstName");
					String lastName = resultset.getString("lastName");
					String birthDate = resultset.getString("DateOfBirth");
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setDOB(birthDate);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("date", user.getDOB());
			session.setAttribute("fullName", user.getFirstName() + " " + user.getLastName());
			request.getRequestDispatcher("/User.jsp").forward(request, response);;
			return;
			
		} else {
			request.setAttribute("errorMessage", "Invalid Username or Password");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
			return;
		}
		
		

	
	}

}
