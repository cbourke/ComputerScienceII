package unl.soc;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Demo {

	
	public static void main(String args[]) {
		
		Isotope strontium = new Isotope(38, "Strontium-90", "Sr", 28.9);
		Isotope strontiumSampleA = new Isotope(strontium, 10.0);
		Isotope strontiumSampleB = new Isotope(strontium, 5.0);
		Isotope strontiumSampleC = new Isotope(strontium, 7.5);
		
		//produce a decay table for sample A:

		double originalMass = strontiumSampleA.getMass();
		int year = 0;

		System.out.printf("%s\n", strontiumSampleA);

		String header = """
				Elapsed Years    Amount
				-------------------------
				""";
		System.out.print(header);
		Duration yearDuration = Duration.ofDays(1);

		while (strontiumSampleA.getMass() >= .5 * originalMass) {
			System.out.printf("%d               %6.2fg\n", year, strontiumSampleA.getMass());
			year++;
			strontiumSampleA.decay(yearDuration);
		}
		System.out.printf("%d               %6.2fg\n", year, strontiumSampleA.getMass());


		
	}
}
