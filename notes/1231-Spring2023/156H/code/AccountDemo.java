package unl.soc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountDemo {

	public static double getMax(List<Double> values) {

		double maxValue = Double.MIN_VALUE;
		for(Double x : values) {
			if(x > maxValue) {
				maxValue = x;
			}
		}
		return maxValue;
		
	}
	public static void main(String[] args) {
		
		//TODO: I want a saving account that has $100 and pays 5% Annual Interest Rate
		Person me = new Person("Chris", "Bourke");
		Savings s1 = new Savings("12345A", me, 100.00, .05);
		Savings s2 = new Savings("234", me, 200.00, .05);
		//TODO: I want an annuity that pays $500/month for 10 years
		Annuity a1 = new Annuity("784932", me, 500.00, 10);
		Annuity a2 = new Annuity("432", me, 100.00, 20);
		
		List<Account> myAccounts = new ArrayList<>();
		myAccounts.add(s1);
		myAccounts.add(s2);
		myAccounts.add(a1);
		myAccounts.add(a2);
		
		double total = 0.0;
		for(Account a : myAccounts) {
			total += a.getValue();
		}
		System.out.println(total);
		
		//Account a = new Account("xyz", me);
		
	}

}
