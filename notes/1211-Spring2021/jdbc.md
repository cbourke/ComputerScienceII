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

* Setup: download and include a Connector/J *driver* from oracle

#### Process:

0. MAY need to load a JDBC driver
1. Create a connection to your database: need user name, password, URL 
2. Create/prepare your query
  - prepare the query
  - execute the query
3. Process your results

## Best Practices

### Avoid the star operator

* Using the SQL `*` operator selects ALL column values and sends them over the wire, taking network bandwith!
* Don't waste bandwidth!
* If you are not going to use or need the data, don't query it
* Only query that which you need
* Cut down on redundancies: joins mean that redundant data is transmitted
* not using the `*` operator protects you from weird database changes 

### Security Issues

* For this course (only) are we storing the password in a Java source file
* This is actually (unfortunately) common
* For this course: use a password you do not care about!
* In practice: you don't store the password you either:
  * Define a "data source" or
  * You set it up to enter it ONCE without storing it
  
### Close Your Resources!

* Failure to close your resources wastes them
* You may *runout* of connections!
* make sure to close them in the proper order

### Dealing with `SQLExceptions`

* Unfortunately most operations throw a checked `SQLException` that *has* to be caught and dealt with
* Just catch and release: rethrow the exception as a `RuntimeExcpeion`
* You *may* even want to log the error *then* rethrow it
* If you do do logging, then use a proper library; `System.out` or `System.err` are not proper logging libraries!
* Solution: use a logging library such as log4j

### Always Use `PreparedStatements`

* In general, strings can contain anything including SQL code!
* Without sanitizing your code, you are susceptible to an *SQL Injection* attack: a user *may* be able to execute arbitrary SQL code on your database!
* `PreparedStatement`s in Java *sanitize* the inputs for you, ensuring that no SQL injection is possible
* Never use anything else!
* Its just simpler to use on method to connect to a database 

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
	
	public static Director getOrInsertDirector(Director d) {
		
		Director result = null;
		
		//1. if the director already exists, get it:
		Director existingDirector = LoadData.getDirectorByName(d);
		if(existingDirector == null) {
			//it does not exist, insert the new record
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
			try {
				//a. configure your prepared statement to return the generated keys:
				ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, d.getFirstName());
				ps.setString(2, d.getLastName());
				ps.executeUpdate();
				//what is the directorId (PK) value of the record I just inserted?
				//b. get the generated keys
				ResultSet keys = ps.getGeneratedKeys();
				//c. get the value of the first record: advance the result set, then get the result
				keys.next();				
				int directorId = keys.getInt(1);
				result = new Director(directorId, d.getFirstName(), d.getLastName());
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			//option B: get the inserted key values
			return result;
			//option A: return LoadData.getDirectorByName(d);
		} else {
			return existingDirector;
		}
		
	}
	

	public static void insertFilm(Film f) {
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
			ps.setString(2, "foo42");
			//get the director directly from the database:
			Director databaseDirector = getOrInsertDirector(f.getDirector());
			ps.setInt(3, databaseDirector.getDirectorId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String args[]) {

		Director d = new Director("Denis", "Villeneuve");
		Film f = new Film("Dune", d);
		SaveData.insertFilm(f);

	}
}


```

```text







```