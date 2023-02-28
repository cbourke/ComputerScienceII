package uno.ece;

import java.util.Objects;

public abstract class Account implements Ownable {

	private final String accountNumber;
	private final Person owner;
	
	public Account(String accountNumber, Person owner) {
		super();
		this.accountNumber = accountNumber;
		this.owner = owner;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public Person getOwner() {
		return owner;
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
		Account other = (Account) obj;
		return Objects.equals(accountNumber, other.accountNumber);
	}
	
	public abstract double getValue();
	
	
}
