package uno.ece;

public class Savings extends Account {
	
	private final double balance;
	private final double apr;
	
	public Savings(String accountNumber, Person owner, double balance, double apr) {
		super(accountNumber, owner);
		this.balance = balance;
		this.apr = apr;
	}

	public double getBalance() {
		return balance;
	}

	public double getApr() {
		return apr;
	}

	@Override
	public String toString() {
		return String.format("Savings Account #%s $%.2f", this.getAccountNumber(), this.balance);
	}

	
	@Override
	public double getValue() {
		return this.balance;
	}

	
	

}
