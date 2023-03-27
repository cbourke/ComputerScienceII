package uno.ece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import uno.ece.entities.Person;

public class DataSaver {

	/**
	 * Attempts to insert the given <code>Person</code> into the database.
	 * 
	 * If successful it will return the auto-generated primary key value of
	 * the new record.  If unsuccessful, it will return <code>null</code>
	 * @param p
	 * @return
	 */
	public static Integer insertPerson(Person p) {
		
		Integer insertedKeyValue = null;
		//0 error handling: check to see if the person exists already
		//  using *SOME* criteria 
//		Person pExists = DataLoader.loadPersonById(p.getPersonId());
//		if(pExists != null) {
//			//throw an exception
//			//return a dummy value		
//			return null;
//		}
		
		// 1. Create a connection to your database:
		// You need a URL, user name and a password
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CS2";

		Connection conn = null;

		// 2. Create an SQL query to submit to the database
		String query = """
				insert into Person (firstName,lastName,dateOfBirth)
				  values (?, ?, ?)
				""";
		PreparedStatement ps;
		try {
			conn = DriverManager.getConnection(url, username, password);
			// 2a. Prepare your statement
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getFirstName());
			ps.setString(2, p.getLastName());
			ps.setString(3, p.getDateOfBirth().toString());
			// 2b. Execute your query:
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			insertedKeyValue = keys.getInt(1);

			ps.close();
			
			//TODO: insert their emails...
			query = "insert into Email (address,personId) values (?,?)";
			ps = conn.prepareStatement(query);
			for(String email : p.getEmails()) {
				//insert the email...
				ps.setString(1, email);
				ps.setInt(2, insertedKeyValue);
				ps.executeUpdate();
			}
			
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return insertedKeyValue;
	}

}
