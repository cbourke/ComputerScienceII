package unl.soc;

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
	

	public static Person loadPerson(int personId) {

		Person p = null;
		
		String username = "cbourke3";
		String password = DatabaseInfo.PASSWORD;
		String serverUrl = "jdbc:mysql://cse-linux-01.unl.edu/" + username;

		Connection conn;
		try {
			//1. make your connection
			conn = DriverManager.getConnection(serverUrl, username, password);

			// 2a. create and prepare your query
			String query = "select firstName as firstName, lastName as lastName, dateOfBirth as dateOfBirth from Person where personId = ?";
			PreparedStatement psPerson = conn.prepareStatement(query);
			psPerson.setInt(1, personId);
			ResultSet rsPerson = psPerson.executeQuery();
			
			while(rsPerson.next()) {
				String lastName = rsPerson.getString("lastName");
				String firstName = rsPerson.getString("firstName");
				String dateOfBirthStr = rsPerson.getString("dateOfBirth");
				LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
				p = new Person(personId, lastName, firstName, dateOfBirth);
				
				//? is a *parameter* in the query which can be set
				// by the prepared statement
				String queryEmail = "select address from Email where personId = ?"; 
				PreparedStatement psEmail = conn.prepareStatement(queryEmail);
				//you use 1-indexing (if you have multiple parameters
				// specify the variable value
				psEmail.setInt(1, personId);
				ResultSet rsEmail = psEmail.executeQuery();
				while(rsEmail.next()) {
					String address = rsEmail.getString("address");
					p.addEmail(address);
				}
				rsEmail.close();
				psEmail.close();
			}
			
			//4. close your resources...
			rsPerson.close();
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
		String serverUrl = "jdbc:mysql://cse-linux-01.unl.edu/" + username;

		// pull all data from the person table and print it out...
		Map<Integer, Person> everyone = new HashMap<>();

		Connection conn;
		try {
			//1. make your connection
			conn = DriverManager.getConnection(serverUrl, username, password);

			// 2a. create and prepare your query
			String query = "select * from Person;";
			PreparedStatement psPerson = conn.prepareStatement(query);
			ResultSet rsPerson = psPerson.executeQuery();
			
			// 2b. Process the Results
			//next advances to the next record and returns true if
			//  valid (points to a valid record), false if no more records
			//we also want their emails:
			// Option 1: use a join query
			// Option 2: load up each person, THEN separately load up each email for each person
			// Option 3: load as you go...
			while(rsPerson.next()) {
				int personId = rsPerson.getInt("personId");
				String lastName = rsPerson.getString("lastName");
				String firstName = rsPerson.getString("firstName");
				String dateOfBirthStr = rsPerson.getString("dateOfBirth");
				LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
				Person p = new Person(personId, lastName, firstName, dateOfBirth);
				everyone.put(personId, p);
				
				//? is a *parameter* in the query which can be set
				// by the prepared statement
				String queryEmail = "SELECT address FROM Email WHERE personId = ?"; 
				PreparedStatement psEmail = conn.prepareStatement(queryEmail);
				//you use 1-indexing (if you have multiple parameters
				// specify the variable value
				psEmail.setInt(1, personId);
				ResultSet rsEmail = psEmail.executeQuery();
				while(rsEmail.next()) {
					String address = rsEmail.getString("address");
					p.addEmail(address);
				}
				rsEmail.close();
				psEmail.close();
			}
			
			//4. close your resources...
			rsPerson.close();
			psPerson.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return everyone;
	}
}
