package unl.soc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import soc.unl.Person;

public class DataSaver {

	public static void savePerson(Person p) {
		//TODO: add this person to the database...
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CS2";
		
		String query = """
				insert into Person 
				  (firstName,lastName,dateOfBirth) values 
				  (?,?,?)
				""";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getFirstName());
			ps.setString(2, p.getLastName());
			ps.setString(3, p.getDateOfBirth().toString());
			//inserts the new record:
			//TODO: fix this statement so that the newly generated
			// primary key value is returned so we can use it below...
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			//advance the ResultSet to the first element:
			keys.next();
			int personId = keys.getInt(1);

			ps.close();
			
			//TODO: insert the person's emails...
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
