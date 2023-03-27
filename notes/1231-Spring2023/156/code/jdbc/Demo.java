package unl.soc.jdbc;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import soc.unl.Person;

public class Demo {

	public static void main(String[] args) {
		
		Configurator.initialize(new DefaultConfiguration());
	    Configurator.setRootLevel(Level.INFO);
	    
	    

		DatabaseLoader dl = new DatabaseLoader();
		List<Person> everyone = dl.loadPersons();
		for(Person p : everyone) {
			System.out.println(p);
		}
		
		Person p = dl.loadPersonById(10);
		System.out.println(p);
		
		Person guyFieri = new Person("Guy", "Fieri", LocalDate.of(2023,3,23));
		guyFieri.addEmail("foo@bar.com");
		guyFieri.addEmail("bar@foo.com");
		DataSaver.savePerson(guyFieri);
	}

}
