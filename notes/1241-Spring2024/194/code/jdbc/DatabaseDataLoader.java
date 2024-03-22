package uno.ece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseDataLoader {

	// singleton pattern: look it up at your local library

	public static Map<Integer, Person> personsMap = loadPersons();

	private static Map<Integer, Person> loadPersons() {

		String serverUrl = "jdbc:mysql://cse-linux-01.unl.edu/cbourke3";
		String username = "cbourke3";
		String password = DatabaseInfo.PASSWORD;

		Map<Integer, Person> people = new HashMap<>();

		try {
			// 1. make your connection
			Connection conn = DriverManager.getConnection(serverUrl, username, password);

			// 2. Formulate your query
			// 2a. write your query
			String queryPerson = "select * from Person";
			PreparedStatement psPerson = conn.prepareStatement(queryPerson);
			ResultSet rsPerson = psPerson.executeQuery();

			// ? is a parameter
			// You can have multilpe parameters, they are 1-indexed
			String queryEmail = "select address from Email where personId = ?";
			PreparedStatement psEmail = conn.prepareStatement(queryEmail);
			ResultSet rsEmail = null;

			// next():
			// 1. advance the result set to the next record (if any)
			// 2. returns true if it now points to a valid record, false otherwise
			while (rsPerson.next()) {
				// the current record is already pointed to by "rs"...
				int personId = rsPerson.getInt("personId");
				String lastName = rsPerson.getString("lastName");
				String firstName = rsPerson.getString("firstName");
				String dateOfBirthStr = rsPerson.getString("dateOfBirth");
				LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
				Person p = new Person(personId, lastName, firstName, dateOfBirth);
				people.put(personId, p);
				// get the emails of person p:
				// you set/reset the parameter:
				psEmail.setInt(1, personId);
				rsEmail = psEmail.executeQuery();
				while (rsEmail.next()) {
					String address = rsEmail.getString("address");
					p.addEmail(address);
				}
				rsEmail.close();
			}

			// Option 1: change the query to have a join query: end up
			// sending a LOT of redundant data over the network!
			// Option 2: process all the people and then process all of the email
			// You end up opening multiple connection
			// Option 3: process emails *as you go*!

			psEmail.close();
			rsPerson.close();
			psPerson.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return people;
	}

	/**
	 * Retrieves the person from the database with the given <code>personId</code>,
	 * returns <code>null</code> if no such record.
	 * 
	 * @param personId
	 * @return
	 */
	public static Person loadPerson(int personId) {

		String serverUrl = "jdbc:mysql://cse-linux-01.unl.edu/cbourke3";
		String username = "cbourke3";
		String password = DatabaseInfo.PASSWORD;

		Person p = null;
		
		try {
			// 1. make your connection
			Connection conn = DriverManager.getConnection(serverUrl, username, password);

			// 2. Formulate your query
			// 2a. write your query
			String queryPerson = "select * from Person where personId = ?";
			PreparedStatement psPerson = conn.prepareStatement(queryPerson);
			psPerson.setInt(1, personId);
			ResultSet rsPerson = psPerson.executeQuery();

			// ? is a parameter
			// You can have multilpe parameters, they are 1-indexed
			String queryEmail = "select address from Email where personId = ?";
			PreparedStatement psEmail = conn.prepareStatement(queryEmail);
			psEmail.setInt(1, personId);
			ResultSet rsEmail = null;

			// next():
			// 1. advance the result set to the next record (if any)
			// 2. returns true if it now points to a valid record, false otherwise
			if (rsPerson.next()) {
				// the current record is already pointed to by "rs"...
				String lastName = rsPerson.getString("lastName");
				String firstName = rsPerson.getString("firstName");
				String dateOfBirthStr = rsPerson.getString("dateOfBirth");
				LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
				p = new Person(personId, lastName, firstName, dateOfBirth);
				// get the emails of person p:
				// you set/reset the parameter:
				psEmail.setInt(1, personId);
				rsEmail = psEmail.executeQuery();
				while (rsEmail.next()) {
					String address = rsEmail.getString("address");
					p.addEmail(address);
				}
				rsEmail.close();
			} 
			if(rsPerson.next()) {
				throw new RuntimeException("Someone screwed with your DB, man personId "+personId);
			}

			// Option 1: change the query to have a join query: end up
			// sending a LOT of redundant data over the network!
			// Option 2: process all the people and then process all of the email
			// You end up opening multiple connection
			// Option 3: process emails *as you go*!

			psEmail.close();
			rsPerson.close();
			psPerson.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return p;
	}

}
