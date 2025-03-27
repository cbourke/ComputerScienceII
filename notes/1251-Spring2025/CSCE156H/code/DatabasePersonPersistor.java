package unl.soc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import unl.soc.Person;

public class DatabasePersonPersistor {

	private static final Logger LOGGER = LogManager.getLogger(DatabasePersonPersistor.class);

	static {
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);
		LOGGER.info("Started...");
	}

	/**
	 * Creates a record in the person (and email) tables for the given person.
	 *
	 * If such a record already exists (based on the UUID), this method has no
	 * effect (and returns the original person), returns a new copy of the given
	 * person with their <code>personId</code> set\ to the newly create record's PK.
	 *
	 * @param p
	 */
	public static Person persistPerson(Person p) {

		Person foo = DatabasePersonLoader.loadPerson(p.getUuid());
		if (foo != null) {
			return foo;
		}

		// 1. create a connection
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String user = "cbourke3";
		String password = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			LOGGER.error("ERROR: Bad password!", e);
			throw new RuntimeException(e);
		}

		String insertPersonQuery = "insert into Person (uuid,firstName,lastName) values (?, ?, ?)";
		String insertEmailQuery = "insert into Email (address, personId) values (?, ?)";
		PreparedStatement ps = null;
		Integer personId = null;
		try {
			ps = conn.prepareStatement(insertPersonQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getUuid().toString());
			ps.setString(2, p.getFirstName());
			ps.setString(3, p.getLastName());
			// insert, update and delete are all executeUpdate()
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			// we only expect one: advance to the first one
			keys.next();
			// pull the key value generated:
			personId = keys.getInt(1);
			keys.close();
			ps.close();

			ps = conn.prepareStatement(insertEmailQuery);
			ps.setInt(2, personId);
			for (String email : p.getEmails()) {
				ps.setString(1, email);
				ps.executeUpdate();
			}
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 4. close your resource(s)
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return new Person(p, personId);

	}

	public static void main(String args[]) {
		LOGGER.debug("inserting new record...");
		Person p = new Person(UUID.randomUUID(), "Seiya", "Suzuki");
		p.addEmail("24@cubs.com");
		p.addEmail("dh@cubs.com");
		p.addEmail("51@team_japan.com");
		DatabasePersonPersistor.persistPerson(p);
		LOGGER.info("successfully inserted new record...");

	}

}
