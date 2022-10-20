
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BankAccount {
	
	private BankUser user = new BankUser();
	private String accountNumber;
	private int amount;
	
	public BankAccount(String acctNum, int amount) {
		
		this.accountNumber = acctNum;
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	

}
