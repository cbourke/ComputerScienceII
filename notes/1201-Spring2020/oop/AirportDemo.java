package unl.cse.airport;

public class AirportDemo {

	public static void main(String args[]) {

		Address lincolnAddr = new Address("123 North Street", "Lincoln", "NE", "68116");

		Airport lincoln = new Airport("Lincoln Muni", "LNK", 40.9, -96.75, lincolnAddr);

		Airport omaha = new Airport("Omaha Eppley", "OMA", 41.30, -95.89);

		// another, *uninitialized* object
		Airport ohare;

		System.out.println(lincoln);

		System.out.println("<city>" +lincoln.getAddress().getCity().toUpperCase()+"</city>");

		Airport movedLincoln = new Airport(lincoln, 80);

		System.out.println(movedLincoln);

		System.out.println(omaha);

		// compute the air distance between omaha and lincoln
		double dist = omaha.getAirDistance(lincoln);
		System.out.println(dist);
	}
}
