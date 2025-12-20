package com.cinco;

import java.time.LocalDate;
import java.util.UUID;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class InvoiceData {

	/**
	 * Removes all records from all tables in the database.
	 */
	public static void clearDatabase() {
		//TODO: implement
	}

	/**
	 * Method to add a person record to the database with the provided data.
	 *
	 * @param personUuid
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public static void addPerson(UUID personUuid, String firstName, String lastName, String phone) {
		//TODO: implement
	}


	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personUuid</code>
	 *
	 * @param personUuid
	 * @param email
	 */
	public static void addEmail(UUID personUuid, String email) {
		//TODO: implement
	}

	/**
	 * Adds a company record to the database with the primary contact person identified by the
	 * given code.
	 *
	 * @param companyUuid
	 * @param name
	 * @param contactUuid
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public static void addCompany(UUID companyUuid, UUID contactUuid, String name, String street, String city, String state,
			String zip) {
		//TODO: implement
	}


	/**
	 * Adds an equipment record to the database of the given values.
	 *
	 * @param equipmentUuid
	 * @param name
	 * @param modelNumber
	 * @param retailPrice
	 */
	public static void addEquipment(UUID equipmentUuid, String name, double pricePerUnit) {
		//TODO: implement
	}


	/**
	 * Adds a service record to the database of the given values.
	 *
	 * @param equipmentUuid
	 * @param name
	 * @param costPerHour
	 */
	public static void addService(UUID equipmentUuid, String name, double costPerHour) {
		//TODO: implement
	}


	/**
	 * Adds a license record to the database of the given values.
	 *
	 * @param equipmentUuid
	 * @param name
	 * @param serviceFee
	 * @param annualFee
	 */
	public static void addLicense(UUID equipmentUuid, String name, double serviceFee, double annualFee) {
		//TODO: implement
	}

	/**
	 * Adds an Invoice record to the database with the given data.
	 *
	 * @param invoiceUuid
	 * @param customerUuid
	 * @param salesPersonUuid
	 * @param date
	 */
	public static void addInvoice(UUID invoiceUuid, UUID customerUuid, UUID salesPersonUuid, LocalDate date) {
		//TODO: implement
	}


	/**
	 * Adds an Equipment purchase record to the given invoice.
	 *
	 * @param invoiceUuid
	 * @param itemUuid
	 */
	public static void addEquipmentPurchaseToInvoice(UUID invoiceUuid, UUID itemUuid, int numberOfUnits) {
		//TODO: implement
	}


	/**
	 * Adds an Equipment lease record to the given invoice.
	 *
	 * @param invoiceUuid
	 * @param itemUuid
	 * @param start
	 * @param end
	 */
	public static void addEquipmentLeaseToInvoice(UUID invoiceUuid, UUID itemUuid, int numberOfUnits) {
		//TODO: implement
	}


	/**
	 * Adds a service record to the given invoice.
	 *
	 * @param invoiceUuid
	 * @param itemUuid
	 * @param servicePersonUuid
	 * @param numberOfHours
	 */
	public static void addServiceToInvoice(UUID invoiceUuid, UUID itemUuid, UUID servicePersonUuid, double numberOfHours) {
		//TODO: implement
	}


	/**
	 * Adds a license record to the given invoice.
	 *
	 * @param invoiceUuid
	 * @param itemUuid
	 * @param beginDate
	 * @param endDate
	 */
	public static void addLicenseToInvoice(UUID invoiceUuid, UUID itemUuid, LocalDate beginDate, LocalDate endDate) {
		//TODO: implement
	}


}
