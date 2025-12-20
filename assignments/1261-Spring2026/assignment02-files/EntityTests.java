package com.cinco;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * JUnit test suite for the invoice system.
 */
public class EntityTests {

	public static final double TOLERANCE = 0.001;

	/**
	 * Creates an instance of purchased equipment and tests if its cost and tax
	 * calculations are correct.
	 *
	 * TODO: finish implementation
	 */
	@Test
	public void testEquipmentPurchase() {

		// data values
		UUID uuid = UUID.fromString("dca4b8e6-9c13-4b1d-aeef-1e3f237f9a9a");
		String name = "Monitor";
		double pricePerUnit = 199.99;

		// 1. Create an instance of equipment with the data values

		// 2. Establish the expected cost and tax (rounded to nearest cent)
		double expectedCost = 4999.75;
		double expectedTax = 262.49;
		double expectedTotal = 5262.24;

		// 3. Invoke methods to determine the cost/tax:
		double actualCost = 0.0;
		double actualTax = 0.0;
		double actualTotal = 0.0;
		// be sure to test your string representation as well
		String s = null;

		// 4. Use assertEquals with the TOLERANCE to compare:
		assertEquals(expectedCost, actualCost, TOLERANCE);
		assertEquals(expectedTax, actualTax, TOLERANCE);
		assertEquals(expectedTotal, actualTotal, TOLERANCE);
		// ensure that the string representation contains necessary elements
		assertTrue(s.contains("Purchase"));
		assertTrue(s.contains("Monitor"));
		// TODO: add any other elements that should be in *your* string representation

	}

	/**
	 * TODO: proper documentation for *every* method
	 */
	@Test
	public void testEquipmentLease() {
		// TODO
	}

	@Test
	public void testService() {
		// TODO
	}

	@Test
	public void testLicense() {
		// TODO
	}

}
