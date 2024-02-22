package unl.soc;

import java.util.ArrayList;
import java.util.List;

public abstract class Asset implements Ownable {

	private String accountNumber;
	private Person owner;
	//TODO: generate getters, constructor, etc.

	public Asset(String accountNumber, Person owner) {
		super();
		this.accountNumber = accountNumber;
		this.owner = owner;
	}
	
	public Person getOwner() {
		return this.owner;
	}

	/**
	 * Returns the value of this asset.
	 * 
	 * @return
	 */
	public abstract double getValue();
	

	public static void main(String args[]) {
		
		Person p = new Person("Chris", "Bourke", null);
		
		Stock google = new Stock("GOOGLE", p, 141.76, 10);
		Stock appharvest = new Stock("APPH", p, 0.067, 100);

		Annuity a1 = new Annuity("FOO", p, 10, 500);
		Annuity a2 = new Annuity("BAR", p, 10, 500);

		List<Stock> myStocks = new ArrayList<>();
		List<Annuity> myAnnuities = new ArrayList<>();
		List<Asset> myAssets = new ArrayList<>();
		
		myStocks.add(google);
		myStocks.add(appharvest);
		
		myAnnuities.add(a1);
		myAnnuities.add(a2);

		myAssets.add(google);
		myAssets.add(appharvest);
		myAssets.add(a1);
		myAssets.add(a2);
		
		double total = 0.0;
		for(Asset asset : myAssets) {
			System.out.println("value: " + asset.getValue());
			total += asset.getValue();
		}

	}

}
