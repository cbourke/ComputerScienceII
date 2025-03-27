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

public class DatabasePersonLoader {

	/**
	 * Loads the person data (along with the email data) for the person with the
	 * given UUID.
	 *
	 * Returns <code>null</code> if no such record exists.
	 *
	 * @param uuid
	 * @return
	 */
	public static Person loadPerson(UUID uuid) {

		Person p = null;

		// 1. create a connection
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String user = "cbourke3";
		String password = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2. formulate your query
		String personQuery = "select personId,firstName,lastName from Person where uuid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 2a. prepare your query
			ps = conn.prepareStatement(personQuery);
			ps.setString(1, uuid.toString());
			// 2b. execute query
			rs = ps.executeQuery();
			// 3. process the results
			if (rs.next()) {
				// right now rs points to the current record
				int personId = rs.getInt("personId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				p = new Person(personId, uuid, firstName, lastName);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 3c. Formluate a query to pull the emails for each person...
		if (p != null) {
			String emailQuery = "select address from Email where personId = ?;";
			try {
				ps = conn.prepareStatement(emailQuery);
				ps.setInt(1, p.getPersonId());
				rs = ps.executeQuery();
				while (rs.next()) {
					String email = rs.getString("address");
					p.addEmail(email);
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		// 4. close your resource(s)
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

	public static Person loadPerson(int personId) {
		Person p = null;

		// 1. create a connection
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String user = "cbourke3";
		String password = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2. formulate your query
		String personQuery = "select uuid,firstName,lastName from Person where personId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 2a. prepare your query
			ps = conn.prepareStatement(personQuery);
			ps.setInt(1, personId);
			// 2b. execute query
			rs = ps.executeQuery();
			// 3. process the results
			if (rs.next()) {
				// right now rs points to the current record
				UUID uuid = UUID.fromString(rs.getString("uuid"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				p = new Person(personId, uuid, firstName, lastName);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 3c. Formluate a query to pull the emails for each person...
		if (p != null) {
			String emailQuery = "select address from Email where personId = ?;";
			try {
				ps = conn.prepareStatement(emailQuery);
				ps.setInt(1, p.getPersonId());
				rs = ps.executeQuery();
				while (rs.next()) {
					String email = rs.getString("address");
					p.addEmail(email);
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		// 4. close your resource(s)
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

	public static List<Person> loadPersons() {
		List<Person> persons = new ArrayList<>();

		// 1. create a connection
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String user = "cbourke3";
		String password = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2. formulate your query
		String personQuery = "select personId,uuid,firstName,lastName from Person;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 2a. prepare your query
			ps = conn.prepareStatement(personQuery);
			// 2b. execute query
			rs = ps.executeQuery();
			// 3. process the results
			while (rs.next()) {
				// right now rs points to the current record
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

		// 3c. Formluate a query to pull the emails for each person...
		String emailQuery = "select address from Email where personId = ?;";
		try {
			ps = conn.prepareStatement(emailQuery);
			for (Person person : persons) {
				ps.setInt(1, person.getPersonId());
				rs = ps.executeQuery();
				while (rs.next()) {
					String email = rs.getString("address");
					person.addEmail(email);
				}
				rs.close();
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 4. close your resource(s)
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

	public static void main(String args[]) {

		List<Person> everyone = loadPersons();
		for (Person p : everyone) {
			System.out.println(p);
		}

		UUID uuidCraig = UUID.fromString("9849a291-d1e0-4cbe-b11c-0d31ad94e147");
		Person craig = DatabasePersonLoader.loadPerson(uuidCraig);
		System.out.println(craig);

		Person kyle = DatabasePersonLoader.loadPerson(34);
		System.out.println(kyle);

	}

}
