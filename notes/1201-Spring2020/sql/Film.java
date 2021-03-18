package unl.cse.film;

import java.time.LocalDate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Film {

	private int filmId;
	private String title;
	private Director director;

	public Film(int filmId, String title, Director director) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.director = director;
	}
	
	public int getFilmId() {
		return filmId;
	}

	public String getTitle() {
		return title;
	}

	public Director getDirector() {
		return director;
	}

	public String toString() {
		return title + " directed by " + director;
	}
	
	public static List<Film> getAllFilms() {

		List<Film> films = new ArrayList<>();

		//String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cbourke";
		String password = "Just4156";

		//String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
		// 1. Load the JDBC Driver
//		try {
//			Class.forName(DRIVER_CLASS).newInstance();
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		}

		// 2. Create a connection to our database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		// 3. Create/prepare your query
		String query = "SELECT " +
				       "  filmId, title, f.directorId AS directorId, firstName, lastName " + 
				       "  FROM Film f " + 
				       "  JOIN Director d " + 
				       "  ON f.directorId = d.directorId";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 3a: prepare the query
			ps = conn.prepareStatement(query);
			// 3b: exeucte your query
			rs = ps.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("filmId");
				String title = rs.getString("title");
				int directorId = rs.getInt("directorId");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				Director d = new Director(directorId, firstName, lastName);				
				Film f = new Film(filmId, title, d);
				films.add(f);
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

		return films;
	}
	
	public static void insertFilm(Film f) {
		
		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cbourke";
		String password = "Just4156";

		// 1. Load the JDBC Driver
//		try {
//			Class.forName(DRIVER_CLASS).newInstance();
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		}

		// 2. Create a connection to our database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		
		//3. create query:
		String query = "INSERT INTO Film (title, eidr, directorId) values (?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, f.title);
			ps.setString(2, "foobar'3333");
			ps.setInt(3, f.director.getDirectorId());
			//execute your query:
			ps.executeUpdate();			
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		
		// 5. Close our resources (connection)
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}


}
