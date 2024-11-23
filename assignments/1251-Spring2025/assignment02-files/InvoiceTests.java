package com.vgb;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for VGB invoice system.
 */
public class InvoiceTests {

	public static final double TOLERANCE = 0.001;


	/**
	 * Tests the subtotal, tax total and grand total values of an invoice in
	 * the VGB system.
	 */
	@Test
	public void testInvoice01() {

		//1. Create test instances 3 different types of invoice items
		//   You may reuse the instances from your Entity test suites
		//2. Create an instance of your invoice and add these 3 items to it
		//3. Calculate and compare the values to the expected values.
		//data values

		double expectedSubtotal = 0;
		double expectedTaxTotal = 0;
		double expectedGrandTotal = 0;

		//Call your invoice's methods to get these values
		double actualSubtotal = 0;
		double actualTaxTotal = 0;
		double actualGrandTotal = 0;

		//Use assertEquals with the TOLERANCE to compare:
		assertEquals(expectedSubtotal, actualSubtotal, TOLERANCE);
		assertEquals(expectedTaxTotal, actualTaxTotal, TOLERANCE);
		assertEquals(expectedGrandTotal, actualGrandTotal, TOLERANCE);
		// ensure that the string representation contains necessary elements
		assertTrue(s.contains("TODO"));


	}

	/**
	 * Tests the subtotal, tax total and grand total values of an invoice in
	 * the VGB system.
	 */
	@Test
	public void testInvoice02() {
		//1. Create test instances the other 2 types of invoice items
		//   You may reuse the instances from your Entity test suites
		//2. Create an instance of your invoice and add these 2 items to it
		//3. Calculate and compare the values to the expected values.
	}



}
