
import java.sql.*;

public class BankUser {

	private String firstName;
	private String lastName;
	private String DOB;
	private String email;
	private String phone;
	private String userName;
	private String password;

	private PersonalInfo personalInfo;
	private BankAccount savings;
	private BankAccount checking;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo pInfo) {
		this.personalInfo = pInfo;
	}

	public BankAccount getSavings() {
		return savings;
	}

	public void setSavings(BankAccount savings) {
		this.savings = savings;
	}

	public BankAccount getChecking() {
		return checking;
	}

	public void setChecking(BankAccount checking) {
		this.checking = checking;
	}

	public int depositChecking(int amount) {
		if (amount < 0) {
			System.out.println("Amount Invalid");
			return 1;
		}
		this.getChecking().setAmount(this.getChecking().getAmount() + amount);

		return 0;
	}

	public int depositSaving(int amount) {
		if (amount < 0) {
			System.out.println("Amount Invalid");
			return 1;
		}
		this.getSavings().setAmount(this.getSavings().getAmount() + amount);

		return 0;

	}

	public int withdrawChecking(int amount) {
		if (amount < 0) {
			System.out.println("Amount Invalid");
			return 1;
		}
		this.getChecking().setAmount(this.getChecking().getAmount() - amount);

		if (this.getChecking().getAmount() < 0) {
			this.getChecking().setAmount(this.getChecking().getAmount() - 40);
		}

		return 0;
	}

	public int withdrawSaving(int amount) {
		if (amount < 0) {
			System.out.println("Amount Invalid");
			return 1;
		}
		this.getSavings().setAmount(this.getSavings().getAmount() - amount);

		if (this.getSavings().getAmount() < 0) {
			this.getSavings().setAmount(this.getSavings().getAmount() - 40);
		}

		return 0;
	}

	public int transferChecking(BankUser user, int amount) {
		if (user == null) {
			System.out.println("Invalid user");
			return 1;
		}
		if (amount < 0 || amount > this.getChecking().getAmount()) {
			System.out.println("Amount Invalid");
			return 1;
		}
		user.getChecking().setAmount(user.getChecking().getAmount() + amount);
		this.getChecking().setAmount(this.getChecking().getAmount() - amount);

		return 0;

	}

	public int transferSaving(BankUser user, int amount) {
		if (user == null) {
			System.out.println("Invalid user");
			return 1;
		}
		if (amount < 0 || amount > this.getSavings().getAmount()) {
			System.out.println("Amount Invalid");
			return 1;
		}
		user.getSavings().setAmount(user.getSavings().getAmount() + amount);
		this.getSavings().setAmount(this.getSavings().getAmount() - amount);

		return 0;

	}

	public boolean registerUser() {
		String connectionUrl = "jdbc:sqlserver://\\SQLEXPRESS.database.windows.net:1433;"
				+ "database=Projects;" + "loginTimeout=30;" + "integratedSecurity=true";
		boolean returnVal = false;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO BankUserTable (FirstName, LastName, DateOfBirth, Email, Phone, Username, Password)"
					+ "Values('" + getFirstName() + "', '" + getLastName() + "', '" + getDOB() + "', '" + getEmail()
					+ "', '" + getPhone() + "', '" + getUserName() + "', '" + getPassword() + "')";

			statement.executeUpdate(sql);
			returnVal = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnVal;

	}

}
