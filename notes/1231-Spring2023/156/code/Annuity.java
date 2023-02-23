package soc.unl;

/**
 * Models an annuity asset which pays the owner a monthly 
 * payment over a given term (years).
 * 
 * @author cbourke
 *
 */
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
	public String toString() {
		return String.format("Annuity Account #%s, $%.2f/mo @ %d years", 
				this.getAccountNumber(), this.monthlyPayment, this.termYears);
	}
	
	public double getValue() {
		return this.monthlyPayment * this.termYears * 12;
	}
	
	
	

}
