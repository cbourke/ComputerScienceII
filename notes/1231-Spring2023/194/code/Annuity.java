package uno.ece;

public class Annuity extends Account {

	private final double monthlyPayment;
	private final int years;

	public Annuity(String accountNumber, Person owner, double monthlyPayment, int years) {
		super(accountNumber, owner);
		this.monthlyPayment = monthlyPayment;
		this.years = years;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public int getYears() {
		return years;
	}

	@Override
	public String toString() {
		return String.format("Annuity Account #%s $%.2f", this.getAccountNumber(), this.getValue());
	}

	@Override
	public double getValue() {
		return this.monthlyPayment * 12 * this.years;
	}

}
