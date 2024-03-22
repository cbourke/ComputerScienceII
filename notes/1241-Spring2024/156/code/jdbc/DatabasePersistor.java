package unl.soc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePersistor {

	/**
	 * Persists (saves) the given person object to the database.
	 * 
	 * @param p
	 */
	public static void savePerson(Person p) {

		//TODO: basic validation
		//TODO: make sure the record doesn't already exist based on some criteria
		
		String username = "cbourke3";
		String password = DatabaseInfo.PASSWORD;
		String serverUrl = "jdbc:mysql://cse-linux-01.unl.edu/" + username;

		Connection conn;
		try {
			//1. make your connection
			conn = DriverManager.getConnection(serverUrl, username, password);
			
			//2. create an insert query:
			String insertPerson = "insert into Person (lastName,firstName,dateOfBirth) values (?, ?, ?);";
			PreparedStatement psInsertPerson = conn.prepareStatement(insertPerson, Statement.RETURN_GENERATED_KEYS);
			psInsertPerson.setString(1, p.getLastName());
			psInsertPerson.setString(2, p.getFirstName());
			psInsertPerson.setString(3, p.getDateOfBirth().toString());
			//3. execute the query
			//   because it is an insert/update/delete query, use:
			psInsertPerson.executeUpdate();
			
			//now that the person is in the database, make a query to load it....
			ResultSet keys = psInsertPerson.getGeneratedKeys();
			//you are only inserting one record, so we only expect on key:
			keys.next();
			int personId = keys.getInt(1);

			//now insert the email records...
			String insertEmail = "insert into Email (personId,address) values (?,?);";
			PreparedStatement psInsertEmail = conn.prepareStatement(insertEmail);
			for(String emailAddress : p.getEmails()) {
				psInsertEmail.setInt(1, personId);
				psInsertEmail.setString(2, emailAddress);
				psInsertEmail.executeUpdate();
			}			
			psInsertEmail.close();

			psInsertPerson.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		
	}

}
