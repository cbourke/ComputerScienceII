package unl.soc;

/**
 * Stock asset.  The value is determined by the share
 * price and number of shares.
 * 
 * 10 * 141.76 = $1417.60
 */
public class Stock extends Asset {

	private double sharePrice;
	private double numberOfShares;
	
	
	
	public Stock(String accountNumber, Person owner, double sharePrice, double numberOfShares) {
		super(accountNumber, owner);
		this.sharePrice = sharePrice;
		this.numberOfShares = numberOfShares;
	}

	@Override
	public double getValue() {
		return this.sharePrice * this.numberOfShares;
	}
}
