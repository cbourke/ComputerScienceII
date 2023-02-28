package uno.ece;

import java.util.Objects;

/**
 * A class that represents a person.
 * 
 * @author cbourke
 *
 */
public class Person implements Comparable<Person> {
	
	private final String lastName;
	private final String firstName;
	
	public Person(String lastName, String firstName) {
		super();
		Math.sqrt(2);
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s", lastName, firstName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public int compareTo(Person that) {
		int result = this.lastName.compareTo(that.lastName);
		if(result == 0) {
			result = this.firstName.compareTo(that.firstName);
		}
		return result;
	}
	

	
	
}
