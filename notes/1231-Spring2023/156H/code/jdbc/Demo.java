package unl.soc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

public class Demo {

	public static void main(String[] args) {
		
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);

		
		DatabaseDataLoader dl = new DatabaseDataLoader();
		
		List<Person> people = dl.loadPersons();
		
		for(Person p : people) {
			System.out.println(p);
		}
		
		Person p = new Person("David", "Ross", LocalDate.of(2023, 3, 30));
		p.addEmail("go@cubs.com");
		p.addEmail("yay@cubbies.com");
		//TODO: put p into the database: "saving" or "persisting"
		DataSaver.savePerson(p);
		
	}

}
