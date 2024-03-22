package unl.soc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Demo {

	public static void main(String[] args) {
		
		//Map<Integer, Person> everyone = DatabaseDataLoader.loadPersons();
		
		Person lebron = DatabaseDataLoader.loadPerson(3);
		System.out.println(lebron);
		
		Person p = new Person("Alberts", "Trev", LocalDate.of(1970, 01, 01));
		p.addEmail("trev@unl.edu");
		p.addEmail("trev@tam.edu");
		DatabasePersistor.savePerson(p);
	}

}
