package unl.soc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import soc.unl.Person;

public class DatabaseLoader implements DataLoader {
	
	private static final Logger LOGGER = LogManager.getLogger(DatabaseLoader.class);

	public Person loadPersonById(int personId) {
		Person p = null;
		
		LOGGER.info("loading person with personId = " + personId);
		
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CfdsS2";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);

			String query = "select firstName,lastName,dateOfBirth from Person where personId = ?";
			PreparedStatement ps = conn.prepareCall(query);
			ps.setInt(1, personId);
			ResultSet rs = ps.executeQuery();
			//Process the result set
			if(rs.next()) {
				//process the *current* record
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				LocalDate dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));
				p = new Person(personId, firstName, lastName, dateOfBirth);
			}
			if(rs.next()) {
				throw new IllegalStateException("Some DBA screwed with our schema, shame on them");
			}
			
			rs.close();
			ps.close();
			
			//TODO: load up the emails of the loaded person...
			if(p != null) {
				query = "select address from Email where personId = ?";				
				ps = conn.prepareStatement(query);
				ps.setInt(1, personId);
				rs = ps.executeQuery();
				while(rs.next()) {
					String address = rs.getString("address");
					p.addEmail(address);
				}
				rs.close();
				ps.close();
			}
			
			conn.close();

		} catch (SQLException e) {
			LOGGER.error("Bad password or something", e);
			throw new RuntimeException(e);
		} 
		
		return p;
	}
	
	public List<Person> loadPersons() {
		List<Person> persons = new ArrayList<>();
		

		LOGGER.info("Loading up everyone...");
		// TODO: connect to the database and pull person data...
		String url = "jdbc:mysql://cse.unl.edu/cbourke?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "cbourke";
		String password = "CS2";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);

			//TODO: the rest of the program...
			String query = "select * from Person";
			PreparedStatement ps = conn.prepareCall(query);
			ResultSet rs = ps.executeQuery();
			//Process the result set
			while(rs.next()) {
				//process the *current* record
				int personId = rs.getInt("personId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				LocalDate dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));
				Person p = new Person(personId, firstName, lastName, dateOfBirth);
				persons.add(p);
			}
			
			rs.close();
			ps.close();
			
			//load up the emails of each person...
			for(Person p : persons) {
				
			}
			
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		LOGGER.info("Done loading everyone..");

		return persons;
	}
	
}
