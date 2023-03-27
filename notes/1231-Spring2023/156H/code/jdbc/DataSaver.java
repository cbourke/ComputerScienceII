package unl.soc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSaver {
	
	public static void savePerson(Person p) {
		// TODO: load up the person data...
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CS2";

		// 1. make your connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			
			String query = """
					insert into Person 
					  (firstName,lastName,dateOfBirth) values
					  (?,?,?)
					""";
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getFirstName());
			ps.setString(2, p.getLastName());
			ps.setString(3, p.getDateOfBirth().toString());
			
			//execute the query
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			//advance the result set to the first (and only) result:
			keys.next();
			
			int personId = keys.getInt(1);
			
			ps.close();
			
			//TODO: now insert their email addresses...
			query = "insert into Email (address,personId) values (?,?)";
			ps = conn.prepareStatement(query);
			for(String email : p.getEmails()) {
				ps.setString(1, email);
				ps.setInt(2, personId);
				ps.executeUpdate();
			}
			ps.close();
			

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
