import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration() {
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
		 
		BankUser user = new BankUser();
		PersonalInfo userPersonal = new PersonalInfo();
		user.setPersonalInfo(userPersonal);
		
		user.getPersonalInfo().setSSN(request.getParameter("SSN"));
		
		if (request.getParameter("PassportNum") != "") {
			user.getPersonalInfo().setPassportNo(request.getParameter("PassportNum"));
			
		} 
		if (request.getParameter("StateNum") != "") {
			user.getPersonalInfo().setStateIDNo(request.getParameter("StateNum"));
			
		}
		user.getPersonalInfo().setDriverLicenseNO(request.getParameter("DriverLicenseNum"));
		user.setFirstName(request.getParameter("FirstName"));
		user.setLastName(request.getParameter("LastName"));
		user.setDOB(request.getParameter("birthDate"));
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.setUserName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		boolean regStatus = user.registerUser();
		
		if(regStatus == false) {
			request.setAttribute("errorMessage", "Registration not successful. Please try again.");
			request.getRequestDispatcher("/RegisterUser.jsp").forward(request, response);
		} 
		request.setAttribute("confirmation", "Registration successful " + user.getFirstName() + " " + user.getLastName());
		HttpSession session = request.getSession();
		session.setAttribute("SSN", user.getPersonalInfo().getSSN());
		request.getRequestDispatcher("/RegisterUser.jsp").forward(request, response);
		
		
		
		
	}

}
