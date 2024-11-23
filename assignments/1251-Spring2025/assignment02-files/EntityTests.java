package com.vgb;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.UUID;

/**
 * JUnit test suite for VGB invoice system.
 */
public class EntityTests {

	public static final double TOLERANCE = 0.001;

	/**
	 * Creates an instance of a piece of equipment and tests if
	 * its cost and tax calculations are correct.
	 *
	 * TODO: finish implementation
	 */
	@Test
	public void testEquipment() {

		//data values
		UUID uuid = UUID.randomUUID();;
		String name = "Backhoe 3000";
		String model = "BH30X2";
		double cost = 95125.00;

		//1. TODO: Create an instance of equipment with the data values

		//2. Establish the expected cost and tax (rounded to nearest cent)
		double expectedCost = 95125.00;
		double expectedTax = 4994.06;

		//3. TODO: Invoke methods to determine the cost/tax:
		double actualCost = 0;
		double actualTax = 0;

		//4. Use assertEquals with the TOLERANCE to compare:
		assertEquals(expectedCost, actualCost, TOLERANCE);
		assertEquals(expectedTax, actualTax, TOLERANCE);
		// ensure that the string representation contains necessary elements
		assertTrue(s.contains("Backhoe 3000"));
		assertTrue(s.contains("BH30X2"));
		assertTrue(s.contains("95125.00"));

	}

	@Test
	public void testLease() {
		//TODO
	}

	@Test
	public void testRental() {
		//TODO
	}

	@Test
	public void testMaterial() {
		//TODO
	}


	@Test
	public void testContract() {
		//TODO
	}




}
