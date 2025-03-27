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

public class DatabasePersister {

	private static final Logger LOGGER = LogManager.getLogger(DatabasePersister.class);

	static {
		  Configurator.initialize(new DefaultConfiguration());
		  Configurator.setRootLevel(Level.ERROR);
		  LOGGER.info("Started...");
	}

	/**
	 * Insert a new record into the person (and maybe the Email) table(s) with the
	 * given person's data.
	 *
	 * However, if the person record exists (based on the UUID) this method has no
	 * effect and returns the person record from the database with the given UUID.
	 *
	 * If a new record is inserted, a new object is returned with the record's
	 * primary key set.
	 *
	 * @param p
	 */
	public static Person persistPerson(Person p) {

		// 0. check to see if the person exists, if so, return
		Person dbPerson = DatabaseLoader.loadPerson(p.getUuid());
		if (DatabaseLoader.loadPerson(p.getUuid()) != null) {
			LOGGER.warn("Person already exists");
			return dbPerson;
		}

		// 1. open a connection to the database
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String login = "cbourke3";
		String password = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			LOGGER.error("Bad password maybe? ");
			throw new RuntimeException(e);
		}

		String insertQuery = "insert into Person (uuid,firstName,lastName) values (?, ?, ?)";
		String insertEmailQuery = "insert into Email (address, personId) values (?, ?)";
		PreparedStatement ps = null;
		int personId;
		try {
			ps = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getUuid().toString());
			ps.setString(2, p.getFirstName());
			ps.setString(3, p.getLastName());
			// executeUpdate is for insert, delete, AND update
			ps.executeUpdate();
			// the insertion will generate a personId for you, but how
			// do we get it?
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
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

		// 4. close the connection and other resource(s)
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
//			if (conn != null && !conn.isClosed()) {
//				conn.close();
//			}
		} catch (SQLException e) {
			LOGGER.error("ERROR: SOMETHING HAPPENED!  DO SOMETHING!");
			throw new RuntimeException(e);
		}

		// the only difference between p and newDBPerson
		// is the primary key (personId)
		// not going to waste resources connecting ot the DB again
		// copy p into newDBPerson with the new personId set...
		Person newDBPerson = new Person(p, personId);
		return newDBPerson;

	}

	public static void main(String args[]) {
		LOGGER.debug("about to insert record...");
		Person p = new Person(null, UUID.randomUUID(), "Kyle", "Tucker");
		p.addEmail("ktucker@cubs.com");
		p.addEmail("ktucker@astros.com");
		p.addEmail("ktucker@mlb.com");
		DatabasePersister.persistPerson(p);
		LOGGER.info("successfully inserted records!");
//		Person kyle = new Person(null, UUID.randomUUID(), "Kyle", "Hendricks");
//		DatabasePersister.persistPerson(kyle);

	}

}
