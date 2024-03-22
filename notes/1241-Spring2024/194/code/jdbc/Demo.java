package uno.ece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Chris Bourke Date:
 * 
 * TODO: add documentation
 */
public class Demo {

	public static void main(String args[]) {

		Map<Integer, Person> people = DatabaseDataLoader.personsMap;
		
		Person lebron = DatabaseDataLoader.loadPerson(3);
		System.out.println(lebron);
		
		Person ohtani = new Person("Ohtani", "Shohei", LocalDate.of(2002, 1, 1));
		DatabaseDataSaver.savePerson(ohtani);

	}

}
