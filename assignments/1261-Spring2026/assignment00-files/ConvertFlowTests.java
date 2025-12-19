package unl.soc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * JUnit test suite for {@link FloodUtils#convertFlow convertFlow}
 *
 */
public class ConvertFlowTests {

	@Test
	void testConvertFlowEmptyList() {
		List<Double> flow = new ArrayList<>();
		List<Double> result = FloodUtils.convertFlow(flow);
		assertTrue(result.isEmpty(), "The result should be an empty list when input is empty.");
	}

	@Test
	void testConvertFlowSingleElement() {
		List<Double> flow = Collections.singletonList(5.0);
		List<Double> result = FloodUtils.convertFlow(flow);
		assertEquals(1, result.size(), "Result list should have one element.");
		assertEquals(53.81955, result.get(0), 0.00001, "The converted value is incorrect.");
	}

	@Test
	void testConvertFlowMultipleElements() {
		List<Double> flow = Arrays.asList(2.0, 3.0, 4.0);
		List<Double> result = FloodUtils.convertFlow(flow);
		assertEquals(3, result.size(), "Result list should have three elements.");
		assertEquals(21.52782, result.get(0), 0.00001, "First value conversion is incorrect.");
		assertEquals(32.29173, result.get(1), 0.00001, "Second value conversion is incorrect.");
		assertEquals(43.05564, result.get(2), 0.00001, "Third value conversion is incorrect.");
	}

	@Test
	void testConvertFlowNegativeValue() {
		List<Double> flow = Collections.singletonList(-1.0);
		List<Double> result = FloodUtils.convertFlow(flow);
		assertEquals(-10.76391, result.get(0), 0.00001, "Negative values should be handled correctly.");
	}

	@Test
	void testConvertFlowZeroValue() {
		List<Double> flow = Collections.singletonList(0.0);
		List<Double> result = FloodUtils.convertFlow(flow);
		assertEquals(0.0, result.get(0), 0.00001, "Zero value should remain zero.");
	}

	@Test
	void testConvertFlowLargeValue() {
		List<Double> flow = Collections.singletonList(1000000.0);
		List<Double> result = FloodUtils.convertFlow(flow);
		assertEquals(10763910.0, result.get(0), 0.00001, "Large values should be handled correctly.");
	}

	@Test
	void testConvertFlowSmallValue() {
		List<Double> flow = Collections.singletonList(0.0001);
		List<Double> result = FloodUtils.convertFlow(flow);
		assertEquals(0.001076391, result.get(0), 0.00000001, "Small values should be handled correctly.");
	}

	@Test
	void testConvertFlowMultipleLargeValues() {
		List<Double> flow = Arrays.asList(1000000.0, 2000000.0, 3000000.0);
		List<Double> result = FloodUtils.convertFlow(flow);
		assertEquals(3, result.size(), "Result list should have three elements.");
		assertEquals(10763910.0, result.get(0), 0.00001, "First value conversion is incorrect.");
		assertEquals(21527820.0, result.get(1), 0.00001, "Second value conversion is incorrect.");
		assertEquals(32291730.0, result.get(2), 0.00001, "Third value conversion is incorrect.");
	}

	@Test
	void testConvertFlowWithNullList() {
		assertThrows(NullPointerException.class, () -> {
			FloodUtils.convertFlow(null);
		}, "Passing null should throw NullPointerException.");
	}

	@Test
	void testConvertFlowNegativeAndPositiveValues() {
		List<Double> flow = Arrays.asList(-1.0, 0.0, 1.0);
		List<Double> result = FloodUtils.convertFlow(flow);
		assertEquals(-10.76391, result.get(0), 0.00001, "Negative value conversion is incorrect.");
		assertEquals(0.0, result.get(1), 0.00001, "Zero value conversion is incorrect.");
		assertEquals(10.76391, result.get(2), 0.00001, "Positive value conversion is incorrect.");
	}

	//TODO: Add two test cases of your own

}
