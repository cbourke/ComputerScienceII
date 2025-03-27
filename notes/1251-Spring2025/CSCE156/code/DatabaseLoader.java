package unl.soc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import unl.soc.Person;

public class DatabaseLoader {

	public static List<Person> loadPersons() {
		List<Person> persons = new ArrayList<>();

		// 1. open a connection to the database
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String login = "cbourke3";
		String password = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2a. formulate my query:
		String query = "select personId,uuid,firstName,lastName from Person";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				int personId = rs.getInt("personId");
				UUID uuid = UUID.fromString(rs.getString("uuid"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				Person person = new Person(personId, uuid, firstName, lastName);
				persons.add(person);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// load all the emails of the loaded person...
		String emailQuery = "select address from Email where personId = ?";
		try {
			ps = conn.prepareStatement(emailQuery);
			for (Person p : persons) {
				ps.setInt(1, p.getPersonId());
				rs = ps.executeQuery();
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

		// 4. close the connection and other resource(s)
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

	/**
	 * Loads the person (defined by the given <code>personId</code> from the
	 * database along with all their emails.
	 *
	 * @param personId
	 * @return
	 */
	public static Person loadPerson(int personId) {

		Person person = null;

		// 1. open a connection to the database
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String login = "cbourke3";
		String password = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2a. formulate my query:
		String query = """
					select uuid,firstName,lastName
					   from Person where personid = ?;
				""";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			// 2b. execute the statement
			rs = ps.executeQuery();
			// 3. process the results...
			// here we only expect at most one result
			// rs.next() advances to the next record and returns
			// true if it is valid
			// false if it is not
			if (rs.next()) {
				// valid record, process it.
				UUID uuid = UUID.fromString(rs.getString("uuid"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				person = new Person(personId, uuid, firstName, lastName);
			} else {
				// no valid record
				person = null;
				// alternatively: throw an exception (but nothing gets cleaned up)
				// throw new RuntimeException("No such person with personId = " + personId);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// load all the emails of the loaded person...
		String emailQuery = "select address from Email where personId = ?";
		try {
			ps = conn.prepareStatement(emailQuery);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String address = rs.getString("address");
				person.addEmail(address);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 4. close the connection and other resource(s)
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

		return person;

	}

	/**
	 * Loads the person (defined by the given <code>uuid</code> from the database
	 * along with all their emails.
	 *
	 * @param uuid
	 * @return
	 */
	public static Person loadPerson(UUID uuid) {

		Person person = null;

		// 1. open a connection to the database
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String login = "cbourke3";
		String password = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2a. formulate my query:
		String query = """
					select personId,firstName,lastName
					   from Person where uuid = ?;
				""";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, uuid.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				// valid record, process it.
				int personId = rs.getInt("personId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				person = new Person(personId, uuid, firstName, lastName);
			} else {
				// no valid record
				person = null;
				// alternatively: throw an exception (but nothing gets cleaned up)
				// throw new RuntimeException("No such person with personId = " + personId);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// load all the emails of the loaded person...
		if(person != null) {
			String emailQuery = "select address from Email where personId = ?";
			try {
				ps = conn.prepareStatement(emailQuery);
				ps.setInt(1, person.getPersonId());
				rs = ps.executeQuery();
				while (rs.next()) {
					String address = rs.getString("address");
					person.addEmail(address);
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		// 4. close the connection and other resource(s)
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

		return person;

	}

	public static void main(String args[]) {

		// pull a particular person (by some criteria) from the database
		// along with all their emails
		Person p = loadPerson(30);
		System.out.println(p);
		p = loadPerson(10);
		System.out.println(p);
		p = loadPerson(-10);
		System.out.println(p);

		p = loadPerson(UUID.fromString("9849a291-d1e0-4cbe-b11c-0d31ad94e147"));
		System.out.println(p);

		List<Person> persons = loadPersons();
		for(Person person : persons) {
			System.out.println(person);
		}
	}

}
