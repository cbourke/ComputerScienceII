package unl.soc;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;


public class ElevationGainTests {

	public static final double TOLERANCE = 0.001;

	@Test
	public void testElevationGain001() {
		
		List<Double> elevations = List.of(350.0, 352.5, 351.2, 355.2, 354.0);

		double expected = 6.5;
		double actual = RunningUtils.elevationGain(elevations);
		assertEquals(actual, expected, 0.001);

	}
	
	@Test
	public void testElevationGain002() {
		
		List<Double> elevations = List.of(1.0, 2.0, 3.0, 4.0, 5.0);

		double expected = 4.0;
		double actual = RunningUtils.elevationGain(elevations);
		assertEquals(actual, expected, 0.001);

	}

	@Test
	public void testElevationGain003() {
		
		List<Double> elevations = List.of(5.0, 4.0, 3.0, 2.0, 1.0);

		double expected = 0.0;
		double actual = RunningUtils.elevationGain(elevations);
		assertEquals(actual, expected, 0.001);

	}

}
