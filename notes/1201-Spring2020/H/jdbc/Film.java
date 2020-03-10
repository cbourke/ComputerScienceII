package unl.cse.film;

import java.time.LocalDate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Film {
	
	private static final Logger LOG = LogManager.getLogger(Film.class);

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
	
	public static List<Film> loadAllFilms() {
		
		LOG.info("loading all films...");
		List<Film> films = new ArrayList<>();

		//1. Load the JDBC Driver (maybe optional)
		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
		try {
			//Java <9:
			//Class.forName(DRIVER_CLASS).newInstance();
			//Java 9+: 
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			LOG.error("something bad happened!", e);
			throw new RuntimeException(e);
		}
		
		//2. Create a connection to our database
		Connection conn = null;
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "Justfds4156";
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			LOG.error("something bad happened!", e);
			throw new RuntimeException(e);
		}
		
		//3. Create/prepare your query a. Prepare the query b. Execute your query
		String query = "select "
				+ "       f.filmId,"
				+ "       f.title,"
				+ "       d.directorId,d.firstName,d.lastName from Film f                             " +
			           "  join Director "
			           + "d on f.directorId = d.directorId;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			//4. Process the result set (for select statements)
			while(rs.next()) {
				int directorId = rs.getInt("directorId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String title = rs.getString("title");
				int filmId = rs.getInt("filmId");
				Director d = new Director(directorId, firstName, lastName);
				Film f = new Film(filmId, title, d);
				films.add(f);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		//5. Clean up
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return films;
	}

}
