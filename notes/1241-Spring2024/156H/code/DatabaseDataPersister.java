package unl.soc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDataPersister {
	
	public static void insertPerson(Person p) {
		String username = "cbourke3";
		String password = DatabaseInfo.PASSWORD;
		String url = "jdbc:mysql://cse-linux-01.unl.edu/" + username;

		Connection conn = null;
		try {
			// 1 connect to the database
			conn = DriverManager.getConnection(url, username, password);
			
			// formulate your query 
			String insertPersonQuery = "insert into Person (lastName,firstName,dateOfBirth) values (?,?,?);";
			PreparedStatement psInsertPerson = conn.prepareStatement(insertPersonQuery, Statement.RETURN_GENERATED_KEYS);
			psInsertPerson.setString(1, p.getLastName());
			psInsertPerson.setString(2, p.getFirstName());
			psInsertPerson.setString(3, p.getDateOfBirth().toString());
			psInsertPerson.executeUpdate();
			
			
			//hey psInsertPerson: what keys did you just generate when you inserted
			//  the record?
			ResultSet keys = psInsertPerson.getGeneratedKeys();
			//advance to the next (and only) key:
			keys.next();
			//retrieve the key value (the first (and only) column)
			int personId = keys.getInt(1);
			
			
			
			//now insert the emails...
			String insertEmailQuery = "insert into Email (personId,address) values (?,?)";
			PreparedStatement psInsertEmail = conn.prepareStatement(insertEmailQuery);
			psInsertEmail.setInt(1, personId);
			for(String email : p.getEmails()) {
				psInsertEmail.setString(2, email);
				psInsertEmail.executeUpdate();
			}
			psInsertEmail.close();

			psInsertPerson.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
