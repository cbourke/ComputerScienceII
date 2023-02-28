package uno.ece;

public class IsotopeOriginal {

	public static double decay(double mass, int year, double halfLife) {
		double result = mass * Math.pow(.5, (year / halfLife));
		return result;
	}

	public static void main(String args[]) {

		if (args.length != 5) {
			System.err.println("Expected 5 arguments");
			System.exit(1);
		}

		int atomicNumber = Integer.parseInt(args[0]);
		String elementName = args[1];
		String elementSymbol = args[2];
		double halfLife = Double.parseDouble(args[3]);
		double initialMass = Double.parseDouble(args[4]);

		double mass = initialMass;
		int year = 0;

		System.out.printf("%s (%d-%s)\n", elementName, atomicNumber, elementSymbol);

		String header = """
				Elapsed Years    Amount
				-------------------------
				""";
		System.out.print(header);

		while (mass >= .5 * initialMass) {
			System.out.printf("%d               %6.2fg\n", year, mass);
			year++;
			mass = decay(mass, 1, halfLife);
		}
		System.out.printf("%d               %6.2fg\n", year, mass);

	}

}
