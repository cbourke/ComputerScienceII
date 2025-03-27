package uno.ece.jdbc;

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

import uno.ece.Person;

public class DatabasePersister {

	private static final Logger LOGGER = LogManager.getLogger(DatabasePersister.class);

	static {
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.WARN);
		LOGGER.info("Started...");
	}

	/**
	 * Creates records in the database (person, email tables) for the given person
	 * object. If the person already exists (based on the UUID), this method has no
	 * effect.
	 *
	 * @param p
	 */
	public static Person persistPerson(Person p) {

		Person dbPerson = DatabaseLoader.loadPerson(p.getUuid());
		if (dbPerson != null) {
			LOGGER.info("Duplicate record: not entering into db...");
			return dbPerson;
		}

		// 1. Open a database connection
		// TODO: move this chunk to a connection factory
		String url = "jdbc:mysql://nuros.unl.edu/cbourke3";
		String user = "cbourke3";
		String pass = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new RuntimeException(e);
		}

		// insert into Person...
		String personInsertQuery = """
				insert into Person (uuid,firstName,lastName)
				  values (?, ?, ?);
				""";
		try {
			PreparedStatement ps = conn.prepareStatement(personInsertQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getUuid().toString());
			ps.setString(2, p.getFirstName());
			ps.setString(3, p.getLastName());
			// execute the query: insert/delete/update ALL use executeUpdate()
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			// only expecting one, so
			keys.next();
			int personId = keys.getInt(1);
			keys.close();
			ps.close();

			// person will eventually have a new PK personId = ??
			// insert into Email...
			String insertEmailQuery = "insert into Email (address, personId) values (?, ?)";
			PreparedStatement emailPs = conn.prepareStatement(insertEmailQuery);
			emailPs.setInt(2, personId);
			for (String email : p.getEmails()) {
				emailPs.setString(1, email);
				emailPs.executeUpdate();
			}
			emailPs.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 4. Clean up
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

		LOGGER.info("Record creation successful...");
		// return a new instance from the database
		return DatabaseLoader.loadPerson(p.getUuid());
	}

	public static void main(String args[]) {

		LOGGER.debug("starting main...");
		Person p = new Person(UUID.randomUUID(), "Matt", "Shaw");
		p.addEmail("shaw@cubs.com");
		p.addEmail("shaw_to_tokyo@mlb.com");
		LOGGER.info("Creating database record for " + p + "...");
		DatabasePersister.persistPerson(p);
		LOGGER.warn("ending program...");

//		Person p2 = new Person(UUID.fromString("a9214499-0d14-409f-bd99-047813fbffb3"), "Nico", "Hoerner");
//
//		DatabasePersister.persistPerson(p);
//		Person dbP2 = DatabasePersister.persistPerson(p2);
//		System.out.println(dbP2);

	}

}
