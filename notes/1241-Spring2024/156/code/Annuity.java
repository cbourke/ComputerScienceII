package unl.soc;

/**
 * Models an annuity asset which pays the owner a monthly 
 * payment over a given term (years).
 * 
 * The value is the number of payments times the monthly payment
 * 
 * Terms = 10 years
 * Months = 120 months
 * Monthly payment = $500
 * Value of this asset: $60,000
 *
 */
public class Annuity extends Asset {
	
	private int terms;
	private double monthlyPayment;
	
	public Annuity(String accountNumber, Person owner, int terms, double monthlyPayment) {
		super(accountNumber, owner);
		this.terms = terms;
		this.monthlyPayment = monthlyPayment;
	}

	@Override
	public double getValue() {
		return this.terms * 12 * this.monthlyPayment;
	}
	



}
