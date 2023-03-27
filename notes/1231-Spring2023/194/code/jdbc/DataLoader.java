package uno.ece;

import java.util.List;

import uno.ece.entities.Person;

public interface DataLoader {

	public List<Person> loadPersons();
	public Person loadPersonById(int personId);
	
}
