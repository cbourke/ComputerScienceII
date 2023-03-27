package unl.soc.jdbc;

import java.util.List;

import soc.unl.Person;

public interface DataLoader {

	public List<Person> loadPersons();
	
}
