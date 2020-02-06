package unl.cse.honors;

import java.util.ArrayList;
import java.util.List;

public class AirportDemo {
	
	public static void main(String args[]) {
		
		Airport lincoln = new Airport("Lincoln Municipal Airport", "LNK", 40.85111, -96.75917);
		
		System.out.println(lincoln);
		
		Address a = new Address("123 A Street", "Omaha", "NE", "68116");

		Airport omaha = new Airport("Omaha Eppley", "OMA", 41.30, -95.89, a);
		
		System.out.println(omaha.getAddress().getCity().toUpperCase());
		
//		Airport omahaRenamed = new Airport(omaha, "Scott Frost Airfield");		
//				
//		System.out.println(omaha);
//		System.out.println(omahaRenamed);
//		
//		double dist = omaha.getAirDistance(lincoln);
//		System.out.println(dist);
		
		//an uninitialized object:
		Airport ohare;
		
		
//		List<Integer> foo = new ArrayList<>();
//		foo.add(10);
//		foo.add(20);
//		foo.add(30);
//
//		List<Integer> bar = new ArrayList<>(foo);
//		bar.add(40);
//		
//		System.out.println(foo);
//		System.out.println(bar);

		
	}
}
