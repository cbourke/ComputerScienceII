package unl.cse.film;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Director {
	

	private int directorId;
	private String firstName;
	private String lastName;

	@Override
	public String toString() {
		return lastName + ", " + firstName;
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

	public Director(int directorId, String firstName, String lastName) {
		super();
		this.directorId = directorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public static List<Director> getAllDirectors() {
		

		List<Director> directors = new ArrayList<>();

		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cbourke";
		String password = "";

		// 1. Load the JDBC Driver
		try {
			//Java < 9:
			Class.forName(DRIVER_CLASS).newInstance();
			//Java 9+:
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 

		// 2. Create a connection to our database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException sqle) {
			//TODO: log information here!
			throw new RuntimeException(sqle);
		}

		// 3. Create/prepare your query
		String query = "SELECT directorId, firstName, lastName FROM Director";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 3a: prepare the query
			ps = conn.prepareStatement(query);
			// 3b: exeucte your query
			rs = ps.executeQuery();
			while (rs.next()) {
				int directorId = rs.getInt("directorId");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				Director d = new Director(directorId, firstName, lastName);
				directors.add(d);
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		// 5. Close our resources (connection)
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
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return directors;
	}

	public static void main(String args[]) {
		
		List<Director> directors = getAllDirectors();
		for (Director d : directors) {
			System.out.println(d);
		}
	}
}
