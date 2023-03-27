package unl.soc.jdbc;

public abstract class Account {

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

	public abstract double getValue();
	
	

}
