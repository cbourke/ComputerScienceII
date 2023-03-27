package unl.soc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;


public class ConnectionPool {

	private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    
	private static final int capacity = 1;
	private static final BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(capacity);

	private static final String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String username = "cbourke";
	private static final String password = "CS2";

	public static void initialize() {

		LOGGER.info("Initializing " + capacity + " connections...");		
		for (int i = 0; i < capacity; i++) {
			Connection conn = null;
			try {
				LOGGER.info("Initializing connection " + (i+1) + "...");		
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new RuntimeException(e);
			}
			connections.offer(conn);
		}
	}
	
	public static Connection getConnection() {
		return connections.poll();
	}

	public static void putConnection(Connection conn) {
		connections.offer(conn);
	}

	public static void shutdown() {
		
		while(!connections.isEmpty()) {
			LOGGER.info("Shutting down connection...");
			Connection c = connections.poll();
			try {
				c.close();
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new RuntimeException(e);
			}
		}
	}

}
