package uno.ece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uno.ece.entities.Person;

public class Demo {

	public static void main(String[] args) {

		DataLoader dl = new DatabaseLoader();

		List<Person> people = dl.loadPersons();

		for(Person p : people) {
			System.out.println(p);
		}
		
//		Person p1 = dl.loadPersonById(42);
//		System.out.println(p1);
		
		Person p = new Person(0, "Jane", "Smith", LocalDate.of(2023, 03, 22));
		p.addEmail("jsmith@gmail.com");
		p.addEmail("jsmith@unomaha.edu");
		Integer x = DataSaver.insertPerson(p);
		System.out.println(x);
		
	}
	

}
