package soc.unl;

/**
 * Models a basic savings account.
 * 
 * @author cbourke
 *
 */
public class Savings extends Account {

	private final double balance;
	private final double annualPercentageRate;
	
	public Savings(String accountNumber, Person owner, double balance, double annualPercentageRate) {
		super(accountNumber, owner);
		this.balance = balance;
		this.annualPercentageRate = annualPercentageRate;
	}


	public double getBalance() {
		return balance;
	}

	public double getAnnualPercentageRate() {
		return annualPercentageRate;
	}

	@Override
	public String toString() {
		return String.format("Savings Account #%s, $%.2f", 
				this.getAccountNumber(), this.balance);
	}
	
	public double getValue() {
		return this.balance;
	}
	
	
	
}
