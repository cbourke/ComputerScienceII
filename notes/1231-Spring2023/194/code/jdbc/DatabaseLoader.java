package uno.ece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uno.ece.entities.Person;

public class DatabaseLoader implements DataLoader {

	
	public Person loadPersonById(int personId) {
		
		Person p = null;
		// 1. Create a connection to your database:
		// You need a URL, user name and a password
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CS2";

		Connection conn = null;

		// 2. Create an SQL query to submit to the database
		String query = "select * from Person where personId = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			conn = DriverManager.getConnection(url, username, password);
			// 2a. Prepare your statement
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			// 2b. Execute your query:
			rs = ps.executeQuery();
			// 3. Process the results:
			if (rs.next()) {
				// pull the individual fields/columns from the current
				// record
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String dateOfBirthStr = rs.getString("dateOfBirth");
				LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
				p = new Person(personId, firstName, lastName, dateOfBirth);
				loadEmails(p);
			} else {
				throw new IllegalStateException("You asked for something that does not exist");
			}
			
//			if(rs.next()) {
//				throw new REallyIllegalStateException("Your DBAs changed something and are idiots...");
//			}

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return p;

	}
	
	private void loadEmails(Person p) {
		
		// 1. Create a connection to your database:
		// You need a URL, user name and a password
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CS2";

		Connection conn = null;

		// 2. Create an SQL query to submit to the database
		String query = "select * from Email where personId = ?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			// 2a. Prepare your statement
			ps = conn.prepareStatement(query);
			ps.setInt(1, p.getPersonId());
			// 2b. Execute your query:
			rs = ps.executeQuery();
			// 3. Process the results:
			while (rs.next()) {
				String address = rs.getString("address");
				p.addEmail(address);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public List<Person> loadPersons() {

		List<Person> everyone = new ArrayList<>();

		// 1. Create a connection to your database:
		// You need a URL, user name and a password
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CS2";

		Connection conn = null;

		// 2. Create an SQL query to submit to the database
		String query = "select * from Person";
		PreparedStatement ps;
		ResultSet rs;
		try {
			conn = DriverManager.getConnection(url, username, password);
			// 2a. Prepare your statement
			ps = conn.prepareStatement(query);
			// 2b. Execute your query:
			rs = ps.executeQuery();
			// 3. Process the results:
			while (rs.next()) {
				// pull the individual fields/columns from the current
				// record
				int personId = rs.getInt("personId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String dateOfBirthStr = rs.getString("dateOfBirth");
				LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
				Person p = new Person(personId, firstName, lastName, dateOfBirth);
				everyone.add(p);
				loadEmails(p);

			}

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


		return everyone;
	}

}
