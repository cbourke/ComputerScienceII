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


```java
package unl.cse.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadData {
	
	public static Director getDirectorById(int directorId) {

		Director d = null;
		
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cbourke";
		String password = "Just4156";

		// 1. Connect to your database:
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2. pull data on this particular director:
		// 2a: query your *parameterized* query, using ? for each parameter
		String query = "select firstName, lastName from Director where directorId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//2a: prepare your query
			ps = conn.prepareStatement(query);
			//2b: set your parameter(s):
			ps.setInt(1, directorId);
			//2c: execute your query
			rs = ps.executeQuery();
			//2d: process the result(s)
			if(rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				d = new Director(directorId, firstName, lastName);
			} else {
				//throw new IllegalStateException("No such director with id = " + directorId);
				//or do nothing
				d = null;
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
		List<Film> results = new ArrayList<Film>();

		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cbourke";
		String password = "Just4156";

//		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
//		//0. Load the JDBC Driver:
//		try {
//			Class.forName(DRIVER_CLASS).newInstance();
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		}

		// 1. Connect to your database:
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 2. pull data on all films:
		String query = "select filmId, title, directorId from Film;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//2a: prepare your query
			ps = conn.prepareStatement(query);
			//2b: execute your query
			rs = ps.executeQuery();
			//3: if it is a select statement, process the results
			while(rs.next()) {
				//get the column values in this record:
				int filmId = rs.getInt("filmId");
				String title = rs.getString("title");
				int directorId = rs.getInt("directorId");
				Director d = getDirectorById(directorId);
				Film f = new Film(filmId, title, d);
				results.add(f);
			}	
			//4: clean up: release your resources
			rs.close();
			ps.close();
			conn.close();			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return results;
	}

	public static void main(String args[]) {

//		Director d = getDirectorById(1);
//		System.out.println(d);
		List<Film> films = getAllFilms();
		for (Film f : films) {
			System.out.println(f);
		}
	}
}

```

```text







```