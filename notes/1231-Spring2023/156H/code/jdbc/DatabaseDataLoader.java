package unl.soc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseDataLoader implements DataLoader {

	private static final Logger LOGGER = LogManager.getLogger(DatabaseDataLoader.class);
	
	
	/**
	 * Loads the <code>Person</code> object from the database
	 * identified by the given <code>personId</code>
	 * 
	 * If no such record exists returns <code>null</code>
	 * 
	 * @param personId
	 * @return
	 */
	public Person loadPersonById(int personId) {
		//TODO: implement
		return null;
	}

	private void loadEmails(Person p) {

		// TODO: load up the person data...
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CS2";

		// 1. make your connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);

			// 2. formulate your query and prepare it...
			// sanitize your queries...
			String query = "select address from Email where personId = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, p.getPersonId());
			ResultSet rs = ps.executeQuery();
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

	public List<Person> loadPersons() {
		
		LOGGER.info("Loading every Person records...");

		List<Person> people = new ArrayList<>();

		// TODO: load up the person data...
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CSfdsa2";

		// 1. make your connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);

			// create query
			String query = """
					select personId,
					       firstName,
					       lastName,
					       dateOfBirth
					from Person
					""";
			// prepare the query
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			// process the data...
			while (rs.next()) {
				int personId = rs.getInt("personId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				LocalDate dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));
				Person p = new Person(personId, firstName, lastName, dateOfBirth);
				loadEmails(p);
				people.add(p);

			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			LOGGER.error("something happened", e);
			throw new RuntimeException(e);
		}
		LOGGER.info("Done loading every Person record...");

		return people;
	}

}
