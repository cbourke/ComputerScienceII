package uno.ece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDataSaver {

	/**
	 * Persists (saves) the given person data to the database.
	 * 
	 * TODO: what to do if the person already exists?  In fact,
	 * What do we mean by a "duplicate person" to begin with?
	 * 
	 * @param p
	 */
	public static void savePerson(Person p) {
		
		String serverUrl = "jdbc:mysql://cse-linux-01.unl.edu/cbourke3";
		String username = "cbourke3";
		String password = DatabaseInfo.PASSWORD;
		
		try {
			// 1. make your connection
			Connection conn = DriverManager.getConnection(serverUrl, username, password);
			
			String insertPersonQuery = """
				insert into Person (lastName,firstName,dateOfBirth) 
				values (?,?,?)""";
			PreparedStatement psPersonInsert = conn.prepareStatement(insertPersonQuery);
			psPersonInsert.setString(1, p.getLastName());
			psPersonInsert.setString(2, p.getFirstName());
			psPersonInsert.setString(3, p.getDateOfBirth().toString());
			psPersonInsert.executeUpdate();
			
			//TODO: we still need to handle the emails.
			//   problem: we still need to know what personId was *JUST* inserted/used
			
			psPersonInsert.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
