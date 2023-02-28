package uno.ece;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountDemo {
	
	public static double getTotal(Collection<? extends Account> accounts) {
		
		double total = 0.0;
		for(Account a : accounts) {
			total += a.getValue();
		}
		return total;
	}

	public static void main(String[] args) {
		
		Person me = new Person("Chris", "Bourke");
		Savings s1 = new Savings("1234XYZ", me, 1000.00, .03);
		Savings s2 = new Savings("abcXYZ", me, 500.00, .02);
		Annuity a1 = new Annuity("YZZAB", me, 500.00, 10);
		Annuity a2 = new Annuity("FOO", me, 100.00, 5);

		double result;
		
		Collections.max(null)
		
		List<Account> myAccounts = new ArrayList<>();
		myAccounts.add(s1);
		myAccounts.add(s2);
		myAccounts.add(a1);
		myAccounts.add(a2);
		result = getTotal(myAccounts);
		System.out.println(result);

		List<Savings> mySavings = new ArrayList<>();
		mySavings.add(s1);
		mySavings.add(s2);
		result = getTotal(mySavings);
		System.out.println(result);
		
		List<Annuity> myAnnuity = new ArrayList<>();
		myAnnuity.add(a1);
		myAnnuity.add(a2);
		result = getTotal(myAnnuity);
		System.out.println(result);
		
//		List<String> names = Arrays.asList("Hello", "World", "Joe");
//		result = getTotal(names);
		
		

		
	}


}
