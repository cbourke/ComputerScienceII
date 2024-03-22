package unl.soc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {

	public static void main(String[] args) {

		Map<Integer, Person> persons = DatabaseDataLoader.loadPersons();

		System.out.println(persons.values());
		
		//want to load *just* LeBron:
		Person lebron = DatabaseDataLoader.loadPerson(3);
		
		System.out.println(lebron);
		
		Person p = new Person("Rhule", "Matt", LocalDate.of(1970, 01, 01));
		p.addEmail("matt@unl.edu");
		p.addEmail("matt@nil_money.com");
		DatabaseDataPersister.insertPerson(p);
	}

}
