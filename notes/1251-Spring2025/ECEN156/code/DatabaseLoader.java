package uno.ece.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uno.ece.Person;

public class DatabaseLoader {

	private static final Logger LOGGER = LogManager.getLogger(DatabaseLoader.class);

	/**
	 * Loads the given person's emails from the database and
	 * adds them to the person object.
	 *
	 * @param p
	 */
	private static void loadEmails(Person p) {
		if(p == null) {
			return;
		}

		// 1. Open a database connection
		//TODO: move this chunk to a connection factory
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String user = "cbourke3";
		String pass = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		PreparedStatement ps = null;
		ResultSet rs = null;

		// load up the email records...
		String emailQuery = "select address from Email where personId = ?";
		try {
			ps = conn.prepareStatement(emailQuery);
			ps.setInt(1, p.getPersonId());
			rs = ps.executeQuery();
			// process the email results...
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

	/**
	 * Loads data (including emails) for the given person (defined
	 * by <code>personId</code> and returns the result.  If no such
	 * person exists in the database, returns <code>null</code>.
	 *
	 * @param personId
	 * @return
	 */
	public static Person loadPerson(int personId) {

		Person p = null;

		// 1. Open a database connection
		//TODO: move this chunk to a connection factory
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String user = "cbourke3";
		String pass = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2. Formulate the query
		String query = "select uuid,firstName,lastName from Person where personId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 2a. Prepare the query
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			// 2b. Execute the query
			rs = ps.executeQuery();
			// 3. Process the results
			if (rs.next()) {
				UUID uuid = UUID.fromString(rs.getString("uuid"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				p = new Person(personId, uuid, firstName, lastName);
			} else {
				//throw new IllegalStateException("No such person with personId = " + personId);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		loadEmails(p);

		// 4. Clean up
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return p;
	}

	/**
	 * Loads data (including emails) for the given person (defined
	 * by <code>uuid</code> and returns the result.  If no such
	 * person exists in the database, returns <code>null</code>.
	 *
	 * @param uuid
	 * @return
	 */
	public static Person loadPerson(UUID uuid) {

		Person p = null;

		// 1. Open a database connection
		//TODO: move this chunk to a connection factory
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String user = "cbourke3";
		String pass = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2. Formulate the query
		String query = "select personId,firstName,lastName from Person where uuid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 2a. Prepare the query
			ps = conn.prepareStatement(query);
			ps.setString(1, uuid.toString());
			// 2b. Execute the query
			rs = ps.executeQuery();
			// 3. Process the results
			if (rs.next()) {
				int personId = rs.getInt("personId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				p = new Person(personId, uuid, firstName, lastName);
			} else {
				//throw new IllegalStateException("No such person with personId = " + personId);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// load up the email records...
		loadEmails(p);

		// 4. Clean up
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return p;
	}

	/**
	 * Loads all person records from the database along with their email records.
	 *
	 * @return
	 */
	public static List<Person> loadPersons() {

		List<Person> persons = new ArrayList<>();

		// 1. Open a database connection
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String user = "cbourke3";
		String pass = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2. Formulate the query
		String query = "select personId,uuid,firstName,lastName from Person p";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 2a. Prepare the query
			ps = conn.prepareStatement(query);
			// 2b. Execute the query
			rs = ps.executeQuery();
			// 3. Process the results
			while (rs.next()) {
				int personId = rs.getInt("personId");
				UUID uuid = UUID.fromString(rs.getString("uuid"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				Person p = new Person(personId, uuid, firstName, lastName);
				persons.add(p);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// load up the email records...
		String emailQuery = "select address from Email where personId = ?";
		try {
			ps = conn.prepareStatement(emailQuery);
			for (Person p : persons) {
				ps.setInt(1, p.getPersonId());
				rs = ps.executeQuery();
				// process the email results...
				while (rs.next()) {
					String address = rs.getString("address");
					p.addEmail(address);
				}
				rs.close();
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 4. Clean up
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return persons;

	}

	public static void main(String[] args) {

		List<Person> persons = loadPersons();
		for (Person p : persons) {
			System.out.println(p);
		}

		Person craig = loadPerson(30);
		System.out.println(craig);
		Person kyle = loadPerson(42);
		System.out.println(kyle);
		Person john = loadPerson(UUID.fromString("38a53d55-72ba-4adf-bc34-d079684a2637"));
		System.out.println(john);
	}

}
