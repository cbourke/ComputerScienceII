package unl.soc;

public class Annuity extends Account {
		
	private final double monthlyPayment;
	private final int termYears;
	
	public Annuity(String accountNumber, Person owner, double monthlyPayment, int termYears) {
		super(accountNumber, owner);
		this.monthlyPayment = monthlyPayment;
		this.termYears = termYears;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public int getTermYears() {
		return termYears;
	}
	
	@Override
	public double getValue() {
		return this.monthlyPayment * this.termYears * 12;
	}
	
	
	
	

}
