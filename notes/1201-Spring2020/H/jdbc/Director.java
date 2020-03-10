package unl.cse.film;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Director {
	
	private int directorId;
	private String firstName;
	private String lastName;

	public Director(int directorId, String firstName, String lastName) {
		super();
		this.directorId = directorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getDirectorId() {
		return directorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	@Override
	public String toString() {
		return lastName + ", " + firstName;
	}
	

	
	public static void addNewDirector(String firstName, String lastName) {

		//1. Load the JDBC Driver (maybe optional)
		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
		try {
			//Java <9:
			//Class.forName(DRIVER_CLASS).newInstance();
			//Java 9+: 
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		//2. Create a connection to our database
		Connection conn = null;
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "Just4156";
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		//3. Create/prepare your query a. Prepare the query b. Execute your query
		String query = "insert into Director (lastName,firstName) values (?,?)";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, lastName);
			ps.setString(2, firstName);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		//5. Clean up
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	
	
	
	

}
