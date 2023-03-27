package unl.soc.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadWorker implements Runnable {

	private final int n = 100;
	private final int id;
	private static final Logger LOGGER = LogManager.getLogger(LoadWorker.class);

	public LoadWorker(int id) {
		this.id = id;
	}

	@Override
	public void run() {

		LOGGER.info("Thread Worker " + id + " starting (inserting " + n + " people...");
		String query = """
				insert into Person
				  (firstName,lastName,dateOfBirth) values
				  (?,?,?)
				""";
		try {
			for (int i = 0; i < n; i++) {
				Connection conn = ConnectionPool.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				LOGGER.info("Thread Worker " + id + " adding person " + (i+1) + "..."); 
				Person p = LoadDemo.randomPerson();

				ps.setString(1, p.getFirstName());
				ps.setString(2, p.getLastName());
				ps.setString(3, p.getDateOfBirth().toString());

				// execute the query
				ps.executeUpdate();
				ps.close();
				ConnectionPool.putConnection(conn);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new RuntimeException(e);
		}
		LOGGER.info("Done");

	}

}
