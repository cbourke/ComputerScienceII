package unl.soc;

import java.util.Objects;

public class Savings extends Account implements Ownable {
	
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
		return String.format("Savings Account #%s $%10.2f", this.accountNumber, this.balance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Savings other = (Savings) obj;
		return Objects.equals(accountNumber, other.accountNumber);
	}

	public double getValue() {
		return this.balance;
	}

	

}
