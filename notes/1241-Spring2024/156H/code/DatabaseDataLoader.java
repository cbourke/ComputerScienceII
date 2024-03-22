package unl.soc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DatabaseDataLoader {

	/**
	 * Loads the person from the database using the given 
	 * <code>personId</code>; if no such record exists, returns
	 * <code>null</code>
	 * 
	 * @param personId
	 * @return
	 */
	public static Person loadPerson(int personId) {
		String username = "cbourke3";
		String password = DatabaseInfo.PASSWORD;
		String url = "jdbc:mysql://cse-linux-01.unl.edu/" + username;

		Person p = null;

		Connection conn = null;
		try {
			// 1 connect to the database
			conn = DriverManager.getConnection(url, username, password);

			// 2. Formulate your query
			String queryPerson = "select firstName,lastName,dateOfBirth from Person where personId = ?";
			PreparedStatement psPerson = conn.prepareStatement(queryPerson);
			psPerson.setInt(1, personId);
			// 2B. Execute your statement
			ResultSet rsPerson = psPerson.executeQuery();

			String queryEmail = "select * from Email where personId = ?";
			PreparedStatement psEmail = conn.prepareStatement(queryEmail);

			// rs.next():
			// 1. advances to the next record
			// 2. returns true if it points to a valid record, false if not
			if (rsPerson.next()) {
				String lastName = rsPerson.getString("lastName");
				String firstName = rsPerson.getString("firstName");
				String dateOfBirthStr = rsPerson.getString("dateOfBirth");
				LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
				p = new Person(personId, lastName, firstName, dateOfBirth);

				// load up the emails for person with personId...
				// ? in queries are *parameters*
				// They can be set by specifying an index (starting at 1)
				// And providing a value...
				psEmail.setInt(1, personId);
				ResultSet rsEmail = psEmail.executeQuery();
				while (rsEmail.next()) {
					String address = rsEmail.getString("address");
					p.addEmail(address);
				}
				rsEmail.close();
			} else {
				//throw new RuntimeException("No person with personId = " + personId + " in the database!");
				//no one by that id here...
				//BAD: resources don't get closed: return null;
			}
			
			if(rsPerson.next()) {
				throw new IllegalStateException(" mulitiple records exist with personId = " + personId);
			} //Project: YAGNI

			// 4 close your resources: in reverse order!
			rsPerson.close();
			psEmail.close();
			psPerson.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return p;
	}

	public static Map<Integer, Person> loadPersons() {

		String username = "cbourke3";
		String password = DatabaseInfo.PASSWORD;
		String url = "jdbc:mysql://cse-linux-01.unl.edu/" + username;

		// PK = Primary Key => Object
		Map<Integer, Person> persons = new HashMap<>();

		Connection conn = null;
		try {
			// 1 connect to the database
			conn = DriverManager.getConnection(url, username, password);

			// 2. Formulate your query
			String queryPerson = "select * from Person";
			PreparedStatement psPerson = conn.prepareStatement(queryPerson);
			// 2B. Execute your statement
			ResultSet rsPerson = psPerson.executeQuery();

			String queryEmail = "select * from Email where personId = ?";
			PreparedStatement psEmail = conn.prepareStatement(queryEmail);

			// rs.next():
			// 1. advances to the next record
			// 2. returns true if it points to a valid record, false if not
			while (rsPerson.next()) {
				int personId = rsPerson.getInt("personId");
				String lastName = rsPerson.getString("lastName");
				String firstName = rsPerson.getString("firstName");
				String dateOfBirthStr = rsPerson.getString("dateOfBirth");
				LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
				Person p = new Person(personId, lastName, firstName, dateOfBirth);
				persons.put(personId, p);

				// load up the emails for person with personId...
				// ? in queries are *parameters*
				// They can be set by specifying an index (starting at 1)
				// And providing a value...
				psEmail.setInt(1, personId);
				ResultSet rsEmail = psEmail.executeQuery();
				while (rsEmail.next()) {
					String address = rsEmail.getString("address");
					p.addEmail(address);
				}
				rsEmail.close();
			}

			// load up the emails from the database...
			// Option 1: load up all the people and THEN load emails
			// Option 2: have a big giant join statement...
			// Option 3: load as you go...

			// 4 close your resources: in reverse order!
			rsPerson.close();
			psEmail.close();
			psPerson.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return persons;
	}

}
