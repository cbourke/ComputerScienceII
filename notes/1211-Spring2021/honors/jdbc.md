# Database Connectivity
## CSCE 156 - Spring 2021

### Overview

* Goal: programmatically connect to a database and process or persist (save) data
* Most languages have some support for *database connectivity*
* Java: JDBC = Java DataBase Connectivity API
* API = Application Programmer Interface
* Perfect illustration of *Dependency Inversion* 
* Don't program toward a specific database, but a generic interface
* Vendors (Oracle, IBM, etc.) provide a *driver* library that conforms to the API
* JDBC provides:
  * `Connection`
  * `PreparedStatement`
  * `ResultSet`
* ORMs (Object-Relational Mappings) systems also exist (JPA, jOOQ)

### Demonstration

* Setup: you download the driver (Connectror/J) from Oracle
* Connect to the films database and:
  * Process Film/Director data
  * Persist Film/Director data

### Process:

0. Load the JDBC Driver (archaic)
1. Make a connection to your database: url, user name, password
2. Create/prepare your query
  a. Prepare the query
  b. Execute your query
  c. Process your results
3. Clean up: close your resources!

## Best Practices

### Dealing with `SQLExceptions`

* Unfortunately most operations throw a checked `SQLException` that *has* to be caught and dealt with
* Just catch and release: rethrow the exception as a `RuntimeExcpeion`
* You *may* even want to log the error *then* rethrow it
* If you do do logging, then use a proper library; `System.out` or `System.err` are not proper logging libraries!
* Suggestion: use log4j version 2

### Always Use `PreparedStatements`

* In general, strings can contain anything including SQL code!
* Without sanitizing your code, you are susceptible to an *SQL Injection* attack: a user *may* be able to execute arbitrary SQL code on your database!
* `PreparedStatement`s in Java *sanitize* the inputs for you, ensuring that no SQL injection is possible
* Never use anything else!
* Its just simpler to use on method to connect to a database 

### Avoid using the star operator

* Using the SQL star operator brings over all data even if you don't use it
* You end up wasting bandwidth and making the connection way slower!
* It also protects you against changes in the database

LoadData.java:
```java
package unl.cse.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;


public class LoadData {
	
	private static final Logger LOGGER = LogManager.getLogger(LoadData.class);

	static {
		//configure the logger: 
		Configurator.initialize(new DefaultConfiguration());
	    Configurator.setRootLevel(Level.INFO);
	}
	
	public static Director getDirectorByName(Director d) {
		Director result = null;
		
		//1. Make your connection:
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cbourke";
		String password = "Just4156";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		//2. formulate your query...
		String query = "select directorId from Director where firstName = ? and lastName = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			//set the parameters:
			ps.setString(1, d.getFirstName());
			ps.setString(2, d.getLastName());
			//execute your query:
			rs = ps.executeQuery();
			if(rs.next()) {
				int directorId = rs.getInt("directorId");
				result = new Director(directorId, d.getFirstName(), d.getLastName());
			} else {
				result = null;
				LOGGER.warn("Cannot find director with name = " + d.getLastName() + ", " + d.getFirstName());
				
				//throw new IllegalStateException("Cannot find director with id = " + directorId);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return result;
	}
	
	public static Director getDirectorById(int directorId) {

		Director d = null;
		
		//1. Make your connection:
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cbourke";
		String password = "Just4156";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		//2. formulate your query...
		String query = "select lastName, firstName from Director where directorId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			//set the parameters:
			ps.setInt(1, directorId);
			//execute your query:
			rs = ps.executeQuery();
			if(rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				d = new Director(directorId, firstName, lastName);
			} else {
				d = null;
				LOGGER.warn("Cannot find director with id = " + directorId);
				//throw new IllegalStateException("Cannot find director with id = " + directorId);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return d;
	}
	
	public static List<Film> getAllFilms() {
		
		List<Film> results = new ArrayList<>();
		
//		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
//		//0. Load the JDBC Driver
//		try {
//			Class.forName(DRIVER_CLASS).newInstance();
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		}
		
		//1. Make your connection:
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cbourke";
		String password = "Just4156";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		//2. formulate your query...
		String query = "select filmId, title, directorId from Film;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			//execute your query:
			rs = ps.executeQuery();
			//process results:
			while(rs.next()) {
				int filmId = rs.getInt("filmId");
				String title = rs.getString("title");
				int directorId = rs.getInt("directorId");
				Director d = getDirectorById(directorId);
				Film f = new Film(filmId, title, d);
				results.add(f);
			}
			//clean up
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		
		return results;
		
	}
	
	public static void main(String args[]) {

	    LOGGER.info("Starting...");
	    LOGGER.error("somthing happened!");
		Director d = getDirectorById(-42);
		System.out.println(d);
		
		//pull all films...
		List<Film> films = getAllFilms();
		for(Film f : films) {
			System.out.println(f);
		}
	}
}
```

SaveData.java:
```java
package unl.cse.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveData {

	/**
	 * This method returns the Director with the given firstName/lastName (in the
	 * given director d) loaded from the database. If no such record exists, it
	 * persists it to the database and then loads it.
	 * 
	 * @param d
	 */
	public static Director getOrInsertDirector(Director d) {

		Director existingDirector = LoadData.getDirectorByName(d);
		if (existingDirector == null) {
			Director newRecord = null;
			// 1. Make your connection:
			String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "cbourke";
			String password = "Just4156";

			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			String query = "insert into Director (firstName, lastName) values (?, ?);";
			PreparedStatement ps = null;
			ResultSet keys = null;
			try {
				ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, d.getFirstName());
				ps.setString(2, d.getLastName());
				ps.executeUpdate();
				//ask the database what PK was just generated by
				//the previous statement
				keys = ps.getGeneratedKeys();
				if(!keys.next()) {
					throw new IllegalStateException("Wow, something went wrong: the record appeared to be inserted, but I didn't get my key!");
				} else {
					int directorId = keys.getInt(1);
					newRecord = new Director(directorId, d.getFirstName(), d.getLastName());
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if(keys != null && !keys.isClosed()) {
						keys.close();
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
			}
			//load the new record...
			//option A: reload it: Director newRecord = LoadData.getDirectorByName(d);
			return newRecord;
		} else {
			return existingDirector;
		}
	}

	public static void insertFilm(Film f) {
		
		Director d = getOrInsertDirector(f.getDirector());

		// 1. Make your connection:
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cbourke";
		String password = "Just4156";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "insert into Film (title, eidr, directorId) values (?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, f.getTitle());
			ps.setString(2, "foo35345-1");
			ps.setInt(3, d.getDirectorId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String args[]) {

		Director d = new Director("Cate", "Shortland");
		Film f = new Film("Black Widow 2", d);
		insertFilm(f);
	}
}
```

```text






```